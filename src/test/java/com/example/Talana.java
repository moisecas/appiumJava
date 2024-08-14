package com.example;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

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
        WebDriverWait wait = new WebDriverWait(driver, 40);  // Incrementar tiempo de espera a 40 segundos

        try {
            Thread.sleep(5000); // Espera de 5 segundos para asegurarse de que la aplicación esté completamente cargada
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Hacer clic en el botón del menú desplegable
        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByAndroidUIAutomator(
            "new UiSelector().description(\"Mostrar menú desplegable\")")));
        menuButton.click();

        // Buscar y hacer clic en la opción que contiene "migracion pe" en toda la pantalla
        try {
            WebElement opcionMigracionPE = driver.findElementByAndroidUIAutomator(
                "new UiSelector().textContains(\"migracion pe\")");
            opcionMigracionPE.click();
            System.out.println("Se ha seleccionado la opción 'migracion pe' en la pantalla.");
        } catch (Exception e) {
            System.out.println("No se pudo seleccionar la opción 'migracion pe' en la pantalla.");
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
