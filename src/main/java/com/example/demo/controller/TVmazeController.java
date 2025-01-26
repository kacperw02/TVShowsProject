package com.example.demo.controller;

import com.example.demo.service.TVmazeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/shows")
public class TVmazeController {

    private final TVmazeService tvmazeService;

    public TVmazeController(TVmazeService tvmazeService) {
        this.tvmazeService = tvmazeService;
    }

    @GetMapping
    public List<Map<String, Object>> getAllShows() {
        return tvmazeService.getAllShows();
    }
}
