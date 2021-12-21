package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ArticlePageHelper;
import pages.CurrentReadingListHelper;
import pages.HomePageHelper;
import pages.MyListsHelper;

public class HomePageTests extends TestBase{
    HomePageHelper homePage;
    ArticlePageHelper articlePage;
    MyListsHelper myLists;
    CurrentReadingListHelper currentReadingList;

    @BeforeMethod
    public void initTests(){
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        articlePage = PageFactory.initElements(driver, ArticlePageHelper.class);
        myLists = PageFactory.initElements(driver, MyListsHelper.class);
        currentReadingList = PageFactory.initElements(driver, CurrentReadingListHelper.class);
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

    @Test
    public void openArticleBySearchAndSwipeUp(){
        String articleName = "Java applet";
        homePage.searchArticleBy("Java")
                .openArticleByName(articleName);
        articlePage.waitUntilPageIsLoaded();
        Assert.assertEquals(articleName,articlePage.getArticleTitle());
    }
    @Test
    public void addArticleToMyReadingListAndDeleteIt(){
        String articleName = "JavaScript";
        homePage
                .searchArticleBy("Java")
                .openArticleByName(articleName);
        articlePage
                .waitUntilPageIsLoaded()
                .putToMyReadingListFirstly()
                .waitUntilPageIsLoaded();
        articlePage.navigateBack();
        homePage
                .waitUntilPageIsLoaded()
                .openMyLists();
        myLists
                .waitUntilPageIsLoaded()
                .openFirstMyList();
        currentReadingList
                .waitUntilPageIsLoaded();
        Assert.assertEquals(currentReadingList.getNameFirstArticle(), "JavaScript");
        currentReadingList
                .deleteArticle("JavaScript");

        Assert.assertTrue(currentReadingList.isEmpty());

    }

    @Test
    public void openArticleBySearchRotation(){
        String articleName = "JavaScript";
        homePage.searchArticleBy("Java")
                .openArticleByName(articleName);
        articlePage.waitUntilPageIsLoaded();
        // ---- rotate as landscape ----
        articlePage.rotateLandscape();
        articlePage.waitUntilPageIsLoaded();
        Assert.assertEquals(articleName,articlePage.getArticleTitle());

        // ----- rotate as portrait ------
        articlePage.rotatePortrait();
        articlePage.waitUntilPageIsLoaded();
        Assert.assertEquals(articleName,articlePage.getArticleTitle());
    }

    @Test
    public void openArticleBySearchGotoBackGround(){
        String articleName = "JavaScript";
        homePage.searchArticleBy("Java")
                .openArticleByName(articleName);
        articlePage.waitUntilPageIsLoaded();
        articlePage.gotoBackGround(5);
        articlePage.waitUntilPageIsLoaded();
        Assert.assertEquals(articleName,articlePage.getArticleTitle());
    }

}
