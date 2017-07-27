/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.appium.java_client.android;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class BaseAndroidTest {
    protected static AndroidDriver<AndroidElement> driver;

    /**
     * initialization.
     */
    @BeforeClass public static void beforeClass() throws Exception {
        String userName = System.getenv("SAUCE_USERNAME");
        String apiKey = System.getenv("SAUCE_API_KEY");
        DesiredCapabilities caps = DesiredCapabilities.android();
        caps.setCapability("appiumVersion", "1.6.5");
        caps.setCapability("deviceName","Android Emulator");
        caps.setCapability("deviceOrientation", "portrait");
        caps.setCapability("browserName", "");
        caps.setCapability("platformVersion","6.0");
        caps.setCapability("platformName","Android");
        caps.setCapability("app","http://appium.s3.amazonaws.com/ApiDemos-debug-2015-03-19.apk");
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
}
