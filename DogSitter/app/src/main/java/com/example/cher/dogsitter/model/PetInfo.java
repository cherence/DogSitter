package com.example.cher.dogsitter.model;

/**
 * This class models the Pet Info Object
 * Created by leisforkokomo on 5/8/16.
 */
public class PetInfo {
    private int petPhoto;
    private String name;
    private String nickname;
    private String age;
    private String weight;
    private String breed;
    private String color;
    private String specialNeeds;
    private String allergies;
    private String medication;
    private String injuries;
    private String fears;
    private String hates;
    private String loves;
    private String tricks;
    private String routine;
    private String hidingSpots;


    public PetInfo() {
    }

    public PetInfo(int petPhoto, String name, String nickname, String age, String weight, String breed, String color, String specialNeeds, String allergies, String medication, String injuries, String fears, String hates, String loves, String tricks, String routine, String hidingSpots) {
        this.petPhoto = petPhoto;
        this.name = name;
        this.nickname = nickname;
        this.age = age;
        this.weight = weight;
        this.breed = breed;
        this.color = color;
        this.specialNeeds = specialNeeds;
        this.allergies = allergies;
        this.medication = medication;
        this.injuries = injuries;
        this.fears = fears;
        this.hates = hates;
        this.loves = loves;
        this.tricks = tricks;
        this.routine = routine;
        this.hidingSpots = hidingSpots;
    }

    public int getPetPhoto() {
        return petPhoto;
    }

    public void setPetPhoto(int petPhoto) {
        this.petPhoto = petPhoto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSpecialNeeds() {
        return specialNeeds;
    }

    public void setSpecialNeeds(String specialNeeds) {
        this.specialNeeds = specialNeeds;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getInjuries() {
        return injuries;
    }

    public void setInjuries(String injuries) {
        this.injuries = injuries;
    }

    public String getFears() {
        return fears;
    }

    public void setFears(String fears) {
        this.fears = fears;
    }

    public String getHates() {
        return hates;
    }

    public void setHates(String hates) {
        this.hates = hates;
    }

    public String getLoves() {
        return loves;
    }

    public void setLoves(String loves) {
        this.loves = loves;
    }

    public String getTricks() {
        return tricks;
    }

    public void setTricks(String tricks) {
        this.tricks = tricks;
    }

    public String getRoutine() {
        return routine;
    }

    public void setRoutine(String routine) {
        this.routine = routine;
    }

    public String getHidingSpots() {
        return hidingSpots;
    }

    public void setHidingSpots(String hidingSpots) {
        this.hidingSpots = hidingSpots;
    }
}

