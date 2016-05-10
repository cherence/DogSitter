package com.example.cher.dogsitter.model;

import java.util.ArrayList;

/**
 * Created by leisforkokomo on 5/9/16.
 */
public class User {
    String uniqueId;
    String fullUserName;
    String emailAddy;
    ArrayList<String> memberships;
    Boolean admin;

    public User() {
    }

    public User(String uniqueId, String fullUserName, String emailAddy, ArrayList<String> memberships, Boolean admin) {
        this.uniqueId = uniqueId;
        this.fullUserName = fullUserName;
        this.emailAddy = emailAddy;
        this.memberships = memberships;
        this.admin = admin;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getFullUserName() {
        return fullUserName;
    }

    public void setFullUserName(String fullUserName) {
        this.fullUserName = fullUserName;
    }

    public String getEmailAddy() {
        return emailAddy;
    }

    public void setEmailAddy(String emailAddy) {
        this.emailAddy = emailAddy;
    }

    public ArrayList<String> getMemberships() {
        return memberships;
    }

    public void setMemberships(ArrayList<String> memberships) {
        this.memberships = memberships;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
