package com.clay.llama_search.rest;

import com.clay.llama_search.dto.SearchRequest;
import com.clay.llama_search.dto.SearchResponse;
import com.clay.llama_search.service.LlamaSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/search")
public class LlamaSearchController {

    @Autowired
    private LlamaSearchService llamaSearchService;

    /**
     * Search for a query
     *
     * @param searchRequest
     * @return
     */
    @PostMapping
    public SearchResponse search(@RequestBody SearchRequest searchRequest) {
        return llamaSearchService.search(searchRequest.getQuery());
    }
}
