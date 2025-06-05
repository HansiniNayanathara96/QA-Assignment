package transmeida;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertyFileReader;

import java.time.Duration;
public class CreateNewBoard {

    WebDriver driver = null;

    PropertyFileReader prop = new PropertyFileReader();

    String url = prop.getProperty("config", "url");


    @Test
    public void testCreateNewBoard() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1. Click "Create new board" to expand the input field
        WebElement createBoardBox = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-cy='create-board']")));
        createBoardBox.click();

        // 2. Type board name
        WebElement boardInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-cy='new-board-input']")));
        boardInput.sendKeys("Test Board");

        // 3. Click "Create board" button
        WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-cy='new-board-create']")));
        createButton.click();

        // 4. Verify board is created (adjust if boards are listed in a div or link with the board name)
        WebElement newBoard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Test Board']")));
        Assert.assertTrue(newBoard.isDisplayed(), "New board was not created successfully!");

        driver.quit();
    }

    private WebElement waitUntilVisible(By locator, int maxTimeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(maxTimeout))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

}
