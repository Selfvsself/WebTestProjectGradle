package org.example.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.example.base.SelenideBaseTest;
import org.example.pages.Pages;
import org.testng.annotations.Test;

import java.util.List;

@Epic("Main page")
@Feature("Header menu")
public class SubHeadMenuItemsTest extends SelenideBaseTest {

    private static final List<String> EXPECTED_SUBMENU_HEADERS = List.of(
            "Shop by Subject",
            "Textbooks",
            "Professional Books and Resources",
            "Exam Guides",
            "Courseware",
            "Imprints",
            "Book Stores"
    );

    @Test(description = "The submenu for the Shop item contains 7 items.")
    @Description("The test verifies that hovering over or clicking the 'Shop' menu " +
            "displays four mandatory resource subheadings to the user.")
    public void subHeadMenuTest() {
        Pages.openMainPage()
                .waitPageLoaded()
                .header()
                .clickOnMenuItem("Shop")
                .checkHeaders(EXPECTED_SUBMENU_HEADERS);
    }
}
