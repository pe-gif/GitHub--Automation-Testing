import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class test {
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
    public void navigate_docs() throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 1000)");
        Thread.sleep(1000);

//opens doc wep page
        driver.findElement(By.xpath("//a[text()='Docs']")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//a[text()='Get started']")).click();
        Thread.sleep(1000);

//Begin get started page
        driver.findElement(By.xpath("//a[text()='Quickstart']")).click();
    }

    @Test
    public void Terms_url() throws Exception {

//opens terms wep page
        driver.get("https://docs.github.com/en/site-policy/github-terms/github-terms-of-service");
        Thread.sleep(1000);

//get/displays url of page
        System.out.println("url: " + driver.getCurrentUrl());
        Thread.sleep(5000);
    }

    @Test
    public void search_docs() throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 5000)");
        Thread.sleep(1000);

//opens doc wep page
        driver.findElement(By.xpath("//a[text()='Docs']")).click();
        Thread.sleep(1000);

        //Searches document
        driver.findElement(By.cssSelector("input.UnstyledTextInput-sc-14ypya-0.jvumlb")).sendKeys("GitHub");
        Thread.sleep(500);

        driver.findElement(By.cssSelector("button.types__StyledButton-sc-ws60qy-0.jXUMlK")).click();
        Thread.sleep(5000);
    }

    @Test
    public void downloads_doc() throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 5000)");
        Thread.sleep(1000);

        //opens doc wep page
        driver.findElement(By.xpath("//a[text()='Docs']")).click();
        Thread.sleep(1000);

        js.executeScript("window.scrollBy(0, 1500)");
        Thread.sleep(500);

        driver.findElement(By.cssSelector("a.btn")).click();
        Thread.sleep(500);

        driver.findElement(By.cssSelector("div.d-flex.gap-2")).click();
        Thread.sleep(5000);

        driver.findElement(By.cssSelector("span.prc-ActionList-ItemLabel-TmBhn")).click();
    }

    @Test
    public void review() throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 5000)");
        Thread.sleep(1000);

        //opens doc wep page
        driver.findElement(By.xpath("//a[text()='Docs']")).click();
        Thread.sleep(1000);

        js.executeScript("window.scrollBy(0, 1500)");
        Thread.sleep(500);

        //press thumb up button
        driver.findElement(By.cssSelector("label.btn.mr-1")).click();
        Thread.sleep(500);

        //Comments
        driver.findElement(By.id("survey-comment")).sendKeys("selenium selenium selenium selenium");
        Thread.sleep(500);

        //email
        driver.findElement(By.id("survey-email")).sendKeys("selenium@gmail.com");
        Thread.sleep(500);

        //sends survey
        driver.findElement(By.cssSelector("button.btn.btn-sm")).click();
        Thread.sleep(5000);
    }

    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }

}
