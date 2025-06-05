package transmeida;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DeleteList {

    WebDriver driver = null;

    WebDriverWait wait;

    @BeforeClass
    public void setup() throws InterruptedException {
        // Setup ChromeDriver
        System.setProperty("web-driver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/board/4");
        // Optional: short wait between deletions
        Thread.sleep(8000);
    }

    @Test
    public void deleteLists() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));


            // 1. Click 3-dot menu button for that list

            WebElement threeDots;
        threeDots = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-cy='list' and .//input[@value='List 2']]//button[@data-cy='list-options']"
        )));
        threeDots.click();


            // 2. Wait for and click "Delete list"
            WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("div[data-cy='delete-list']")));
            deleteBtn.click();


        }

    private WebElement waitUntilNextElement(By locator, int maxTimeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(maxTimeout))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    }







