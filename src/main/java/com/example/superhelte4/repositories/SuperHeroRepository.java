package com.example.superhelte4.repositories;

import com.example.superhelte4.DTO.HeroCityDTO;
import com.example.superhelte4.DTO.HeroPowerCountDTO;
import com.example.superhelte4.DTO.HeroPowersDTO;
import com.example.superhelte4.model.SuperHero;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("SuperHeroDB")
public class SuperHeroRepository implements ISuperheltRepository {
    @Value("${spring.datasource.url}")
    private String db_url;
    @Value("${spring.datasource.username}")
    private String uid;
    @Value("${spring.datasource.password}")
    private String pwd;

    public List<SuperHero> getAll() {
        List<SuperHero> tempSuperHero = new ArrayList<>();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SuperHeroDB", "root", "Tor42Am41")) {
            String SQL = "SELECT * FROM SuperHero;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String heroName = rs.getString("heroName");
                String realName = rs.getString("realName");
                int creationYear = rs.getInt("creationYear");
                tempSuperHero.add(new SuperHero(realName, heroName, heroName, creationYear));
            }
            return tempSuperHero;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public HeroPowerCountDTO showSpecificHeroPowerCount(String superHeroName) {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SuperHeroDB", "root", "Tor42Am41")) {
            String SQL = "SELECT realName, heroName, COUNT(superHeroID) " +
                    "FROM SuperHero " +
                    "LEFT JOIN SuperPowerLinkTable ON id = superHeroID  " +
                    "WHERE heroName = ? " +
                    "GROUP BY id ";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, superHeroName);
            ResultSet rs = ps.executeQuery();

            HeroPowerCountDTO tempSuperHero = null;
            while (rs.next()) {
                String heroName = rs.getString("heroName");
                String realName = rs.getString("realName");
                int count = rs.getInt("count(superHeroID)");
                tempSuperHero = new HeroPowerCountDTO(realName, heroName, count);
            }
            return tempSuperHero;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public HeroPowersDTO showSpecificHeroPowers(String superHeroName) {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SuperHeroDB", "root", "Tor42Am41")) {
            String SQL = "SELECT heroName, realName, superPowerName " +
                    "FROM SuperHero sh " +
                    "LEFT JOIN SuperPowerLinkTable spl ON sh.id = spl.superHeroID " +
                    "LEFT JOIN Superpower sp ON spl.superPowerID = sp.id " +
                    "WHERE heroName = ?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, superHeroName);
            ResultSet rs = ps.executeQuery();

            HeroPowersDTO tempSuperHero = null;
            ArrayList<String> superPowers = new ArrayList<>();
            while (rs.next()) {
                String heroName = rs.getString("heroName");
                String realName = rs.getString("realName");
                superPowers.add(rs.getString("superPowerName"));
                tempSuperHero = new HeroPowersDTO(realName, heroName, superPowers);
            }
            return tempSuperHero;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public HeroCityDTO showSpecificHeroCity(String superHeroName) {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SuperHeroDB", "root", "Tor42Am41")) {
            String SQL = "SELECT SuperHero.heroName, City.cityName " +
                    "FROM SuperHero " +
                    "LEFT JOIN City ON SuperHero.cityID = City.id " +
                    "WHERE heroName = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, superHeroName);
            ResultSet rs = ps.executeQuery();
            HeroCityDTO tempSuperHero = null;
            while (rs.next()) {
                String heroName = rs.getString("heroName");
                String city = rs.getString("cityName");
                tempSuperHero = new HeroCityDTO(heroName, city);
            }
            return tempSuperHero;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
/*
    @Override
    public List<SuperHero> getAllSuperheroes() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SuperHeroDB", "root", "Tor42Am41")) {
            String SQL = "SELECT realName, heroName, creationYear, cityName" +
                    "FROM SuperHero" +
                    "LEFT JOIN City ON SuperHero.cityID = City.id;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            List<SuperHero> superheroes = new ArrayList<>();
            while (rs.next()) {
                String name= rs.getString("realName");
                String heroName = rs.getString("heroName");
                String superheroPower = rs.getString("cityID");
                int creationYear = rs.getInt("creationYear");
                SuperHero superhero = new SuperHero(name, heroName, superheroPower, creationYear);
                superheroes.add(superhero);
            }
            return superheroes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<HeroPowerCountDTO> showHeroPowerCount() {
        List<HeroPowerCountDTO> list = new ArrayList<>();

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SuperHeroDB", "root", "Tor42Am41")) {
            String sql = "SELECT realName, heroName, COUNT(SuperPowerLinkTable.superHeroID) " +
                    "FROM SuperHero " +
                    "LEFT JOIN SuperPowerLinkTable ON id = superHeroID  " +
                    "WHERE heroName = ? " +
                    "GROUP BY id ";
            PreparedStatement psts = con.prepareStatement(sql);
            ResultSet rs = psts.executeQuery();
            String currentDname = "";
            HeroPowerCountDTO currentDto = null;

            while (rs.next()) {
                String realName = rs.getString("realName");
                String heroName = rs.getString("heroName");
                int powerCount = rs.getInt("COUNT(SuperPowerLinkTable.superHeroID)");
                String DNAME = rs.getString("dname");
                HeroPowerCountDTO heroPowerCountDTO = new HeroPowerCountDTO(realName, heroName, powerCount);
                if (DNAME.equals(currentDname))
                    currentDto.(heroPowerCountDTO);
                else {
                    currentDto = new HeroPowerCountDTO(DEPTNO, DNAME, new ArrayList<>(List.of(HeroPowerCountDTO)));
                    currentDname = DNAME;
                }
                list.add(currentDto);
            }
        } catch (SQLException e) {
            System.out.println("Cannot connect to database");
            e.printStackTrace();
        }
        return list;
    }

    public SuperHeroRepository() {
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

    public SuperHero getSuperHero(String superHeroName) {
        SuperHero tempHero = null;
        for (SuperHero s : superheroDatabase) {
            if (s.getSuperheroName().equals(superHeroName))
                tempHero = s;
        }
        return tempHero;
    }

 */

/*

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

    //Samler en gruppe af helte, når man skal gemme resultater. Bruges til når der skal redigeres.

    public ArrayList<model.SuperHero> searchForHeroList(String searchName) {
        model.SuperHero hero;
        findSuperheroList.clear(); //Clear for at når man søger flere gange så gemmes de gamle svar ikke

        for (int n = 0; n < superheroDatabase.size(); n++) {
            hero = superheroDatabase.get(n);
            if (hero.getName().toLowerCase().contains(searchName.toLowerCase())) {
                findSuperheroList.add(hero);

            } else if (hero.getSuperheroName() != null && hero.getSuperheroName().contains(searchName.toLowerCase())) {
                findSuperheroList.add(hero);
            }
        }
        return findSuperheroList;
    }
*/


