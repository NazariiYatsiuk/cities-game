package com.example.cities.game.repository;

import com.example.cities.game.model.City;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findTopByTitleIsStartingWith(char letter);
}
