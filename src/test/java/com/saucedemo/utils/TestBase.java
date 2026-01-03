package com.saucedemo.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class TestBase {
    protected WebDriver driver;
    protected static final String BASE_URL = "https://www.saucedemo.com/";
    
    @BeforeEach
    public void setUp() {
        driver = WebDriverFactory.getDriver();
        driver.get(BASE_URL);
    }
    
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}