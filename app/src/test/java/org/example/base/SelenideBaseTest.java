package org.example.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.*;

public abstract class SelenideBaseTest {
    @BeforeSuite(alwaysRun = true)
    protected void globalSetup() {
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.timeout = Long.parseLong(System.getProperty("timeout", "10000"));
        Configuration.pageLoadTimeout = Long.parseLong(System.getProperty("pageLoadTimeout", "15000"));
        Configuration.headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        Configuration.pageLoadStrategy = "eager";
        Configuration.reportsFolder = "build/reports/tests";
        Configuration.screenshots = true;
    }

    @BeforeMethod(alwaysRun = true)
    protected void setupThreadLoggersAndDrivers() {
        if (!SelenideLogger.hasListener("AllureSelenide")) {
            SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                    .screenshots(true)
                    .savePageSource(false));
        }
    }

    @AfterMethod(alwaysRun = true)
    protected void tearDown() {
        Selenide.closeWebDriver();
    }
}
