package transmeida;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.List;
public class AddList2 {

        WebDriver driver = null;

        WebDriverWait wait;


    @BeforeClass
        public void setup() throws InterruptedException {
            // Setup ChromeDriver
            System.setProperty("web-driver.chrome.driver", "path/to/chromedriver");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("http://localhost:3000/board/4");
        // TEMPORARY: Pause to see browser before test
        Thread.sleep(40000);
        }



    @Test
    public void testAddAndVerifyLists() {
        String[] listNames = {"List 1", "List 2"};

        for (String name : listNames) {
            // Wait for input to be visible
            WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("input[data-cy='add-list-input']")
            ));
            input.clear();
            input.sendKeys(name);

            // Click the "Add list" button
            WebElement addButton = driver.findElement(By.xpath("//button[text()='Add list']"));
            addButton.click();

            // Optional: small wait after clicking
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.cssSelector("input[data-cy='add-list-input'][value='" + name + "']")
            ));
        }

        // VERIFY: Get all list title elements and check if both were added
        List<WebElement> lists = driver.findElements(By.cssSelector("div.w-list > div > div > span"));
        boolean list1Found = false;
        boolean list2Found = false;

        for (WebElement list : lists) {
            String text = list.getText().trim();
            if (text.equals("List 1")) list1Found = true;
            if (text.equals("List 2")) list2Found = true;
        }

        Assert.assertTrue(list1Found, "'List 1' was not found on the board!");
        Assert.assertTrue(list2Found, "'List 2' was not found on the board!");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}