package com.tripidevs.swoly.log;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tripidevs.swoly.DBHandler;
import com.tripidevs.swoly.DatabaseItem;
import com.tripidevs.swoly.R;

import java.util.List;

public class LogFragment extends Fragment {

    TextView test;
    DBHandler dbHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_log,container,false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onStart() {
        super.onStart();
        DBHandler db = new DBHandler(getActivity());

//        db.addItem(new DatabaseItem("Bench", 135));
//        db.addItem(new DatabaseItem("Bench", 145));
//        db.addItem(new DatabaseItem("Squat", 235));
//        db.addItem(new DatabaseItem("Bench", 170));
//        db.addItem(new DatabaseItem("Deadlift", 140));
//        db.addItem(new DatabaseItem("Squat", 260));
//        db.addItem(new DatabaseItem("Bench", 200));
//        db.addItem(new DatabaseItem("Bench", 205));
//        db.addItem(new DatabaseItem("Deadlift", 160));
//        db.addItem(new DatabaseItem("Deadlift", 170));
//        db.addItem(new DatabaseItem("Squat", 300));
//        db.addItem(new DatabaseItem("Bench", 220));
//        db.addItem(new DatabaseItem("Squat", 320));

        printItems(); // Uncomment this if you want to get the items printed to logcat
//        clearItems();
//        printItems();
    }

    public void printItems() {
        DBHandler db = new DBHandler(getActivity());

        // Reading all items
        Log.d("SQL: ", "Reading all items...");
        List<DatabaseItem> contacts = db.getAllItems();

        for (DatabaseItem cn : contacts) {
            String log = "ID: "+cn.getID()+", Item: " + cn.getItem() + ", Value: " + cn.getValue();
            // Writing Items to log
            Log.d("SQL: ", log);
        }
        db.close();
    }

    public void clearItems() {
        DBHandler db = new DBHandler(getActivity());
        db.deleteAllItems();
        String log = "Clearing all items...";
        Log.d("SQL: ", log);
        db.close();
    }
}