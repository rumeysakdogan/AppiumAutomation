package com.cybertek.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class CalculatorTest {

    AppiumDriver<AndroidElement> driver;

    @Before
    public void setUp() throws MalformedURLException {
        // localhost --> 0.0.0.0
        //desiredCapabilities.setCapability("platformName", "Android");
        //to specify test settings and required info about device and app under the test
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.0");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.android.calculator2");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"com.android.calculator2.Calculator");
        // for native apps we need to specify app-package and app-activity
        // for newly downloaded apps, instead app-package,app-activity , we will specify app with .apk
        //for new apps - just use "app"
        //for pre-installed - "appPackage" amd "appActivity"
        //address of appium server
        //localhost means that appium server is running on your computer
        //if, appium server launched on some other computer
        //specify IP/DNS address instead of localhost
        //4723 - default port number of appium server. Can be changed
        URL url = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver<>(url, desiredCapabilities);
    }

    @After
    public void tearDown(){
        driver.closeApp();
    }

    @Test
    public void calculatorTest() throws Exception {

        AndroidElement btn2 = driver.findElement(MobileBy.id("com.android.calculator2:id/digit_2"));
        AndroidElement plusBttn = driver.findElement(MobileBy.AccessibilityId("plus"));
        AndroidElement equalsBttn = driver.findElement(MobileBy.AccessibilityId("equals"));
        AndroidElement resultElement = driver.findElementById("com.android.calculator2:id/result");

        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.elementToBeClickable(btn2));
        btn2.click();   // 2
        plusBttn.click();   // +
        btn2.click();   // 2
        equalsBttn.click(); // =

        int expected = 4;
        int actual = Integer.parseInt( resultElement.getText() );

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void calculatorTestWithTouchActions(){

        AndroidElement bttn9 = driver.findElementById("com.android.calculator2:id/digit_9");
        AndroidElement bttn0 = driver.findElementById("com.android.calculator2:id/digit_0");
        AndroidElement divideBttn = driver.findElement(MobileBy.AccessibilityId("divide"));
        AndroidElement bttn5 = driver.findElementById("com.android.calculator2:id/digit_5");
        AndroidElement resultElement = driver.findElementById("com.android.calculator2:id/result");
        AndroidElement equalsBttn = driver.findElement(MobileBy.AccessibilityId("equals"));

        // we can click on elements, or we can use touch actions
        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(new TapOptions().withElement(new ElementOption().withElement(bttn9))).perform();
        touchAction.tap(new TapOptions().withElement(new ElementOption().withElement(bttn0))).perform();
        touchAction.tap(new TapOptions().withElement(new ElementOption().withElement(divideBttn))).perform();
        touchAction.tap(new TapOptions().withElement(new ElementOption().withElement(bttn5))).perform();
        touchAction.tap(new TapOptions().withElement(new ElementOption().withElement(equalsBttn))).perform();

        int expected = 18;
        int actual = Integer.parseInt( resultElement.getText() );
        Assert.assertEquals(expected, actual);
    }


}
