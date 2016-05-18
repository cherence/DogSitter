package com.example.cher.dogsitter.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.cher.dogsitter.R;
import com.example.cher.dogsitter.model.PetInfo;
import com.example.cher.dogsitter.model.User;
import com.firebase.client.Firebase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PetInfoEditFragment extends Fragment {
    private static final String TAG = "PetInfoEditFragment";
    private Button soleButton;
    private ImageView profileImageView;
    private EditText nameEditText;
    private EditText nickNameEditText;
    private EditText ageEditText;
    private EditText weightEditText;
    private EditText breedEditText;
    private EditText colorEditText;
    private EditText specialNeedsEditText;
    private EditText allergiesEditText;
    private EditText medicationEditText;
    private EditText injuriesEditText;
    private EditText fearsEditText;
    private EditText hatesEditText;
    private EditText lovesEditText;
    private EditText tricksEditText;
    private EditText routineEditText;
    private EditText hideoutEditText;
    private PetInfo receivedPetInfo;
    private PetInfo updatePetInfo;
    private PetInfo createPetInfo;
    private int receivedButtonId;
    private Firebase receivedRef;
    private Firebase rootFbRef;
    private Firebase petInfoFbRef;
    private User newUser;
    private ArrayList<PetInfo> petInfoArrayList;


    public PetInfoEditFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newUser = User.getInstance();
        rootFbRef = new Firebase("https://dogsitter.firebaseio.com/user/" + newUser.getUniqueId() + "/ownerProfile");
        petInfoFbRef = rootFbRef.child("petInfoPage");
        receivedButtonId = -122484;
//        receivedPetInfo = new PetInfo();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pet_info_edit, container, false);
        initializeViews(v);
        if (receivedPetInfo != null){ //if receivedPetInfo object exists then set the hints.
            prefillEditText();
        }
        setButton();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    private void initializeViews(View v) {
        profileImageView = (ImageView) v.findViewById(R.id.pet_profile_ImageView_id);
        soleButton = (Button) v.findViewById(R.id.petEdit_onlyButton_id);
        nameEditText = (EditText) v.findViewById(R.id.pet_name_editText_id);
        nickNameEditText = (EditText) v.findViewById(R.id.pet_nickName_editText_id);
        ageEditText = (EditText) v.findViewById(R.id.pet_age_editText_id);
        weightEditText = (EditText) v.findViewById(R.id.pet_weight_editText_id);
        breedEditText = (EditText) v.findViewById(R.id.pet_breed_editText_id);
        colorEditText = (EditText) v.findViewById(R.id.pet_color_editText_id);
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

    public PetInfo getReceivedPetInfo() {
        return receivedPetInfo;
    }

    public void setReceivedPetInfo(PetInfo receivedPetInfo) {
        if (receivedPetInfo != null){ //if  receivedPetInfo object exists then set hints
            prefillEditText();
            Log.i(TAG, "receivedPetInfo != null " + receivedPetInfo.getName());
        } else {
            this.receivedPetInfo = receivedPetInfo; //if receivedPetInfo object doesn't exist yet, then set object to object.
            Log.i(TAG, "receivedPetInfo == null ");
        }

    }

    public Firebase getReceivedRef() {
        return receivedRef;
    }

    public void setReceivedRef(Firebase receivedRef) {
        this.receivedRef = receivedRef;
    }

    public int getReceivedButtonId() {
        return receivedButtonId;
    }

    public void setReceivedButtonId(int receivedButtonId) {
        this.receivedButtonId = receivedButtonId;
    }

    public void prefillEditText(){
        profileImageView.setImageResource(receivedPetInfo.getPetPhoto());
        nameEditText.setText(receivedPetInfo.getName());
        nickNameEditText.setText(receivedPetInfo.getNickname());
        ageEditText.setText(receivedPetInfo.getAge());
        weightEditText.setText(receivedPetInfo.getWeight());
        breedEditText.setText(receivedPetInfo.getBreed());
        colorEditText.setText(receivedPetInfo.getColor());
        specialNeedsEditText.setText(receivedPetInfo.getSpecialNeeds());
        allergiesEditText.setText(receivedPetInfo.getAllergies());
        medicationEditText.setText(receivedPetInfo.getMedication());
        injuriesEditText.setText(receivedPetInfo.getInjuries());
        fearsEditText.setText(receivedPetInfo.getFears());
        hatesEditText.setText(receivedPetInfo.getHates());
        lovesEditText.setText(receivedPetInfo.getLoves());
        tricksEditText.setText(receivedPetInfo.getTricks());
        routineEditText.setText(receivedPetInfo.getRoutine());
        hideoutEditText.setText(receivedPetInfo.getHidingSpots());
    }

    public void setButton(){
        switch (receivedButtonId){
            case -122484:
                soleButton.setText("Save Changes");
                soleButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //update firebase to change existing profile at the receivedPosition position.
                        updatePetInfo = new PetInfo(
                                R.drawable.placeholder_pet_profile, //eventually change this so the user can take a picture
                                nameEditText.getText().toString(),
                                nickNameEditText.getText().toString(),
                                ageEditText.getText().toString(),
                                weightEditText.getText().toString(),
                                breedEditText.getText().toString(),
                                colorEditText.getText().toString(),
                                specialNeedsEditText.getText().toString(),
                                allergiesEditText.getText().toString(),
                                medicationEditText.getText().toString(),
                                injuriesEditText.getText().toString(),
                                fearsEditText.getText().toString(),
                                hatesEditText.getText().toString(),
                                lovesEditText.getText().toString(),
                                tricksEditText.getText().toString(),
                                routineEditText.getText().toString(),
                                hideoutEditText.getText().toString());
                        receivedRef.setValue(updatePetInfo);
                    }
                });
                break;
            default:
                soleButton.setText("Create Dog Profile");
                soleButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //update firebase to add a new dog profile
                        createPetInfo = new PetInfo(
                                R.drawable.placeholder_pet_profile, //eventually change this so the user can take a picture
                                nameEditText.getText().toString(),
                                nickNameEditText.getText().toString(),
                                ageEditText.getText().toString(),
                                weightEditText.getText().toString(),
                                breedEditText.getText().toString(),
                                colorEditText.getText().toString(),
                                specialNeedsEditText.getText().toString(),
                                allergiesEditText.getText().toString(),
                                medicationEditText.getText().toString(),
                                injuriesEditText.getText().toString(),
                                fearsEditText.getText().toString(),
                                hatesEditText.getText().toString(),
                                lovesEditText.getText().toString(),
                                tricksEditText.getText().toString(),
                                routineEditText.getText().toString(),
                                hideoutEditText.getText().toString());
                        petInfoArrayList = new ArrayList<PetInfo>();
                        petInfoArrayList = newUser.getOwnerProfile().getPetInfoPage();
                        petInfoArrayList.add(createPetInfo);
                        petInfoFbRef.setValue(petInfoArrayList);
                    }
                });
                break;
        }
    }


}

/* =================BANK===================
nameEditText.setHint(receivedPetInfo.getName());
        nickNameEditText.setHint(receivedPetInfo.getNickname());
        ageEditText.setHint(receivedPetInfo.getAge());
        weightEditText.setHint(receivedPetInfo.getWeight());
        breedEditText.setHint(receivedPetInfo.getBreed());
        specialNeedsEditText.setHint(receivedPetInfo.getSpecialNeeds());
        allergiesEditText.setHint(receivedPetInfo.getAllergies());
        medicationEditText.setHint(receivedPetInfo.getMedication());
        injuriesEditText.setHint(receivedPetInfo.getInjuries());
        fearsEditText.setHint(receivedPetInfo.getFears());
        hatesEditText.setHint(receivedPetInfo.getHates());
        lovesEditText.setHint(receivedPetInfo.getLoves());
        tricksEditText.setHint(receivedPetInfo.getTricks());
        routineEditText.setHint(receivedPetInfo.getRoutine());
        hideoutEditText.setHint(receivedPetInfo.getHidingSpots());

if (receivedButtonId == -122484){
            soleButton.setText("Save Changes");
        } else {
            soleButton.setText("Create Dog Profile");
        }

 */
