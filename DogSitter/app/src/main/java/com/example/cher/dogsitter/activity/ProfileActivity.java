package com.example.cher.dogsitter.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.cher.dogsitter.Interface.OnAddPressedListener;
import com.example.cher.dogsitter.Interface.OnPetSelectedListener;
import com.example.cher.dogsitter.R;
import com.example.cher.dogsitter.fragment.ChatFragment;
import com.example.cher.dogsitter.fragment.MySitterFragment;
import com.example.cher.dogsitter.fragment.OwnerInfoFragment;
import com.example.cher.dogsitter.fragment.PetInfoDisplayFragment;
import com.example.cher.dogsitter.fragment.PetInfoEditFragment;
import com.example.cher.dogsitter.fragment.SitterInfoFragment;
import com.example.cher.dogsitter.model.Group;
import com.example.cher.dogsitter.model.PetInfo;
import com.example.cher.dogsitter.model.User;
import com.example.cher.dogsitter.wishlist.FeedFragment;
import com.example.cher.dogsitter.wishlist.FirstAidFragment;
import com.example.cher.dogsitter.wishlist.GroomFragment;
import com.example.cher.dogsitter.wishlist.HouseFragment;
import com.example.cher.dogsitter.wishlist.MedicineFragment;
import com.example.cher.dogsitter.wishlist.PottyFragment;
import com.example.cher.dogsitter.wishlist.StuffFragment;
import com.example.cher.dogsitter.wishlist.WalkFragment;
import com.firebase.client.Firebase;

public class ProfileActivity extends AppCompatActivity implements OnPetSelectedListener, OnAddPressedListener {
    private static final String TAG = "ProfileActivity";
    public static final String BUNDLE_KEY = "key for the entire bundle";
    public static final String KEY_PHOTO = "petPhoto";
    public static final String KEY_FAB_ID = "buttonId for FAB";
    public static final String KEY_EDIT_ID = "buttonId for onTouch";

