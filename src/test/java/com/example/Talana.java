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

     //Acá es donde empiezo a crear los metodos para las pruebas  

   
    WebDriverWait wait = new WebDriverWait(driver, 30);  // Esperar que el elemento esté presente y sea clickable
    WebElement el1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.view.ViewGroup"))); // Encontrar el elemento por xpath
    
    el1.click(); // Hacer clic en el elemento

    WebElement el2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Spinner[@resource-id='com.talana.nextqa:id/enviroment_ambient']")));
    el2.click(); // Hacer clic en el segundo elemento, listbox
}


    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}