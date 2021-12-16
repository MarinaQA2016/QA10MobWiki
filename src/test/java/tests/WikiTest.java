package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class WikiTest extends  TestBase{
    @BeforeMethod
    public void initTests(){
        //---- wait that the search element exists on the home page ----
        waitUntilElementIsClickable(By.xpath("//*[@resource-id = 'org.wikipedia:id/search_container']/*[@class = 'android.widget.TextView']"),20);
    }

    @Test
    public void wikiApplTest() {
        //---- check that the search element exists on the home page ----
        Assert.assertEquals("Search Wikipedia", driver
                .findElement(By.xpath("//*[@resource-id = 'org.wikipedia:id/search_container']/*[@class = 'android.widget.TextView']")).getText());
    }
    @Test
    public void openArticleBySearch()  {
        String articleName = "JavaScript";

        // ---- press on the search element ---
        driver.findElement(By.xpath("//*[@resource-id = 'org.wikipedia:id/search_container']/*[@class = 'android.widget.TextView']")).click();

        // ---- enter the search word -----
        waitUntilElementIsClickable(By.id("org.wikipedia:id/search_src_text"),15);
        driver.findElement(By.id("org.wikipedia:id/search_src_text")).sendKeys("Java");

        // ---- wait until the article with defined name is loaded and click on it ---
        String locatorByXpath = "//*[@resource-id = 'org.wikipedia:id/page_list_item_container'][.//*[@text='"+ articleName +"']]";
        waitUntilElementIsClickable(By.xpath(locatorByXpath),10);
        driver.findElement(By.xpath(locatorByXpath)).click();

        // ---- wait until the title of the article is loaded and check it ---
        waitUntilElementIsVisible(By.id("org.wikipedia:id/view_page_title_text"),20);
        Assert.assertEquals(articleName,driver.findElement(By.id("org.wikipedia:id/view_page_title_text")).getText());

    }

}
