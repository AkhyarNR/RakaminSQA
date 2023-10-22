package saucedemo.cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class addtocart {
    WebDriver driver;

    String baseUrl = "https://www.saucedemo.com/";
    @Given("User login and on the dashboard for checkout")
    public void userLoginAndOnTheDashboardForCheckout() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl);
        String loginPageAssert = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]")).getText();
        Assert.assertEquals(loginPageAssert, "Swag Labs");

        // input username
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        // input password
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        // click login
        driver.findElement(By.id("login-button")).click();
        // check is user on dashboard page
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]"));
        String product = driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).getText();
        Assert.assertEquals(product, "Sauce Labs Backpack");
    }

    @When("User select a product")
    public void userSelectAProduct() {
        // select a product
        driver.findElement(By.id("item_4_title_link")).click();
    }

    @And("User add to cart")
    public void userAddToCart() {
        // click add to cart
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
    }

    @Then("User check the cart")
    public void userCheckTheCart() {
        // check the cart
        String cart = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span")).getText();
        Assert.assertEquals(cart, "1");
        driver.close();
    }
}
