package com.example.cher.dogsitter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.cher.dogsitter.model.PetInfo;
import com.firebase.ui.FirebaseRecyclerAdapter;

public class PetInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_info);


    }
}
