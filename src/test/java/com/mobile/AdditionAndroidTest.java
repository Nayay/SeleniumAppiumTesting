package com.mobile;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

    public class AdditionAndroidTest {
        AndroidDriver<AndroidElement> driver;
        @BeforeClass
        public void setUp() throws MalformedURLException, InterruptedException{
            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setCapability(MobileCapabilityType.DEVICE_NAME, "p28");
            cap.setCapability(MobileCapabilityType.UDID, "emulator-5554");
            cap.setCapability(MobileCapabilityType.APPLICATION_NAME, MobilePlatform.ANDROID);
            cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, " ");
            cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.android.calculator2");
            cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.android.calculator2.Calculator");
            driver = new AndroidDriver<AndroidElement>(new URL("http:0.0.0.0:4723/wd/hub"), cap);
            Thread.sleep(5000);
        }

        @Test
        public void testCal() throws Exception {
            //locate the Text on the calculator by using By.name
            WebElement seven = driver.findElementById("com.android.calculator2:id/digit_7");
            seven.click();
            WebElement plus=driver.findElementById("com.android.calculator2:id/op_add");
            plus.click();
            WebElement four = driver.findElementById("com.android.calculator2:id/digit_4");
            four.click();
            WebElement equalTo=driver.findElementById("com.android.calculator2:id/eq");
            equalTo.click();
            Thread.sleep(2000);
            WebElement results=driver.findElementById("com.android.calculator2:id/result");
            assert results.getText().equals("11"):"Actual value is : "+results.getText()+" did not match with expected value: 11";
            getScreenShot(driver,"hello");
        }

        //This method is to capture the screenshot and return the path of the screenshot.
        public static String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
            String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            // after execution, you could see a folder "FailedTestsScreenshots" under src folder
            String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
            File finalDestination = new File(destination);
            FileUtils.copyFile(source, finalDestination);
            return destination;
        }

        @AfterClass
        public void teardown(){
            //close the app
            driver.quit();
        }
    }

