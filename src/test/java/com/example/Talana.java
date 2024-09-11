package com.example;

import java.net.MalformedURLException;
import java.net.URL;
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

        try {
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//android.widget.Button[@resource-id='com.talana.nextqa:id/saveEnvirontment']")));
            saveButton.click();
            System.out.println("Se ha hecho clic en el botón de guardar ambiente.");
        } catch (Exception e) {
            System.out.println("No se pudo hacer clic en el botón de guardar ambiente.");
            e.printStackTrace();
        }

        
        try { // Hacer clic en el botón con el resource-id "com.talana.nextqa:id/btnLogin"
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//android.widget.Button[@resource-id='com.talana.nextqa:id/btnLogin']")));
            loginButton.click();
            System.out.println("Se ha hecho clic en el botón de login.");
        } catch (Exception e) {
            System.out.println("No se pudo hacer clic en el botón de login.");
            e.printStackTrace();
        }

        // buscar caja de texto usuario
        try {
            MobileElement userTextBox = driver.findElementByAndroidUIAutomator(
                "new UiSelector().textContains(\"usuario\")");
            userTextBox.click();
            System.out.println("Se ha hecho clic en la caja de texto de usuario.");

            // escribir user en la caja de texto como ej y comprobar si estamos donde necesitamos
            userTextBox.sendKeys("98621005");
            System.out.println("Se ha escrito el user en la caja de texto de usuario.");
        } catch (Exception e) {
            System.out.println("No se pudo interactuar con la caja de texto de usuario.");
            e.printStackTrace();
        }

        //localizar el botón "Continuar" y hacer clic en él
        try {
            MobileElement continuarButton = driver.findElementByAndroidUIAutomator(
                "new UiSelector().text(\"Continuar\")");
            continuarButton.click();
            System.out.println("Se ha hecho clic en el botón 'Continuar'.");
        } catch (Exception e) {
            System.out.println("No se pudo hacer clic en el botón 'Continuar'.");
            e.printStackTrace();
        }

        try {  // Agregar una espera de 10 segundos antes de finalizar la prueba
            Thread.sleep(10000); // Espera de 10 segundos
        } catch (InterruptedException e) {
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
