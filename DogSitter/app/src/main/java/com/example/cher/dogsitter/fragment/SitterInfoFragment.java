package com.example.cher.dogsitter.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cher.dogsitter.R;
import com.example.cher.dogsitter.activity.MainActivity;
import com.example.cher.dogsitter.model.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class SitterInfoFragment extends Fragment {
    Button shareCodeButton;
    User newUser;


    public SitterInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sitter_info, container, false);
        shareCodeButton = (Button) v.findViewById(R.id.sitterFrag_shareCode_button_id);

        shareCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newUser = User.getInstance();
                String message = "My unique code for the DogSitter app is: " + newUser.getUniqueId() + ". Looking forward to dogsitting for you!";
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("*/*");//share.setType("text/plain").setType("image/*")
                share.putExtra(Intent.EXTRA_TEXT, message);
//                share.putExtra(Intent.EXTRA_STREAM, R.drawable.iamregisteredareyou);
                startActivity(Intent.createChooser(share, "Select a platform to share your unique sitter code"));
            }
        });
        return v;
    }

}
