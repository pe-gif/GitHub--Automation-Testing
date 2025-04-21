import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Tests {

    /// initialize test Driver function

    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void setUp() throws Exception {
        System.setProperty("webdriver.google.driver", "C:\\Users\\peter\\Downloads\\chromedriver-win64\\chromedriver.exe");
        driver.get("https://www.google.com");
        driver.manage().window().maximize();
        driver.get("https://www.github.com/login");

        driver.findElement(By.id("login_field")).sendKeys("githubemailtest@gmail.com");
        Thread.sleep(500);

        driver.findElement(By.id("password")).sendKeys("Githubpassword123-");
        Thread.sleep(500);

        driver.findElement(By.name("commit")).click();
        Thread.sleep(500);

    }

    @Test
    public void test1() throws Exception {
        System.setProperty("webdriver.google.driver", "C:\\Users\\peter\\Downloads\\chromedriver-win64\\chromedriver.exe");
        driver.get("https://github.com/issues/assigned");
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("div.Box-sc-g0xbh4-0.evWEbn")).click();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("div.Box-sc-g0xbh4-0.prc-ActionList-ItemDescriptionWrap-VJA7h")).click();
        Thread.sleep(2000);

        driver.findElement(By.id(":r23:")).sendKeys("This is a test issue");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//span[text()='Create']")).click();
    }

    @Test
    public void test2() throws Exception {
        driver.get("https://github.com/issues/assigned");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//div[text()='Created by me']")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//a[text()='This is a test issue']")).click();
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("div.Box-sc-g0xbh4-0.gQxdEn")).click();
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("div.prc-ActionList-ActionListContent-sg9-x")).click();
        Thread.sleep(1000);

    }

    @Test
    public void test3() throws Exception {
        driver.get("https://github.com/issues/assigned");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//div[text()='Created by me']")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//a[text()='This is a test issue']")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//span[text()='Create sub-issue']")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//span[text()='Create a new issue from scratch']")).click();
        Thread.sleep(2000);

        driver.findElement(By.className("prc-components-Input-Ic-y8")).sendKeys("This is a issue of the issue");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//span[text()='Create']")).click();
    }

    @Test
    public void test4() throws Exception {
        driver.get("https://github.com/issues/assigned");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//div[text()='Created by me']")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//a[text()='This is a test issue']")).click();
        Thread.sleep(1000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 1000)");

        driver.findElement(By.xpath("//textarea[@class='prc-Textarea-TextArea-13q4j MarkdownInput-module__textArea--QjIwG']")).sendKeys("Test issue is completedddd");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//span[text()='Comment']")).click();
        Thread.sleep(1000);
    }

    @Test
    public void test5() throws Exception {
        driver.get("https://github.com/issues/assigned");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//div[text()='Created by me']")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//a[text()='This is a test issue']")).click();
        Thread.sleep(1000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 1000)");

        driver.findElement(By.xpath("//span[text()='Delete issue']")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//span[text()='Delete']")).click();
        Thread.sleep(1000);
    }

    @AfterTest
    public void tearDown() throws Exception {
        driver.get("https://github.com/issues/created");
        Thread.sleep(2000);
        driver.quit();
    }
}
