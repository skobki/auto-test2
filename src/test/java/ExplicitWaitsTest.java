import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ExplicitWaitsTest {
    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(ExplicitWaitsTest.class);
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
    public void elementAttributeExpectedConditionTest() {
        //Открыть страницу webdriveruniversity.com
        driver.get("https://webdriveruniversity.com/Accordion/index.html");
        logger.info("Открыта страница webdriveruniversity");
        //Раскрыть гармошку
        //Кликнуть на кнопку [Manual testing]
        By btnManualTestingXpath = By.xpath("//button[text()=\"Manual Testing\"]");
        WebElement btnManualTesting = driver.findElement(btnManualTestingXpath);
        btnManualTesting.click();
        //Подождать, пока не покажется блок с текстом
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        By divManualTestingXpath = By.xpath("//button[text()=\"Manual Testing\"]/following-sibling::div[1]");
        WebElement divManualTesting = driver.findElement(divManualTestingXpath);
        wait.until(ExpectedConditions.attributeToBe(divManualTesting, "style", "max-height: 70px;"));
        logger.info("Гармошка раскрыта");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void elementStateExpectedConditionTest() {
        //Открыть страницу webdriveruniversity
        driver.get("https://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html");
        logger.info("Открыта страница webdriveruniversity");
        //Подождать, пока чекбокс [Option 1] не станет кликабельным
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        By chbOption1Xpath = By.xpath("//label[text()=\"Option 1\"]");
        WebElement chbOption1 = driver.findElement(chbOption1Xpath);
        wait.until(ExpectedConditions.elementToBeClickable(chbOption1));
        //Проставить чекбокс [Option 1]
        chbOption1.click();
        logger.info("Чекбокс проставлен");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void elementsNumberExpectedConditionTest() {
        //Открыть страницу webdriveruniversity
        driver.get("https://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html");
        logger.info("Открыта страница webdriveruniversity");
        //Подождать, пока количество чекбоксов, имеющих текст [Option] не будет равняться 4
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        By chbOptionXpath = By.xpath("//label[contains(text(),\"Option\")]");
        wait.until(ExpectedConditions.numberOfElementsToBe(chbOptionXpath, 4));
        //Подсчитать количество чекбоксов, имеющих текст [Option]
        List<WebElement> chbOption = driver.findElements(chbOptionXpath);
        int count = chbOption.size();
        logger.info("Чекбоксов, имеющих текст [Option]: " + count);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void elementPresenceExpectedConditionTest() {
        // Открыть страницу webdriveruniversity.com
        driver.get("https://webdriveruniversity.com/Autocomplete-TextField/autocomplete-textfield.html");
        logger.info("Открыта страница webdriveruniversity.com - https://webdriveruniversity.com/Autocomplete-TextField/autocomplete-textfield.html");
        // Ввести текст в поле ввода [Food Item]
        By tbxFoodItemXpath = By.xpath("//div[@class=\"autocomplete\"]/input");
        WebElement tbxFoodItem = driver.findElement(tbxFoodItemXpath);
        tbxFoodItem.sendKeys("Ca");
        // Подождать пока отобразятся подсказки
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        By divAutoCompleteXpath = By.xpath("//div/input[@type=\"hidden\"]");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(divAutoCompleteXpath));
        // Вывести в лог содержимое подсказок
        List<WebElement> divAutoComplete = driver.findElements(divAutoCompleteXpath);
        for (WebElement element : divAutoComplete) {
            logger.info("Подсказка: " + element.getAttribute("value"));
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void elementVisibilityExpectedConditionTest() {
        // Открыть страницу webdriveruniversity.com
        driver.get("https://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html");
        logger.info("Открыта страница webdriveruniversity.com - https://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html");
        // Нажать на выпадашку [JAVA]
        By selProgLangsXpath = By.xpath("//h2[text()=\"Dropdown Menu(s)\"]/following-sibling::div/select[1]");
        WebElement selProgLangs = driver.findElement(selProgLangsXpath);
        selProgLangs.click();
        logger.info("Нажата выпадашка [JAVA]");
        // Подождать пока отобразится вариант для выбора [Python] в выпадашке [JAVA]
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        By optJavaXpath = By.xpath("//h2[text()=\"Dropdown Menu(s)\"]/following-sibling::div/select[1]/option[@value=\"python\"]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(optJavaXpath));
        logger.info("Отображается вариант для выбора [Python]");
        // Выбрать вариант для выбора [Python]
        WebElement optJava = driver.findElement(optJavaXpath);
        optJava.click();
        logger.info("Выбран вариант для выбора [Python]");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void elementInvisibilityExpectedConditionTest() {
        // Открыть страницу webdriveruniversity.com
        driver.get("https://webdriveruniversity.com/Hidden-Elements/index.html");
        logger.info("Открыта страница webdriveruniversity.com - https://webdriveruniversity.com/Hidden-Elements/index.html");
        // Подождать что не отобразится элемент [Not Displayed]
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        By divSpanXpath = By.xpath("//div/span[1]");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(divSpanXpath));
        logger.info("Не отображается элемент [Not Displayed]");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void elementTextExpectedConditionTest() {
        // Открыть страницу webdriveruniversity.com
        driver.get("https://webdriveruniversity.com/Accordion/index.html");
        logger.info("Открыта страница webdriveruniversity.com - https://webdriveruniversity.com/Accordion/index.html");
        // Вывести текст в блоке с @id="text-appear-box"
        By divPXpath = By.xpath("//div[@id=\"text-appear-box\"]/p");
        WebElement divP = driver.findElement(divPXpath);
        logger.info("Текст в блоке @id=\"text-appear-box\": " + divP.getText());
        // Подождать пока смениться текст в блоке
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        wait.until(ExpectedConditions.textToBe(divPXpath, "LOADING COMPLETE."));
        // Вывести текст в блоке с @id="text-appear-box"
        logger.info("Текст в блоке @id=\"text-appear-box\": " + divP.getText());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void pageExpectedConditionTest() {
        // Открыть страницу webdriveruniversity.com
        driver.get("https://webdriveruniversity.com/index.html");
        logger.info("Открыта страница webdriveruniversity.com - https://webdriveruniversity.com/index.html");
        // Подождать пока появится заголовок страницы
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        wait.until(ExpectedConditions.titleContains("WebDriverUniversity.com"));
        // Вывести текст в блоке с @id="text-appear-box"
        logger.info("Заголовок страницы: " + driver.getTitle());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void alertExpectedConditionTest() {
        // Открыть страницу webdriveruniversity.com
        driver.get("https://webdriveruniversity.com/Popup-Alerts/index.html");
        logger.info("Открыта страница webdriveruniversity.com - https://webdriveruniversity.com/Popup-Alerts/index.html");
        // Нажать на кнопку [CLICK ME!] в блоке [JavaScript Alert]
        By spanJSAlertXpath = By.xpath("//h2[text()=\"JavaScript Alert\"]/following-sibling::div/span");
        WebElement spanJSAlert = driver.findElement(spanJSAlertXpath);
        spanJSAlert.click();
        logger.info("Нажата кнопка [CLICK ME!] в блоке [JavaScript Alert]");
        // Подождать пока появится алерт
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        wait.until(ExpectedConditions.alertIsPresent());
        logger.info("Алерт отобразился!");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void frameExpectedConditionTest() {
        // Открыть страницу webdriveruniversity.com
        driver.get("https://webdriveruniversity.com/IFrame/index.html");
        logger.info("Открыта страница webdriveruniversity.com - https://webdriveruniversity.com/IFrame/index.html");
        // Подождать доступность фрейма и переключиться на него
        By iframeXpath = By.xpath("//iframe");
        WebElement iframe = driver.findElement(iframeXpath);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
        logger.info("Выполнено переключение на фрейм");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void windowsExpectedConditionTest() {
        // Открыть страницу webdriveruniversity.com
        driver.get("https://webdriveruniversity.com/index.html");
        logger.info("Открыта страница webdriveruniversity.com - https://webdriveruniversity.com/index.html");
        // Подождать когда будет открыто одно окно
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        wait.until(ExpectedConditions.numberOfWindowsToBe(1));
        logger.info("Открыто одно окно");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void jsScriptExpectedConditionTest() {
        // Открыть страницу webdriveruniversity.com
        driver.get("https://webdriveruniversity.com/index.html");
        logger.info("Открыта страница webdriveruniversity.com - https://webdriveruniversity.com/index.html");
        // Подождать выполнения JS скрипта
        String jsScript = "window.setTimeout(arguments[arguments.length - 1], 500);";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        wait.until(ExpectedConditions.javaScriptThrowsNoExceptions(jsScript));
        logger.info("Выполнение JS скрипта успешно");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void logicalExpectedConditionTest() {
        // Открыть страницу webdriveruniversity.com
        driver.get("https://webdriveruniversity.com/IFrame/index.html");
        logger.info("Открыта страница webdriveruniversity.com - https://webdriveruniversity.com/IFrame/index.html");
        // Подождать доступность фрейма и переключиться на него
        By iframeXpath = By.xpath("//iframe");
        WebElement iframe = driver.findElement(iframeXpath);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        wait.until(ExpectedConditions.and(
                ExpectedConditions.numberOfWindowsToBe(1),
                ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe)));
        logger.info("Открыто одно окно и выполнено переключение на фрейм");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
