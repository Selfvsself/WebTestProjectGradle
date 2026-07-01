package org.example.clients.SearchClient.api;

import org.example.clients.SearchClient.model.SearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchService {

    @GET("v1/search/product")
    Call<SearchResponse> search(
            @Query("q") String query,
            @Query("type") String type,
            @Query("locale") String locale
    );
}
