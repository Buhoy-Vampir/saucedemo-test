package com.saucedemo.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.utils.TestBase;

public class LoginTests extends TestBase {
    
    @Test
    void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        
        loginPage.login("standard_user", "secret_sauce");
        
        assertTrue(productsPage.isPageLoaded());
        assertEquals("Products", productsPage.getPageTitle());
    }
    
    @Test
    void testLoginWithWrongPassword() {
        LoginPage loginPage = new LoginPage(driver);
        
        loginPage.login("standard_user", "wrong_password");
        
        String error = loginPage.getErrorMessage();
        assertEquals("Epic sadface: Username and password do not match any user in this service", error);
    }
    
    @Test
    void testLockedOutUser() {
        LoginPage loginPage = new LoginPage(driver);
        
        loginPage.login("locked_out_user", "secret_sauce");
        
        String error = loginPage.getErrorMessage();
        assertEquals("Epic sadface: Sorry, this user has been locked out.", error);
    }
    
    @Test
    void testLoginWithEmptyFields() {
        LoginPage loginPage = new LoginPage(driver);
        
        loginPage.login("", "");
        
        String error = loginPage.getErrorMessage();
        assertEquals("Epic sadface: Username is required", error);
    }
    
    @Test
    void testPerformanceGlitchUser() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        
        loginPage.login("performance_glitch_user", "secret_sauce");
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        assertTrue(productsPage.isPageLoaded());
    }
}