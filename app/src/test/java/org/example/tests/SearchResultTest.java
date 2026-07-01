package org.example.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.example.base.SelenideBaseTest;
import org.example.pages.Pages;
import org.testng.annotations.Test;

@Epic("Main page")
@Feature("Search")
public class SearchResultTest extends SelenideBaseTest {
    private static final String SEARCH_VALUE = "Java";
    private static final int EXPECTED_NUMBERS_OF_CARDS = 10;

    @Test(description = "Verification of the content of all search cards")
    @Description("Check that every card’s title contains the searched word.")
    public void testSearchResults() {
        Pages.openMainPage()
                .waitPageLoaded()
                .header()
                .inputInSearch(SEARCH_VALUE)
                .pressEnterInSearch()
                .waitPageLoaded()
                .checkNumberOfCards(EXPECTED_NUMBERS_OF_CARDS)
                .performForAllCards(card -> card.checkTitleContain(SEARCH_VALUE));
    }
}
