package com.tripidevs.swoly.log;

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
        String tableName = "bench";
        DBHandler db = new DBHandler(getActivity(), tableName);

//        db.logAllTables();
//        db.deleteTable();
//        db.createTable();
//        db.addItem(new DatabaseItem(13));


//        printItems(tableName); // Uncomment this if you want to get the items printed to logcat
//        clearItems(tableName);
//        printItems();
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

    public void clearItems(String tableName) {
        DBHandler db = new DBHandler(getActivity(), tableName);
        db.deleteAllItems();
        String log = "Clearing all items...";
        Log.d("SQL: ", log);
        db.close();
    }
}