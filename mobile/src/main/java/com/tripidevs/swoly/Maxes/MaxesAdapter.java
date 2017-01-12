package com.tripidevs.swoly.Maxes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tripidevs.swoly.R;

import java.util.ArrayList;

/**
 * Created by Innocent on 1/10/2017.
 */

public class MaxesAdapter extends RecyclerView.Adapter<MaxesAdapter.MaxesViewHolder> {
    ArrayList<MaxesCard> maxes = new ArrayList<>();
    ArrayList<Button> cardButtons = new ArrayList<>();

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
    public void onBindViewHolder(final MaxesViewHolder holder, int position) {
        MaxesCard max = maxes.get(position);
        holder.lift.setText(max.getLiftName());
        holder.weight.setText(max.getLiftMax());
        holder.pre1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Button has been pressed",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return maxes.size();
    }

    public static class MaxesViewHolder extends RecyclerView.ViewHolder{
        TextView lift;
        TextView weight;
        Button pre1, pre2, pre3, pre4, pre5;
        RelativeLayout cardLayout;
        public MaxesViewHolder(View view){
            super(view);
            lift = (TextView) view.findViewById(R.id.liftTitle);
            weight = (TextView) view.findViewById(R.id.weight);
            cardLayout = (RelativeLayout) view.findViewById(R.id.relative);
            for(short i =0; i<cardLayout.getChildCount(); i++){
                //if(cardLayout.getChildAt(i).getTyp)
            }
            this.pre1 = (Button) view.findViewById(R.id.preset1);
            this.pre2 = (Button) view.findViewById(R.id.preset2);
            this.pre3 = (Button) view.findViewById(R.id.preset3);
            this.pre4 = (Button) view.findViewById(R.id.preset4);
            this.pre5 = (Button) view.findViewById(R.id.preset5);

        }
    }

}
