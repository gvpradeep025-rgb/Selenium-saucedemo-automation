package com.pradeep.qa.tests;

import com.pradeep.qa.pages.CartPage;
import com.pradeep.qa.pages.ProductsPage;
import com.pradeep.qa.listeners.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CartTest extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"smoke"}, description = "Adding two products updates the cart badge and cart page")
    public void addProductsToCart() {
        ProductsPage products = loginPage.loginAs("standard_user", "secret_sauce")
                .addToCart("Sauce Labs Backpack")
                .addToCart("Sauce Labs Bike Light");
        Assert.assertEquals(products.getCartCount(), 2);

        CartPage cart = products.openCart();
        Assert.assertTrue(cart.getItemNames().contains("Sauce Labs Backpack"));
        Assert.assertTrue(cart.getItemNames().contains("Sauce Labs Bike Light"));
    }

    @Test(retryAnalyzer = RetryAnalyzer.class, description = "Removing a product lowers the cart count")
    public void removeProductFromCart() {
        ProductsPage products = loginPage.loginAs("standard_user", "secret_sauce")
                .addToCart("Sauce Labs Backpack")
                .removeFromCart("Sauce Labs Backpack");
        Assert.assertEquals(products.getCartCount(), 0);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class, description = "Sort by price low to high orders the prices correctly")
    public void sortByPriceLowToHigh() {
        List<Double> prices = loginPage.loginAs("standard_user", "secret_sauce")
                .sortBy("Price (low to high)")
                .getAllPrices();
        List<Double> sorted = new ArrayList<>(prices);
        Collections.sort(sorted);
        Assert.assertEquals(prices, sorted);
    }
}
