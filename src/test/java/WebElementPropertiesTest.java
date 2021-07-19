import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;

import java.util.Locale;

public class WebElementPropertiesTest {
    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(WebElementPropertiesTest.class);
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
    public void getTagNameTest() {
        driver.get("https://demosite.executeautomation.com/index.html?UserName=&Password=&Login=login");
        logger.info("Открыта страница demosite.executeautomation.com - " + "https://demosite.executeautomation.com/index.html?UserName=&Password=&Login=login");
        WebElement element = driver.findElement(By.id("FirstName"));
        //Получение имени тега элемента
        String tagName = element.getTagName();
        logger.info("Tag Name: <" + tagName + ">");
        Assertions.assertTrue(tagName.equals("input"), "Значение tagName != input");
    }
    @Test
    public void getAttributeTest() {
        driver.get("https://demosite.executeautomation.com/index.html?UserName=&Password=&Login=login");
        logger.info("Открыта страница demosite.executeautomation.com - " + "https://demosite.executeautomation.com/index.html?UserName=&Password=&Login=login");
        WebElement element = driver.findElement(By.id("FirstName"));
        //Получение значения атрибута элемента
        String attributeValue = element.getAttribute("name");
        logger.info("Значение attribute: name = " + attributeValue);
        Assertions.assertTrue(attributeValue.equals("name"), "Значение attributeValue != name");
    }
    @Test
    public void getSizeTest() {
        driver.get("https://demosite.executeautomation.com/index.html?UserName=&Password=&Login=login");
        logger.info("Открыта страница demosite.executeautomation.com - " + "https://demosite.executeautomation.com/index.html?UserName=&Password=&Login=login");
        WebElement element = driver.findElement(By.id("FirstName"));
        //Получение размеров (значения ширины и высоты) элемента
        Dimension size = element.getSize();
        int height = size.getHeight();
        int width = size.getWidth();
        logger.info("Size: height = " + height + " width = " + width);
        Assertions.assertTrue(height == 21, "Значение height != 21");
        Assertions.assertTrue(width == 124, "Значение width != 124");
    }
    @Test
    public void getLocationTest() {
        driver.get("https://demosite.executeautomation.com/index.html?UserName=&Password=&Login=login");
        logger.info("Открыта страница demosite.executeautomation.com - " + "https://demosite.executeautomation.com/index.html?UserName=&Password=&Login=login");
        WebElement element = driver.findElement(By.id("FirstName"));
        //Получение положения верхнего левого угла (значения координат x, y) элемента
        Point location = element.getLocation();
        int x = location.getX();
        int y = location.getY();
        logger.info("Location: x = " + x + " y = " + y);
        Assertions.assertTrue(x == 136, "Значение x != 136");
        Assertions.assertTrue(y == 275, "Значение y != 275");
    }
    @Test
    public void getRectTest() {
        driver.get("https://demosite.executeautomation.com/index.html?UserName=&Password=&Login=login");
        logger.info("Открыта страница demosite.executeautomation.com - " + "https://demosite.executeautomation.com/index.html?UserName=&Password=&Login=login");
        WebElement element = driver.findElement(By.id("FirstName"));
        //Получение размеров и положения верхнего угла элемента
        Rectangle rect = element.getRect();

        Dimension size = rect.getDimension();
        int height = size.getHeight();
        int width = size.getWidth();
        logger.info("Size: height = " + height + " width = " + width);
        Assertions.assertTrue(height == 21, "Значение height != 21");
        Assertions.assertTrue(width == 128, "Значение width != 128");

        Point location = rect.getPoint();
        int x = location.getX();
        int y = location.getY();
        logger.info("Location: x = " + x + " y = " + y);
        Assertions.assertTrue(x == 136, "Значение x != 136");
        Assertions.assertTrue(y == 275, "Значение y != 275");
    }
    @Test
    public void isDisplayedTest() {
        driver.get("https://demosite.executeautomation.com/index.html?UserName=&Password=&Login=login");
        logger.info("Открыта страница demosite.executeautomation.com - " + "https://demosite.executeautomation.com/index.html?UserName=&Password=&Login=login");
        WebElement element = driver.findElement(By.id("FirstName"));
        //Проверка видимости элемента
        boolean isDisplayed = element.isDisplayed();
        logger.info("Is Displayed: " + isDisplayed);
        Assertions.assertTrue(isDisplayed, "Не отображается");
    }
    @Test
    public void isEnabledTest() {
        driver.get("https://demosite.executeautomation.com/index.html?UserName=&Password=&Login=login");
        logger.info("Открыта страница demosite.executeautomation.com - " + "https://demosite.executeautomation.com/index.html?UserName=&Password=&Login=login");
        WebElement element = driver.findElement(By.id("FirstName"));
        //Проверка доступности элемента
        boolean isEnabled = element.isEnabled();
        logger.info("Is Enabled: " + isEnabled);
        Assertions.assertTrue(isEnabled, "Не доступно");
    }
    @Test
    public void isSelectedTest() {
        driver.get("https://demosite.executeautomation.com/index.html?UserName=&Password=&Login=login");
        logger.info("Открыта страница demosite.executeautomation.com - " + "https://demosite.executeautomation.com/index.html?UserName=&Password=&Login=login");
        WebElement element = driver.findElement(By.id("FirstName"));
        //Проверка выбора элемента
        boolean isSelected = element.isSelected();
        logger.info("Is Selected: " + isSelected);
        Assertions.assertTrue(isSelected, "Не выбрано");
    }
    @Test
    public void getTextTest() {
        driver.get("https://demosite.executeautomation.com/index.html?UserName=&Password=&Login=login");
        logger.info("Открыта страница demosite.executeautomation.com - " + "https://demosite.executeautomation.com/index.html?UserName=&Password=&Login=login");
        WebElement element = driver.findElement(By.tagName("h1"));
        //Получение текстового содержимого элемента
        String text = element.getText();
        logger.info("Text: " + text);
        Assertions.assertTrue(text.contains("Selenium"), "Значение text не содержит Selenium");
    }
    @Test
    public void getCssValueTest() {
        driver.get("https://demosite.executeautomation.com/index.html?UserName=&Password=&Login=login");
        logger.info("Открыта страница demosite.executeautomation.com - " + "https://demosite.executeautomation.com/index.html?UserName=&Password=&Login=login");
        WebElement element = driver.findElement(By.id("FirstName"));
        //Получение значения CSS элемента
        String cssValue = element.getCssValue("width");
        logger.info("CSS Value: width = " + cssValue);
        Assertions.assertTrue(cssValue.equals("120px"), "Значение cssValue != 120px");
    }
}
