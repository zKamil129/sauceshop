package tests;

import model.Product;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;

public class SauceDemo extends TestBase {
    WebDriver driver;
    String randomProductPrice = "";
    Product product;
    String insidePrice = "";

    @BeforeClass
    public void login(){
        app.session().login();

    }

    @Test(priority = 1)
    public void testOpenMainPage() {

        System.out.println(app.mainPage().getProducts());
        product = new HashSet<Product>(app.mainPage().getProducts()).iterator().next();
        randomProductPrice = product.getPrice();
        System.out.println("RandomProduct: " + product);
        System.out.println("RandomProductPrice: " + randomProductPrice);
    }

    @Test(priority = 2)
    public void testProductPageInfo() {

        app.mainPage().openProductPage(product);

        insidePrice = app.productPage().getPriceOnProductPage();
        System.out.println("Inside price:" + insidePrice);

        Assert.assertEquals(randomProductPrice, insidePrice);
    }

    @Test(priority = 3)
    public void testAddToCart() {
        app.productPage().addToCartFromProductPage();
        String number = "1";
        String numberCheck = app.cartPage().getNumberOfProductsInCart();
        Assert.assertEquals(numberCheck, number);
    }

    @Test(priority = 4)
    public void testCartPage() {
        app.productPage().openCartPage();
        String CartPrice = app.cartPage().getCartPrice();
        System.out.println("Checkout price: " + CartPrice);
        String noDollarPrice = insidePrice.replace("$", "");
        Assert.assertEquals(noDollarPrice, CartPrice);
    }

    @Test(priority = 5)
    public void testFillCheckoutInput() {
        app.cartPage().checkout();

        app.checkoutPage().fillCheckoutInput();
    }

    @Test(priority = 6)
    public void testCheckPrices() {
        app.checkoutPage().confirmCheckoutInput();

        String finalPrice = app.checkoutPage().getFinalPrice();
        System.out.println(finalPrice);
        Assert.assertEquals(finalPrice, randomProductPrice);
        System.out.println("Final price: OK");

        String itemTotal = app.checkoutPage().getItemTotal();
        String itemTotal2 = itemTotal.split(" ")[2];
        Assert.assertEquals(itemTotal2, randomProductPrice);
        System.out.println("Item total price: OK");

        BigDecimal actualTax = new BigDecimal(randomProductPrice.replaceAll("\\$","")).multiply(new BigDecimal("0.08")).setScale(2, RoundingMode.HALF_UP);
        String expectedTax = "Tax: $" + actualTax;
        String tax = app.checkoutPage().getTax();
        Assert.assertEquals(tax, expectedTax);
        System.out.println("Tax: OK");
        BigDecimal actualTotal = new BigDecimal(randomProductPrice.replaceAll("\\$","")).add(actualTax);
        String expectedTotal = "Total: $" + actualTotal;
        String total = app.checkoutPage().getTotalBrutto();
        Assert.assertEquals(total, expectedTotal);
        System.out.println("Total: OK");
    }

    @Test(priority = 7)
    public void testFinishedOrederInfo() {
        app.checkoutPage().finishOrder();

        String expectedMessage1 = "THANK YOU FOR YOUR ORDER";
        String expectedMessage2 = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";

        String Message1 = app.checkoutPage().getSuccessTitle();

        String Message2 = app.checkoutPage().getSuccessMessage();
        Assert.assertEquals(Message1, expectedMessage1);
        System.out.println("Message1: OK");
        Assert.assertEquals(Message2, expectedMessage2);
        System.out.println("Message2: OK");
    }


}
