import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;

public class sauceDemo {
    WebDriver driver;
    String firstItemPrice = "";

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
    @Test
    public void testSauceShops(){
        driver.findElement(By.cssSelector("input[data-test='username']")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("input[id='password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("input[id='login-button']")).click();

        String price = driver.findElement(By.cssSelector("div[class='inventory_item_price']")).getText();
        System.out.println("price:" + price);

        driver.findElement(By.cssSelector("div[class='inventory_item_name']")).click();
        String insidePrice = driver.findElement(By.cssSelector("div[class='inventory_details_price']")).getText();
        System.out.println("Inside price:" + insidePrice);

        Assert.assertEquals(price, insidePrice);
        System.out.println("OK");

        driver.findElement(By.cssSelector("button[class='btn_primary btn_inventory']")).click();
        String number = "1";
        String numberCheck = driver.findElement(By.cssSelector("span[class='fa-layers-counter shopping_cart_badge']")).getText();
        Assert.assertEquals(numberCheck,number);
        System.out.println("OK");

        driver.findElement(By.cssSelector("svg[data-icon='shopping-cart']")).click();
        String checkoutPrice = driver.findElement(By.cssSelector("div[class='inventory_item_price']")).getText();
        System.out.println("Checkout price: "+checkoutPrice);
        String noDollarPrice = insidePrice.replace("$","");
        Assert.assertEquals(noDollarPrice,checkoutPrice);
        System.out.println("OK");

        driver.findElement(By.cssSelector("a[class='btn_action checkout_button']")).click();
        driver.findElement(By.cssSelector("input[id='first-name']")).sendKeys("imie");
        driver.findElement(By.cssSelector("input[id='last-name']")).sendKeys("nazwisko");
        driver.findElement(By.cssSelector("input[id='postal-code']")).sendKeys("123456");
        driver.findElement(By.cssSelector("input[class='btn_primary cart_button']")).click();

        String finalPrice = driver.findElement(By.cssSelector("div[class='inventory_item_price']")).getText();
        System.out.println(finalPrice);
        Assert.assertEquals(finalPrice,price);
        System.out.println("Final price: OK");

        String itemTotal = driver.findElement(By.cssSelector("div[class='summary_subtotal_label']")).getText();
        String itemTotal2 = itemTotal.split(" ")[2];
        Assert.assertEquals(itemTotal2,price);
        System.out.println("Item total price: OK");

        String expectedTax = "Tax: $2.40";
        String tax = driver.findElement(By.cssSelector("div[class='summary_tax_label']")).getText();
        Assert.assertEquals(tax,expectedTax);
        System.out.println("Tax: OK");

        String expectedTotal = "Total: $32.39";
        String total = driver.findElement(By.cssSelector("div[class='summary_total_label']")).getText();
        Assert.assertEquals(total,expectedTotal);
        System.out.println("Total: OK");

        driver.findElement(By.cssSelector("a[class='btn_action cart_button']")).click();

        String expectedMessage1 = "THANK YOU FOR YOUR ORDER";
        String expectedMessage2 = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";

        String Message1 = driver.findElement(By.cssSelector("h2[class='complete-header']")).getText();
        String Message2 = driver.findElement(By.cssSelector("div[class='complete-text']")).getText();
        Assert.assertEquals(Message1,expectedMessage1);
        System.out.println("Message1: OK");
        Assert.assertEquals(Message2,expectedMessage2);
        System.out.println("Message2: OK");


    }


    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
