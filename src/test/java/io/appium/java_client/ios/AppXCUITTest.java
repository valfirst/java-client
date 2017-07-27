package io.appium.java_client.ios;

import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.BeforeClass;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class AppXCUITTest extends BaseIOSTest {

    /**
     * initialization.
     */
    @BeforeClass public static void beforeClass() throws Exception {
        String userName = System.getenv("SAUCE_USERNAME");
        String apiKey = System.getenv("SAUCE_API_KEY");
        DesiredCapabilities caps = DesiredCapabilities.iphone();
        caps.setCapability("appiumVersion", "1.6.5");
        caps.setCapability("deviceName","iPhone 7 Simulator");
        caps.setCapability("deviceOrientation", "portrait");
        caps.setCapability("platformVersion","10.3");
        caps.setCapability("platformName", "iOS");
        caps.setCapability("browserName", "");
        caps.setCapability(IOSMobileCapabilityType.ALLOW_TOUCHID_ENROLL, "true");
        caps.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 500000);
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        caps.setCapability("app","http://appium.s3.amazonaws.com/TestApp10.2.app.zip");
        driver = new IOSDriver<>(new URL("http://" + userName
                + ":" + apiKey + "@ondemand.saucelabs.com:80/wd/hub"), caps);
    }
}
