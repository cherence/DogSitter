package com.example.cher.dogsitter.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cher.dogsitter.R;
import com.example.cher.dogsitter.activity.ProfileActivity;
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
    private Bundle bundle;
    private String[] dataBundle;
    private int photoBundle;


    public PetInfoEditFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newUser = User.getInstance();
        rootFbRef = new Firebase("https://dogsitter.firebaseio.com/user/" + newUser.getUniqueId() + "/ownerProfile");
        petInfoFbRef = rootFbRef.child("petInfoPage");
        receivedPetInfo = new PetInfo();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pet_info_edit, container, false);
        initializeViews(v);
        bundle = getArguments();
        if (receivedPetInfo != null){ //if receivedPetInfo object exists then set the hints.
            Log.i(TAG, "onCreateView: receivedPetInfo != null so app wil prefilLEditText" + receivedPetInfo.getName());
            receivedButtonId = -122484;
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
            Log.i(TAG, "receivedPetInfo != null " + receivedPetInfo.getAge());
            this.receivedPetInfo = receivedPetInfo;
            //prefillEditText();
            receivedButtonId = -122484;

        } else {
            Log.i(TAG, "receivedPetInfo == null ");
            this.receivedPetInfo = receivedPetInfo; //if receivedPetInfo object doesn't exist yet, then set object to object.
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
//        profileImageView.setImageResource(R.drawable.placeholder_pet_profile); workaround
        Log.i(TAG, "prefillEditText: " + receivedPetInfo.getAge());
        if (profileImageView==null)return;
//        profileImageView.setImageResource(receivedPetInfo.getPetPhoto()); //ideal
        photoBundle = bundle.getInt(ProfileActivity.KEY_PHOTO);
        profileImageView.setImageResource(photoBundle);
        dataBundle = bundle.getStringArray(ProfileActivity.BUNDLE_KEY);
        nameEditText.setText(dataBundle[0]);
        nickNameEditText.setText(dataBundle[1]);
        ageEditText.setText(dataBundle[2]);
        weightEditText.setText(dataBundle[3]);
        breedEditText.setText(dataBundle[4]);
        colorEditText.setText(dataBundle[5]);
        specialNeedsEditText.setText(dataBundle[6]);
        allergiesEditText.setText(dataBundle[7]);
        medicationEditText.setText(dataBundle[8]);
        injuriesEditText.setText(dataBundle[9]);
        fearsEditText.setText(dataBundle[10]);
        hatesEditText.setText(dataBundle[11]);
        lovesEditText.setText(dataBundle[12]);
        tricksEditText.setText(dataBundle[13]);
        routineEditText.setText(dataBundle[14]);
        hideoutEditText.setText(dataBundle[15]);
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
                        String key = bundle.getString("KEY_REF");
                        receivedRef = petInfoFbRef.child(key);
                        receivedRef.setValue(updatePetInfo);
                        Toast.makeText(getContext(), "Pet profile successfully updated", Toast.LENGTH_LONG).show();
                    }
                });
                //create delete petinfo button. make it gone initially and only visible here.
                //receivedRef.getRef().removeValue(); make it delete the ref then display a toast.
                //Toast.makeText(getContext(), "Pet profile successfully deleted", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(getContext(), "Pet profile successfully created", Toast.LENGTH_LONG).show();
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
