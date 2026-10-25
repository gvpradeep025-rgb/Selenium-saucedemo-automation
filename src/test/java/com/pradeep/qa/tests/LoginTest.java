package com.pradeep.qa.tests;

import com.pradeep.qa.pages.ProductsPage;
import com.pradeep.qa.listeners.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class, priority = 1, groups = {"smoke"}, description = "Valid user can log in")
    public void validLoginShowsProductsPage() {
        ProductsPage products = loginPage.loginAs("standard_user", "secret_sauce");
        Assert.assertEquals(products.getTitle(), "Products");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    @DataProvider(name = "invalidLogins")
    public Object[][] invalidLogins() {
        return new Object[][] {
            {"standard_user", "wrong_password", "Username and password do not match"},
            {"unknown_user",  "secret_sauce",   "Username and password do not match"},
            {"",              "secret_sauce",   "Username is required"},
            {"standard_user", "",               "Password is required"},
            {"locked_out_user", "secret_sauce", "Sorry, this user has been locked out"}
        };
    }

    @Test(retryAnalyzer = RetryAnalyzer.class, priority = 2, dataProvider = "invalidLogins", description = "Invalid logins show the right error")
    public void invalidLoginShowsError(String user, String pass, String expectedError) {
        loginPage.loginAs(user, pass);
        Assert.assertTrue(loginPage.getErrorMessage().contains(expectedError),
                "Expected error to contain: " + expectedError);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class, priority = 3, description = "Password field hides the text")
    public void passwordIsMasked() {
        Assert.assertTrue(loginPage.isPasswordMasked());
    }

    @Test(retryAnalyzer = RetryAnalyzer.class, priority = 4, description = "After logout, user returns to login page")
    public void logoutReturnsToLogin() {
        loginPage.loginAs("standard_user", "secret_sauce").logout();
        Assert.assertTrue(driver.getCurrentUrl().endsWith("saucedemo.com/"));
    }
}
