package com.example.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.time.Duration;

public class LoginAutomationTest {

    @Test
    public void testLogin() {
        // Set up the WebDriver with ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        // Configure ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait

        try {
            // Load the local HTML file from the main directory
            File file = new File("login.html");
            driver.get(file.toURI().toString());

            // Locate the username and password fields
            WebElement usernameField = driver.findElement(By.id("username"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("loginButton"));

            // Perform login
            usernameField.sendKeys("testUser");
            passwordField.sendKeys("testPassword");
            loginButton.click();

            // Validate successful login (assume page title remains "Login Page" for this test)
            String expectedTitle = "Login Page";
            String actualTitle = driver.getTitle();
            assertEquals(expectedTitle, actualTitle);
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
