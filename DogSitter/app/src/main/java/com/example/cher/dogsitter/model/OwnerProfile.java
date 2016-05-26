package com.example.cher.dogsitter.model;

import android.provider.MediaStore;

import java.util.ArrayList;

/**
 * Created by leisforkokomo on 5/11/16.
 */
public class OwnerProfile {
    ArrayList<PetInfo> petInfoPage;
    ArrayList<OwnerInfo> ownerInfoPage;
    ArrayList<House> houseInfoPage;
    ArrayList<Medicine> medicineInfoPage;
    ArrayList<Feed> feedInfoPage;
    ArrayList<Potty> pottyInfoPage;
    ArrayList<Walk> walkInfoPage;
    ArrayList<Groom> groomInfoPage;
    ArrayList<Stuff> stuffInfoPage;
    ArrayList<FirstAid> firstAidInfoPage;

    public OwnerProfile() {
    }

    public OwnerProfile(ArrayList<PetInfo> petInfoPage) { //constructor
        this.petInfoPage = petInfoPage;
    }

    public ArrayList<PetInfo> getPetInfoPage() {
        return petInfoPage;
    }

    public void setPetInfoPage(ArrayList<PetInfo> petInfoPage) {
        this.petInfoPage = petInfoPage;
    }

    public ArrayList<OwnerInfo> getOwnerInfoPage() {
        return ownerInfoPage;
    }

    public void setOwnerInfoPage(ArrayList<OwnerInfo> ownerInfoPage) {
        this.ownerInfoPage = ownerInfoPage;
    }

    public ArrayList<House> getHouseInfoPage() {
        return houseInfoPage;
    }

    public void setHouseInfoPage(ArrayList<House> houseInfoPage) {
        this.houseInfoPage = houseInfoPage;
    }

    public ArrayList<Medicine> getMedicineInfoPage() {
        return medicineInfoPage;
    }

    public void setMedicineInfoPage(ArrayList<Medicine> medicineInfoPage) {
        this.medicineInfoPage = medicineInfoPage;
    }

    public ArrayList<Feed> getFeedInfoPage() {
        return feedInfoPage;
    }

    public void setFeedInfoPage(ArrayList<Feed> feedInfoPage) {
        this.feedInfoPage = feedInfoPage;
    }

    public ArrayList<Potty> getPottyInfoPage() {
        return pottyInfoPage;
    }

    public void setPottyInfoPage(ArrayList<Potty> pottyInfoPage) {
        this.pottyInfoPage = pottyInfoPage;
    }

    public ArrayList<Walk> getWalkInfoPage() {
        return walkInfoPage;
    }

    public void setWalkInfoPage(ArrayList<Walk> walkInfoPage) {
        this.walkInfoPage = walkInfoPage;
    }

    public ArrayList<Groom> getGroomInfoPage() {
        return groomInfoPage;
    }

    public void setGroomInfoPage(ArrayList<Groom> groomInfoPage) {
        this.groomInfoPage = groomInfoPage;
    }

    public ArrayList<Stuff> getStuffInfoPage() {
        return stuffInfoPage;
    }

    public void setStuffInfoPage(ArrayList<Stuff> stuffInfoPage) {
        this.stuffInfoPage = stuffInfoPage;
    }

    public ArrayList<FirstAid> getFirstAidInfoPage() {
        return firstAidInfoPage;
    }

    public void setFirstAidInfoPage(ArrayList<FirstAid> firstAidInfoPage) {
        this.firstAidInfoPage = firstAidInfoPage;
    }
}
