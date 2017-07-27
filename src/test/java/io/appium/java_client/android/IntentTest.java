package io.appium.java_client.android;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.function.Predicate;

public class IntentTest {
    protected static AndroidDriver<?> driver;

    /**
     * initialization.
     */
    @BeforeClass public static void beforeClass() throws Exception {
        String userName = System.getenv("SAUCE_USERNAME");
        String apiKey = System.getenv("SAUCE_API_KEY");
        DesiredCapabilities caps = DesiredCapabilities.android();
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        caps.setCapability("appiumVersion", "1.6.5");
        caps.setCapability("deviceName","Android Emulator");
        caps.setCapability("deviceOrientation", "portrait");
        caps.setCapability("browserName", "");
        caps.setCapability("platformVersion","6.0");
        caps.setCapability("platformName","Android");
        caps.setCapability("app","http://appium.s3.amazonaws.com/IntentExample.apk");
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


    @Test public void startActivityWithIntent() throws Exception {
        Predicate<AndroidDriver> predicate = driver -> {
            Activity activity = new Activity("com.android.mms",
                    ".ui.ComposeMessageActivity")
                    .setIntentAction("android.intent.action.SEND")
                    .setIntentCategory("android.intent.category.DEFAULT")
                    .setIntentFlags("0x4000000")
                    .setOptionalIntentArguments("-d \"TestIntent\" -t \"text/plain\"");
            driver.startActivity(activity);
            return true;
        };
        assertTrue(predicate.test(driver));

    }

    @Test public void startActivityWithDefaultIntentAndDefaultCategoryWithOptionalArgs() {
        final Activity activity = new Activity("com.prgguru.android", ".GreetingActivity")
                .setIntentAction("android.intent.action.MAIN")
                .setIntentCategory("android.intent.category.DEFAULT")
                .setIntentFlags("0x4000000")
                .setOptionalIntentArguments("--es \"USERNAME\" \"AppiumIntentTest\" -t \"text/plain\"");
        driver.startActivity(activity);
        assertEquals(driver.findElementById("com.prgguru.android:id/textView1").getText(),
            "Welcome AppiumIntentTest");
    }
}
