import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.*;

    public class OnlinerTest {

    private final WebDriver driver;

    public OnlinerTest() throws IOException {
        driver = new Main().getDriver();
    }

    @BeforeTest
    public void startDriver () {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @AfterTest
    public void stopDriver() {
        driver.close();
    }

    @Test
    public void OpenOnlinerTest() {
        driver.get("http://onliner.by");
        String login = "demo.mail1@yandex.ru";
        String password = "123456";

        WebDriverWait wait = new WebDriverWait(driver, 100, 15);
        wait.until(ExpectedConditions.titleContains("Onliner.by"));
        assertEquals(driver.getTitle(), "Onliner.by");

        WebElement enterButton = driver.findElement(By.xpath(".//*[@id='userbar']/div[2]/div[1]"));
        wait.until(ExpectedConditions.visibilityOf(enterButton));
        enterButton.click();

        WebElement loginEnterButton = driver.findElement(By.xpath(".//*[@id='auth-container__forms']//div[4]/div/button"));
        wait.until(ExpectedConditions.visibilityOf(loginEnterButton));

        assertTrue(loginEnterButton.isDisplayed());

        WebElement loginField = driver.findElement(By.xpath(".//*[@id='auth-container__forms']//div[1]/div[1]/input"));
        wait.until(ExpectedConditions.visibilityOf(loginField));
        loginField.sendKeys(login);

        WebElement passwordField = driver.findElement(By.xpath(".//*[@id='auth-container__forms']//div[1]/div[2]/input"));
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys(password);

        loginEnterButton.click();

        WebElement exitButton1 = driver.findElement(By.xpath(".//*[@id='userbar']/div[1]/a"));
        wait.until(ExpectedConditions.visibilityOf(exitButton1));
        assertTrue(exitButton1.isDisplayed());

        Random random = new Random();
        String numOfTopic = String.valueOf(random.nextInt(15) + 1);
        String topicXpath = ".//*[@id='container']//*[@class='catalog-bar']//li[" + numOfTopic + "]";
        WebElement topic = driver.findElement(By.xpath(topicXpath));
        wait.until(ExpectedConditions.visibilityOf(topic));
        String topicName = topic.getText();
        topic.click();

        WebElement currentTopic = driver.findElement(By.xpath(".//*[@id='container']//h1"));
        wait.until(ExpectedConditions.visibilityOf(currentTopic));

        assertEquals(topicName, currentTopic.getText() );

        WebElement exitButton2 = driver.findElement(By.xpath(".//*[@id='userbar']/div[1]/a"));
        wait.until(ExpectedConditions.visibilityOf(exitButton2));

        assertEquals(exitButton2.getAttribute("class"), "exit");

        Actions builder  = new Actions(driver);
        builder.click(exitButton2).build().perform();

    }

}
