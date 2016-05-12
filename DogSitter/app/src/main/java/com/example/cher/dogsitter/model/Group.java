package com.example.cher.dogsitter.model;

import java.util.ArrayList;

/**
 * Created by leisforkokomo on 5/9/16.
 */
public class Group {
    String name;
    ArrayList<String> members;
    private static Group group = null;

    public Group() {
    }

    public static Group getInstance(){
        if (group == null){
            group = new Group();
        }
        return group;
    }

    public Group(String name, ArrayList<String> members) {
        this.name = name;
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<String> members) {
        this.members = members;
    }
}
