import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SauceDemo extends TestBase {
    WebDriver driver;
    String price = "";
    String insidePrice = "";



    @Test(priority = 1)
    public void testOpenMainPage() {
        login();

        price = getFirstItemPrice();

        System.out.println("price:" + price);
    }

    @Test(priority = 2)
    public void testProductPageInfo() {

        openProductPage();

        insidePrice = getPriceOnProductPage();
        System.out.println("Inside price:" + insidePrice);

        Assert.assertEquals(price, insidePrice);
    }

    @Test(priority = 3)
    public void testAddToCart() {
        addToCartFromProductPage();
        String number = "1";
        String numberCheck = getNumberOfProductsInCart();
        Assert.assertEquals(numberCheck, number);
    }

    @Test(priority = 4)
    public void testCartPage() {
        openCartPage();
        String CartPrice = getCartPrice();
        System.out.println("Checkout price: " + CartPrice);
        String noDollarPrice = insidePrice.replace("$", "");
        Assert.assertEquals(noDollarPrice, CartPrice);
    }

    @Test(priority = 5)
    public void testFillCheckoutInput() {
        checkout();

        fillCheckoutInput();
    }

    @Test(priority = 6)
    public void testCheckPrices() {
        confirmCheckoutInput();

        String finalPrice = getFinalPrice();
        System.out.println(finalPrice);
        Assert.assertEquals(finalPrice, price);
        System.out.println("Final price: OK");

        String itemTotal = getItemTotal();
        String itemTotal2 = itemTotal.split(" ")[2];
        Assert.assertEquals(itemTotal2, price);
        System.out.println("Item total price: OK");

        String expectedTax = "Tax: $2.40";
        String tax = getTax();
        Assert.assertEquals(tax, expectedTax);
        System.out.println("Tax: OK");

        String expectedTotal = "Total: $32.39";
        String total = getTotalBrutto();
        Assert.assertEquals(total, expectedTotal);
        System.out.println("Total: OK");
    }

    @Test(priority = 7)
    public void testFinishedOrederInfo() {
        finishOrder();

        String expectedMessage1 = "THANK YOU FOR YOUR ORDER";
        String expectedMessage2 = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";

        String Message1 = getSuccessTitle();

        String Message2 = getSuccessMessage();
        Assert.assertEquals(Message1, expectedMessage1);
        System.out.println("Message1: OK");
        Assert.assertEquals(Message2, expectedMessage2);
        System.out.println("Message2: OK");
    }


}
