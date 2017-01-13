package com.tripidevs.swoly.maxes;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
        holder.weight.setText(String.valueOf(max.getLiftMax()));
        for (final Button button: holder.cardButtons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MaxesFragment.percentageClick(button, holder.weight);
                }
            });
        }
        holder.vertMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp(v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return maxes.size();
    }

    protected void showPopUp(final View v){
        PopupMenu popup = new PopupMenu(v.getContext(),v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.max_card_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_edit:
                        Toast.makeText(v.getContext(), "Edit was clicked", Toast
                                .LENGTH_SHORT).show();
                        return true;
                    case R.id.menu_delete:
                        Toast.makeText(v.getContext(), "Max will be deleted",
                                Toast
                                .LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.show();
    }

    public static class MaxesViewHolder extends RecyclerView.ViewHolder{
        TextView lift;
        TextView weight;
        RelativeLayout cardLayout;
        ArrayList<AppCompatButton> cardButtons = new ArrayList<>();
        ImageView vertMenu;
        FloatingActionButton floatingActionButton;

        public MaxesViewHolder(View view){
            super(view);
            lift = (TextView) view.findViewById(R.id.liftTitle);
            weight = (TextView) view.findViewById(R.id.weight);
            cardLayout = (RelativeLayout) view.findViewById(R.id.relative);
            floatingActionButton = (FloatingActionButton) view.findViewById(R
                    .id.additionFAB);
            for(short i =0; i<cardLayout.getChildCount(); i++){
                if(cardLayout.getChildAt(i) instanceof AppCompatButton){
                    cardButtons.add((AppCompatButton) cardLayout.getChildAt(i));
                }
            }
            vertMenu = (ImageView) view.findViewById(R.id.cardMenu);
        }
    }



}
