package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PageBase {
    WebDriver driver;

    public String getApplTitle() {
        return driver.getTitle();
    }

    public void refreshPage(){
        driver.navigate().refresh();
    }
    public void navigateBack(){ driver.navigate().back();}
    public void sleep(int timeMS){
        try {
            Thread.sleep(timeMS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void scrollingTillElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();",element);
    }
    public void scrollingByPixels(WebElement element, int mooveByX, int mooveByY) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy("+ mooveByX + "," + mooveByY + ");");
    }
    public void fillInTextField(WebElement field, String value) {
        field.clear();
        field.click();
        field.sendKeys(value);
    }
    public String getAnotherHandle(){
        String currentHandle = driver.getWindowHandle();
        String anotherHandle = "";
        for (String handle: driver.getWindowHandles()){
            if (!handle.equals(currentHandle)) anotherHandle = handle;
        }
        return anotherHandle;
    }

    public void switchToAnotherWindow() {
        driver.switchTo().window(this.getAnotherHandle());
    }
    public void closeNotLastWindow(){
        String anotherHandle = getAnotherHandle();
        driver.close();
        driver.switchTo().window(anotherHandle);
    }
    public void swipeUp (){
        sleep(2);
        AppiumDriver appDriver = (AppiumDriver) driver;
        TouchAction action = new TouchAction(appDriver);
        Dimension size = driver.manage().window().getSize();
        int x = (int) (size.width*0.5);
        int y1 = (int) (size.height *0.7);
        int y2 = (int) (size.getHeight()*0.3);

        action.press(PointOption.point(x,y1))
                .waitAction()
                .moveTo(PointOption.point(x,y2))
                .release()
                .perform();
    }
    public void swipeFromLeftToRight (int y){
        sleep(2);
        AppiumDriver appDriver = (AppiumDriver) driver;
        TouchAction action = new TouchAction(appDriver);
        Dimension size = driver.manage().window().getSize();
        int x1 = (int) (size.width*0.2);
        int x2 = (int) (size.width*0.8);

        action.press(PointOption.point(x1,y))
                .waitAction()
                .moveTo(PointOption.point(x2,y))
                .release()
                .perform();
    }

    public void swipeUpToElement(By locator, int maxTime){
        int counter = 0;
        while (driver.findElements(locator).size()==0 && counter <maxTime)
        {
            this.swipeUp();
            counter++;
        }
    }

    public void gotoBackGround(int sec) {
        AppiumDriver appDriver = (AppiumDriver) driver;
        appDriver.runAppInBackground(Duration.ofSeconds(sec));
    }

    public void rotatePortrait() {
        AppiumDriver appDriver = (AppiumDriver) driver;
        appDriver.rotate(ScreenOrientation.PORTRAIT);
    }

    public void rotateLandscape() {
        AppiumDriver appDriver = (AppiumDriver) driver;
        appDriver.rotate(ScreenOrientation.LANDSCAPE);
    }

    public void waitUntilElementIsVisible(By locator, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void waitUntilNumberOfWindowsIs(int number, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions.numberOfWindowsToBe(number));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void waitUntilElementIsVisible(WebElement element, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsClickable(By locator, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsClickable(WebElement element, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntiAllElementAreClickable(List<WebElement> elementsList, int time) {
        try {
            for (WebElement element : elementsList){
            new WebDriverWait(driver,time).until(ExpectedConditions.elementToBeClickable(element));}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntiAllElementArePresent(By locator, int time) {
        try {
                new WebDriverWait(driver,time).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilAllElementsAreVisible(By locator, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilTextToBePresentInElement(WebElement element, String text, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions.textToBePresentInElement(element,text));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilAllElementsAreVisible(List<WebElement> elementsList, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions.visibilityOfAllElements(elementsList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsInVisible(WebElement element, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void waitUntilAllElementsAreInVisible(List<WebElement> list, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions.invisibilityOfAllElements(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
