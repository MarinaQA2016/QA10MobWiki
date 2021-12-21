package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class ArticlePageHelper extends PageBase{
    @FindBy (id = "org.wikipedia:id/view_page_title_text")
    WebElement pageTitle;
    @FindBy (className = "android.support.v7.app.ActionBar$Tab")
    WebElement addToReadingListButton;
    @FindBy (id = "org.wikipedia:id/onboarding_button")
    WebElement gotItButton;
    @FindBy (id = "android:id/button1")
    WebElement okAddArticleToMyListButton;

    public ArticlePageHelper(WebDriver driver){
        this.driver = driver;
    }

    public ArticlePageHelper waitUntilPageIsLoaded(){
        // ---- wait until the title of the article is loaded and check it ---
        waitUntilElementIsVisible(pageTitle,20);
        return this;
    }

    public String getArticleTitle(){
        return pageTitle.getText();
    }

    public ArticlePageHelper putToMyReadingListFirstly() {
        waitUntilElementIsClickable(addToReadingListButton,10);
        addToReadingListButton.click();
        waitUntilElementIsClickable(gotItButton,15);
        gotItButton.click();
        waitUntilElementIsClickable(okAddArticleToMyListButton,10);
        okAddArticleToMyListButton.click();
        //waitUntilElementIsInVisible(okAddArticleToMyListButton,10);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

}
