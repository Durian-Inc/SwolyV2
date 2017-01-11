package com.tripidevs.swoly.Maxes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tripidevs.swoly.R;

import java.util.ArrayList;

/**
 * Created by Innocent on 1/10/2017.
 */

public class MaxesAdapter extends RecyclerView.Adapter<MaxesAdapter.MaxesViewHolder> {
    ArrayList<MaxesCard> maxes = new ArrayList<>();

    public MaxesAdapter(ArrayList<MaxesCard> maxes){
        this.maxes = maxes;
    }
    @Override
    public MaxesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .card_maxes, parent, false);
        MaxesViewHolder maxesViewHolder = new MaxesViewHolder(view);
        return maxesViewHolder;
    }

    @Override
    public void onBindViewHolder(MaxesViewHolder holder, int position) {
        MaxesCard max = maxes.get(position);
        holder.lift.setText(max.getLiftName());
        holder.weight.setText(max.getLiftMax());
    }

    @Override
    public int getItemCount() {
        return maxes.size();
    }

    public static class MaxesViewHolder extends RecyclerView.ViewHolder{
        TextView lift;
        TextView weight;
        public MaxesViewHolder(View view){
            super(view);
            lift = (TextView) view.findViewById(R.id.liftTitle);
            weight = (TextView) view.findViewById(R.id.weight);
        }
    }
}
