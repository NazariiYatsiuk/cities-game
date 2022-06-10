package com.example.cities.game.service;

import com.example.cities.game.exception.DataProcessingException;
import com.example.cities.game.model.City;

public interface CityService {
    City getRandomCity();

    String beginGame();

    String findNextCity(String word) throws DataProcessingException;

}
