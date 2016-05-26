package com.example.cher.dogsitter.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cher.dogsitter.R;
import com.example.cher.dogsitter.activity.MainActivity;
import com.example.cher.dogsitter.model.User;
import com.example.cher.dogsitter.rvholder.MembershipRecyclerViewAdapter;
import com.example.cher.dogsitter.rvholder.SitterInfoRVHolder;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SitterInfoFragment extends Fragment {

    private static final String TAG = "SitterInfoFragment: ";

    Button shareCodeButton;
    User newUser;
    User newestUser;
    RecyclerView recyclerView;
    Firebase userFbRef;
    Firebase specificUserFbRef;
    ArrayList<String> allMemberships;
    ArrayList<String> realNameMemberships;
    MembershipRecyclerViewAdapter membershipRecyclerViewAdapter;

//    SitterInfoRVHolder sitterInfoRVHolder;


    public SitterInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sitter_info, container, false);

        newUser = User.getInstance();
        String newUserUniqueId = newUser.getUniqueId();
        userFbRef = new Firebase("https://dogsitter.firebaseio.com/user");
        specificUserFbRef = userFbRef.child(newUserUniqueId);

        shareCodeButton = (Button) v.findViewById(R.id.sitterFrag_shareCode_button_id);

        shareCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "My unique code for the DogSitter app is: " + newUser.getUniqueId() + ". Looking forward to dogsitting for you!";
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("*/*");//share.setType("text/plain").setType("image/*")
                share.putExtra(Intent.EXTRA_TEXT, message);
//                share.putExtra(Intent.EXTRA_STREAM, R.drawable.iamregisteredareyou);
                startActivity(Intent.createChooser(share, "Select a platform to share your unique sitter code"));
            }
        });

        allMemberships = new ArrayList<>();
        realNameMemberships = new ArrayList<>();

        recyclerView = (RecyclerView) v.findViewById(R.id.sitterFrag_recyclerView_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        membershipRecyclerViewAdapter = new MembershipRecyclerViewAdapter(this.getContext(), realNameMemberships);
        recyclerView.setAdapter(membershipRecyclerViewAdapter);

        specificUserFbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                newestUser = dataSnapshot.getValue(User.class);
                allMemberships.addAll(newestUser.getMemberships());
//                allMemberships.add(newestUser.getMemberships()); take the groupNames, make a fbref to group. get the value>getmembers>getUiD>firebaseuserref using uid>getFUllNAME
//                for (int i=0; allMemberships.size(); i++)
                membershipRecyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        return v;
    }


}
