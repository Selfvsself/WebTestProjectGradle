package org.example.clients.SearchClient.model;

import lombok.Data;

import java.util.List;

@Data
public class SearchResponse {
    private String status;
    private String searchString;
    private String type;
    private List<String> products;
}
