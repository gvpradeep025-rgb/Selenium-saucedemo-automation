package com.pradeep.qa.tests;

import com.pradeep.qa.pages.CheckoutPage;
import com.pradeep.qa.listeners.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"smoke"}, description = "End-to-end: login, add item, checkout, place order")
    public void completeCheckout() {
        CheckoutPage checkout = loginPage.loginAs("standard_user", "secret_sauce")
                .addToCart("Sauce Labs Backpack")
                .openCart()
                .checkout()
                .enterDetails("Test", "User", "560001");

        Assert.assertTrue(checkout.getTotal().startsWith("Total: $"));
        checkout.finish();
        Assert.assertEquals(checkout.getConfirmationMessage(), "Thank you for your order!");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class, description = "Checkout without first name shows an error")
    public void checkoutWithoutFirstNameShowsError() {
        CheckoutPage checkout = loginPage.loginAs("standard_user", "secret_sauce")
                .addToCart("Sauce Labs Backpack")
                .openCart()
                .checkout()
                .enterDetails("", "User", "560001");
        Assert.assertEquals(checkout.getErrorMessage(), "Error: First Name is required");
    }
}
