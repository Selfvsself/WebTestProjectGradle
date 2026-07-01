package org.example.pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;

public final class Pages {
    private Pages() {
    }

    @Step("Open main page")
    public static MainPage openMainPage() {
        open(getMainPageUrl());
        return Selenide.page(MainPage.class);
    }

    public static StudentResourcesPage studentResourcesPage() {
        return Selenide.page(StudentResourcesPage.class);
    }

    public static String getMainPageUrl() {
        return System.getProperty("url", "https://wiley.com");
    }
}
