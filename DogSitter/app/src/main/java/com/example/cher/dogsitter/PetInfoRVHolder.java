package com.example.cher.dogsitter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by leisforkokomo on 5/10/16.
 */
public class PetInfoRVHolder extends RecyclerView.ViewHolder{
    public ImageView photoImageView;
    public TextView nameTextView;
    public TextView nicknameTextView;
    public TextView ageTextView;
    public TextView breedTextView;
    public TextView colorTextView;
    public TextView weightTextView;
    public TextView specialNeedsTextView;
    public TextView allergiesTextView;
    public TextView medicationTextView;
    public TextView injuriesTextView;
    public TextView fearsTextView;
    public TextView hatesTextView;
    public TextView lovesTextView;
    public TextView tricksTextView;
    public TextView routineTextView;
    public TextView hideoutsTextView;


    public PetInfoRVHolder(final View v) {
        super(v);
        photoImageView = (ImageView) v.findViewById(R.id.pet_profile_ImageView_id);
        nameTextView = (TextView) v.findViewById(R.id.pet_name_textView_id);
        nicknameTextView = (TextView) v.findViewById(R.id.pet_nickName_textView_id);
        ageTextView = (TextView) v.findViewById(R.id.pet_age_textView_id);
        breedTextView = (TextView) v.findViewById(R.id.pet_breed_textView_id);
        colorTextView = (TextView) v.findViewById(R.id.pet_color_textView_id);
        weightTextView = (TextView) v.findViewById(R.id.pet_weight_textView_id);
        specialNeedsTextView = (TextView) v.findViewById(R.id.pet_specialNeeds_textView_id);
        allergiesTextView = (TextView) v.findViewById(R.id.pet_allergies_textView_id);
        medicationTextView = (TextView) v.findViewById(R.id.pet_medication_textView_id);
        injuriesTextView= (TextView) v.findViewById(R.id.pet_injuries_textView_id);
        fearsTextView = (TextView) v.findViewById(R.id.pet_fears_textView_id);
        hatesTextView= (TextView) v.findViewById(R.id.pet_hates_textView_id);
        lovesTextView = (TextView) v.findViewById(R.id.pet_loves_textView_id);
        tricksTextView = (TextView) v.findViewById(R.id.pet_tricks_textView_id);
        routineTextView = (TextView) v.findViewById(R.id.pet_routine_textView_id);
        hideoutsTextView = (TextView) v.findViewById(R.id.pet_hideouts_textView_id);




    }
}
