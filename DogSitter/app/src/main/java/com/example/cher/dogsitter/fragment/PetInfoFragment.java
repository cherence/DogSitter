package com.example.cher.dogsitter.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.cher.dogsitter.PetInfoRVHolder;
import com.example.cher.dogsitter.R;
import com.example.cher.dogsitter.model.Group;
import com.example.cher.dogsitter.model.PetInfo;
import com.example.cher.dogsitter.model.User;
import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseRecyclerAdapter;

/**
 *
 */
public class PetInfoFragment extends Fragment {
    RecyclerView petInfoRV;
    FirebaseRecyclerAdapter<PetInfo, PetInfoRVHolder> petInfoFbRecyclerAdapter;
    Firebase rootFbRef;
    Firebase petInfoFbRef;
    Firebase eachPetInfoFbRef;
    Button editButton;
    ImageView profileImageView;
    EditText nameEditText;
    EditText nickNameEditText;
    EditText ageEditText;
    EditText weightEditText;
    EditText breedEditText;
    EditText specialNeedsEditText;
    EditText allergiesEditText;
    EditText medicationEditText;
    EditText injuriesEditText;
    EditText fearsEditText;
    EditText hatesEditText;
    EditText lovesEditText;
    EditText tricksEditText;
    EditText routineEditText;
    EditText hideoutEditText;
    User newUser;
    Group newGroup;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newUser = User.getInstance();
        rootFbRef = new Firebase("https://dogsitter.firebaseio.com/");
        petInfoFbRef = new Firebase("https://dogsitter.firebaseio.com/" + "");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pet_info, container, false);
        editButton = (Button) v.findViewById(R.id.pet_edit_button_id);

//        if (newUser.getUniqueId() == newGroup.getMembers().get(0)){ //if the user's unique id matches the unique id of the profile being viewed then the user can edit fragment contents.

//        }

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



//
//        //DISPLAYING
//        petInfoFbRecyclerAdapter = new FirebaseRecyclerAdapter<PetInfo, PetInfoRVHolder>(PetInfo.class, R.layout.fragment_pet_info, PetInfoRVHolder.class, petInfoFbRef) {
//            @Override
//            protected void populateViewHolder(PetInfoRVHolder petInfoRVHolder, final PetInfo petInfo, final int i) {
////                petInfoRVHolder.
//
//
//            }
//        };
//

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

}