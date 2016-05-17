package com.example.cher.dogsitter.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import java.util.ArrayList;

/**
 *
 */
public class PetInfoViewFragment extends Fragment {
    private static final String TAG = "PetInfoFragment";
    RecyclerView petInfoRV;
    FirebaseRecyclerAdapter<PetInfo, PetInfoRVHolder> petInfoFbRecyclerAdapter;
    PetInfo dummyPetInfoObject;
    Firebase rootFbRef;
    Firebase petInfoFbRef;
    Firebase eachPetInfoFbRef;
    Button editButton;
    Button addButton;
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
    ArrayList<PetInfo> petInfoArrayList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newUser = User.getInstance();
        newGroup = Group.getInstance();
        rootFbRef = new Firebase("https://dogsitter.firebaseio.com/user/" + newUser.getUniqueId() + "/ownerProfile");
        petInfoFbRef = rootFbRef.child("petInfoPage"); // <<make this the pet info child/branch
        dummyPetInfoObject = new PetInfo(R.drawable.placeholder_pet_profile, "name", "nicknames", "age", "breed", "color", "weight", "special needs", "allergies", "medication", "injuries", "fears", "hates", "loves", "tricks", "routine", "hideouts");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pet_info, container, false);
        initializeViews(v);
        Log.i(TAG, "onCreateView: the user's unique id is " + newUser.getUniqueId() + " and the first member of the group that this profile belongs to is " + newGroup.getMembers().get(0));

        petInfoRV = (RecyclerView) v.findViewById(R.id.pet_recyclerView_id);
        petInfoRV.setLayoutManager(new LinearLayoutManager(getContext()));
        petInfoFbRecyclerAdapter = new FirebaseRecyclerAdapter<PetInfo, PetInfoRVHolder>(
                PetInfo.class,
                R.layout.list_item_pet_info,
                PetInfoRVHolder.class,
                petInfoFbRef
        ) {
            @Override
            protected void populateViewHolder(PetInfoRVHolder petInfoRVHolder, final PetInfo petInfo, final int i) {
//                petInfoRVHolder.photoImageView.setImageResource(petInfo.getPetPhoto());
                petInfoRVHolder.nameTextView.setText(petInfo.getName());
                petInfoRVHolder.nicknameTextView.setText(petInfo.getNickname());
                petInfoRVHolder.ageTextView.setText(petInfo.getAge());
                petInfoRVHolder.weightTextView.setText(petInfo.getWeight());
                petInfoRVHolder.breedTextView.setText(petInfo.getBreed());
                petInfoRVHolder.specialNeedsTextView.setText(petInfo.getSpecialNeeds());
                petInfoRVHolder.allergiesTextView.setText(petInfo.getAllergies());
                petInfoRVHolder.medicationTextView.setText(petInfo.getMedication());
                petInfoRVHolder.injuriesTextView.setText(petInfo.getInjuries());
                petInfoRVHolder.fearsTextView.setText(petInfo.getFears());
                petInfoRVHolder.hatesTextView.setText(petInfo.getHates());
                petInfoRVHolder.lovesTextView.setText(petInfo.getLoves());
                petInfoRVHolder.tricksTextView.setText(petInfo.getTricks());
                petInfoRVHolder.routineTextView.setText(petInfo.getRoutine());
                petInfoRVHolder.hideoutsTextView.setText(petInfo.getHidingSpots());
            }
        };
        petInfoRV.setAdapter(petInfoFbRecyclerAdapter);

//        if the user's unique id matches the unique id of the profile being viewed then the user can edit fragment contents.
//        in the case of sitters: their unique ID will be different from the dog owner's so the sitter can't edit any field in dog owner's profile.
        if (newUser.getUniqueId().equals(newGroup.getMembers().get(0))) {
            editButton.setVisibility(View.VISIBLE);
            addButton.setVisibility(View.VISIBLE);
        }

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeEditTextsVisible();

            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                petInfoArrayList = new ArrayList<PetInfo>();
                petInfoArrayList= newUser.getOwnerProfile().getPetInfoPage();
                petInfoArrayList.add(dummyPetInfoObject);
                petInfoFbRef.setValue(petInfoArrayList);



//                petInfoArrayList.add(dummyPetInfoObject);
//                petInfoFbRef.setValue(dummyPetInfoObject);

            }
        });

        return v;
    }

    @Override
    public void onActivityCreated (@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    private void initializeViews(View v){
        addButton = (Button) v.findViewById(R.id.pet_addButton_id);
        editButton = (Button) v.findViewById(R.id.pet_editButton_id);
        nameEditText = (EditText) v.findViewById(R.id.pet_name_editText_id);
        nickNameEditText = (EditText) v.findViewById(R.id.pet_nickName_editText_id);
        ageEditText = (EditText) v.findViewById(R.id.pet_age_editText_id);
        weightEditText = (EditText) v.findViewById(R.id.pet_weight_editText_id);
        breedEditText = (EditText) v.findViewById(R.id.pet_breed_editText_id);
        specialNeedsEditText = (EditText) v.findViewById(R.id.pet_specialNeeds_editText_id);
        allergiesEditText = (EditText) v.findViewById(R.id.pet_allergies_editText_id);
        medicationEditText = (EditText) v.findViewById(R.id.pet_medication_editText_id);
        injuriesEditText = (EditText) v.findViewById(R.id.pet_injuries_editText_id);
        fearsEditText = (EditText) v.findViewById(R.id.pet_fears_editText_id);
        hatesEditText = (EditText) v.findViewById(R.id.pet_hates_editText_id);
        lovesEditText = (EditText) v.findViewById(R.id.pet_loves_editText_id);
        tricksEditText = (EditText) v.findViewById(R.id.pet_tricks_editText_id);
        routineEditText = (EditText) v.findViewById(R.id.pet_routine_editText_id);
        hideoutEditText = (EditText) v.findViewById(R.id.pet_hideouts_editText_id);
    }

    private void makeEditTextsVisible(){
        nameEditText.setVisibility(View.VISIBLE);
        nickNameEditText.setVisibility(View.VISIBLE);
        ageEditText.setVisibility(View.VISIBLE);
        weightEditText.setVisibility(View.VISIBLE);
        breedEditText.setVisibility(View.VISIBLE);
        specialNeedsEditText.setVisibility(View.VISIBLE);
        allergiesEditText.setVisibility(View.VISIBLE);
        medicationEditText.setVisibility(View.VISIBLE);
        injuriesEditText.setVisibility(View.VISIBLE);
        fearsEditText.setVisibility(View.VISIBLE);
        hatesEditText.setVisibility(View.VISIBLE);
        lovesEditText.setVisibility(View.VISIBLE);
        tricksEditText.setVisibility(View.VISIBLE);
        routineEditText.setVisibility(View.VISIBLE);
        hideoutEditText.setVisibility(View.VISIBLE);
    }



}
