package com.example.superhelte4.controllers;

import com.example.superhelte4.DTO.HeroFormDTO;
import com.example.superhelte4.DTO.HeroPowersDTO;
import com.example.superhelte4.model.SuperHero;
import com.example.superhelte4.repositories.ISuperheltRepository;
//import org.apache.catalina.core.ApplicationContext;
import com.example.superhelte4.repositories.SuperHeroRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(path = "Superhero")

@Controller
public class SuperHeroController {
    ISuperheltRepository shRepo;
    SuperHeroRepository superHeroRepository = new SuperHeroRepository();
    public SuperHeroController(ApplicationContext context, @Value("${superhero.repository.impl}") String impl){
        shRepo = (ISuperheltRepository) context.getBean(impl);
    }

    @GetMapping("overview")
    public String getAll(Model model) {
        List<SuperHero> list = shRepo.getAll();
        System.out.println(list);
        model.addAttribute("list", list);
        return "allHeroes";
    }
    @GetMapping(path="/powers/{superHeroName}")
    public String showSpecificHeroPowers(@PathVariable String superHeroName, Model model) {
        HeroPowersDTO hero = shRepo.showSpecificHeroPowers(superHeroName);
        model.addAttribute("hero", hero);
        return "showPowers";

    }
    @GetMapping(path="/delete/{superHeroName}")
    public String deleteSpecific(@PathVariable String superHeroName, Model model) {
        HeroPowersDTO hero = shRepo.showSpecificHeroPowers(superHeroName);
        model.addAttribute("hero", hero);
        return "showPowers";

    }

    @GetMapping("createHero")
    public String showForm(Model model) {
        HeroFormDTO  heroFormDTO = new HeroFormDTO();
        model.addAttribute("heroFormDTO", heroFormDTO);

        List<String> listCities = new ArrayList<>(List.of("New York City", "Metropolis", "Gotham City", "Central City"));
        model.addAttribute("listCities", listCities);
        List<String> listPowers = new ArrayList<>(List.of("Flying", "Super strength", "Invisibility", "Rich"));
        model.addAttribute("listPower", listPowers);
        return "addHero";
    }

    @PostMapping("/createHero")
    public String submitForm(@ModelAttribute("heroFormDTO") HeroFormDTO heroFormDTO) {
        System.out.println(heroFormDTO);
        superHeroRepository.addSuperHero(heroFormDTO);
        return "redirect:overview";



    }
/*
    @GetMapping(path = "")
    public ResponseEntity<List<SuperHero>> showAllHeroes() {
        List<SuperHero> heroList = shRepo.getAll();
        return new ResponseEntity<>(heroList, HttpStatus.OK);
    }

    @GetMapping(path="/superpower/count/{superHeroName}")
    public ResponseEntity<HeroPowerCountDTO> showSpecficHeroPowerCount(@PathVariable String superHeroName) {
        HeroPowerCountDTO s = shRepo.showSpecificHeroPowerCount(superHeroName);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }
    @GetMapping(path="/superpower/{superHeroName}")
    public ResponseEntity<HeroPowersDTO> showSpecficHeroPowers(@PathVariable String superHeroName) {
        HeroPowersDTO s = shRepo.showSpecificHeroPowers(superHeroName);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }
    @GetMapping(path="/city/{superHeroName}")
    public ResponseEntity<HeroCityDTO> showSpecificHeroCity(@PathVariable String superHeroName) {
        HeroCityDTO s = shRepo.showSpecificHeroCity(superHeroName);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }


    @GetMapping(path = "/")
    public ResponseEntity<SuperHero> showAllHeroes() {
        SuperHero s = superHeroRepository.getAllSuperheroes();
        return new ResponseEntity<>(s, HttpStatus.OK);
    }


    @GetMapping(path="{superHeroName}")
    public ResponseEntity<SuperHero> showSpecificHero(@PathVariable String superHeroName) {
        SuperHero s = superHeroRepository.getSuperHero(superHeroName);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @GetMapping("delete")
    public ResponseEntity<Void> deleteSuperHero(@RequestParam String superHeroName) {
        superHeroRepository.deleteSuperHero(superHeroName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path="create")
    public ResponseEntity<SuperHero> createSuperHero(@RequestBody SuperHero s){
        superHeroRepository.createSuperHero(s);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @PutMapping(path="edit")
    public ResponseEntity<SuperHero> editSuperHero(@RequestBody SuperHero s){
        superHeroRepository.editSuperHero(s);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }
     */
}
