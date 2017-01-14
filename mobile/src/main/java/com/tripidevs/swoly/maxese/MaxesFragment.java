package com.tripidevs.swoly.maxese;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.tripidevs.swoly.DBHandler;
import com.tripidevs.swoly.DatabaseItem;
import com.tripidevs.swoly.R;

import java.util.ArrayList;

public class MaxesFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<MaxesCard> list = new ArrayList<>();
    ArrayList<DatabaseItem> currMaxes;
    ArrayList<String> liftNames;
    FloatingActionButton floatingActionButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            DBHandler dbHandler = new DBHandler(getActivity());
            liftNames = dbHandler.listAllTables();
            DBHandler currentTable;
            for(String liftName: liftNames){
                currentTable = new DBHandler(getActivity(), liftName);
                currMaxes = currentTable.getAllItems();
                MaxesCard newMax = new MaxesCard(createTitle(liftName), MaxesAdapter
                        .findLastValue(currentTable, liftName));
                list.add(newMax);
            }
        }
        catch (Exception e){

        }
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
                        String newTitle;
                        EditText userInputName = (EditText) v.findViewById(R.id
                                .txtLiftName);
                        EditText userInputWeight = (EditText) v.findViewById
                                (R.id.txtWeight);
                        String name = userInputName.getText().toString();
                        int weight = Integer.parseInt( userInputWeight.getText()
                                .toString());
                        DBHandler db = new DBHandler(getActivity(),
                                createValidString(name.toLowerCase()));
                        db.addItem(new DatabaseItem(weight));
                        MaxesCard newMax = new MaxesCard(name,weight);
                        list.add(newMax);
                        adapter.notifyDataSetChanged();
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


    protected static String createValidString(String oldString){
        String newString="";
        for(Character character: oldString.toCharArray())
        {
            if(!character.equals(" "))
                newString+="_";
            else
                newString+=character;
        }
        return newString;
    }

    protected static String createTitle(String oldString){
        String newString="";
        for(Character character: oldString.toCharArray()){
            if(character.equals("_"))
                newString+=" ";
            else
                newString+=character;
        }
        return newString;
    }
}
