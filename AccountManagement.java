package githubtests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

public class AccountManagement extends BaseTest {

    WebDriverWait wait;

    // Prevents this class from running before the Login class finishes
    @BeforeClass(dependsOnGroups = "login")
    public void setUpAfterLogin() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @BeforeMethod
    public void initWait() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 1) // Sets user status with emoji
    public void setStatus() throws InterruptedException {
        // Wait for and click the profile avatar
        WebElement profileAvatar = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("img.avatar.circle")
        ));
        profileAvatar.click();
        Thread.sleep(1000); // In case there's a delay

        // Click "Set status"
        WebElement setStatus = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(text(),'Set status')]")
        ));
        setStatus.click();
        Thread.sleep(1000);

        // Type "Tired" into the status input
        WebElement statusInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("input[name='message']")
        ));
        statusInput.clear();
        statusInput.sendKeys("Tired");

        // Click emoji picker button
        WebElement emojiButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[aria-label='Choose an emoji']")
        ));
        emojiButton.click();
        Thread.sleep(1000);

        // Select the sleepy emoji from the picker
        WebElement sleepyEmoji = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("g-emoji[alias='sleepy']")
        ));
        sleepyEmoji.click();
        Thread.sleep(1000);

        // Click the final "Set status" button
        WebElement finalSetStatus = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Set status']")
        ));
        finalSetStatus.click();
        Thread.sleep(1000);
    }

    @Test(priority = 2) // Upload a profile picture
    public void changeProfilePicture() throws InterruptedException {
        // Click "Settings"
        WebElement settingsOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Settings']")
        ));
        settingsOption.click();
        Thread.sleep(2000);

        // Click the profile picture
        WebElement profilePic = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("img.avatar-user")
        ));
        profilePic.click();
        Thread.sleep(2000);

        // Go straight to file input instead of clicking the Uploadlabel to prevent complications
        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("avatar_upload")
        ));
        fileInput.sendKeys("/Users/Yani/Downloads/Profile_Picture.png");
        Thread.sleep(1500); // Wait for preview to load

        // Click "Set new profile picture"
        WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Set new profile picture']")
        ));
        confirmBtn.click();
    }

    @Test(priority = 3) // Updates the display name, email, and bio
    public void changeNameEmailBio() throws InterruptedException {
        String[] names = {"Testing Group"};
        WebElement nameInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("user_profile_name")
        ));

        for (String name : names) {
            nameInput.clear();
            nameInput.sendKeys(name);
            Thread.sleep(1000);
        }

        // Select the public email dropdown and set to verified email
        WebElement emailDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("user_profile_email")
        ));
        emailDropdown.click();
        Thread.sleep(1000);

        WebElement emailOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//option[contains(text(),'githubemailtest@gmail.com')]")
        ));
        emailOption.click();

        // Fill in the bio with greetings
        WebElement bioTextArea = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("user_profile_bio")
        ));
        String multiLangHello = String.join("\n", new String[]{
                "Hello", "Hola", "Bonjour", "Hallo", "Ciao",
                "こんにちは", "안녕하세요", "你好", "Olá", "Здравствуйте"
        });
        bioTextArea.clear();
        bioTextArea.sendKeys(multiLangHello);

        // Scroll down to ensure the bio box is fully in view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", bioTextArea);
        Thread.sleep(1000);

        // Resize the bio text box multiple times.
        int[][] sizePattern = {
                {150, 300},  // small
                {300, 500},  // big
                {150, 300},  // small
                {300, 500},  // big
                {150, 300}   // small
        };

        // Click the "Update profile" button
        WebElement updateButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(., 'Update profile')]")
        ));
        updateButton.click();
    }

    @Test(priority = 4) // Changes the theme for the website
    public void changeTheme() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll down to make sure the "Appearance" section is in view
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(1500);

        // Click the "Appearance" section
        WebElement appearanceTab = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(),'Appearance')]")
        ));
        js.executeScript("arguments[0].scrollIntoView(true);", appearanceTab);
        wait.until(ExpectedConditions.elementToBeClickable(appearanceTab)).click();
        Thread.sleep(1500);

        // Open Theme mode dropdown
        WebElement themeDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("color_mode_type_select")
        ));
        themeDropdown.click();
        Thread.sleep(1000);

        // Select "Single theme"
        Select themeSelect = new Select(themeDropdown);
        themeSelect.selectByValue("single");
        Thread.sleep(1500);

        // Click on "Light high contrast" theme
        WebElement lightHighContrast = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("option-light_high_contrast")
        ));
        lightHighContrast.click();
        Thread.sleep(1500);

        // Revert to the original theme
        WebElement darkDefault = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("option-dark")
        ));
        darkDefault.click();
        Thread.sleep(1500);
    }

    @Test(priority = 5) // Tests a variety of radio and checkbox interactions in the "Accessibility" section
    public void checkboxesAndRadios() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Helper method for scrolling, this helps keep the interactions more centered on the screen.
        scrollBy(js, 0);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(1500);

        // Click on "Accessibility" link using JS
        WebElement accessibilityLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a[href='/settings/accessibility']"))
        );
        js.executeScript("arguments[0].scrollIntoView(true);", accessibilityLink);
        js.executeScript("arguments[0].click();", accessibilityLink);
        Thread.sleep(1500);

        // Keyboard Shortcuts, checkbox
        scrollBy(js, 200);
        WebElement keyboardCheckbox = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("user_keyboard_shortcuts_preference"))
        );
        keyboardCheckbox.click();
        Thread.sleep(800);
        keyboardCheckbox.click();
        Thread.sleep(1000);

        // Motion Section, radio buttons
        scrollBy(js, 150);
        driver.findElement(By.id("user_animated_images_enabled")).click();
        Thread.sleep(800);
        driver.findElement(By.id("user_animated_images_disabled")).click();
        Thread.sleep(800);
        driver.findElement(By.id("user_animated_images_system")).click();
        Thread.sleep(1000);

        // Link Underlines, radio buttons
        scrollBy(js, 100);
        driver.findElement(By.id("user_link_underlines_false")).click();
        Thread.sleep(800);
        driver.findElement(By.id("user_link_underlines_true")).click();
        Thread.sleep(1000);

        // Hovercards, checkboxes
        scrollBy(js, 150);
        WebElement hovercardsCheckbox = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("user_hovercards_enabled"))
        );
        hovercardsCheckbox.click();
        Thread.sleep(800);
        hovercardsCheckbox.click();
        Thread.sleep(1000);

        // URL Paste Behavior, radio buttons
        scrollBy(js, 200);
        driver.findElement(By.id("user_paste_url_markdown_false")).click();
        Thread.sleep(800);
        driver.findElement(By.id("user_paste_url_markdown_true")).click();
        Thread.sleep(1000);

        driver.get("https://github.com/");
        Thread.sleep(2000);
    }

    // Scroll helper method for potential future use
    public void scrollBy(JavascriptExecutor js, int pixels) {
        js.executeScript("window.scrollBy(0, arguments[0]);", pixels);
    }

}
