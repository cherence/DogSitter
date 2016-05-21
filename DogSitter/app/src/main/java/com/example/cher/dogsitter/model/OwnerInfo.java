package com.example.cher.dogsitter.model;

/**
 * Created by leisforkokomo on 5/11/16.
 */
public class OwnerInfo {
    private String name;
    private String cell1;
    private String cell2;
    private String addyStreet;
    private String addyCity;
    private String addyState;
    private String addyZip;

    private String nameEc;
    private String phoneEc;
    private String workPhoneEc;
    private String addyStreetEc;
    private String addyCityEc;
    private String addyStateEc;
    private String addyZipEc;

    private String tripStartDate;
    private String tripStartTime;
    private String tripEndDate;
    private String tripEndTime;
    private String tripDestination;
    private String tripStayName;
    private String tripStayAddy;
    private String tripStayPhone;
    private String tripNotes;

    public OwnerInfo(String name, String cell1, String cell2, String addyStreet, String addyCity,
                     String addyState, String addyZip, String nameEc, String phoneEc,
                     String workPhoneEc, String addyStreetEc, String addyCityEc, String addyStateEc,
                     String addyZipEc, String tripStartDate, String tripStartTime,
                     String tripEndDate, String tripEndTime, String tripDestination,
                     String tripStayName, String tripStayAddy, String tripStayPhone,
                     String tripNotes) {
        this.name = name;
        this.cell1 = cell1;
        this.cell2 = cell2;
        this.addyStreet = addyStreet;
        this.addyCity = addyCity;
        this.addyState = addyState;
        this.addyZip = addyZip;
        this.nameEc = nameEc;
        this.phoneEc = phoneEc;
        this.workPhoneEc = workPhoneEc;
        this.addyStreetEc = addyStreetEc;
        this.addyCityEc = addyCityEc;
        this.addyStateEc = addyStateEc;
        this.addyZipEc = addyZipEc;
        this.tripStartDate = tripStartDate;
        this.tripStartTime = tripStartTime;
        this.tripEndDate = tripEndDate;
        this.tripEndTime = tripEndTime;
        this.tripDestination = tripDestination;
        this.tripStayName = tripStayName;
        this.tripStayAddy = tripStayAddy;
        this.tripStayPhone = tripStayPhone;
        this.tripNotes = tripNotes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCell1() {
        return cell1;
    }

    public void setCell1(String cell1) {
        this.cell1 = cell1;
    }

    public String getCell2() {
        return cell2;
    }

    public void setCell2(String cell2) {
        this.cell2 = cell2;
    }

    public String getAddyStreet() {
        return addyStreet;
    }

    public void setAddyStreet(String addyStreet) {
        this.addyStreet = addyStreet;
    }

    public String getAddyCity() {
        return addyCity;
    }

    public void setAddyCity(String addyCity) {
        this.addyCity = addyCity;
    }

    public String getAddyState() {
        return addyState;
    }

    public void setAddyState(String addyState) {
        this.addyState = addyState;
    }

    public String getAddyZip() {
        return addyZip;
    }

    public void setAddyZip(String addyZip) {
        this.addyZip = addyZip;
    }

    public String getNameEc() {
        return nameEc;
    }

    public void setNameEc(String nameEc) {
        this.nameEc = nameEc;
    }

    public String getPhoneEc() {
        return phoneEc;
    }

    public void setPhoneEc(String phoneEc) {
        this.phoneEc = phoneEc;
    }

    public String getWorkPhoneEc() {
        return workPhoneEc;
    }

    public void setWorkPhoneEc(String workPhoneEc) {
        this.workPhoneEc = workPhoneEc;
    }

    public String getAddyStreetEc() {
        return addyStreetEc;
    }

    public void setAddyStreetEc(String addyStreetEc) {
        this.addyStreetEc = addyStreetEc;
    }

    public String getAddyCityEc() {
        return addyCityEc;
    }

    public void setAddyCityEc(String addyCityEc) {
        this.addyCityEc = addyCityEc;
    }

    public String getAddyStateEc() {
        return addyStateEc;
    }

    public void setAddyStateEc(String addyStateEc) {
        this.addyStateEc = addyStateEc;
    }

    public String getAddyZipEc() {
        return addyZipEc;
    }

    public void setAddyZipEc(String addyZipEc) {
        this.addyZipEc = addyZipEc;
    }

    public String getTripStartDate() {
        return tripStartDate;
    }

    public void setTripStartDate(String tripStartDate) {
        this.tripStartDate = tripStartDate;
    }

    public String getTripStartTime() {
        return tripStartTime;
    }

    public void setTripStartTime(String tripStartTime) {
        this.tripStartTime = tripStartTime;
    }

    public String getTripEndDate() {
        return tripEndDate;
    }

    public void setTripEndDate(String tripEndDate) {
        this.tripEndDate = tripEndDate;
    }

    public String getTripEndTime() {
        return tripEndTime;
    }

    public void setTripEndTime(String tripEndTime) {
        this.tripEndTime = tripEndTime;
    }

    public String getTripDestination() {
        return tripDestination;
    }

    public void setTripDestination(String tripDestination) {
        this.tripDestination = tripDestination;
    }

    public String getTripStayName() {
        return tripStayName;
    }

    public void setTripStayName(String tripStayName) {
        this.tripStayName = tripStayName;
    }

    public String getTripStayAddy() {
        return tripStayAddy;
    }

    public void setTripStayAddy(String tripStayAddy) {
        this.tripStayAddy = tripStayAddy;
    }

    public String getTripStayPhone() {
        return tripStayPhone;
    }

    public void setTripStayPhone(String tripStayPhone) {
        this.tripStayPhone = tripStayPhone;
    }

    public String getTripNotes() {
        return tripNotes;
    }

    public void setTripNotes(String tripNotes) {
        this.tripNotes = tripNotes;
    }
}

/*
    String tripDepartAirline;
    String tripDepartFlightNo;
    String tripArriveAirline;
    String tripArriveFlightNo;
 */