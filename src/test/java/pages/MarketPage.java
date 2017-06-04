package pages;

import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.WebWindow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import pages.common.AnyPage;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class MarketPage extends AnyPage {


    @FindBy(xpath = "html/body/div[1]/div[3]/div/div[2]/div/div[2]/div/div[1]/div/a[2]")
    private List<WebElement> yandexMarketTab;


    public void initialize() throws IOException {

    }

    public void cleanUp() {
    }

    public WebResponse getWebResponse() {
        return null;
    }

    public WebWindow getEnclosingWindow() {
        return null;
    }

    public URL getUrl() {
        return null;
    }

    public boolean isHtmlPage() {
        return false;
    }

    private void switchToYandexMarketTab() {
    WebElement marketTab = yandexMarketTab.get(1);
    marketTab.click();
/*        driver.findElement(By.xpath(String.format("/*//*[@id='ui-datepicker-div']//a[text()='%s']", todayDay))).click();
        orderCallBack_timeOfCallList.get(0).click();*/
    }

    private void sweetchToElectronicks() {
    }

    public String getBalance() {
        WebDriver driver = new ChromeDriver();// Заглушка чтобы пока не сифонило
        driver.findElement(By.xpath("//a[@class='refresh-link']")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return driver.findElement(By.xpath("//*[@id='acc-content']/div[2]/div[2]")).getText().replaceAll("\\s", "");
    }
}
