package com.example.superhelte4.repositories;

import com.example.superhelte4.DTO.HeroCityDTO;
import com.example.superhelte4.DTO.HeroPowerCountDTO;
import com.example.superhelte4.DTO.HeroPowersDTO;
import com.example.superhelte4.model.SuperHero;

import java.util.List;

public interface ISuperheltRepository {
    List<SuperHero> getAll();

    HeroPowerCountDTO showSpecificHeroPowerCount(String superHeroName);

    HeroPowersDTO showSpecificHeroPowers(String superHeroName);

    HeroCityDTO showSpecificHeroCity(String superHeroName);
}
