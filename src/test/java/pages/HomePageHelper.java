package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageHelper extends PageBase{
    @FindBy(xpath = "//*[@resource-id = 'org.wikipedia:id/search_container']/*[@class = 'android.widget.TextView']")
    WebElement searchFieldText;
    @FindBy(id = "org.wikipedia:id/search_src_text")
    WebElement searchFieldTextEntering;

    public HomePageHelper (WebDriver driver){
        this.driver = driver;
    }


    public void waitUntilPageIsLoaded() {
        //---- wait that the search element exists on the home page ----
        waitUntilElementIsClickable(searchFieldText, 20);
    }

    public String getSearchFieldText(){
        return searchFieldText.getText();
    }

    public HomePageHelper searchArticleBy(String value) {
        // ---- press on the search element ---
        searchFieldText.click();
        // ---- enter the search word -----
        waitUntilElementIsClickable(searchFieldTextEntering,15);
        fillInTextField(searchFieldTextEntering, value);
        // ----- close text panel ---
        this.navigateBack();
        return this;
    }

    public HomePageHelper openArticleByName(String name) {
        // ---- wait until the article with defined name is loaded and click on it ---
        String articleLocator = this.articleLocatorByNameXPath(name);
        // ---- swiping till the article with title = name appears ----
        this.swipeUpToElement(By.xpath(articleLocator),20);
        waitUntilElementIsClickable(By.xpath(articleLocator),10);
        driver.findElement(By.xpath(articleLocator)).click();
        return this;
    }

    public String articleLocatorByNameXPath(String name){
        return "//*[@resource-id = 'org.wikipedia:id/page_list_item_container'][.//*[@text='"+ name +"']]";
    }
}
