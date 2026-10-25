package com.pradeep.qa.pages;

import com.pradeep.qa.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage {

    private final By pageTitle     = By.className("title");
    private final By cartBadge     = By.className("shopping_cart_badge");
    private final By cartIcon      = By.className("shopping_cart_link");
    private final By sortDropdown  = By.className("product_sort_container");
    private final By itemPrices    = By.className("inventory_item_price");
    private final By menuButton    = By.id("react-burger-menu-btn");
    private final By logoutLink    = By.id("logout_sidebar_link");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return getText(pageTitle);
    }

    /** Builds the add-to-cart button id from the product name, e.g. "Sauce Labs Backpack" -> add-to-cart-sauce-labs-backpack */
    public ProductsPage addToCart(String productName) {
        click(By.id("add-to-cart-" + toId(productName)));
        return this;
    }

    public ProductsPage removeFromCart(String productName) {
        click(By.id("remove-" + toId(productName)));
        return this;
    }

    public int getCartCount() {
        return isDisplayed(cartBadge) ? Integer.parseInt(getText(cartBadge)) : 0;
    }

    public ProductsPage sortBy(String visibleText) {
        new Select(waitForVisible(sortDropdown)).selectByVisibleText(visibleText);
        return this;
    }

    public List<Double> getAllPrices() {
        List<Double> prices = new ArrayList<>();
        for (WebElement price : driver.findElements(itemPrices)) {
            prices.add(Double.parseDouble(price.getText().replace("$", "")));
        }
        return prices;
    }

    public CartPage openCart() {
        click(cartIcon);
        return new CartPage(driver);
    }

    public LoginPage logout() {
        click(menuButton);
        click(logoutLink);
        return new LoginPage(driver);
    }

    private String toId(String productName) {
        return productName.toLowerCase().replace(" ", "-");
    }
}
