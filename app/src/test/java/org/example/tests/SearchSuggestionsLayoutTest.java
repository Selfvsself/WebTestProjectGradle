package org.example.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.example.base.SelenideBaseTest;
import org.example.pages.Pages;
import org.testng.annotations.Test;

@Epic("Main page")
@Feature("Search")
public class SearchSuggestionsLayoutTest extends SelenideBaseTest {
    private static final String SEARCH_VALUE = "Java";

    @Test(description = "Search drop down area is positioned horizontally relative to the search header")
    @Description("The search hint is positioned horizontally relative to the search input field.")
    public void testSearchSuggestionsHorizontalLayout() {
        Pages.openMainPage()
                .waitPageLoaded()
                .header()
                .inputInSearch(SEARCH_VALUE)
                .verifySuggestionsAreHorizontallyAlignedWithSearch();
    }
}
