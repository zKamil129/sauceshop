package appmanager;

import model.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class MainPageHelper {
    WebDriver driver;

    public MainPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void openFirstProductPage() {
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

    public List<Product> getProducts() {
        List<Product>  product = new ArrayList<>();
        List<WebElement> elements = driver.findElements(By.xpath("//div[contains(@class,'inventory_list')]/div[contains(@class,'inventory_item')]"));
        for(WebElement element :elements){
            String name = element.findElement(By.xpath(".//div[contains(@class,'inventory_item_name')]")).getText();
            String description = element.findElement(By.xpath(".//div[contains(@class,'inventory_item_desc')]")).getText();
            String price = element.findElement(By.xpath(".//div[contains(@class,'inventory_item_price')]")).getText();

            product.add(new Product().withName(name).withDescription(description).withPrice(price));

    }
        return product;
    }

    public void openProductPage(Product product) {
        driver.findElement(By.xpath("//div[contains(@class,'inventory_list')]/div[contains(@class,'inventory_item')]//div[contains(@class,'inventory_item_name') and contains(text(),'"+product.getName()+"')]")).click();
    }
}
