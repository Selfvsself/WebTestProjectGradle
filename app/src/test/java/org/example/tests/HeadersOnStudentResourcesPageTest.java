package org.example.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.example.base.SelenideBaseTest;
import org.example.pages.Pages;
import org.testng.annotations.Test;

import java.util.List;

@Epic("Student Resources")
@Feature("Headers")
public class HeadersOnStudentResourcesPageTest extends SelenideBaseTest {
    private static final String TARGET_MENU_ITEM = "Teach & Grow";
    private static final String TARGET_SUBMENU_ITEM = "Student resources";
    private static final List<String> EXPECTED_HEADERS = List.of(
            "Textbooks",
            "WileyPLUS",
            "Knewton Alta",
            "zyBooks",
            "Digital Tools",
            "Exam Guides"
    );

    @Test(description = "The Student Resources page contains the correct headers.")
    @Description("Check that every card’s title contains the searched word.")
    public void testHeadersOnStudentResourcesPage() {
        Pages.openMainPage()
                .waitPageLoaded()
                .header()
                .clickOnMenuItem(TARGET_MENU_ITEM)
                .clickOnHeader(TARGET_SUBMENU_ITEM);

        Pages.studentResourcesPage()
                .waitPageLoaded()
                .checkHeaders(EXPECTED_HEADERS);
    }
}
