package githubtests;


import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.*;


import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;


public class Fork extends BaseTest {


    @Test(priority = 1 )
    // Create Fork with other repository
    public void test1_createFork() throws InterruptedException {
        //navigate to repository
        driver.get("https://github.com/ggstephen6724/weight-conversion");
        Thread.sleep(2000);
        System.out.println("Landed on repository page");

        try {
            Thread.sleep(2000);
            WebElement dropdownMenu = driver.findElement(By.xpath("//*[@id=\"my-forks-menu-506511423\"]/summary"));
            dropdownMenu.click(); // Open
            Thread.sleep(500);
            dropdownMenu.click(); // Close
            Thread.sleep(2000);
        } catch (NoSuchElementException e) {
            System.out.println("Dropdown not found, moving on.");
        }

        // Try clicking "Fork" or fallback to "View existing forks"
        try {
            Thread.sleep(2000);
            WebElement forkButton = driver.findElement(By.xpath("//*[@id=\"fork-button\"]"));
            forkButton.click();
            Thread.sleep(2000);
        } catch (NoSuchElementException e) {
            try {
                WebElement viewExistingForks = driver.findElement(By.xpath("//*[@id=\"js-repo-pjax-container\"]/react-app/div/form/div[1]/span/span/a"));
                viewExistingForks.click();
                Thread.sleep(2000);
                return; // Stop test here if fork already exists
            } catch (NoSuchElementException ex) {
                System.out.println("Neither 'Fork' nor 'View existing forks' found");
                return;
            }
        }

        // Add a description if input is present
        try {
            Thread.sleep(2000);
            WebElement descriptionBox = driver.findElement(By.xpath("//*[@id=\"js-repo-pjax-container\"]/react-app/div/form/div[6]/div/span")); // May need updating
            descriptionBox.click();
            WebElement textBox = driver.findElement(By.xpath("//*[@id=\":r1d:\"]"));
            textBox.clear();
            textBox.sendKeys("Test Description");
            Thread.sleep(2000);
        } catch (NoSuchElementException e) {
            System.out.println("Description input not found.");
        }

        // Click Create Fork button
        try {
            Thread.sleep(2000);
            WebElement createForkBtn = driver.findElement(By.xpath("//*[@id=\"js-repo-pjax-container\"]/react-app/div/form/div[9]/button")); // May vary
            createForkBtn.click();
            Thread.sleep(9000);
        } catch (NoSuchElementException e) {
            System.out.println("Create fork button not found.");
        }
    }

    @Test(priority = 2) // TRY TO FIX THIS PART
    public void test2_validateForkSuccess() {
        // Check for presence of forked repo UI element
        try {
            WebElement repoOwner = driver.findElement(By.xpath("//*[@id=\"repository-container-header\"]/div[1]/div[1]/div[2]/span/a"));
            WebElement repoName = driver.findElement(By.xpath("//*[@id=\"repo-title-component\"]/strong/a"));
            String fullRepoName = repoOwner.getText() + "/" + repoName.getText();
            System.out.println("Forked Repo: " + fullRepoName);

            // Assert that we're on a forked repo (this is a placeholder condition)
            assertNotEquals("URL should contain user fork", driver.getCurrentUrl().contains("https://github.com/ggstephen6724/weight-conversion"));
        } catch (NoSuchElementException e) {
            System.out.println("Fork verification elements not found.");
        }
    }

    @Test (priority = 3)
    public void test2_leaveForkNetwork() throws InterruptedException {
        WebElement settingsBtn = driver.findElement(By.id("settings-tab"));
        settingsBtn.click();
        Thread.sleep(2000);

        // Scroll Down

        // Leave Fork Network
        WebElement leaveBtn = driver.findElement(By.id("dialog-show-repo-detach-menu-dialog"));
        leaveBtn.click();
        Thread.sleep(2000);
        WebElement acknowledgeBtn = driver.findElement(By.id("repo-detach-proceed-button"));
        acknowledgeBtn.click();
        WebElement textBox = driver.findElement(By.id("verification_field"));
        textBox.click();
        textBox.sendKeys("SoftwareTestingGroup/Weight-Conversion");
        WebElement leave = driver.findElement(By.id("repo-detach-proceed-button"));
        leave.click();
    }


    @Test (priority = 4)
    public void test3_deleteRepository() throws InterruptedException {
        // Scroll Down
        Thread.sleep(2000);
        WebElement deleteRepoBtn = driver.findElement(By.id("dialog-show-repo-delete-menu-dialog"));
        deleteRepoBtn.click();
        WebElement confirmBtn = driver.findElement(By.id("repo-delete-proceed-button"));
        confirmBtn.click();
        WebElement acknowledgeBtn = driver.findElement(By.id("repo-delete-proceed-button"));
        acknowledgeBtn.click();
        WebElement textBox = driver.findElement(By.id("verification_field"));
        textBox.click();
        textBox.sendKeys("SoftwareTestingGroup/Weight-Conversion");
        WebElement leave = driver.findElement(By.id("repo-delete-proceed-button"));
        leave.click();

    }
}
