package com.example.cher.dogsitter.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.cher.dogsitter.Interface.OnAddPressedListener;
import com.example.cher.dogsitter.Interface.OnPetSelectedListener;
import com.example.cher.dogsitter.PetInfoRVHolder;
import com.example.cher.dogsitter.R;
import com.example.cher.dogsitter.model.Group;
import com.example.cher.dogsitter.model.OwnerInfo;
import com.example.cher.dogsitter.model.OwnerProfile;
import com.example.cher.dogsitter.model.PetInfo;
import com.example.cher.dogsitter.model.User;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseRecyclerAdapter;

import java.util.ArrayList;

/**
 *
 */
public class PetInfoDisplayFragment extends Fragment {
    private static final String TAG = "PetInfoFragment";
    private RecyclerView petInfoRV;
    private FirebaseRecyclerAdapter<PetInfo, PetInfoRVHolder> petInfoFbRecyclerAdapter;
    private PetInfo dummyPetInfoObject;
    private Firebase rootFbRef;
    private Firebase petInfoFbRef;
    private Firebase eachPetInfoFbRef;
    private Firebase refToItemClicked;
    private FloatingActionButton fab;
    private OnPetSelectedListener mPetSelectedListener;
    private OnAddPressedListener mAddPressedListener;
    private User newUser;
    private Group newGroup;
    private ArrayList<PetInfo> petInfoArrayList;
    private PetInfoEditFragment petInfoEditFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pet_info, container, false);
        initializeViews(v);
//        Log.i(TAG, "onCreateView: the user's unique id is " + newUser.getUniqueId() + " and the first member of the group that this profile belongs to is " + newGroup.getMembers().get(0));
        newUser = User.getInstance();
        newGroup = Group.getInstance();
        rootFbRef = new Firebase("https://dogsitter.firebaseio.com/user/" + newUser.getUniqueId() + "/ownerProfile");
//        rootFbRef.addValueEventListener(new ValueEventListener() { //will update the newUser singleton used in this fragment.
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                OwnerProfile ownerProfile = dataSnapshot.getValue(OwnerProfile.class);
//                Log.i(TAG, "onDataChange: this is what's in the datasnapshot--a petInfoPage array the size of " + dataSnapshot.getValue(OwnerProfile.class).getPetInfoPage().size());
//                ArrayList<PetInfo> newUserPetInfoArray = ownerProfile.getPetInfoPage();
//                newUser.getOwnerProfile().setPetInfoPage(newUserPetInfoArray); //used to be newUser.getOwnerProfile().getPetInfoPage() = ownerProfile.getPetInfoPage();
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//            }
//        });
        petInfoFbRef = rootFbRef.child("petInfoPage"); // <<make this the pet info child/branch
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
                petInfoRVHolder.colorTextView.setText(petInfo.getColor());
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

                petInfoRVHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i(TAG, "position clicked was " + i);
                        refToItemClicked = petInfoFbRecyclerAdapter.getRef(i);
                        refToItemClicked.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                dummyPetInfoObject = dataSnapshot.getValue(PetInfo.class);
                                mPetSelectedListener.onPetSelected(dummyPetInfoObject, refToItemClicked);
                                Log.i(TAG, "the petInfo stored in the dummyPetInfoObject is " + dummyPetInfoObject.getName());
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });
                    }
                });
            }
        };
        petInfoRV.setAdapter(petInfoFbRecyclerAdapter);
//        if the user's unique id matches the unique id of the profile being viewed then the user can edit fragment contents.
//        in the case of sitters: their unique ID will be different from the dog owner's so the sitter can't edit any field in dog owner's profile.
        if (newUser.getUniqueId().equals(newGroup.getMembers().get(0))) {
            fab.setVisibility(View.VISIBLE);
        }
        setAddOnClickListener();
        Log.i(TAG, "onCreateView: size of the petProfile array is " + newUser.getOwnerProfile().getPetInfoPage().size());
        return v;
    }

    @Override
    public void onActivityCreated (@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
//        setRetainInstance(true);
    }

    private void initializeViews(View v){
        fab = (FloatingActionButton) v.findViewById(R.id.pet_fab_id);
        petInfoEditFragment = new PetInfoEditFragment();
    }

    private void setAddOnClickListener(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAddPressedListener.onAddPressed(fab.getId());
                Log.i(TAG, "the fab was clicked ant its ID is " + fab.getId());
            }
        });
    }




    @Override
    public void onAttach(Context context) { //do these need to be in 2 different try/catch?
        super.onAttach(context);
        try {
            mPetSelectedListener = (OnPetSelectedListener) getActivity();
        } catch (ClassCastException e){
            Log.e(TAG, "Must implement OnPetSelectedListener");
        }
        try {
            mAddPressedListener = (OnAddPressedListener) getActivity();
        } catch (ClassCastException e){
            Log.e(TAG, "Must implement OnPetSelectedListener");
        }


    }
}


/* =================BANK===================
mRecycleViewAdapter = new FirebaseRecyclerAdapter<Chat, ChatHolder>(Chat.class, R.layout.message, ChatHolder.class, mChatRef) {
@Override
public void populateViewHolder(ChatHolder chatView, Chat chat, final int position) {
        chatView.setName(chat.getName());
        chatView.setText(chat.getText());

        if (mAuthData != null && chat.getUid().equals(mAuthData.getUid())) {
        chatView.setIsSender(true);
        } else {
        chatView.setIsSender(false);
        }

        chatView.mView.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        Log.w(TAG, "You clicked on "+position);
        mRecycleViewAdapter.getRef(position).removeValue();
        }
        });
        }
        };
*/

/*
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                petInfoArrayList = new ArrayList<PetInfo>();
                petInfoArrayList = newUser.getOwnerProfile().getPetInfoPage();
                petInfoArrayList.add(dummyPetInfoObject);
                petInfoFbRef.setValue(petInfoArrayList);
//                petInfoArrayList.add(dummyPetInfoObject);
//                petInfoFbRef.setValue(dummyPetInfoObject);
            }
        });


 dummyPetInfoObject = new PetInfo(R.drawable.placeholder_pet_profile, "name", "nicknames", "age", "breed", "color",
// "weight", "special needs", "allergies", "medication", "injuries", "fears", "hates", "loves", "tricks", "routine", "hideouts");
 */
