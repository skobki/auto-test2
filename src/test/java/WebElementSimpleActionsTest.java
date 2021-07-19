import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;

import java.util.Locale;

public class WebElementSimpleActionsTest {
    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(WebElementSimpleActionsTest.class);
    String env = System.getProperty("browser", "chrome");
    String st = System.getProperty("option", "NORMAL");

    @BeforeEach
    public void setUp() {
        logger.info("Браузер = " + env);
        logger.info("Стратегия загрузки страницы = " + st);
        driver = WebDriverFactory.getDriver(env.toLowerCase(), st.toUpperCase());
        logger.info("Драйвер запущен");
    }

    @AfterEach
    public void setDown() {
        if(driver != null) {
            driver.quit();
            logger.info("Драйвер остановлен");
        }
    }
    @Test
    public void clickTest() {
        driver.get("https://demosite.executeautomation.com/index.html?UserName=&Password=&Login=login");
        logger.info("Открыта страница demosite.executeautomation.com - " + "https://demosite.executeautomation.com/index.html?UserName=&Password=&Login=login");
        WebElement element = driver.findElement(By.name("Save"));
        //Нажатие на элемент
        element.click();
        logger.info("Нажата кнопка \"Save\"");

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void sendKeysTest() {
        driver.get("https://demosite.executeautomation.com/index.html?UserName=&Password=&Login=login");
        logger.info("Открыта страница demosite.executeautomation.com - " + "https://demosite.executeautomation.com/index.html?UserName=&Password=&Login=login");
        WebElement element = driver.findElement(By.name("Initial"));
        //Ввод текста
        element.sendKeys("Initial");

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void clearTest() {
        driver.get("https://demosite.executeautomation.com/index.html?UserName=&Password=&Login=login");
        logger.info("Открыта страница demosite.executeautomation.com - " + "https://demosite.executeautomation.com/index.html?UserName=&Password=&Login=login");
        WebElement element = driver.findElement(By.name("Initial"));
        //Удаление текста
        element.sendKeys("Initial");
        element.clear();

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void submitTest() {
        driver.get("https://demosite.executeautomation.com/index.html?UserName=&Password=&Login=login");
        logger.info("Открыта страница demosite.executeautomation.com - " + "https://demosite.executeautomation.com/index.html?UserName=&Password=&Login=login");
        WebElement element = driver.findElement(By.name("Initial"));
        //Отправка данных формы
        element.submit();

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
