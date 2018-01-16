package com.tripidevs.swoly.maxes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tripidevs.swoly.DBHandler;
import com.tripidevs.swoly.DatabaseItem;
import com.tripidevs.swoly.R;

import java.util.ArrayList;

/**
 * Created by Innocent on 1/10/2017.
 */

public class MaxesAdapter extends RecyclerView.Adapter<MaxesAdapter.MaxesViewHolder> {
    ArrayList<MaxesCard> maxes = new ArrayList<>();



    private int lastPos = -1;

    public MaxesAdapter(ArrayList<MaxesCard> maxes){
        this.maxes = maxes;
    }

    @Override
    public MaxesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .maxes_card, parent, false);
        MaxesViewHolder maxesViewHolder = new MaxesViewHolder(view);
        return maxesViewHolder;
    }

    @Override
    public void onBindViewHolder(final MaxesViewHolder holder, int position) {
        MaxesCard max = maxes.get(position);
        holder.lift.setText(max.getLiftName());
        holder.weight.setText(String.valueOf(max.getLiftMax()));
        holder.cardPosition = holder.getAdapterPosition();
        for (final Button button: holder.cardButtons) {
            if(button.getText().length() >= 1){
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        percentageClick(button, holder.lift, holder
                                .weight, v);
                    }
                });
            }
            else if(button.getId() == R.id.customIcon){
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setCustomPercentage(view, holder.lift, holder.weight, holder.customPercent);
                    }
                });
            }

        }
        holder.vertMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp(v, holder.lift, holder.weight, holder.cardPosition);
            }
        });
        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return maxes.size();
    }

    protected void showPopUp(final View view, final TextView lift, final
    TextView weight, final int position){
        final PopupMenu popup = new PopupMenu(view.getContext(),view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.max_card_menu, popup.getMenu());
        final Animation deleteAnimation = AnimationUtils.loadAnimation(view.getContext(),
                android.R.anim
                .slide_out_right);
        final Animation editItemAnimation = AnimationUtils.loadAnimation
                (weight.getContext(), android.R
                        .anim.fade_in);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_edit:
                        LayoutInflater layoutInflater = LayoutInflater.from
                                (view.getContext());
                        final View v = layoutInflater.inflate(R.layout.maxes_dialog_edit,
                                null);
                        final AlertDialog.Builder alertBuilder = new
                                AlertDialog.Builder(v.getContext());

                        alertBuilder.setView(v);
                        alertBuilder
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        EditText newMax = (EditText) v
                                                .findViewById(R.id.editNewMax);
                                        DBHandler dbHandler = new DBHandler(v
                                                .getContext(), MaxesFragment
                                                .createValidString(lift.getText()
                                                        .toString().toLowerCase().trim()));
                                        dbHandler.addItem(new DatabaseItem
                                                (Integer.parseInt(newMax
                                                        .getText().toString())));
                                        dbHandler.close();
                                        weight.startAnimation(editItemAnimation);
                                        weight.setText(newMax.getText());
                                    }
                                })
                                .setCancelable(false);
                        AlertDialog alertDialog = alertBuilder.create();
                        alertDialog.show();
                        return true;
                    case R.id.menu_delete:
                        DBHandler dbHandler = new DBHandler(view
                                .getContext(), MaxesFragment
                                .createValidString(lift.getText()
                                        .toString().toLowerCase().trim()));
                        dbHandler.addItem(new DatabaseItem(-1));
                        dbHandler.close();
                        maxes.remove(position);
                        view.startAnimation(deleteAnimation);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, maxes.size());
                        return true;
                    case R.id.menu_deleteHistory:
                        DBHandler db = new DBHandler(view.getContext(), MaxesFragment
                                .createValidString(lift.getText()
                                        .toString().toLowerCase().trim()));
                        db.deleteTable();
                        db.close();
                        maxes.remove(position);
                        view.startAnimation(deleteAnimation);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, maxes.size());
                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.show();
    }

    protected void setCustomPercentage(View view, final TextView title,
                                       final TextView currMax, final Button btnCustomPercent){
        LayoutInflater layoutInflater = LayoutInflater.from
                (view.getContext());
        final View v = layoutInflater.inflate(R.layout.maxes_dialog_custompercent,
                null);
        final AlertDialog.Builder alertBuilder = new
                AlertDialog.Builder(v.getContext());

        alertBuilder.setView(v);
        alertBuilder
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText customPercentage = (EditText) v
                                .findViewById(R.id.editPercent);
                        btnCustomPercent.setText(customPercentage.getText().toString());
                        btnCustomPercent.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                percentageClick(btnCustomPercent, title, currMax, v);
                            }
                        });
                    }
                })
                .setCancelable(false);
        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();
    }

    protected void percentageClick(Button button,TextView title,
                                   TextView currMax, View v){
        final Animation percentAnimation = AnimationUtils.loadAnimation
                (currMax.getContext(), android.R.anim.fade_in);
        String liftName = MaxesFragment.createValidString(title.getText().toString()
                .toLowerCase().trim());
        DBHandler db = new DBHandler(v.getContext(), liftName);
        float newWeight, oldWeight = findLastValue(db, liftName);
        float percentage = Float.parseFloat(button.getText().toString())/100;
        newWeight = Math.round((((oldWeight*percentage)-45)/2));
        currMax.startAnimation(percentAnimation);
        currMax.setText(String.valueOf(newWeight));
        db.close();
    }

    protected static int findLastValue(DBHandler db, String tableName){
        int foundValue;
        String selectQuery = "SELECT  * FROM " + tableName;
        SQLiteDatabase db1 = db.getReadableDatabase();
        Cursor cursor = db1.rawQuery(selectQuery, null);
        cursor.moveToLast();
        foundValue = Integer.parseInt(cursor.getString(1));
        cursor.close();
        return foundValue;
    }


    private void  setAnimation(View viewToAnimate, int pos){
        if(pos > lastPos){
            Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(),
                    android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPos = pos;
        }
    }

    public static class MaxesViewHolder extends RecyclerView.ViewHolder{
        TextView lift;
        TextView weight;
        RelativeLayout cardLayout;
        ArrayList<AppCompatButton> cardButtons = new ArrayList<>();
        ImageView vertMenu;
        FloatingActionButton floatingActionButton;
        int cardPosition;
        View viewCard;
        Button customPercent;

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
                    if(cardLayout.getChildAt(i).getId() == R.id.customPercent) {
                        customPercent = (Button) cardLayout.getChildAt(i);
                    }
                }
            }
            vertMenu = (ImageView) view.findViewById(R.id.cardMenu);
            viewCard = view;
        }
    }



}
