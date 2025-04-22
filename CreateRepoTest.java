package githubtests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.*;

import java.time.Duration;


public class CreateRepo extends BaseTest {
    WebDriverWait wait;

//--------------------------------------------METHOD 1: create Repo -----------------------------------------------------------------------------

     @BeforeMethod
     public void waitSetup() {
         //create wait
         wait = new WebDriverWait(driver, Duration.ofSeconds(20));
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
        WebElement repoName = driver.findElement(By.cssSelector(
                "input[aria-describedby*='RepoNameInput-message']"));
        repoName.sendKeys("test_repo");
        Thread.sleep(1000);

        //scrolling
        JavascriptExecutor scroll = (JavascriptExecutor) driver;
        scroll.executeScript("window.scroll(0,300)","");
        Thread.sleep(1000);

        //clicking to make repo private and then public, then private
        WebElement privateRepo = driver.findElement(By.cssSelector("label[for=':rg:']"));
        privateRepo.click();
        Thread.sleep(1000);
        WebElement publicRepo = driver.findElement(By.cssSelector("label[for=':rf:']"));
        publicRepo.click();
        Thread.sleep(1000);
        privateRepo.click();
        Thread.sleep(1000);


        //adding a readme
        WebElement addReadMe = driver.findElement(By.cssSelector("label[for=':ri:']"));
        addReadMe.click();
        Thread.sleep(2000);

        //click repository button
        WebElement createRepoButton = driver.findElement(By.xpath(
                "/html/body/div[1]/div[5]/main/react-app/div/form/div[6]/button/span/span"));
        createRepoButton.click();
        Thread.sleep(3000);

        String repoLnk = driver.getCurrentUrl();
        System.out.println("The url for the created repository is " + repoLnk);
    }

    //--------------------------------------------METHOD 2: Rename Repo -----------------------------------------------------------------------------

    @Test(priority = 2, dependsOnMethods = "testCreateRepo")
    public void renameRepo() throws InterruptedException {
        //click on the settings tab
        WebElement settings = driver.findElement(By.xpath("//*[@id=\"settings-tab\"]/span[1]"));
        settings.click();
        Thread.sleep(2000);

        // Type new repo name on the textbox
        WebElement renametxt = driver.findElement(By.xpath("//*[@id=\"rename-field\"]"));
        renametxt.clear();
        renametxt.sendKeys("GitHub Report Final");
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


//--------------------------------------------METHOD 3: Add Contributors -----------------------------------------------------------------------------

    @Test(priority = 3, dependsOnMethods = "renameRepo")
    public void addContributors() throws InterruptedException {
        //click on the settings tab
        WebElement settings = driver.findElement(By.xpath("//*[@id=\"settings-tab\"]/span[1]"));
        settings.click();
        Thread.sleep(2000);

        //clicks on the collaborators tab
        WebElement collaborators = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//span[contains(text(), 'Collaborators')]")));
        collaborators.click();
        Thread.sleep(2000);

        //scrolling
        JavascriptExecutor scroll = (JavascriptExecutor) driver;
        scroll.executeScript("window.scroll(0,100)","");
        Thread.sleep(2000);

        //clicks on the add people button
        WebElement addPpl = driver.findElement(By.xpath("//span[text()='Add people']"));
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
        Thread.sleep(2000);

        //scrolling
        scroll.executeScript("window.scroll(0,100)","");
        Thread.sleep(3000);


    }

//--------------------------------------------METHOD 4: Add Readme -----------------------------------------------------------------------------

    @Test(priority = 4)
    public void addReadme() throws InterruptedException {
        //click on code tab
        WebElement codeTab = driver.findElement(By.xpath("//span[@data-content='Code']"));
        codeTab.click();
        Thread.sleep(2000);

        //click on the edit icon
        WebElement editIcon = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@aria-label='Edit file']")));
        editIcon.click();
        Thread.sleep(2000);

        // add text to the readme
        WebElement readme = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//*[@id=\"repo-content-pjax-container\"]/react-app/div/div/div[1]/div/div/div[2]/div[2]/div/div[3]/div[2]/div/div[2]/file-attachment/div/div/div[2]/div[2]/div")));
        readme.clear();
        Thread.sleep(2000);
        readme.sendKeys("# GitHub Report Final \n\n This repository includes the report and .java files for our project" +
                "in software testing.");
        Thread.sleep(2000);

        //view preview
        WebElement preview = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//*[@id=\"repo-content-pjax-container\"]/react-app/div/div/div[1]/div/div/div[2]/div[2]/div/div[3]/div[2]/div/div[1]/div/ul/li[2]/button/span/div")));
        preview.click();
        Thread.sleep(3000);

        // commit changes
        WebElement commit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Commit changes...']")));
        commit.click();
        Thread.sleep(2000);

        //confirm Update
        WebElement confirmUpdate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//span[text()='Commit changes']/ancestor::button")));
        confirmUpdate.click();
        Thread.sleep(2000);

    }

//--------------------------------------------METHOD 5: Change Visibility -----------------------------------------------------------------------------

    @Test(priority = 5)
    public void changevisibility() throws InterruptedException {
        //click on the settings tab
        WebElement settings = driver.findElement(By.xpath("//*[@id=\"settings-tab\"]/span[1]"));
        settings.click();
        Thread.sleep(2000);

        //scrolling
        JavascriptExecutor scroll = (JavascriptExecutor) driver;
        scroll.executeScript("window.scroll(0,3000)", "");
        Thread.sleep(2000);

        //clicks on the visibility button
        WebElement visibilityButton = driver.findElement(By.xpath("//span[text()='Change visibility']/ancestor::button"));
        visibilityButton.click();
        Thread.sleep(1000);

        //clicks on change to change to public
        WebElement changeToPublic = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//span[normalize-space()='Change to public']/ancestor::button")));
        changeToPublic.click();
        Thread.sleep(1000);

        //clicks on first confromation message
        WebElement confirmPublic = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//span[normalize-space()='I want to make this repository public']/ancestor::button")));
        confirmPublic.click();
        Thread.sleep(1000);

        //2nd confirmation message
        WebElement confirmPublic2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//span[normalize-space()='I have read and understand these effects']/ancestor::button")));
        confirmPublic2.click();
        Thread.sleep(1000);

        //final confirmation message
        WebElement confirmPublic3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//span[normalize-space()='Make this repository public']/ancestor::button")));
        confirmPublic3.click();
        Thread.sleep(3000);

        //scroll to confirm change
        scroll.executeScript("window.scroll(0,3000)", "");
        Thread.sleep(3000);

        //navigate to homepage
        driver.get("https://www.github.com/");
        Thread.sleep(2000);


    }

}
