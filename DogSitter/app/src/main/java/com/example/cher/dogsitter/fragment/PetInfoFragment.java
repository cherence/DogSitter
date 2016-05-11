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

import com.example.cher.dogsitter.PetInfoRVHolder;
import com.example.cher.dogsitter.R;
import com.example.cher.dogsitter.model.PetInfo;
import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseRecyclerAdapter;

/**

 */
public class PetInfoFragment extends Fragment {

    RecyclerView petInfoRV;
    FirebaseRecyclerAdapter<PetInfo, PetInfoRVHolder> petInfoFbRecyclerAdapter;
    Firebase petInfoFbRef;
    Firebase eachPetInfoFbRef;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        petInfoFbRef = new Firebase()
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pet_info, container, false);

        petInfoFbRecyclerAdapter = new FirebaseRecyclerAdapter<PetInfo, PetInfoRVHolder>(PetInfo.class, R.layout.fragment_pet_info, PetInfoRVHolder.class, petInfoFbRef) {
            @Override
            protected void populateViewHolder(PetInfoRVHolder petInfoRVHolder, final PetInfo petInfo, final int i) {
                petInfoRVHolder.


            }
        };


        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

}