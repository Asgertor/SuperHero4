package com.example.superhelte4.repositories;

import com.example.superhelte4.DTO.HeroCityDTO;
import com.example.superhelte4.DTO.HeroPowerCountDTO;
import com.example.superhelte4.DTO.HeroPowersDTO;
import com.example.superhelte4.model.SuperHero;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
@Repository("SuperheroStub")
public class SuperHeroStubRepository implements ISuperheltRepository {
    ArrayList<SuperHero> superheroDatabase;
    public SuperHeroStubRepository() {
        this.superheroDatabase = new ArrayList<>();
        superheroDatabase.add(new SuperHero("Clark Kent", "Superman", "Flight and super strength", 1938));
        superheroDatabase.add(new SuperHero("Bruce Wayne", "Batman", "Martial arts and gadgets", 1939));
        superheroDatabase.add(new SuperHero("Diana Prince", "Wonder Woman", "Superhuman strength and agility", 1941));
        superheroDatabase.add(new SuperHero("Peter Parker", "Spider-Man", "Wall-crawling and spider-sense", 1962));
        superheroDatabase.add(new SuperHero("Tony Stark", "Iron Man", "High-tech suit and genius intellect", 1963));
        superheroDatabase.add(new SuperHero("Steve Rogers", "Captain America", "Superhuman strength and agility", 1941));
        superheroDatabase.add(new SuperHero("Natasha Romanoff", "Black Widow", "Martial arts and espionage", 1964));
        superheroDatabase.add(new SuperHero("T'Challa", "Black Panther", "Superhuman strength and agility", 1966));
        superheroDatabase.add(new SuperHero("Carol Danvers", "Captain Marvel", "Energy projection and flight", 1967));
        superheroDatabase.add(new SuperHero("Bruce Banner", "The Hulk", "Superhuman strength and regeneration", 1962));
    }

    public ArrayList<SuperHero> getHeroDatabase() {
        return superheroDatabase;
    }

    @Override
    public List<SuperHero> getAll() {
        return getHeroDatabase();
    }

    @Override
    public HeroPowerCountDTO showSpecificHeroPowerCount(String superHeroName) {
        return null;
    }

    @Override
    public HeroPowersDTO showSpecificHeroPowers(String superHeroName) {
        return null;
    }

    @Override
    public HeroCityDTO showSpecificHeroCity(String superHeroName) {
        return null;
    }
    /*

    public SuperHero getSuperHero(String superHeroName) {
        SuperHero tempHero = null;
        for (SuperHero s : superheroDatabase) {
            if (s.getSuperheroName().equals(superHeroName))
                tempHero = s;
        }
        return tempHero;
    }

    public void deleteSuperHero(String superHeroName) {
        for (SuperHero s : superheroDatabase) {
            if (s.getSuperheroName().equals(superHeroName)) {
                superheroDatabase.remove(s);
            }
        }
    }
    public void createSuperHero(SuperHero s) {
        superheroDatabase.add(s);
    }
    public void editSuperHero(SuperHero newHero) {
        for (SuperHero s : superheroDatabase) {
            if (s.getSuperheroName().equals(newHero.getSuperheroName())) {
                superheroDatabase.remove(s);
                superheroDatabase.add(newHero);
            }
        }
    }


     */
}
