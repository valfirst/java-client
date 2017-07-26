package io.appium.java_client.android;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class UIAutomator2Test {
    private static AppiumDriverLocalService service;
    protected static AndroidDriver<AndroidElement> driver;

    /**
     * initialization.
     */
    @BeforeClass public static void beforeClass() throws Exception {
        String userName = System.getenv("SAUCE_USERNAME");
        String apiKey = System.getenv("SAUCE_API_KEY");
        DesiredCapabilities caps = DesiredCapabilities.android();
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,AutomationName.ANDROID_UIAUTOMATOR2);
        caps.setCapability("appiumVersion", "1.6.5");
        caps.setCapability("deviceName","Android Emulator");
        caps.setCapability("deviceOrientation", "portrait");
        caps.setCapability("browserName", "");
        caps.setCapability("platformVersion","6.0");
        caps.setCapability("platformName","Android");
        caps.setCapability("app","sauce-storage:ApiDemos-debug.apk");
        driver = new AndroidDriver<>(new URL("http://" + userName
                + ":" + apiKey + "@ondemand.saucelabs.com:80/wd/hub"), caps);
    }

    /**
     * finishing.
     */
    @AfterClass public static void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }


    @After public void afterMethod() {
        driver.rotate(new DeviceRotation(0, 0, 0));
    }

    @Test public void testLandscapeRightRotation() {
        DeviceRotation landscapeRightRotation = new DeviceRotation(0, 0, 90);
        driver.rotate(landscapeRightRotation);
        assertEquals(driver.rotation(), landscapeRightRotation);
    }

    @Test public void testLandscapeLeftRotation() {
        DeviceRotation landscapeLeftRotation = new DeviceRotation(0, 0, 270);
        driver.rotate(landscapeLeftRotation);
        assertEquals(driver.rotation(), landscapeLeftRotation);
    }

    @Test public void testPortraitUpsideDown() {
        DeviceRotation landscapeRightRotation = new DeviceRotation(0, 0, 180);
        driver.rotate(landscapeRightRotation);
        assertEquals(driver.rotation(), landscapeRightRotation);
    }

    @Test public void testToastMSGIsDisplayed() throws InterruptedException {
        final WebDriverWait wait = new WebDriverWait(driver, 10);
        Activity activity = new Activity("io.appium.android.apis", ".view.PopupMenu1");
        driver.startActivity(activity);

        MobileElement popUpElement = driver.findElement(MobileBy.AccessibilityId("Make a Popup!"));
        popUpElement.click();
        driver.findElement(By.xpath(".//*[@text='Search']")).click();
        assertNotNull(wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//*[@text='Clicked popup menu item Search']"))));

        popUpElement.click();
        driver.findElement(By.xpath(".//*[@text='Add']")).click();
        assertNotNull(wait.until(ExpectedConditions
            .presenceOfElementLocated(By.xpath("//*[@text='Clicked popup menu item Add']"))));

        popUpElement.click();
        driver.findElement(By.xpath(".//*[@text='Edit']")).click();
        assertNotNull(wait.until(ExpectedConditions
            .presenceOfElementLocated(By.xpath("//*[@text='Clicked popup menu item Edit']"))));

        driver.findElement(By.xpath(".//*[@text='Share']")).click();
        assertNotNull(wait.until(ExpectedConditions
            .presenceOfElementLocated(By.xpath("//*[@text='Clicked popup menu item Share']"))));
    }
}
