package org.example.clients.SearchClient;

import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.example.clients.SearchClient.model.SearchResponse;

public class SearchApiAssertions {

    private final SearchResponse searchResponse;

    public SearchApiAssertions(SearchResponse searchResponse) {
        this.searchResponse = searchResponse;
    }

    public static SearchApiAssertions assertThat(SearchResponse searchResponse) {
        return new SearchApiAssertions(searchResponse);
    }

    @Step("Verify that the server returned a successful, non-empty response")
    public SearchApiAssertions isNotNull() {
        Assertions.assertThat(searchResponse)
                .as("The search API response must not be empty")
                .isNotNull();
        return this;
    }

    @Step("Verify that exactly {expectedSize} products were returned in the response")
    public SearchApiAssertions productsHasSize(int expectedSize) {
        Assertions.assertThat(searchResponse.getProducts())
                .as("List of product suggestions")
                .isNotEmpty()
                .hasSize(expectedSize);
        return this;
    }

    @Step("Verify that each found product contains the text '{text}' (case-insensitive)")
    public SearchApiAssertions isAllProductsContainsTest(String text) {
        Assertions.assertThat(searchResponse.getProducts())
                .as("List of product suggestions")
                .isNotEmpty()
                .allSatisfy(product -> Assertions.assertThat(product).containsIgnoringCase(text));
        return this;
    }
}
