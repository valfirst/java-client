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

package io.appium.java_client.ios;

import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.BeforeClass;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class BaseSafariTest extends BaseIOSTest {

    @BeforeClass public static void beforeClass() throws Exception {
        String userName = System.getenv("SAUCE_USERNAME");
        String apiKey = System.getenv("SAUCE_API_KEY");
        DesiredCapabilities caps = DesiredCapabilities.iphone();
        caps.setCapability("appiumVersion", "1.6.5");
        caps.setCapability("deviceName","iPhone 7 Simulator");
        caps.setCapability("deviceOrientation", "portrait");
        caps.setCapability("platformVersion","10.3");
        caps.setCapability("platformName", "iOS");
        caps.setCapability("browserName", "Safari");
        caps.setCapability(IOSMobileCapabilityType.ALLOW_TOUCHID_ENROLL, "true");
        caps.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 500000);
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        driver = new IOSDriver<>(new URL("http://" + userName
                + ":" + apiKey + "@ondemand.saucelabs.com:80/wd/hub"), caps);
    }
}
