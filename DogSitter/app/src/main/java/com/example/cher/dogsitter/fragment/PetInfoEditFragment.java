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
import com.example.cher.dogsitter.model.OwnerProfile;
import com.example.cher.dogsitter.model.PetInfo;
import com.example.cher.dogsitter.model.User;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PetInfoEditFragment extends Fragment {
    private static final String TAG = "PetInfoEditFragment";
    private Button addEditButton;
    private Button deleteButton;
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
    private PetInfo updatePetInfo;
    private PetInfo createPetInfo;
    private Firebase receivedRef;
    private Firebase rootFbRef;
    private Firebase petInfoFbRef;
    private User newUser;
    private ArrayList<PetInfo> petInfoArrayList;
    private Bundle petObjectBundle;
    private Bundle fabBundle;
    private String[] dataBundle;
    private int photoBundle;
    private int buttonId;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private PetInfoDisplayFragment petInfoDisplayFragment;


    public PetInfoEditFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        Log.i(TAG, "onCreate: " + petObjectBundle + fabBundle);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pet_info_edit, container, false);
        initializeViews(v);
        newUser = User.getInstance();
        Log.i(TAG, "onCreate: ************** just created newUser singleton the size of the petProfile array is " + newUser.getOwnerProfile().getPetInfoPage().size());
        rootFbRef = new Firebase("https://dogsitter.firebaseio.com/user/" + newUser.getUniqueId() + "/ownerProfile");
        petInfoFbRef = rootFbRef.child("petInfoPage");
        Log.i(TAG, "#1 test if bundles are null. petObject bundle is " + petObjectBundle + " fabBundle is " + fabBundle);
        petObjectBundle = getArguments();
        fabBundle = getArguments();
        Log.i(TAG, "#2 test if bundles are null. petObject bundle is " + petObjectBundle + " fabBundle is " + fabBundle);
//        if (petObjectBundle != null){ //if receivedPetInfo object exists then set the hints.
//             //might need to be under initializeView
//            Log.i(TAG, "onCreateView: receivedPetInfo != null so app wil prefilLEditText" + petObjectBundle.getInt(ProfileActivity.KEY_PHOTO));
//            buttonId = -122484;
//            preFillEditText();
//        }
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
        addEditButton = (Button) v.findViewById(R.id.petEdit_onlyButton_id);
        deleteButton = (Button) v.findViewById(R.id.petEdit_deleteButton_id);
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


    public void preFillEditText() {
        Log.i(TAG, "prefillEditText: " + petObjectBundle.getInt(ProfileActivity.KEY_PHOTO));
        if (profileImageView == null) return; //try deleting later. probably not needed
        photoBundle = petObjectBundle.getInt(ProfileActivity.KEY_PHOTO);
        profileImageView.setImageResource(photoBundle);
        dataBundle = petObjectBundle.getStringArray(ProfileActivity.BUNDLE_KEY);
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

    public void setButton() {
        buttonId = fabBundle.getInt(ProfileActivity.KEY_FAB_ID);
        buttonId = petObjectBundle.getInt(ProfileActivity.KEY_EDIT_ID);
        Log.i(TAG, "setButton: the buttonId = " + buttonId);
        petInfoDisplayFragment = new PetInfoDisplayFragment();
        switch (buttonId) {
            case -122484:
                preFillEditText();
                String key = petObjectBundle.getString("KEY_REF");
                receivedRef = petInfoFbRef.child(key);
                addEditButton.setText("Save Changes");
                addEditButton.setOnClickListener(new View.OnClickListener() {
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
                        Toast.makeText(getContext(), "Pet profile successfully updated", Toast.LENGTH_LONG).show();
                        setFragmentLogistics();
                        fragmentTransaction.replace(R.id.profile_container_id, petInfoDisplayFragment);
//                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                });
                deleteButton.setVisibility(View.VISIBLE);
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        receivedRef.removeValue();
                        Toast.makeText(getContext(), "Pet profile successfully deleted", Toast.LENGTH_LONG).show();
                        setFragmentLogistics();
                        fragmentTransaction.replace(R.id.profile_container_id, petInfoDisplayFragment);
//                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                });
                //create delete petinfo button. make it gone initially and only visible here.
                //receivedRef.getRef().removeValue(); make it delete the ref then display a toast.
                //Toast.makeText(getContext(), "Pet profile successfully deleted", Toast.LENGTH_LONG).show();
                break;
            default:
                addEditButton.setText("Create Dog Profile");
                addEditButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        //update firebase to add a new dog profile
                        petInfoArrayList = new ArrayList<PetInfo>();
                        petInfoArrayList = newUser.getOwnerProfile().getPetInfoPage();
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
                        petInfoArrayList.add(createPetInfo);
                        petInfoFbRef.setValue(petInfoArrayList);
                        Toast.makeText(getContext(), "Pet profile successfully created", Toast.LENGTH_LONG).show();
                        setFragmentLogistics();
                        fragmentTransaction.remove(PetInfoEditFragment.this).replace(R.id.profile_container_id, petInfoDisplayFragment);
//                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                });
                break;
        }
    }

    private void setFragmentLogistics() {
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
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
//        profileImageView.setImageResource(R.drawable.placeholder_pet_profile); workaround
        //        profileImageView.setImageResource(receivedPetInfo.getPetPhoto()); //ideal

        if (buttonIdBundle != -122484){ //happens when the add FAB button is pressed b/c the FAB buttonId returns a positive #

        }


        if (receivedPetInfo != null){ //if receivedPetInfo object exists then set the hints.
            Log.i(TAG, "onCreateView: receivedPetInfo != null so app wil prefilLEditText" + receivedPetInfo.getName());
            receivedButtonId = -122484;
            prefillEditText();
        }


    private PetInfo receivedPetInfo; //never used b/c using bundles now
    private int receivedButtonId; //never used b/c using bundles now

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

    rootFbRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                OwnerProfile ownerProfile = dataSnapshot.getValue(OwnerProfile.class);
                                petInfoArrayList = ownerProfile.getPetInfoPage();
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
                                petInfoArrayList.add(createPetInfo);
                                petInfoFbRef.setValue(petInfoArrayList);
//                                Toast.makeText(getContext(), "Pet profile successfully created", Toast.LENGTH_LONG).show();
                                setFragmentLogistics();
                                fragmentTransaction.remove(PetInfoEditFragment.this).replace(R.id.profile_container_id, petInfoDisplayFragment);
//                        fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });
 */
