package org.example.clients.SearchClient;

import io.qameta.allure.Step;
import org.example.clients.BaseApiClient;
import org.example.clients.SearchClient.api.SearchService;
import org.example.clients.SearchClient.model.SearchResponse;

public class SearchApiClient extends BaseApiClient {

    private final SearchService searchService = createClient(
            SearchService.class,
            System.getProperty("search_url", "https://search-api.wiley.com/prod/api/")
    );

    @Step("Send an API request to search by the word: '{query}'")
    public SearchResponse searchFor(String query) {
        return executeCall(searchService.search(query, "suggestion", "en"));
    }
}
