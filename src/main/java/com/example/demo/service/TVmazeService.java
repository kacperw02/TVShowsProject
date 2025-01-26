package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class TVmazeService {

    private final RestTemplate restTemplate;

    public TVmazeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Map<String, Object>> getAllShows() {
        String url = "https://api.tvmaze.com/shows";
        return restTemplate.getForObject(url, List.class);
    }

    public Map<String, Object> getShowDetails(int showId) {
        String url = "https://api.tvmaze.com/shows/" + showId + "?embed[]=cast&embed[]=seasons";
        return restTemplate.getForObject(url, Map.class);
    }

}