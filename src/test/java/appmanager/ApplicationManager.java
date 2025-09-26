package appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.HashMap;

public class ApplicationManager {
    WebDriver driver;
    SessionHelper sessionHelper;
    MainPageHelper mainPageHelper;
    ProductPageHelper productPageHelper;
    CartPageHelper cartPageHelper;
    CheckoutPageHelper checkoutPageHelper;

    public void init() {
        //avoid Chrome Unnecessary Alerts
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.password_manager_leak_detection", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        chromePrefs.put("credentials_enable_service", false);
        options.setAcceptInsecureCerts(true);
        options.setExperimentalOption("prefs", chromePrefs);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://www.saucedemo.com/v1/");
        sessionHelper = new SessionHelper(driver);
        mainPageHelper = new MainPageHelper(driver);
        productPageHelper = new ProductPageHelper(driver);
        cartPageHelper = new CartPageHelper(driver);
        checkoutPageHelper = new CheckoutPageHelper(driver);
    }


    public void stop() {
        driver.quit();
    }

    public SessionHelper session() {
        return sessionHelper;
    }

    public MainPageHelper mainPage() {
        return mainPageHelper;
    }

    public ProductPageHelper productPage() {
        return productPageHelper;
    }

    public CartPageHelper cartPage() {
        return cartPageHelper;
    }

    public CheckoutPageHelper checkoutPage() {
        return checkoutPageHelper;
    }
}
