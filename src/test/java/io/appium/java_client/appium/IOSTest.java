package io.appium.java_client.appium;

import static org.junit.Assert.assertNotEquals;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.AppIOSTest;

import org.junit.Test;

public class IOSTest extends AppIOSTest {

    @Test
    public void findByAccessibilityIdFromDriverTest() {
        assertNotEquals(driver
                .findElementByAccessibilityId("ComputeSumButton")
                .getText(), null);
        assertNotEquals(driver
                .findElementsByAccessibilityId("ComputeSumButton")
                .size(), 0);
    }

    @Test public void findByByIosUIAutomationFromDriverTest() {
        assertNotEquals(driver
                .findElement(MobileBy.IosUIAutomation(".elements().withName(\"Answer\")"))
                .getText(), null);
        assertNotEquals(driver
                .findElements(MobileBy.IosUIAutomation(".elements().withName(\"Answer\")"))
                .size(), 0);
    }

    @Test public void findByAccessibilityIdFromElementTest() {
        assertNotEquals(driver.findElementsByClassName("UIAWindow").get(1)
                .findElementsByAccessibilityId("ComputeSumButton").size(), 0);
    }

    @Test public void findByByIosUIAutomationTest() {
        assertNotEquals((driver.findElementsByClassName("UIAWindow")
                .get(1))
                .findElementByIosUIAutomation(".elements().withName(\"Answer\")").getText(), null);
    }
}
