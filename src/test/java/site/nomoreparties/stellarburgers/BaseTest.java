package site.nomoreparties.stellarburgers;

        import com.codeborne.selenide.Configuration;
        import org.junit.Before;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.chrome.ChromeOptions;

        import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class BaseTest {
    ChromeDriver driver;

    @Before
    public void setUp() {
        Configuration config = new Configuration();
        config.pageLoadStrategy = "normal";
        config.startMaximized = true;
    }

    public void setUpYandexDriver() {
        System.setProperty("webdriver.chrome.driver", "/Users/nikita/Documents/webdriver/yandexdriver_108");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
        driver = new ChromeDriver(options);
        setWebDriver(driver);
    }

    public void closeYandexDriver() {
        driver.quit();
    }
}