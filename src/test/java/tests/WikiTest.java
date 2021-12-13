package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class WikiTest {
    public AppiumDriver driver;

    @Test
    public void wikiApplTest() throws MalformedURLException, InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "WikiNexux5x");
        capabilities.setCapability("platformVersion", "8.1");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", "org.wikipedia.main.MainActivity");
        capabilities.setCapability("automationName", "Uiautomator2");
        capabilities.setCapability("app",
                "C:/Marina/TelRan/Auto/Groups/QAHaifa10/QA10MobWiki/apk/wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        Thread.sleep(20000);

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }


}
