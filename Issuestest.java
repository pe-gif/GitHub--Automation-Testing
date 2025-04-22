package githubtests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class Issue extends BaseTest {
    WebDriverWait wait;

    @Test(priority = 1)
    public void Create_Issue() throws Exception {
        //opens issue github page
        driver.get("https://github.com/issues/assigned");
        Thread.sleep(2000);

        //begin creating a issue
        driver.findElement(By.cssSelector("div.Box-sc-g0xbh4-0.evWEbn")).click();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("div.Box-sc-g0xbh4-0.prc-ActionList-ItemDescriptionWrap-VJA7h")).click();
        Thread.sleep(2000);

        //naming issue
        driver.findElement(By.id(":r23:")).sendKeys("This is a test issue");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//span[text()='Create']")).click();
    }

    @Test(priority = 2)
    public void Assign_To() throws Exception {
        driver.get("https://github.com/issues/assigned");
        Thread.sleep(1000);

        //navigate to created issue
        driver.findElement(By.xpath("//div[text()='Created by me']")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//a[text()='This is a test issue']")).click();
        Thread.sleep(1000);

        //opens assign box and assigns self
        driver.findElement(By.cssSelector("div.Box-sc-g0xbh4-0.gQxdEn")).click();
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("div.prc-ActionList-ActionListContent-sg9-x")).click();
        Thread.sleep(1000);

    }

    @Test(priority = 3)
    public void Create_Sub() throws Exception {
        driver.get("https://github.com/issues/assigned");
        Thread.sleep(1000);

        //navigate to created issue
        driver.findElement(By.xpath("//div[text()='Created by me']")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//a[text()='This is a test issue']")).click();
        Thread.sleep(1000);

        //begin creating a sub issue
        driver.findElement(By.xpath("//span[text()='Create sub-issue']")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//span[text()='Create a new issue from scratch']")).click();
        Thread.sleep(2000);

        //names the sub issue
        driver.findElement(By.className("prc-components-Input-Ic-y8")).sendKeys("This is a issue of the issue");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//span[text()='Create']")).click();
    }

    @Test(priority = 4)
    public void Comment() throws Exception {
        driver.get("https://github.com/issues/assigned");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//div[text()='Created by me']")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//a[text()='This is a test issue']")).click();
        Thread.sleep(1000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 1000)");

        //opens up a comment section in issue and types the issue
        driver.findElement(By.xpath("//textarea[@class='prc-Textarea-TextArea-13q4j MarkdownInput-module__textArea--QjIwG']")).sendKeys("Test issue is completedddd");
        Thread.sleep(1000);

        //comment button
        driver.findElement(By.xpath("//span[text()='Comment']")).click();
        Thread.sleep(1000);
    }

    @Test(priority = 5)
    public void Deletes() throws Exception {
        driver.get("https://github.com/issues/assigned");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//div[text()='Created by me']")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//a[text()='This is a test issue']")).click();
        Thread.sleep(1000);

        //Scroll down to delete key
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 1000)");

        //Deletes and confirms the issue
        driver.findElement(By.xpath("//span[text()='Delete issue']")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//span[text()='Delete']")).click();
        Thread.sleep(1000);
    }
}
