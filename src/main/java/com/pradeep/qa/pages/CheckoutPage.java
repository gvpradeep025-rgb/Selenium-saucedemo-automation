package com.pradeep.qa.pages;

import com.pradeep.qa.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    private final By firstName      = By.id("first-name");
    private final By lastName       = By.id("last-name");
    private final By postalCode     = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By finishButton   = By.id("finish");
    private final By errorMessage   = By.cssSelector("[data-test='error']");
    private final By completeHeader = By.className("complete-header");
    private final By totalLabel     = By.className("summary_total_label");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage enterDetails(String first, String last, String zip) {
        type(firstName, first);
        type(lastName, last);
        type(postalCode, zip);
        click(continueButton);
        return this;
    }

    public String getTotal() {
        return getText(totalLabel);
    }

    public CheckoutPage finish() {
        click(finishButton);
        return this;
    }

    public String getConfirmationMessage() {
        return getText(completeHeader);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }
}
