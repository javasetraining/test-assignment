package com.seleniumtest.app;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SecondTest extends FirstTest {
    @Test
    public void secondtTestCase() throws Exception {
        goToYandexPage();
        switchToYandexMarketTab();
        sweetchToElectronicks();
        selectSecrion("Наушники и Bluetooth-гарнитуры");
        searchIfNotHiddenAndSelect("Beats");
        setPriceToNeeded(5000);
        pushTheApplyButton(); // sometimes button does'nt works
        Thread.sleep(3000);
        countAndVerifyElementsOnThePage();
    }
}
