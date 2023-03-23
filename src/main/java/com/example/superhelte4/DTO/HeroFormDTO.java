package com.example.superhelte4.DTO;

import java.util.List;

public class HeroFormDTO {
    private int heroID;

    private String heroName;

    private String realName;

    private int creationYear;

    private String city;

    List<String> powerList;

    public HeroFormDTO(int heroID, String heroName, String realName,

                       int creationYear, String city, List<String> powerList) {

        this.heroID = heroID;

        this.heroName = heroName;

        this.realName = realName;

        this.creationYear = creationYear;

        this.city = city;

        this.powerList = powerList;

    }

    public HeroFormDTO() { // default konstruktør skal laves

    }

    public int getHeroID() {
        return heroID;
    }

    public void setHeroID(int heroID) {
        this.heroID = heroID;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getCreationYear() {
        return creationYear;
    }

    public void setCreationYear(int creationYear) {
        this.creationYear = creationYear;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getPowerList() {
        return powerList;
    }

    public void setPowerList(List<String> powerList) {
        this.powerList = powerList;
    }

    public void addPower(String power) {

        powerList.add(power);

    }
}