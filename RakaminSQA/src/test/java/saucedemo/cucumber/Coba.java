package saucedemo.cucumber;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Coba {
    @Test
    public void login(){
        WebDriver driver;

        String baseUrl = "https://www.saucedemo.com/";

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
        driver.close();
    }
}
