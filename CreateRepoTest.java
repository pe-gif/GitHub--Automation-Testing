package Basic_SeleniumCodes;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.safari.SafariDriver;
import java.util.List;
import java.time.Duration;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;


public class createRepoTest  {
    WebDriver safariDriver;
    WebDriverWait wait;

    @BeforeTest
    public void setuptest() {
        System.out.println("This class will be an automation test to create a repository and interact with some of the" +
                "options.");

    }
    WebDriver setup()  {
        /// initialize test Driver function
        System.setProperty("webdriver.safari.driver", "/usr/bin/safaridriver");
        safariDriver = new SafariDriver();
        safariDriver.get("https://www.github.com/login");
        safariDriver.manage().window().maximize();
        wait = new WebDriverWait(safariDriver, Duration.ofSeconds(3));
        return safariDriver;
    }

    @Test
    public void login() throws InterruptedException {
        WebDriver safariDriver = setup();
        Thread.sleep(2000);
        WebElement username = safariDriver.findElement(By.id("login_field"));
        username.sendKeys("githubemailtest@gmail.com");
        Thread.sleep(2000);
        WebElement password = safariDriver.findElement(By.id("password"));
        password.sendKeys("Githubpassword123-");
        Thread.sleep(2000);
        WebElement loginButton = safariDriver.findElement(By.name("commit"));
        loginButton.click();
        Thread.sleep(2000);


    }

//--------------------------------------------METHOD 1 -----------------------------------------------------------------------------

    @BeforeMethod
    public void CreateRepoMethod() {
        System.out.println("This method will create a new repository on github");
    }
    @Test(priority = 1)
    public void testCreateRepo() throws InterruptedException {

        /// This function waits for the dropdown menu and then clicks it
        WebElement moreButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("global-create-menu-anchor")));
        moreButton.click();
        Thread.sleep(2000);
        //selecting "New repository"
        WebElement select = wait.until(ExpectedConditions.elementToBeClickable((By.xpath("" +
                "//span[text()='New repository']"))));
        select.click();
        Thread.sleep(2000);

        //Inserting text into textbox
        WebElement repoName = safariDriver.findElement(By.cssSelector(
                "input[aria-describedby*='RepoNameInput-message']"));
        repoName.sendKeys("test_repo");
        Thread.sleep(1000);

        //scrolling
        JavascriptExecutor scroll = (JavascriptExecutor) safariDriver;
        scroll.executeScript("window.scroll(0,300)","");
        Thread.sleep(2000);

        //clicking to make repo private and then public
        WebElement privateRepo = safariDriver.findElement(By.cssSelector("label[for=':rg:']"));
        privateRepo.click();
        Thread.sleep(2000);
        WebElement publicRepo = safariDriver.findElement(By.cssSelector("label[for=':rf:']"));
        publicRepo.click();
        Thread.sleep(2000);


        //adding a readme
        WebElement addReadMe = safariDriver.findElement(By.cssSelector("label[for=':ri:']"));
        addReadMe.click();
        Thread.sleep(2000);

        //click repository button
        WebElement createRepoButton = safariDriver.findElement(By.xpath(
                "/html/body/div[1]/div[5]/main/react-app/div/form/div[6]/button/span/span"));
        createRepoButton.click();
        Thread.sleep(3000);

    }

    @AfterMethod
    public void createRepoAfter() {
        System.out.println("The repository has been created.");
    }
    //--------------------------------------------METHOD 2 -----------------------------------------------------------------------------

    @Test(priority = 2)
    public void renameRepo() throws InterruptedException {
        //click on the settings tab
        WebElement settings = safariDriver.findElement(By.xpath("//*[@id=\"settings-tab\"]/span[1]"));
        settings.click();
        Thread.sleep(2000);

        // Type new repo name on the textbox
        WebElement renametxt = safariDriver.findElement(By.xpath("//*[@id=\"rename-field\"]"));
        renametxt.sendKeys("GitHub Testing Report");
        Thread.sleep(2000);

        //click rename button
        WebElement renameButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Rename') and @type='submit']")));
        renameButton.click();
        Thread.sleep(2000);

        //click button to confirm rename
        WebElement renameButtonConfirm = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Rename') and @type='submit']")));
        renameButtonConfirm.click();
        Thread.sleep(2000);
        


    }


//--------------------------------------------METHOD 3 -----------------------------------------------------------------------------

    @BeforeMethod
    public void shareRepoBefore() {
        System.out.println("This method will share the repository with a user ");

    }
    @Test(priority = 3)
    public void shareRepo() throws InterruptedException {
        //click on the settings tab
        WebElement settings = safariDriver.findElement(By.xpath("//*[@id=\"settings-tab\"]/span[1]"));
        settings.click();
        Thread.sleep(2000);

        //clicks on the collaborators tab
        WebElement collaborators = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//span[contains(text(), 'Collaborators')]")));
        collaborators.click();
        Thread.sleep(2000);

        //scrolling
        JavascriptExecutor scroll = (JavascriptExecutor) safariDriver;
        scroll.executeScript("window.scroll(0,100)","");
        Thread.sleep(2000);

        //clicks on the add people button
        WebElement addPpl = safariDriver.findElement(By.xpath("//span[text()='Add people']"));
        addPpl.click();
        Thread.sleep(2000);

        //waits for the emerging window to click on "find people" textbox and then enters the username
        WebElement invite = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"repo-add-access-selector\"]/div[1]/auto-complete/div/input")));
        invite.sendKeys("ymendiola17");
        Thread.sleep(2000);

        // selects the user once it appears in the list
        WebElement selectPpl = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-autocomplete-label='ymendiola17']")));
        selectPpl.click();
        Thread.sleep(2000);

        // clicks on the invite button
        WebElement inviteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='js-selected-member-id' and text()='ymendiola17']")));
        inviteButton.click();
        Thread.sleep(3000);

    }

//--------------------------------------------METHOD 4 -----------------------------------------------------------------------------

    @AfterTest
    public void closeBrowser() {
        safariDriver.quit();

    }

}
