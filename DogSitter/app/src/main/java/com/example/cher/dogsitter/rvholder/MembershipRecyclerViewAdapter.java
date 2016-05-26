package com.example.cher.dogsitter.rvholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cher.dogsitter.R;

import java.util.List;

/**
 * Created by leisforkokomo on 5/12/16.
 */
public class MembershipRecyclerViewAdapter extends RecyclerView.Adapter<SitterInfoRVHolder> {

    private List<String> itemsList;
    private Context context;

    public MembershipRecyclerViewAdapter(Context context, List<String> itemsList){
        this.itemsList = itemsList;
        this.context = context;
    }

    public SitterInfoRVHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sitter_vh_row, null);
        return new SitterInfoRVHolder(view);
    }

    public void onBindViewHolder(SitterInfoRVHolder sitterInfoRVHolder, int i){
        String sitterName = itemsList.get(i);
        sitterInfoRVHolder.ownerNameTextView.setText(sitterName);
    }

    public int getItemCount(){
        return (null != itemsList ? itemsList.size() : 0);
    }
}

