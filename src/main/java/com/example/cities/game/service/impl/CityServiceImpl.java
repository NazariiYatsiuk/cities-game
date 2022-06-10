package com.example.cities.game.service.impl;

import com.example.cities.game.exception.DataProcessingException;
import com.example.cities.game.model.City;
import com.example.cities.game.repository.CityRepository;
import com.example.cities.game.service.CityService;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private String lastCity;

    @Override
    public City getRandomCity() {
        List<City> allWords = cityRepository.findAll();
        int id = new Random().nextInt(allWords.size());
        return allWords.get(id);
    }

    @Override
    public String beginGame() {
        City randomCity = getRandomCity();
        lastCity = randomCity.getTitle();
        cityRepository.delete(randomCity);
        return randomCity.getTitle();
    }

    @Override
    public String findNextCity(String title) {
        if (lastCity == null) {
            throw new DataProcessingException("You should begin the game first");
        }
        if (!checkIfValid(title)) {
            throw new DataProcessingException("Wrong city title." +
                    " It should start from letter, that previous city ends with.");
        }
        Optional<City> nextWord = cityRepository
                .findTopByTitleIsStartingWith(title.charAt(title.length() - 1));
        if (nextWord.isPresent()) {
            cityRepository.delete(nextWord.get());
            return nextWord.get().getTitle();
        }
        return "Congrats, you won";
    }

    public boolean checkIfValid (String title) {
        return title.toLowerCase()
                .startsWith(String.valueOf(lastCity
                        .charAt(lastCity.length() - 1)));
    }
}
