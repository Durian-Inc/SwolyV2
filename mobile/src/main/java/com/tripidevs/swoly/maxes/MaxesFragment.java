package com.tripidevs.swoly.maxes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tripidevs.swoly.DBHandler;
import com.tripidevs.swoly.DatabaseItem;
import com.tripidevs.swoly.R;

import java.util.ArrayList;
import java.util.List;

public class MaxesFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<MaxesCard> list = new ArrayList<>();
    ArrayList<DatabaseItem> currMaxes = new ArrayList<>();
    FloatingActionButton floatingActionButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_maxes,container,false);
        recyclerView = (RecyclerView) v.findViewById(R.id.maxesRecyclerView);
        return v;
    }

    @Override
    public void onStart(){
        super.onStart();
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new MaxesAdapter(list);
        recyclerView.setAdapter(adapter);
        floatingActionButton = (FloatingActionButton) getActivity().findViewById(R.id
                .additionFAB);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewMax();
            }
        });
    }

    protected static void percentageClick(Button button, TextView
            weight){
        float newWeight, oldWeight = Float.parseFloat(weight.getText().toString
                ());
        float percentage = Float.parseFloat(button.getText().toString())/100;
        newWeight = (((oldWeight*percentage)-45)/2);
        weight.setText(String.valueOf(newWeight));
    }

    public void createNewMax(){
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        final View v = layoutInflater.inflate(R.layout.dialog_max_input, null);
        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getContext());
        alertBuilder.setView(v);
        alertBuilder
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText userInputName = (EditText) v.findViewById(R.id
                                .txtLiftName);
                        EditText userInputWeight = (EditText) v.findViewById
                                (R.id.txtWeight);
                        String name = userInputName.getText().toString();
                        int weight = Integer.parseInt( userInputWeight.getText()
                                .toString());
                        DBHandler db = new DBHandler(getActivity(), name
                                .toLowerCase());
                        db.createTable();
                        db.addItem(new DatabaseItem(weight));
                        MaxesCard newMax = new MaxesCard(name,weight);
                        list.add(newMax);
                        adapter.notifyDataSetChanged();
                        printItems(name);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();

    }

    public void printItems(String tableName) {
        DBHandler db = new DBHandler(getActivity(), tableName);

        // Reading all items
        Log.d("SQL: ", "Reading all items from "+tableName+"...");
        List<DatabaseItem> contacts = db.getAllItems();

        for (DatabaseItem cn : contacts) {
            String log = "ID: "+cn.getID()+", Value: " + cn.getValue();
            // Writing Items to log
            Log.d("SQL: ", log);
        }
        db.close();
    }

}
