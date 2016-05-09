package com.example.cher.dogsitter;

/**
 * This class models the Pet Info Object
 * Created by leisforkokomo on 5/8/16.
 */
public class PetInfo {
    String name;
    String nickname;
    String age;
    String breed;
    String color;
    String weight;
    String specialNeeds;
    String allergies;
    String medication;
    String injuries;
    String fears;
    String hates;
    String loves;
    String routine;
    String hangouts;
    String hidingSpots;
    String cautions;
    String tricks;

    public PetInfo(String name, String nickname, String age, String breed, String color, String weight, String specialNeeds, String allergies, String medication, String injuries, String fears, String hates, String loves, String routine, String hangouts, String hidingSpots, String cautions, String tricks) {
        this.name = name;
        this.nickname = nickname;
        this.age = age;
        this.breed = breed;
        this.color = color;
        this.weight = weight;
        this.specialNeeds = specialNeeds;
        this.allergies = allergies;
        this.medication = medication;
        this.injuries = injuries;
        this.fears = fears;
        this.hates = hates;
        this.loves = loves;
        this.routine = routine;
        this.hangouts = hangouts;
        this.hidingSpots = hidingSpots;
        this.cautions = cautions;
        this.tricks = tricks;
    }
}
