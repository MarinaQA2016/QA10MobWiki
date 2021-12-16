package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ArticlePageHelper;
import pages.HomePageHelper;

public class HomePageTests extends TestBase{
    HomePageHelper homePage;
    ArticlePageHelper articlePage;

    @BeforeMethod
    public void initTests(){
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        articlePage = PageFactory.initElements(driver, ArticlePageHelper.class);
        homePage.waitUntilPageIsLoaded();
    }

    @Test
    public void wikiAplTest(){
        //---- check that the search element exists on the home page ----
        Assert.assertEquals("Search Wikipedia", homePage.getSearchFieldText());
    }

    @Test
    public void openArticleBySearch(){
        String articleName = "JavaScript";
        homePage.searchArticleBy("Java")
                .openArticleByName(articleName);
        articlePage.waitUntilPageIsLoaded();
        Assert.assertEquals(articleName,articlePage.getArticleTitle());
    }

}
