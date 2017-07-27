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

package io.appium.java_client.pagefactory_tests;

import io.appium.java_client.ios.BaseSafariTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class IOSMobileBrowserCompatibilityTest extends BaseSafariTest {

    @FindBy(name = "q")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/someId\")")
    @iOSFindBy(className = "someClass") private WebElement searchTextField;

    @AndroidFindBy(className = "someClass")
    @FindBys({@FindBy(className = "r"), @FindBy(tagName = "a")}) @iOSFindBy(className = "someClass")
    private List<WebElement> foundLinks;

    @Test public void test() {
        driver.get("https://www.google.com");

        searchTextField.sendKeys("Hello");
        searchTextField.submit();
        Assert.assertNotEquals(0, foundLinks.size());
        searchTextField.clear();
        searchTextField.sendKeys("Hello, Appium!");
        searchTextField.submit();
    }

}
