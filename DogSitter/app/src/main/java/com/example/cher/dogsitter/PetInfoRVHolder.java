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
    ImageView photoImageView;
    TextView nameTextView;
    TextView nicknameTextView;
    TextView ageTextView;
    TextView breedTextView;
    TextView colorTextView;
    TextView weightTextView;
    TextView specialNeedsTextView;
    TextView allergiesTextView;
    TextView medicationTextView;
    TextView injuriesTextView;
    TextView fearsTextView;
    TextView hatesTextView;
    TextView lovesTextView;
    TextView tricksTextView;
    TextView routineTextView;
    TextView hideoutsTextView;

    EditText nameEditText;
    EditText nicknameEditText;
    EditText ageEditText;
    EditText breedEditText;
    EditText colorEditText;
    EditText weightEditText;
    EditText specialNeedsEditText;
    EditText allergiesEditText;
    EditText medicationEditText;
    EditText injuriesEditText;
    EditText fearsEditText;
    EditText hatesEditText;
    EditText lovesEditText;
    EditText tricksEditText;
    EditText routineEditText;
    EditText hideoutsEditText;

    TextView ageLabelTextView;
    TextView breedLabelTextView;
    TextView colorLabelTextView;
    TextView weightLabelTextView;
    TextView specialNeedsLabelTextView;
    TextView allergiesLabelTextView;
    TextView medicationLabelTextView;
    TextView injuriesLabelTextView;
    TextView fearsLabelTextView;
    TextView hatesLabelTextView;
    TextView lovesLabelTextView;
    TextView tricksLabelTextView;
    TextView routineLabelTextView;
    TextView hideoutsLabelTextView;

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

        nameEditText = (EditText) v.findViewById(R.id.pet_name_editText_id);
        nicknameEditText = (EditText) v.findViewById(R.id.pet_nickName_editText_id);
        ageEditText = (EditText) v.findViewById(R.id.pet_age_editText_id);
        breedEditText = (EditText) v.findViewById(R.id.pet_breed_editText_id);
        colorEditText = (EditText) v.findViewById(R.id.pet_color_editText_id);
        weightEditText = (EditText) v.findViewById(R.id.pet_weight_editText_id);
        specialNeedsEditText = (EditText) v.findViewById(R.id.pet_specialNeeds_editText_id);
        allergiesEditText = (EditText) v.findViewById(R.id.pet_allergies_editText_id);
        medicationEditText = (EditText) v.findViewById(R.id.pet_medication_editText_id);
        injuriesEditText= (EditText) v.findViewById(R.id.pet_injuries_editText_id);
        fearsEditText= (EditText) v.findViewById(R.id.pet_fears_editText_id);
        hatesEditText= (EditText) v.findViewById(R.id.pet_hates_editText_id);
        lovesEditText = (EditText) v.findViewById(R.id.pet_loves_editText_id);
        tricksEditText = (EditText) v.findViewById(R.id.pet_tricks_editText_id);
        routineEditText = (EditText) v.findViewById(R.id.pet_routine_editText_id);
        hideoutsEditText = (EditText) v.findViewById(R.id.pet_hideouts_editText_id);

        ageLabelTextView = (TextView) v.findViewById(R.id.pet_ageLabel_textView_id);
        breedLabelTextView = (TextView) v.findViewById(R.id.pet_breedLabel_textView_id);
        colorLabelTextView = (TextView) v.findViewById(R.id.pet_colorLabel_textView_id);
        weightLabelTextView = (TextView) v.findViewById(R.id.pet_weightLabel_textView_id);
        specialNeedsLabelTextView = (TextView) v.findViewById(R.id.pet_specialNeedsLabel_textView_id);
        allergiesLabelTextView = (TextView) v.findViewById(R.id.pet_allergiesLabel_textView_id);
        medicationLabelTextView = (TextView) v.findViewById(R.id.pet_medicationLabel_textView_id);
        injuriesLabelTextView= (TextView) v.findViewById(R.id.pet_injuriesLabel_textView_id);
        fearsLabelTextView = (TextView) v.findViewById(R.id.pet_fearsLabel_textView_id);
        hatesLabelTextView= (TextView) v.findViewById(R.id.pet_hatesLabel_textView_id);
        lovesLabelTextView = (TextView) v.findViewById(R.id.pet_lovesLabel_textView_id);
        tricksLabelTextView = (TextView) v.findViewById(R.id.pet_tricksLabel_textView_id);
        routineLabelTextView = (TextView) v.findViewById(R.id.pet_routineLabel_textView_id);
        hideoutsLabelTextView = (TextView) v.findViewById(R.id.pet_hideoutsLabel_textView_id);


    }
}
