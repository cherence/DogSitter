package com.example.cher.dogsitter.model;

import java.util.ArrayList;

/**
 * Created by leisforkokomo on 5/9/16.
 */
public class Group {
    ArrayList<String> members;

    public Group() {
    }

    public Group(ArrayList<String> members) {
        this.members = members;
    }

    public ArrayList<String> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<String> members) {
        this.members = members;
    }
}
