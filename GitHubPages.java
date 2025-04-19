import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.*;


public class githubPages {
    WebDriver chromeDriver;
    WebDriverWait wait;

   WebDriver setup()  {
        /// initialize test Driver function
        System.setProperty("webdriver.chrome.driver", "/Users/Yani/Documents/chromedriver");
        chromeDriver = new ChromeDriver();
        chromeDriver.get("https://www.github.com/login");
        chromeDriver.manage().window().maximize();
        wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(15));
        return chromeDriver;
    }

    @Test(priority = 0)
    public void login() throws InterruptedException {
        chromeDriver = setup();
        Thread.sleep(2000);
        WebElement username = chromeDriver.findElement(By.id("login_field"));
        username.sendKeys("githubemailtest@gmail.com");
        Thread.sleep(1000);
        WebElement password = chromeDriver.findElement(By.id("password"));
        password.sendKeys("Githubpassword123-");
        Thread.sleep(1000);
        WebElement loginButton = chromeDriver.findElement(By.name("commit"));
        loginButton.click();
        Thread.sleep(1000);

    }


//--------------------------------------------METHOD 1: Access Repo method -----------------------------------------------------------------------------

    @Test(priority = 1)
    public void AccessRepoTest() throws InterruptedException {
        //click show more on the right side of the page
        WebElement showMoreButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-disable-with='Loading more...']")));
        showMoreButton.click();
        Thread.sleep(2000);

        //find existing repo
        WebElement repo = chromeDriver.findElement(By.partialLinkText("Final"));
        repo.click();
        Thread.sleep(2000);

        //get the url of the repo
        String currentUrl = chromeDriver.getCurrentUrl();
        System.out.println("The url for the existing repo is " + currentUrl);

    }

//--------------------------------------------METHOD 2: Upload Files method -----------------------------------------------------------------------------

    @Test(priority = 2)
    public void addFiles() throws InterruptedException {
        //increase the duration of seconds
        wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(20));

        //click add file icon
        WebElement addFile = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[.//span[text()='Add file']]                        ")));
        addFile.click();
        Thread.sleep(1000);

        //upload file button
        WebElement addFilebtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Upload files']")));
        addFilebtn.click();
        Thread.sleep(2000);

        //upload all files
        WebElement file = chromeDriver.findElement(By.id("upload-manifest-files-input"));
        String[] filePaths = {
                "/Users/Yani/Library/CloudStorage/OneDrive-FloridaGulfCoastUniversity/chapisGallery/index.html",
                "/Users/Yani/Library/CloudStorage/OneDrive-FloridaGulfCoastUniversity/chapisGallery/styles.css",
                "/Users/Yani/Library/CloudStorage/OneDrive-FloridaGulfCoastUniversity/chapisGallery/20231026_183831.jpg",
                "/Users/Yani/Library/CloudStorage/OneDrive-FloridaGulfCoastUniversity/chapisGallery/20231112_164632.jpg",
                "/Users/Yani/Library/CloudStorage/OneDrive-FloridaGulfCoastUniversity/chapisGallery/20231126_094509.jpg"
        };

        for (String path : filePaths) {
            file.sendKeys(path);
            Thread.sleep(1000);  // Give the UI a moment for each upload.
        }

        //waits until all files are updated
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".js-manifest-file-list-root .js-manifest-file-entry"), 5));
        //confirms there are 5 files
        Assert.assertEquals(chromeDriver.findElements(By.cssSelector(".js-manifest-file-list-root .js-manifest-file-entry")).size(), 5);
        System.out.println("5 files have been uploaded to the repository.");


        // commit message
        WebElement commitMessage = chromeDriver.findElement(By.id("commit-summary-input"));
        commitMessage.sendKeys("Added files to the repository via automation");
        Thread.sleep(2000);


        //scrolling
        JavascriptExecutor scroll = (JavascriptExecutor) chromeDriver;
        scroll.executeScript("window.scroll(0,500)","");

        //commit changes
        WebElement commitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//button[contains(text(), 'Commit changes')]")));
        commitButton.click();
        Thread.sleep(10000);

    }

