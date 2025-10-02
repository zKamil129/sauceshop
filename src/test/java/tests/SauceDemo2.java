package tests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SauceDemo2 extends TestBase{
    //String cartNumber = "";

    @Test(priority = 1)
    public void testOpenMainPage(){
        app.session().login();
        app.mainPage().openFirstProductPage();
        app.productPage().backToMainPage();
    }

    @Test(priority = 2)
    public void testAddingProducts(){
        app.mainPage().addToCartFirstProduct();
        String cartNumber = "1";
        String realCartNumber = app.mainPage().itemsInCart();
        Assert.assertEquals(cartNumber,realCartNumber);
        app.mainPage().goToCart();
        app.cartPage().continueShopping();

    }

    @Test(priority = 3)
    public void removeProduct(){
        app.mainPage().goToCart();
        app.cartPage().removeProduct();

    }




}
