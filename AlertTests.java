import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class AlertTests {

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
    public void alertTest() throws InterruptedException {

        driver.get("https://demo.automationtesting.in/Alerts.html");


        driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/ul/li[3]/a")).click();

        driver.findElement(By.xpath("//*[@id=\"Textbox\"]/button")).click();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys("Lika Martiashvili");
        alert.accept();

        WebElement demo1 = driver.findElement(By.id("demo1"));
        String textt = demo1.getText();

        Assert.assertTrue(textt.contains("Lika Martiashvili"), "Text does not contain expected name and surname! Found: " + textt);

    }
}