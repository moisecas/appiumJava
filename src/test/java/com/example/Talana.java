package com.example;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;

public class Talana {
    private AndroidDriver<MobileElement> driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "11");
        capabilities.setCapability("deviceName", "Android Device");
        capabilities.setCapability("appPackage", "com.talana.nextqa");
        capabilities.setCapability("appActivity", "com.talana.next.view.splash.SplashActivity");

        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testTalanaApp() {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        // Esperar unos segundos adicionales para asegurarnos de que la aplicación esté completamente cargada
        try {
            Thread.sleep(5000); // Espera de 5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Asegurarse de que el Spinner esté presente y visible
        WebElement el2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.Spinner[@resource-id='com.talana.nextqa:id/enviroment_ambient']")));

        // Verificar si el Spinner está habilitado y se puede hacer clic
        if (el2.isEnabled()) {
            el2.click(); // Hacer clic en el Spinner para mostrar la lista
        } else {
            System.out.println("El Spinner no está habilitado para hacer clic.");
        }

        // Esperar que el botón "GUARDAR AMBIENTE" esté presente y sea clickable
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@resource-id='com.talana.nextqa:id/saveEnvironment']")));
        saveButton.click(); // Hacer clic en el botón "GUARDAR AMBIENTE"

        // Esperar que la opción "Migración PE" esté presente y sea clickable
        WebElement migrationOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@text='migracion pe']")));
        migrationOption.click(); // Hacer clic en la opción "Migración PE"
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    } 
}
