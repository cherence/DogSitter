package com.example.cher.dogsitter.rvholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.cher.dogsitter.R;
import com.example.cher.dogsitter.model.User;

/**
 * Created by leisforkokomo on 5/12/16.
 */
public class SitterInfoRVHolder extends RecyclerView.ViewHolder {
    TextView ownerNameTextView;

    public SitterInfoRVHolder(View v) {
        super(v);
        ownerNameTextView = (TextView) v.findViewById(R.id.sitter_name_textview);
    }
}
