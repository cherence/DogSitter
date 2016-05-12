package com.example.cher.dogsitter.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.cher.dogsitter.R;
import com.example.cher.dogsitter.model.Group;
import com.example.cher.dogsitter.model.User;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MySitterFragment extends Fragment {
    private static final String TAG = "MySitterFragment";
    EditText enterCodeEditText;
    Button submitButton;
    Button requestCodeButton;
    RecyclerView recyclerView;
    Firebase rootFbRef;
    Firebase userFbRef;
    Firebase groupFbRef;
    User newUser;
    Group newGroup;
    User sitterUser;
    Firebase sitterMembershipsFbRef;


    public MySitterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootFbRef = new Firebase("https://dogsitter.firebaseio.com/");
        userFbRef = rootFbRef.child("user");
        groupFbRef = rootFbRef.child("group");
        newUser = User.getInstance();
        newGroup = Group.getInstance();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_sitter, container, false);
        enterCodeEditText = (EditText) v.findViewById(R.id.mySitter_enterCode_editText_id);
        submitButton = (Button) v.findViewById(R.id.mySitter_submitButton_id);
        requestCodeButton = (Button) v.findViewById(R.id.mySitter_requestCodeButton_id);
        recyclerView = (RecyclerView) v.findViewById(R.id.mySitter_recyclerView_id);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codeOfNewSitter = enterCodeEditText.getText().toString();
                Log.i(TAG, "onClick: " + newGroup.getName());
                Firebase ownersGroupFbRef = groupFbRef.child(newGroup.getName()).child("members"); //add the sitter to my group

                //ADD NULL CHECK SO IT DOESN'T CRASH--> IF UNIQUE ID ISN'T VALID SHOW A TOAST.

                ArrayList<String> ownersGroupMembers= newGroup.getMembers();
                ownersGroupMembers.add(codeOfNewSitter); //add new sitter to members array
                Log.i(TAG, "onClick: " + ownersGroupMembers.get(1));
                ownersGroupFbRef.setValue(ownersGroupMembers);

//                ownersGroupFbRef.setValue(newGroup.getMembers().add(codeOfNewSitter)); //add new sitter to members array

                //group level: what to add to the child--> codeOfNewSitter <--adds this sitter as a member of my group

                final Firebase sitterUserFbRef = userFbRef.child(codeOfNewSitter); //will access the sitter's user account
                //user level: what to add to the child --> update memberships to user.getUniqueId

                sitterUserFbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        sitterUser = dataSnapshot.getValue(User.class);
                        sitterUser.getMemberships().add("GROUP" + newUser.getUniqueId());

                        sitterUserFbRef.setValue(sitterUser); //update sitter's memberships to include owner's group

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
            }
        });

        requestCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Please share your unique Dogsitter app code with me. Thanks for agreeing to dogsit for me!";
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("*/*");//share.setType("text/plain").setType("image/*")
                share.putExtra(Intent.EXTRA_TEXT, message);
//                share.putExtra(Intent.EXTRA_STREAM, R.drawable.iamregisteredareyou);
                startActivity(Intent.createChooser(share, "Select a platform to reach out to your dogsitter"));
            }
        });
        return v;
    }

}
