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
}
