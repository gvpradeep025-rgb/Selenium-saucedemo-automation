# Selenium SauceDemo Automation

![Selenium Tests](https://github.com/gvpradeep025-rgb/selenium-saucedemo-automation/actions/workflows/tests.yml/badge.svg)

UI test automation for the demo shopping site [saucedemo.com](https://www.saucedemo.com), built with **Selenium WebDriver 4, Java and TestNG** using the **Page Object Model**.

## What it tests

| Area | Test cases |
|------|-----------|
| Login | Valid login · wrong password · unknown user · empty username · empty password · locked-out user (data-driven) · password masked · logout |
| Cart | Add two products · remove product · sort by price low to high |
| Checkout | End-to-end order placement · missing first name shows error |

13 test runs in total (the data-driven login test runs 5 times).

## Tech stack

- Java 11+
- Selenium WebDriver 4 (Selenium Manager downloads the browser driver automatically)
- TestNG (annotations, assertions, DataProvider, groups, HTML report)
- Maven
- GitHub Actions (runs every test on each push, in headless Chrome)

## Project structure

```
src/main/java/com/pradeep/qa
├── base/BasePage.java        # shared actions with explicit waits
├── pages/                    # one class per page (Page Object Model)
│   ├── LoginPage.java
│   ├── ProductsPage.java
│   ├── CartPage.java
│   └── CheckoutPage.java
└── utils/ConfigReader.java   # reads config.properties

src/test/java/com/pradeep/qa/listeners
├── TestListener.java         # PASS/FAIL log and summary
└── RetryAnalyzer.java        # re-runs a flaky test once

src/test/java/com/pradeep/qa/tests
├── BaseTest.java             # opens/closes browser, screenshot on failure
├── LoginTest.java
├── CartTest.java
└── CheckoutTest.java

src/test/resources/config.properties
testng.xml                    # full regression suite
smoke.xml                     # smoke tests only
docs/manual-test-cases.md     # 17 manual test cases
docs/bug-reports.md           # sample bug reports
.github/workflows/tests.yml   # CI pipeline
```

## How to run

You need Java 11+, Maven and Chrome installed.

```bash
mvn clean test                       # run the full suite in Chrome
mvn clean test -Dbrowser=firefox     # run in Firefox
mvn clean test -Dheadless=true       # run without opening a browser window
mvn clean test -Dsuite=smoke.xml     # run only the smoke tests
```

The TestNG report is created at `target/surefire-reports/index.html`. Screenshots of failed tests are saved in `screenshots/`.

## Key concepts used

- **Page Object Model**: locators and actions live in page classes, tests only call methods, so a UI change is fixed in one place.
- **Explicit waits** (`WebDriverWait`) instead of `Thread.sleep`.
- **Data-driven testing** with TestNG `@DataProvider`.
- **Method chaining**: page methods return the next page, e.g. `loginAs(...).addToCart(...).openCart()`.
- **Config file** so the browser and URL can change without touching code.
- **TestNG groups** to run a quick smoke suite separately from full regression.
- **Listeners**: custom PASS/FAIL logging and a retry analyzer for flaky tests.
- **Screenshot on failure**, saved automatically.
- **CI with GitHub Actions**: tests run on every push and the report is uploaded as an artifact.

## Manual testing work

- [Manual test cases](docs/manual-test-cases.md): 17 cases covering positive, negative, UI and security scenarios.
- [Bug reports](docs/bug-reports.md): bugs found in SauceDemo's test users, written with severity, priority and steps.
