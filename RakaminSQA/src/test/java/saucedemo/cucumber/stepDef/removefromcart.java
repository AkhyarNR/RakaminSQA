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

public class removefromcart {
    WebDriver driver;

    String baseUrl = "https://www.saucedemo.com/";

    @Given("User login and on the dashboard")
    public void userLoginAndOnTheDashboard(){
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

        // select a product
        driver.findElement(By.id("item_4_title_link")).click();
        // click add to cart
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        // check the cart
        String cart = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span")).getText();
        Assert.assertEquals(cart, "1");
    }

    @When("User go to cart")
    public void userGoToCart() {
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
    }

    @And("User remove the product")
    public void userRemoveTheProduct() {
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
    }

    @Then("The product will be removed")
    public void theProductWillBeRemoved() {
        driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]")).isDisplayed();
        driver.close();
    }
}
