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

        // Esperar que el Spinner esté presente y sea clickable
        WebElement el2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Spinner[@resource-id='com.talana.nextqa:id/enviroment_ambient']")));
        el2.click(); // Hacer clic en el Spinner para mostrar la lista

        // Esperar unos segundos para asegurarnos de que la lista se haya desplegado
        try {
            Thread.sleep(2000); // Espera de 2 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Imprimir todos los elementos TextView visibles
        List<MobileElement> options = driver.findElements(By.className("android.widget.TextView"));
        System.out.println("Elementos TextView disponibles:");
        for (MobileElement option : options) {
            System.out.println(option.getText());
        }

        // Esperar que la opción "Migración PE" esté presente y sea clickable
        WebElement migrationOption = null;
        for (MobileElement option : options) {
            if (option.getText().contains("migracion pe")) {
                migrationOption = option;
                break;
            }
        }

        if (migrationOption != null) {
            System.out.println("Texto del elemento encontrado: " + migrationOption.getText());
            System.out.println("Estado del elemento: Enabled=" + migrationOption.isEnabled() + ", Displayed=" + migrationOption.isDisplayed());

            // Usar TouchAction para hacer clic en las coordenadas del elemento
            TouchAction touchAction = new TouchAction(driver);
            touchAction.tap(PointOption.point(migrationOption.getLocation().getX(), migrationOption.getLocation().getY())).perform();
        } else {
            System.out.println("Elemento 'migracion pe' no encontrado.");
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
