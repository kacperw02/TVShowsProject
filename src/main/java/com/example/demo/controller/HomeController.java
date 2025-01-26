package com.example.demo.controller;

import com.example.demo.service.TVmazeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final TVmazeService tvmazeService;

    // Dostępne gatunki do wyboru
    private static final List<String> STATIC_GENRES = Arrays.asList(
            "Action", "Adventure", "Anime", "Comedy", "Crime", "Drama",
            "Family", "Fantasy", "History", "Horror", "Romance",
            "Science-Fiction", "Sports", "Thriller", "Travel", "War", "Western"
    );

    public HomeController(TVmazeService tvmazeService) {
        this.tvmazeService = tvmazeService;
    }

    @GetMapping("/")
    public String showHomePage(@RequestParam(required = false) String genre,
                               @RequestParam(required = false) String name,
                               Model model) {
        List<Map<String, Object>> allShows = tvmazeService.getAllShows();

        // Filtrowanie seriali po gatunku (jeśli wybrano)
        if (genre != null && !genre.isEmpty()) {
            allShows = allShows.stream()
                    .filter(show -> ((List<String>) show.get("genres")).contains(genre))
                    .collect(Collectors.toList());
        }

        // Filtrowanie seriali po nazwie (jeśli podano)
        if (name != null && !name.isEmpty()) {
            allShows = allShows.stream()
                    .filter(show -> show.get("name").toString().toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());
        }

        // Sprawdzenie, czy znaleziono jakiekolwiek seriale
        boolean showsNotFound = allShows.isEmpty();

        model.addAttribute("shows", allShows.stream().limit(20).collect(Collectors.toList()));
        model.addAttribute("genres", STATIC_GENRES);
        model.addAttribute("selectedGenre", genre);
        model.addAttribute("searchedName", name);
        model.addAttribute("showsNotFound", showsNotFound);
        return "home";
    }

    @GetMapping("/details")
    public String showDetails(@RequestParam int id, Model model) {
        Map<String, Object> showDetails = tvmazeService.getShowDetails(id);
        model.addAttribute("show", showDetails);
        return "details";
    }

}
