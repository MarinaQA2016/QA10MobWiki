package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ArticlePageHelper extends PageBase{
    @FindBy (id = "org.wikipedia:id/view_page_title_text")
    WebElement pageTitle;

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
}
