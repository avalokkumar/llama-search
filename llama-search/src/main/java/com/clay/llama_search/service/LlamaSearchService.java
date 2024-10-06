package com.clay.llama_search.service;

import com.clay.llama_search.dto.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LlamaSearchService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String LLAMA_SEARCH_URL = "http://localhost:8000/search";

    public SearchResponse search(String query) {
        String fullUrl = LLAMA_SEARCH_URL + "?query=" + query;
        return restTemplate.postForObject(fullUrl, null, SearchResponse.class);
    }
}
