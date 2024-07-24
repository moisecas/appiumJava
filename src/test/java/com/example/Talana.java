package com.example;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

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
        // Tu código de prueba aquí
        MobileElement el1 = driver.findElementById("com.talana.nextqa:id/element_id"); // Reemplaza con el ID correcto
        el1.click();
        MobileElement el2 = driver.findElementByXPath("//android.widget.TextView[@text='Example']"); // Reemplaza con el XPath correcto
        el2.sendKeys("Hello, Appium!");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}