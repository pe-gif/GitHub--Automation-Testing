package githubtests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.time.Duration;

public class Login extends BaseTest {

    WebDriverWait wait;

    // Initializes wait before each test method
    @BeforeMethod
    public void initWait() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Link to navigate to GitHub's login page
    public void goToLoginPage() {
        driver.get("https://github.com/login");
    }

    @Test(priority = 1) // Go to the GitHub homepage and verify page title.
    public void navigateToGitHubHomepage() {
        driver.get("https://github.com");
        Assert.assertEquals(driver.getTitle(), "GitHub · Build and ship software on a single, collaborative platform · GitHub");
    }

    @Test(priority = 2) // Click on "Sign in" and confirm redirection.
    public void accessSignInPage() {
        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign in")));
        signInButton.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
    }

    @Test(priority = 3) // Click on "Create and account" and confirm redirection.
    public void selectCreateAnAccount() {
        WebElement createAccountLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Create an account")));
        createAccountLink.click();

        // This is how the redirection is confirmed. Waits for page to load and checks URL for "signup".
        wait.until(ExpectedConditions.urlContains("signup"));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("signup"));
    }

    @Test(priority = 4) // Enter taken credentials to test the error handling of the create account page
    public void takenSignupDetails() throws InterruptedException {
        driver.get("https://github.com/signup");

        // Email
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys("githubemailtest@gmail.com");
        Thread.sleep(1000);

        // Username
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
        usernameField.sendKeys("SoftwareTestingGroup");
        Thread.sleep(1000);

        // Password
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        passwordField.sendKeys("passw"); // We enter a password with less characters than the minimum.
        Thread.sleep(1000);

        // Country Dropdown
        WebElement countryDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@aria-labelledby='country-dropdown-label']")));
        countryDropdown.click();
        Thread.sleep(1000);

        // Wait for the dropdown list to be visible
        WebElement countryList = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//ul[@role='listbox']")));

        // Loops through the dropdown options until it fiends "Canada", then clicks on it.
        List<WebElement> countryOptions = driver.findElements(By.xpath("//ul[@role='listbox']//span"));
        for (WebElement option : countryOptions) {
            if (option.getText().equals("Canada")) {
                option.click();
                break;
            }
        }

// Waits 3 seconds for visual verification
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

// Since normal click doesn't work, we click the "Sign in" button is JS
        WebElement signInLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(text(),'Sign in')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signInLink);

// Wait for redirect to login
        wait.until(ExpectedConditions.urlContains("/login"));

        Assert.assertTrue(driver.getCurrentUrl().contains("/login"), "Should now be in login page.");
    }


    @Test(priority = 5) // Click on "Forgot password?", and verify proper redirection.
    public void forgotPasswordRedirection() {
        WebElement forgotPasswordLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Forgot password?")));
        forgotPasswordLink.click();

        // Confirms redirection by waiting until the URL contains "password_reset"
        wait.until(ExpectedConditions.urlContains("password_reset"));

        // Wait 3 seconds for visual confirmation
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Assertion to confirm we're on the password reset page
        Assert.assertTrue(driver.getCurrentUrl().contains("password_reset"), "Should now be in password reset page.");
    }


    @Test(priority = 6) // Try to log in with no credentials to test error handling
    public void attemptLoginWithNoCredentials() {
        goToLoginPage();

        // Click the "Sign in" button with nothing entered
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.name("commit")));
        loginButton.click();

        // Wait until the error message appears
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("js-flash-container")));

        // Wait 3 seconds for visual confirmation.
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(errorMessage.isDisplayed(), "Error message appeared.");
    }


    @Test(priority = 7) // Try to log in with wrong credentials to test error handling
    public void attemptLoginWithIncorrectCredentials() throws InterruptedException {
        goToLoginPage();

        // Wait for login fields to be visible
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_field")));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));

        // Enter incorrect username
        usernameField.sendKeys("wrongusername");
        Thread.sleep(1000);

        // Enter incorrect password
        passwordField.sendKeys("wrongpassword");
        Thread.sleep(1000);

        // Wait and click the login button
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.name("commit")));
        loginButton.click();

        // Wait for error message to appear
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("js-flash-container")));

        // Wait 3 seconds for visual confirmation.
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(errorMessage.isDisplayed(), "Error message appeared.");
    }



    @Test(priority = 8, groups = "login") // Successful login, the group is so that new next class only begins when the final test in this class is completed.
    public void loginWithValidCredentials() throws InterruptedException {
        // Credentials
        String username = System.getenv("GITHUB_USERNAME") != null ? System.getenv("GITHUB_USERNAME") : "githubemailtest@gmail.com";
        String password = System.getenv("GITHUB_PASSWORD") != null ? System.getenv("GITHUB_PASSWORD") : "Githubpassword123-";

        // Locate and fill out the login fields
        WebElement usernameField = driver.findElement(By.id("login_field"));
        WebElement passwordField = driver.findElement(By.id("password"));

        // Correct email
        usernameField.clear();
        usernameField.sendKeys(username);
        Thread.sleep(1000);

        // Correct password
        passwordField.clear();
        passwordField.sendKeys(password);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click the "Sign in" button
        WebElement loginButton = driver.findElement(By.name("commit"));
        loginButton.click();

        // Wait for the page to load and verify it being checking if the word "Dashboard" is visible.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), "Dashboard"));

        Assert.assertTrue(driver.getPageSource().contains("Dashboard"), "Dashboard is visible, successfully logged in.");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
