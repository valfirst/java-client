package io.appium.java_client.ios;

import org.junit.BeforeClass;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class UICatalogIOSTest extends BaseIOSTest {

    @BeforeClass
    public static void beforeClass() throws Exception {
        String userName = System.getenv("SAUCE_USERNAME");
        String apiKey = System.getenv("SAUCE_API_KEY");
        DesiredCapabilities caps = DesiredCapabilities.iphone();
        caps.setCapability("appiumVersion", "1.6.5");
        caps.setCapability("deviceName","iPhone 6 Simulator");
        caps.setCapability("deviceOrientation", "portrait");
        caps.setCapability("platformVersion","9.0");
        caps.setCapability("platformName", "iOS");
        caps.setCapability("browserName", "");
        caps.setCapability("app","http://appium.s3.amazonaws.com/UICatalog7.1.app.zip");
        driver = new IOSDriver<>(new URL("http://" + userName
                + ":" + apiKey + "@ondemand.saucelabs.com:80/wd/hub"), caps);
    }
}
