package com.example.cher.dogsitter.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.cher.dogsitter.R;

public class OptionsActivity extends AppCompatActivity {
    AHBottomNavigation bottomNavigation;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        initializeViews();
        createNaviBar();
    }

    private void initializeViews(){
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation_id);
    }

    private void createNaviBar(){
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

            }
        });
    }

}
