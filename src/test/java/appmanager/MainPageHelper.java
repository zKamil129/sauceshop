package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPageHelper {
    WebDriver driver;

    public MainPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void openProductPage() {
        driver.findElement(By.cssSelector("div[class='inventory_item_name']")).click();
    }

    public String getFirstItemPrice() {
        return driver.findElement(By.cssSelector("div[class='inventory_item_price']")).getText();
    }

    public void addToCartFirstProduct() {
        driver.findElement(By.xpath("//button[@class='btn_primary btn_inventory']")).click();
    }

    public String itemsInCart() {
        return driver.findElement(By.xpath("//span[@class='fa-layers-counter shopping_cart_badge']")).getText();
    }

    public void goToCart() {
        driver.findElement(By.xpath("//a[@class='shopping_cart_link fa-layers fa-fw']")).click();
    }

}
