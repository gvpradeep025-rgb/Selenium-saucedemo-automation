package com.pradeep.qa.pages;

import com.pradeep.qa.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    private final By itemNames      = By.className("inventory_item_name");
    private final By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getItemNames() {
        List<String> names = new ArrayList<>();
        for (WebElement item : driver.findElements(itemNames)) {
            names.add(item.getText());
        }
        return names;
    }

    public CheckoutPage checkout() {
        click(checkoutButton);
        return new CheckoutPage(driver);
    }
}
