# Manual Test Cases: SauceDemo

Application: https://www.saucedemo.com · Test data: `standard_user` / `secret_sauce`

| ID | Module | Scenario | Steps | Expected result | Type | Automated |
|----|--------|----------|-------|-----------------|------|-----------|
| TC_LOGIN_01 | Login | Valid login | Enter standard_user / secret_sauce, click Login | Products page opens | Positive | Yes |
| TC_LOGIN_02 | Login | Wrong password | Enter standard_user / wrong_password, click Login | Error: "Username and password do not match any user in this service" | Negative | Yes |
| TC_LOGIN_03 | Login | Unregistered user | Enter unknown_user / secret_sauce, click Login | Same mismatch error | Negative | Yes |
| TC_LOGIN_04 | Login | Empty username | Leave username empty, enter password, click Login | Error: "Username is required" | Negative | Yes |
| TC_LOGIN_05 | Login | Empty password | Enter username, leave password empty, click Login | Error: "Password is required" | Negative | Yes |
| TC_LOGIN_06 | Login | Locked user | Enter locked_out_user / secret_sauce | Error: "Sorry, this user has been locked out." | Negative | Yes |
| TC_LOGIN_07 | Login | Password masked | Type in password field | Characters shown as dots | UI | Yes |
| TC_LOGIN_08 | Login | Logout | Login, open menu, click Logout | Login page shown | Positive | Yes |
| TC_LOGIN_09 | Login | Back button after logout | Logout, press browser Back | Products page must not open without login | Security | No |
| TC_CART_01 | Cart | Add two products | Add Backpack and Bike Light | Cart badge shows 2, both items in cart | Positive | Yes |
| TC_CART_02 | Cart | Remove product | Add Backpack, click Remove | Cart badge disappears | Positive | Yes |
| TC_CART_03 | Products | Sort low to high | Choose "Price (low to high)" | Prices appear in ascending order | Positive | Yes |
| TC_CART_04 | Cart | Cart kept after refresh | Add item, refresh page | Item still in cart | Positive | No |
| TC_CHK_01 | Checkout | Complete order | Add item → Cart → Checkout → fill details → Continue → Finish | "Thank you for your order!" | End-to-end | Yes |
| TC_CHK_02 | Checkout | Missing first name | Leave first name empty, click Continue | Error: "First Name is required" | Negative | Yes |
| TC_CHK_03 | Checkout | Missing postal code | Leave postal code empty, click Continue | Error: "Postal Code is required" | Negative | No |
| TC_CHK_04 | Checkout | Total is correct | Add 2 items, reach overview | Item total + tax = Total | Positive | No |
