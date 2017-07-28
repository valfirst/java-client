package io.appium.java_client.appium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.BaseAndroidTest;
import io.appium.java_client.android.StartsActivity;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.Response;

import java.util.Map;

public class AndroidTest extends BaseAndroidTest {

    private StartsActivity startsActivity;

    @Before
    public void setUp() throws Exception {
        startsActivity = new StartsActivity() {
            @Override
            public Response execute(String driverCommand, Map<String, ?> parameters) {
                return driver.execute(driverCommand, parameters);
            }

            @Override
            public Response execute(String driverCommand) {
                return driver.execute(driverCommand);
            }
        };
        Activity activity = new Activity("io.appium.android.apis", ".ApiDemos");
        startsActivity.startActivity(activity);
    }

    @Test
    public void findByAccessibilityIdFromDriverTest() {
        assertNotEquals(driver.findElementByAccessibilityId("Graphics").getText(), null);
        assertEquals(driver.findElementsByAccessibilityId("Graphics").size(), 1);
    }

    @Test  public void findByAndroidUIAutomatorFromDriverTest() {
        assertNotEquals(driver
                .findElement(MobileBy
                        .AndroidUIAutomator("new UiSelector().clickable(true)")).getText(), null);
        assertNotEquals(driver
                .findElements(MobileBy
                        .AndroidUIAutomator("new UiSelector().clickable(true)")).size(), 0);
        assertNotEquals(driver
                .findElements(MobileBy
                        .AndroidUIAutomator("new UiSelector().clickable(true)")).size(), 1);
    }

    @Test public void findByAccessibilityIdFromElementTest() {
        assertNotEquals(driver.findElementById("android:id/content")
                .findElement(MobileBy.AccessibilityId("Graphics")).getText(), null);
        assertEquals(driver.findElementById("android:id/content")
                .findElements(MobileBy.AccessibilityId("Graphics")).size(), 1);
    }

    @Test public void findByAndroidUIAutomatorFromElementTest() {
        assertNotEquals(driver.findElementById("android:id/content")
                .findElement(MobileBy
                        .AndroidUIAutomator("new UiSelector().clickable(true)")).getText(), null);
        assertNotEquals(driver.findElementById("android:id/content")
                .findElements(MobileBy
                        .AndroidUIAutomator("new UiSelector().clickable(true)")).size(), 0);
        assertNotEquals(driver.findElementById("android:id/content")
                .findElements(MobileBy
                        .AndroidUIAutomator("new UiSelector().clickable(true)")).size(), 1);
    }

    @Test public void replaceValueTest() {
        String originalValue = "original value";

        Activity activity = new Activity("io.appium.android.apis", ".view.Controls1");
        startsActivity.startActivity(activity);
        AndroidElement editElement = driver
                .findElement(MobileBy
                .AndroidUIAutomator("resourceId(\"io.appium.android.apis:id/edit\")"));
        editElement.sendKeys(originalValue);
        assertEquals(originalValue, editElement.getText());
        String replacedValue = "replaced value";
        editElement.replaceValue(replacedValue);
        assertEquals(replacedValue, editElement.getText());
    }

    @Test public void scrollingToSubElement() {
        driver.findElementByAccessibilityId("Views").click();
        AndroidElement list = driver.findElement(By.id("android:id/list"));
        MobileElement radioGroup = list
                .findElement(MobileBy
                        .AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
                                + "new UiSelector().text(\"Radio Group\"));"));
        assertNotNull(radioGroup.getLocation());
    }

}