    private AHBottomNavigation bottomNavigation;
    private String selectionExtra;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private PetInfoDisplayFragment petInfoDisplayFragment;
    private PetInfoEditFragment petInfoEditFragment;
    private SitterInfoFragment sitterInfoFragment;
    private ChatFragment chatFragment;//    private static final String KEY_NAME = "NAME";
    private OwnerInfoFragment ownerInfoFragment;
    private MySitterFragment mySitterFragment;
    private FeedFragment feedFragment;
    private FirstAidFragment firstAidFragment;
    private GroomFragment groomFragment;
    private HouseFragment houseFragment;
    private MedicineFragment medicineFragment;
    private PottyFragment pottyFragment;
    private StuffFragment stuffFragment;
    private WalkFragment walkFragment;
    private User user;
    private Group group;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initializeViews();
        initializeFragments();
        getIntentToSetFragment();
        displayProperFragment();
        createAndSetNaviBar();
    }

    private void initializeViews(){
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation_id);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout_id);
        drawerToggle = setupDrawerToggle();
        drawer.addDrawerListener(drawerToggle);
        navigationView = (NavigationView) findViewById(R.id.nvView_id);
        setUpDrawerContent(navigationView);
    }

    private void initializeFragments(){
        petInfoDisplayFragment = new PetInfoDisplayFragment();
        petInfoEditFragment = new PetInfoEditFragment();
        sitterInfoFragment = new SitterInfoFragment();
        chatFragment = new ChatFragment();
        ownerInfoFragment = new OwnerInfoFragment();
        mySitterFragment = new MySitterFragment();
        feedFragment = new FeedFragment();
        firstAidFragment = new FirstAidFragment();
        groomFragment = new GroomFragment();
        houseFragment = new HouseFragment();
        medicineFragment = new MedicineFragment();
        pottyFragment = new PottyFragment();
        stuffFragment = new StuffFragment();
        walkFragment = new WalkFragment();

    }

    private ActionBarDrawerToggle setupDrawerToggle(){
        return new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    private void setUpDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });
    }

    public void selectDrawerItem(MenuItem menuItem){
        setFragmentLogistics();
        fragmentTransaction.addToBackStack(null);
        switch (menuItem.getItemId()){
            case R.id.drawer_mySitter_id:
                fragmentTransaction.replace(R.id.profile_container_id, mySitterFragment);
                break;
            case R.id.drawer_dog_id:
                fragmentTransaction.replace(R.id.profile_container_id, petInfoDisplayFragment);
                break;
            case R.id.drawer_owner_id:
                fragmentTransaction.replace(R.id.profile_container_id, ownerInfoFragment);
                break;
            case R.id.drawer_home_id:
                fragmentTransaction.replace(R.id.profile_container_id, houseFragment);
                break;
            case R.id.drawer_medicine_id:
                fragmentTransaction.replace(R.id.profile_container_id, medicineFragment);
                break;
            case R.id.drawer_feed_id:
                fragmentTransaction.replace(R.id.profile_container_id, feedFragment);
                break;
            case R.id.drawer_potty_id:
                fragmentTransaction.replace(R.id.profile_container_id, pottyFragment);
                break;
            case R.id.drawer_walk_id:
                fragmentTransaction.replace(R.id.profile_container_id, walkFragment);
                break;
            case R.id.drawer_groom_id:
                fragmentTransaction.replace(R.id.profile_container_id, groomFragment);
                break;
            case R.id.drawer_stuff_id:
                fragmentTransaction.replace(R.id.profile_container_id, stuffFragment);
                break;
            case R.id.drawer_firstAid_id:
                fragmentTransaction.replace(R.id.profile_container_id, firstAidFragment);
                break;
            case R.id.drawer_logout_id:
                //do stuff to log a user out of facebook here.
                break;
        }
        fragmentTransaction.commit();
        menuItem.setChecked(true);
        //setTitle(menuItem.getTitle());
        drawer.closeDrawers();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //the action bar home/up action should open or close the drawer
        if (drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // `onPostCreate` called when activity start-up is complete after `onStart()`
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private void createAndSetNaviBar(){
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.icon_owner_48, R.color.primary);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.icon_sitter_48, R.color.primary_dark);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.icon_chat_48, R.color.primary_light);
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
                    fragmentTransaction.replace(R.id.profile_container_id, petInfoDisplayFragment);
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
                fragmentTransaction.add(R.id.profile_container_id, petInfoDisplayFragment);
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

    @Override
    public void onPetSelected(PetInfo petSelected, Firebase refToItemPressed) {
        setFragmentLogistics();
        Bundle bundle = new Bundle();
        int photoBundle = petSelected.getPetPhoto();
        String[] dataBundle = {petSelected.getName(),
                petSelected.getNickname(), petSelected.getAge(), petSelected.getBreed(),
                petSelected.getColor(), petSelected.getWeight(), petSelected.getSpecialNeeds(),
                petSelected.getAllergies(), petSelected.getMedication(), petSelected.getInjuries(),
                petSelected.getFears(), petSelected.getHates(), petSelected.getLoves(),
                petSelected.getTricks(), petSelected.getRoutine(), petSelected.getHidingSpots()};
        bundle.putStringArray(BUNDLE_KEY, dataBundle);
        bundle.putString("KEY_REF", refToItemPressed.getKey());
        bundle.putInt(KEY_PHOTO, photoBundle);
        bundle.putInt(KEY_EDIT_ID, -122484);
        Log.i(TAG, "onPetSelected: " + refToItemPressed.getKey());
        petInfoEditFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.profile_container_id, petInfoEditFragment);
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onAddPressed(int buttonId) {
        setFragmentLogistics();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_FAB_ID, buttonId);
        petInfoEditFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.profile_container_id, petInfoEditFragment);
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}


/* ======================BANK======================

//    bundle.putString("NAME",petSelected.getName());
//    bundle.putString("AGE", petSelected.getAge());
//    petInfoEditFragment.setReceivedPetInfo(petSelected);
//    petInfoEditFragment.setReceivedRef(refToItemPressed);
      petInfoEditFragment.setReceivedButtonId(buttonId);

//    private static final String KEY_NAME = "NAME";
//    private static final String KEY_NICKNAME = "";
//    private static final String KEY_AGE = "";
//    private static final String KEY_BREED = "";
//    private static final String KEY_COLOR = "";
//    private static final String KEY_WEIGHT = "";
//    private static final String KEY_SPECIAL_NEEDS = "";
//    private static final String KEY_ALLERGIES = "";
//    private static final String KEY_MEDICATION = "";
//    private static final String KEY_INJURIES = "";
//    private static final String KEY_FEARS = "";
//    private static final String KEY_HATES = "";
//    private static final String KEY_LOVES = "";
//    private static final String KEY_TRICKS = "";
//    private static final String KEY_ROUTINE = "";
//    private static final String KEY_HIDINGSPOTS = "";
//    private static final String KEY_NICKNAME = "";
//    private static final String KEY_AGE = "";
//    private static final String KEY_BREED = "";
//    private static final String KEY_COLOR = "";
//    private static final String KEY_WEIGHT = "";
//    private static final String KEY_SPECIAL_NEEDS = "";
//    private static final String KEY_ALLERGIES = "";
//    private static final String KEY_MEDICATION = "";
//    private static final String KEY_INJURIES = "";
//    private static final String KEY_FEARS = "";
//    private static final String KEY_HATES = "";
//    private static final String KEY_LOVES = "";
//    private static final String KEY_TRICKS = "";
//    private static final String KEY_ROUTINE = "";
//    private static final String KEY_HIDINGSPOTS = "";
 */