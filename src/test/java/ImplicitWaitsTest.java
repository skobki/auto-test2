import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class ImplicitWaitsTest {
    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(ImplicitWaitsTest.class);
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
    public void implicitlyWaitTest() {
        //Установка неявного ожидания до появления элемента на странице
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1));
        //Открытие страницы DNS
        driver.get("https://www.dns-shop.ru/");
        logger.info("Открыта страница DNS");
        //Нажатие на сслыку "Магазины"
        WebElement linkShops = driver.findElement(By.xpath("//a[text()=\"Магазины\"]"));
        linkShops.click();
        logger.info("Нажата ссылка \"Магазины\"");
        //Нажатие на выпадающе меню "Каталог"
        WebElement spanCatalog = driver.findElement(By.xpath("//span[@class=\"catalog-spoiler \"]"));
        spanCatalog.click();
        logger.info("Нажата ссылка на выпадающее меню \"Каталог\"");
        //Нажатие на вкладку "Бытовая техника"
        WebElement linkAppliances = driver.findElement(By.partialLinkText("Бытовая"));
        linkAppliances.click();
        logger.info("Нажата ссылка \"Бытовая техника\"");
    }

    @Test
    public void pageLoadTimeoutTest() {
        //Установка неявного ожидания до завершения загрузки страницы
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(1));
        //Открытие страницы DNS
        driver.get("https://www.dns-shop.ru/");
        logger.info("Отрыта страница DNS");
    }

    @Test
    public void setScriptTimeoutTest() {
        //Установить неявное ожидание до завершения выполнения асинхронного сценария
        driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(0));
        //Открыть страницу DNS
        driver.get("https://www.dns-shop.ru");
        logger.info("Открыта страница DNS");
        ((JavascriptExecutor) driver).executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 500");
    }
}
