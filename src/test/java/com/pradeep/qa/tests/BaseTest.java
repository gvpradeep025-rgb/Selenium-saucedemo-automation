package com.pradeep.qa.tests;

import com.pradeep.qa.pages.LoginPage;
import com.pradeep.qa.utils.ConfigReader;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/** Opens a fresh browser before every test and closes it after. Takes a screenshot when a test fails. */
public abstract class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = createDriver(ConfigReader.get("browser"));
        driver.manage().window().maximize();
        driver.get(ConfigReader.get("baseUrl"));
        loginPage = new LoginPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            saveScreenshot(result.getName());
        }
        if (driver != null) {
            driver.quit();
        }
    }

    private WebDriver createDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "firefox":
                return new FirefoxDriver();
            case "edge":
                return new EdgeDriver();
            default:
                ChromeOptions options = new ChromeOptions();
                if (ConfigReader.getBoolean("headless")) {
                    options.addArguments("--headless=new", "--window-size=1920,1080");
                }
                return new ChromeDriver(options);   // Selenium Manager downloads the driver automatically
        }
    }

    private void saveScreenshot(String testName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path target = Paths.get("screenshots", testName + ".png");
            Files.createDirectories(target.getParent());
            Files.copy(src.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            System.out.println("Screenshot failed: " + e.getMessage());
        }
    }
}
