import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class FormTests {

    WebDriver driver;
    WebDriverWait wait;
    @BeforeClass
    public void setup(){

        driver= new ChromeDriver();
        wait= new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }
    @AfterClass
    public void tearDown(){

        driver.quit();
    }
    @Test
    public void formTests()  {

        driver.get("https://demoqa.com/automation-practice-form");

        driver.findElement(By.id("firstName")).sendKeys("Lika");

        driver.findElement(By.id("lastName")).sendKeys("Martiashvili");

        driver.findElement(By.id("userEmail")).sendKeys("lika@example.com");

        WebElement femaleRadio = driver.findElement(By.cssSelector("label[for='gender-radio-2']"));

        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", femaleRadio);

        wait.until(ExpectedConditions.elementToBeClickable(femaleRadio));


        femaleRadio.click();



        driver.findElement(By.id("userNumber")).sendKeys("555446622");


        driver.findElement(By.id("dateOfBirthInput")).click();
        WebElement dropDownMonth = driver.findElement(By.className("react-datepicker__month-select"));
        Select month = new Select(dropDownMonth);
        month.selectByVisibleText("February");

        WebElement dropDownYear = driver.findElement(By.className("react-datepicker__year-select"));
        Select year = new Select(dropDownYear);
        year.selectByVisibleText("2005");

        WebElement day = driver.findElement(By.xpath("//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[2]/div[1]/div[4]"));
        day.click();

        driver.findElement(By.id("subjectsInput")).sendKeys("Quantum programming");

        WebElement radio = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='hobbies-checkbox-2']")));
        radio.click();

        driver.findElement(By.id("currentAddress")).sendKeys("tbilisi, georgia");

        WebElement state = driver.findElement(By.id("react-select-3-input"));
        state.sendKeys("NCR");
        state.sendKeys(Keys.ENTER);

        WebElement city = driver.findElement(By.id("react-select-4-input"));
        city.sendKeys("Delhi");
        city.sendKeys(Keys.ENTER);

        WebElement submit = driver.findElement(By.id("submit"));
        submit.click();

        WebElement modal = driver.findElement(By.className("modal-content"));
        Assert.assertTrue(modal.isDisplayed(), "Pop-up didn't appear");

        String text = modal.getText();
        Assert.assertTrue(text.contains("Lika Martiashvili"));
        Assert.assertTrue(text.contains("lika@example.com"));
        Assert.assertTrue(text.contains("555446622"));
        Assert.assertTrue(text.contains("Female"));
        Assert.assertTrue(text.contains("07 February,2005"));
        Assert.assertTrue(text.contains("Reading"));
        Assert.assertTrue(text.contains("NCR Delhi"));

    }
}