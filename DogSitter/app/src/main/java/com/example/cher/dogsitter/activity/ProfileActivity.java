package com.example.cher.dogsitter.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.cher.dogsitter.R;
import com.example.cher.dogsitter.fragment.ChatFragment;
import com.example.cher.dogsitter.fragment.OwnerInfoFragment;
import com.example.cher.dogsitter.fragment.PetInfoFragment;
import com.example.cher.dogsitter.fragment.SitterInfoFragment;
import com.example.cher.dogsitter.model.Group;
import com.example.cher.dogsitter.model.User;

public class ProfileActivity extends AppCompatActivity {
    AHBottomNavigation bottomNavigation;
    String selectionExtra;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    PetInfoFragment petInfoFragment;
    SitterInfoFragment sitterInfoFragment;
    ChatFragment chatFragment;
    User user;
    Group group;

    //declare sitter info fragment.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initializeViews();
        getIntentToSetFragment();
        displayProperFragment();
        createAndSetNaviBar();
    }

    private void initializeViews(){
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation_id);
        petInfoFragment = new PetInfoFragment();
        sitterInfoFragment = new SitterInfoFragment();
        chatFragment = new ChatFragment();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.);
    }

    private void createAndSetNaviBar(){
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, android.R.drawable.ic_media_play, R.color.primary);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, android.R.drawable.ic_media_pause, R.color.primary_dark);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, android.R.drawable.ic_media_ff, R.color.primary_light);
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#00BCD4")); // Set background color
        bottomNavigation.setBehaviorTranslationEnabled(false); // Disable the translation inside the CoordinatorLayout
        bottomNavigation.setAccentColor(Color.parseColor("#CDDC39")); // Change colors
        bottomNavigation.setInactiveColor(Color.parseColor("#FFFFFF")); // Change colors
        bottomNavigation.setForceTint(true); // Force to tint the drawable (useful for font with icon for example)
        bottomNavigation.setCurrentItem(0); // Set current item programmatically

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, boolean wasSelected) {
                setFragmentLogistics();
                fragmentTransaction.addToBackStack(null); //might go right above fragmenttransaction.commit
                if (position == 0){
                    fragmentTransaction.replace(R.id.profile_container_id, petInfoFragment);
                } else if (position == 1){
                    fragmentTransaction.replace(R.id.profile_container_id, sitterInfoFragment);
                } else if (position == 2){
                    fragmentTransaction.replace(R.id.profile_container_id, chatFragment);
                }
                fragmentTransaction.commit();
            }
        });
    }

    private void getIntentToSetFragment(){
        Intent intentRetrieveFromSelectionActivity = getIntent();
        selectionExtra = intentRetrieveFromSelectionActivity.getStringExtra(SelectionActivity.PROFILE_TYPE_KEY);
    }



    private void displayProperFragment(){
        setFragmentLogistics();
        switch (selectionExtra){
            case "My Owner Profile":
                fragmentTransaction.add(R.id.profile_container_id, petInfoFragment);
                break;
            case "My Sitter Profile":
                fragmentTransaction.add(R.id.profile_container_id, sitterInfoFragment);
                break;
        }

        fragmentTransaction.commit();
    }

    private void setFragmentLogistics(){
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
    }

}
