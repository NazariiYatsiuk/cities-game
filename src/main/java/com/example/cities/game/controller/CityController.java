package com.example.cities.game.controller;

import com.example.cities.game.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cities-game")
public class CityController {
    private final CityService cityService;

    @GetMapping("/begin")
    public String beginGame() {
        return cityService.beginGame();
    }

    @GetMapping("/next")
    public String nextWord(@RequestParam String city) {
        return cityService.findNextCity(city);
    }

    @PostMapping("/end")
    public String endGame() {
        return "Thank you for the game.";
    }
}
