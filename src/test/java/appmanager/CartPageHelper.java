package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPageHelper {
    WebDriver driver;

    public CartPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public String getNumberOfProductsInCart() {
        return driver.findElement(By.cssSelector("span[class='fa-layers-counter shopping_cart_badge']")).getText();
    }

    public void checkout() {
        driver.findElement(By.cssSelector("a[class='btn_action checkout_button']")).click();
    }

    public String getCartPrice() {
        return driver.findElement(By.cssSelector("div[class='inventory_item_price']")).getText();
    }

    public void continueShopping(){
        driver.findElement(By.xpath("//a[@class='btn_secondary']")).click();
    }

    public void removeProduct(){
        driver.findElement(By.xpath("//button[@class='btn_secondary cart_button']")).click();
    }
}
