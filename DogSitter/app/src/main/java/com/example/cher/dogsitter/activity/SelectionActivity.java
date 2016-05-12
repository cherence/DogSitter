package com.example.cher.dogsitter.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.example.cher.dogsitter.R;

public class SelectionActivity extends AppCompatActivity {
    Button ownerProfileButton;
    Button sitterProfileButton;
    Intent intentSendtoProfileActivity;
    public static final String PROFILE_TYPE_KEY = "this key sends the name of the button clicked to the profile activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        initializeViews();
        initializeIntent();
        setOnClickListeners();
    }

    private void initializeViews(){
        ownerProfileButton = (Button) findViewById(R.id.selection_ownerProfile_button_id);
        sitterProfileButton = (Button) findViewById(R.id.selection_sitterProfile_button_id);
    }

    private void initializeIntent(){
        intentSendtoProfileActivity = new Intent(SelectionActivity.this, ProfileActivity.class);
    }

    private void setOnClickListeners(){
        ownerProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String owner = ownerProfileButton.getText().toString();
                intentSendtoProfileActivity.putExtra(PROFILE_TYPE_KEY, owner);
                startActivity(intentSendtoProfileActivity);
            }
        });

        sitterProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sitter = sitterProfileButton.getText().toString();
                intentSendtoProfileActivity.putExtra(PROFILE_TYPE_KEY, sitter);
                startActivity(intentSendtoProfileActivity);
            }
        });
    }


}
