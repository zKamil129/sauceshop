package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPageHelper {
    WebDriver driver;

    public CheckoutPageHelper(WebDriver driver) {
        this.driver = driver;
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
}
