package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CurrentReadingListHelper extends PageBase{
    @FindBy (id = "org.wikipedia:id/page_list_item_title")
    List<WebElement> articlesTitles;

    public CurrentReadingListHelper (WebDriver driver){
        this.driver = driver;
    }

    public CurrentReadingListHelper waitUntilPageIsLoaded(){
        waitUntiAllElementAreClickable(articlesTitles, 15);
        return this;
    }

    public String getNameFirstArticle(){
        return  articlesTitles.get(0).getText();
    }

    public void deleteArticle(String name) {
        WebElement articleTitle = driver.findElement(By.xpath("//*[@text = '" + name +"']"));
        int y = articleTitle.getLocation().y + articleTitle.getSize().height;
        this.swipeFromLeftToRight(y);
    }

    public boolean isEmpty() {
        return articlesTitles.size()==0;
    }
}
