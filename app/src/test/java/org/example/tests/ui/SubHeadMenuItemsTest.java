package org.example.tests.ui;

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

    private static final String TARGET_MENU_ITEM = "Shop";
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
    @Description("The test verifies that clicking the 'Shop' menu " +
            "displays four mandatory resource subheadings to the user.")
    public void subHeadMenuTest() {
        Pages.openMainPage()
                .waitPageLoaded()
                .header()
                .clickOnMenuItem(TARGET_MENU_ITEM)
                .checkHeaders(EXPECTED_SUBMENU_HEADERS);
    }
}