//--------------------------------------------METHOD 3: Pages method-----------------------------------------------------------------------------

    @Test(priority = 3, dependsOnMethods = "AccessRepoTest")
    public void pages() throws InterruptedException {
        //click on the settings tab
        WebElement settings = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Settings')]")));
        settings.click();
        Thread.sleep(2000);

        //click on the pages tab
        WebElement pagesbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Pages')]")));
        pagesbtn.click();
        Thread.sleep(2000);


        //selects the branch option
        WebElement branch = wait.until(ExpectedConditions.elementToBeClickable(By.id("pages_select_branch-button")));
        branch.click();
        Thread.sleep(2000);

        //selects main
        WebElement searchMain = wait.until(ExpectedConditions.elementToBeClickable(By.id("pages_select_branch-filter")));
        searchMain.sendKeys("main");
        Thread.sleep(2000);

        // Locate the main branch option after search
        WebElement mainOption = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//li[@data-name='main']//button[./span[text()[normalize-space()='main']] and @role='option']")
        ));
        mainOption.click();
        Thread.sleep(2000);

        //save button
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space(text())='Save']")));
        saveButton.click();
        Thread.sleep(2000);


    }

//--------------------------------------------METHOD 4: configure about-----------------------------------------------------------------------------

    @Test(priority = 4, dependsOnMethods = "pages")
    public void configureAbout() throws InterruptedException {
        //click on code tab
        WebElement codeTab = chromeDriver.findElement(By.xpath("//span[@data-content='Code']"));
        codeTab.click();
        Thread.sleep(3000);

        //click on the settings tab
        WebElement settings = chromeDriver.findElement(By.cssSelector("svg[aria-label='Edit repository metadata']"));
        settings.click();
        Thread.sleep(3000);

        //click on the description textbox
        WebElement desc = chromeDriver.findElement(By.id("repo_description"));
        desc.sendKeys("Below is the link for the website");
        Thread.sleep(3000);

        //check box to use the deployed website
        WebElement websiteCheckBox = chromeDriver.findElement(By.xpath("//*[@id=\"repo_sections_pages_url\"]"));
        websiteCheckBox.click();
        Thread.sleep(3000);


        //saves changes to the about page
        WebElement saveChanges = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//button[contains(text(), 'Save changes')]")));
        saveChanges.click();
        Thread.sleep(10000);


    }


//--------------------------------------------METHOD 5: access website using link-----------------------------------------------------------------------------

    @Test(priority = 5, dependsOnMethods = "configureAbout")
    public void accessWebsite() throws InterruptedException {


        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("softwaretestinggroup")));
        link.click();

        // Store the current window handle
        String originalWindow = chromeDriver.getWindowHandle();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        // Switch to the new window
        for (String windowHandle : chromeDriver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                chromeDriver.switchTo().window(windowHandle);
                break;
            }


            String currentUrl = chromeDriver.getCurrentUrl();
            System.out.println("The url for the hosted webpage is " + currentUrl);
        }

    }
//--------------------------------------------METHOD 6: take screenshot-----------------------------------------------------------------------------

    @Test(priority = 6, dependsOnMethods = "accessWebsite")
    public void takeScreenshot() throws InterruptedException, IOException {
        Thread.sleep(5000);

        //take screenshot
        TakesScreenshot screenshot = (TakesScreenshot) chromeDriver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);

        // Define destination file path
        File destination = new File("/Users/Yani/Library/CloudStorage/OneDrive-FloridaGulfCoastUniversity/CEN4072-Java Projects/GitHubTesting-Project/websiteScreenshot.png");
        Files.copy(source, destination); //save screenshot
        //confirm screenshot has been taken
        System.out.println("Screenshot saved to: " + destination.getAbsolutePath());


        Thread.sleep(3000);

        chromeDriver.quit();


    }

}

