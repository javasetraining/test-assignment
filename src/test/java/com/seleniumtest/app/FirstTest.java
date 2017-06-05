package com.seleniumtest.app;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.common.TestBase;

import java.util.List;

import static junit.framework.TestCase.assertFalse;


public class FirstTest extends TestBase {

    @Test
    public void firstTestCase() throws Exception {
        goToYandexPage();
        switchToYandexMarketTab();
        sweetchToElectronicks();
        selectSecrion("Телевизоры");
        selectTvModel("LG");
        selectTvModel("Samsung");
        setPriceToNeeded(20000);
        pushTheApplyButton(); // sometimes button does'nt works
        Thread.sleep(3000);
        countAndVerifyElementsOnThePage();
    }

/*    private void insertFirtsListElementAndSearch() {
        driver.findElement(By.id("header-search")).sendKeys(firstTVmodelName);
    }*/


    private void shareFirstElement() {
        String tvModelNamne = driver.findElement(By.xpath("//SPAN[@class='snippet-card__header-text']")).getText();
        System.out.println(tvModelNamne);
    }

    public void countAndVerifyElementsOnThePage() throws InterruptedException {
        List<WebElement> items = driver.findElements(By.cssSelector(".snippet-card__header-text"));
        int elementsCount = items.size();
        System.out.println("на странице " + elementsCount + " элементов");
        //      System.out.println("первый элемент" + items.get(0));
        List<WebElement> itemsXpath = driver.findElements(By.xpath("//SPAN[@class='snippet-card__header-text']"));
        System.out.println(itemsXpath.get(0).getText());
        String firstTVmodelName = itemsXpath.get(0).getText();
        //    System.out.println("first element" + firstLIstElement);
        int elementsOnPage = 12;  // on page should be 12 elements by default
        Assert.assertEquals("Число елементов не соответсвует 12-ти", elementsOnPage, elementsCount); //assert for element count

        driver.findElement(By.id("header-search")).sendKeys(firstTVmodelName);
        driver.findElement(By.xpath("(//BUTTON[@role='button'])[1]")).click();
        Thread.sleep(3000);
        driver.findElement(By.className("n-product-summary__headline")).getText().compareTo(firstTVmodelName);

    }

    public void pushTheApplyButton() {
        driver.findElement(By.className("n-filter-panel-aside__apply")).click();
    }


    private void selectModel(String s) {
        String LG = "html/body/div[1]/div[4]/div[2]/div[2]/div[3]/div/div[4]/div[2]/div/div[1]/div[3]/a/span/label";
        String Samsung = "html/body/div[1]/div[4]/div[2]/div[2]/div[3]/div/div[4]/div[2]/div/div[1]/div[7]/a/span/label";
        driver.findElement(By.xpath(LG)).click();
        driver.findElement(By.xpath(Samsung)).click();
        //LABEL[@class='checkbox__label'][text()='LG']
        //LABEL[@class='checkbox__label'][text()='Samsung']
    }

    public void searchIfNotHiddenAndSelect(String checkBox) {
        String checkBoxselected = ("//LABEL[@class='checkbox__label'][text()='" + checkBox + "']");
        Boolean isPresent = driver.findElements(By.linkText(checkBox)).size() > 0;
        if (isPresent) {
            driver.findElement(By.xpath(checkBoxselected)).click();
        } else {
            driver.findElement(By.linkText("Ещё")).click();
            driver.findElement(By.xpath(checkBoxselected)).click();
        }
    }

    public void selectTvModel(String tvModel) {
        String checkBoxselected = ("//LABEL[@class='checkbox__label'][text()='" + tvModel + "']");
        driver.findElement(By.xpath(checkBoxselected)).click();
    }

    public void setPriceToNeeded(int price) throws InterruptedException {
        driver.findElement(By.xpath(".//*[@id='glf-pricefrom-var']")).sendKeys(String.valueOf(price));
    }

    protected void selectSecrion(String s) {
        driver.findElement(By.linkText(s)).click();
    }

    protected void switchToYandexMarketTab() {
        WebElement marketTab = By.xpath("html/body/div[1]/div[3]/div/div[2]/div/div[2]/div/div[1]/div/a[2]").findElement(driver);
        marketTab.click();
    }

    protected void sweetchToElectronicks() {
        driver.findElement(By.linkText("Электроника")).click();
    }

    public void waitForPageToLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoad = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                    }
                };

        Wait<WebDriver> wait = new WebDriverWait(driver, 60);
        try {
            wait.until(pageLoad);
        } catch (Throwable pageLoadWaitError) {
            assertFalse("Timeout during page load", true);
        }
    }
}
