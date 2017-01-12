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
//        printItems(); // Uncomment this if you want to get the items printed to logcat
    }

    public void printItems() {
        DBHandler db = new DBHandler(getActivity());

        // Reading all items
        Log.d("Reading: ", "Reading all items..");
        List<DatabaseItem> contacts = db.getAllItems();

        for (DatabaseItem cn : contacts) {
            String log = "ID: "+cn.getID()+", Item: " + cn.getItem() + ", Value: " + cn.getValue();
            // Writing Items to log
            Log.d("Item: ", log);
        }
    }
}