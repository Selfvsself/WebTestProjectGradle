package org.example.tests.api;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.example.clients.SearchClient.SearchApiAssertions;
import org.example.clients.SearchClient.SearchApiClient;
import org.example.clients.SearchClient.model.SearchResponse;
import org.testng.annotations.Test;

@Epic("Backend Services")
@Feature("Search functionality")
public class SearchSuggestionApiTest {
    private final SearchApiClient searchApiClient = new SearchApiClient();
    private static final String SEARCH_VALUE = "Java";
    private static final int EXPECTED_NUMBERS_OF_SUGGESTIONS = 6;

    @Test(description = "Verify autocomplete search suggestions for products")
    @Description("The test validates the search autocomplete API by sending a partial product name. " +
            "It verifies that the service returns a structured response with the correct number of items, " +
            "and checks that every suggested product matches the requested search term (case-insensitive)")
    public void testSearchSuggestionApi() {
        SearchResponse response = searchApiClient.searchFor(SEARCH_VALUE);

        SearchApiAssertions.assertThat(response)
                .isNotNull()
                .productsHasSize(EXPECTED_NUMBERS_OF_SUGGESTIONS)
                .isAllProductsContainsTest(SEARCH_VALUE);
    }
}
