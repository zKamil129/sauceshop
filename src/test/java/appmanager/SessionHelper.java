package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper {
    private final WebDriver driver;

    public SessionHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void login() {
        driver.findElement(By.cssSelector("input[data-test='username']")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("input[id='password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("input[id='login-button']")).click();
    }
}
