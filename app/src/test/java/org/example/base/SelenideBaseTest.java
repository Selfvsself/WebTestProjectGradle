package org.example.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public abstract class SelenideBaseTest {
    @BeforeClass
    protected void setupDriverBeforeMethod() {
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.timeout = Long.parseLong(System.getProperty("timeout", "5000"));
        Configuration.pageLoadTimeout = Long.parseLong(System.getProperty("pageLoadTimeout", "15000"));
        Configuration.headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        Configuration.pageLoadStrategy = "eager";

        Configuration.reportsFolder = "build/reports/tests";
        Configuration.screenshots = true;
    }

    @BeforeMethod
    protected void setupThreadLoggersAndDrivers() {
        if (!SelenideLogger.hasListener("AllureSelenide")) {
            SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                    .screenshots(true)
                    .savePageSource(false));
        }
    }

    @AfterMethod(alwaysRun = true)
    protected void logoutAfterMethod() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }
}
