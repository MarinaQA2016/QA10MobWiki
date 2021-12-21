package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyListsHelper extends PageBase {
    @FindBy (id = "org.wikipedia:id/item_title")
    List<WebElement> myListTitles;

    public MyListsHelper (WebDriver driver){
        this.driver = driver;
    }

    public MyListsHelper waitUntilPageIsLoaded(){
        waitUntiAllElementAreClickable(myListTitles,15);
        return this;
    }
    public MyListsHelper openFirstMyList(){
        myListTitles.get(0).click();
        return this;
    }
}
