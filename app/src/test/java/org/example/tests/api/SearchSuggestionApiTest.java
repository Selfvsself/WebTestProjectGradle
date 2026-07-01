package org.example.tests.api;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.example.clients.SearchClient.SearchApiClient;
import org.example.clients.SearchClient.model.SearchResponse;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Main page")
@Feature("Search")
public class SearchSuggestionApiTest {
    private final SearchApiClient searchApiClient = new SearchApiClient();
    private static final String SEARCH_VALUE = "Java";
    private static final int EXPECTED_NUMBERS_OF_SUGGESTIONS = 6;

    @Test(description = "The search service returns correct suggestion.")
    @Description("The search service returns correct suggestion.")
    public void testSearchSuggestionApi() {
        SearchResponse response = searchApiClient.searchFor(SEARCH_VALUE);

        assertThat(response)
                .as("The search API response must not be empty")
                .isNotNull();

        assertThat(response.getProducts())
                .as("List of product suggestions")
                .isNotEmpty()
                .hasSize(EXPECTED_NUMBERS_OF_SUGGESTIONS)
                .allSatisfy(product -> assertThat(product).containsIgnoringCase(SEARCH_VALUE));
    }
}
