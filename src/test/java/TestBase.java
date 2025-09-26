import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.HashMap;

public class TestBase {
    WebDriver driver;
    @BeforeClass
    public void setup() {
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
    }

    public void openProductPage() {
        driver.findElement(By.cssSelector("div[class='inventory_item_name']")).click();
    }

    public void login() {
        driver.findElement(By.cssSelector("input[data-test='username']")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("input[id='password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("input[id='login-button']")).click();
    }

    public String getFirstItemPrice() {
        return driver.findElement(By.cssSelector("div[class='inventory_item_price']")).getText();
    }

    public String getPriceOnProductPage() {
        return driver.findElement(By.cssSelector("div[class='inventory_details_price']")).getText();
    }

    public void addToCartFromProductPage() {
        driver.findElement(By.cssSelector("button[class='btn_primary btn_inventory']")).click();
    }

    public String getNumberOfProductsInCart() {
        return driver.findElement(By.cssSelector("span[class='fa-layers-counter shopping_cart_badge']")).getText();
    }

    public void checkout() {
        driver.findElement(By.cssSelector("a[class='btn_action checkout_button']")).click();
    }

    public void fillCheckoutInput() {
        driver.findElement(By.cssSelector("input[id='first-name']")).sendKeys("imie");
        driver.findElement(By.cssSelector("input[id='last-name']")).sendKeys("nazwisko");
        driver.findElement(By.cssSelector("input[id='postal-code']")).sendKeys("123456");
    }

    public void confirmCheckoutInput() {
        driver.findElement(By.cssSelector("input[class='btn_primary cart_button']")).click();
    }

    public String getFinalPrice() {
        return driver.findElement(By.cssSelector("div[class='inventory_item_price']")).getText();
    }

    public String getItemTotal() {
        return driver.findElement(By.cssSelector("div[class='summary_subtotal_label']")).getText();
    }

    public String getTax() {
        return driver.findElement(By.cssSelector("div[class='summary_tax_label']")).getText();
    }

    //final price + tax
    public String getTotalBrutto() {
        return driver.findElement(By.cssSelector("div[class='summary_total_label']")).getText();
    }

    public void finishOrder() {
        driver.findElement(By.cssSelector("a[class='btn_action cart_button']")).click();
    }

    public String getSuccessTitle() {
        return driver.findElement(By.cssSelector("h2[class='complete-header']")).getText();
    }

    public String getSuccessMessage() {
        return driver.findElement(By.cssSelector("div[class='complete-text']")).getText();
    }
    public void openCartPage(){
        driver.findElement(By.cssSelector("svg[data-icon='shopping-cart']")).click();
    }
    public String getCartPrice(){
        return driver.findElement(By.cssSelector("div[class='inventory_item_price']")).getText();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
