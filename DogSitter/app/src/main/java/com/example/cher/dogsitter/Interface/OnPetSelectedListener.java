package com.example.cher.dogsitter.Interface;

import com.example.cher.dogsitter.model.PetInfo;
import com.firebase.client.Firebase;

/**
 * Creates the skeleton method for this interface.
 * Created by leisforkokomo on 5/17/16.
 */
public interface OnPetSelectedListener {
    void onPetSelected(PetInfo petSelected, Firebase refToItemPressed);

}
