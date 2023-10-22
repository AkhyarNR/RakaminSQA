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

public class login {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("User in login page")
    public void userInTheLoginPage() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl);
        String loginPageAssert = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]")).getText();
        Assert.assertEquals(loginPageAssert, "Swag Labs");
    }

    @When("Input username")
    public void inputUsername() {
        // input username
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
    }

    @And("Input password")
    public void inputPassword() {
        // input password
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
    }

    @And("Click login button")
    public void clickLoginButton() {
        // click login
        driver.findElement(By.id("login-button")).click();
    }

    @Then("User redirected to Dashboard")
    public void userRedirectedToDashboard() {
        // check is user on dashboard page
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]"));
        String product = driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).getText();
        Assert.assertEquals(product, "Sauce Labs Backpack");
        driver.close();
    }

    @And("Input invalid password")
    public void inputInvalidPassword() {
        // input invalid password
        driver.findElement(By.id("password")).sendKeys("password");
    }

    @Then("User get error message")
    public void userGetErrorMessage() {
        String message = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        Assert.assertEquals(message, "Epic sadface: Username and password do not match any user in this service");
        driver.close();
    }
}
