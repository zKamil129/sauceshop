package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPageHelper {
    WebDriver driver;

    public ProductPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public String getPriceOnProductPage() {
        return driver.findElement(By.cssSelector("div[class='inventory_details_price']")).getText();
    }

    public void addToCartFromProductPage() {
        driver.findElement(By.cssSelector("button[class='btn_primary btn_inventory']")).click();
    }

    public void openCartPage() {
        driver.findElement(By.cssSelector("svg[data-icon='shopping-cart']")).click();
    }
}
