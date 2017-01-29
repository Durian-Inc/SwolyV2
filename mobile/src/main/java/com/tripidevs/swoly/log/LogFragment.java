package com.tripidevs.swoly.log;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.tripidevs.swoly.DBHandler;
import com.tripidevs.swoly.DatabaseItem;
import com.tripidevs.swoly.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class LogFragment extends Fragment {

    TextView test;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.log_fragment,container,false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onStart() {
        super.onStart();

        DBHandler db = new DBHandler(getActivity(), "weight");

        ArrayList<DatabaseItem> list = db.getAllItems();

        DatabaseItem[] arr = list.toArray(new DatabaseItem[list.size()]);

        List<Entry> entries = new ArrayList<Entry>();

        for (DatabaseItem data : arr) {

            // turn your data into Entry objects
            Log.d("SQL: ", Integer.toString(data.getValue()));
            entries.add(new Entry(data.getID(), data.getValue()));
        }

        Utils.init(getActivity());

        LineChart chart = (LineChart) getView().findViewById(R.id.chart);

        LineDataSet dataSet = new LineDataSet(entries, "Weight"); // add entries to dataset
        dataSet.setColor(344489);
        dataSet.setValueTextColor(299229);

        Description e=new Description();
        e.setText("");

        chart.setDescription(e);
        chart.setNoDataText("Log your Weight!");

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate();

//        String tableName = "bench";
//        DBHandler db = new DBHandler(getActivity(),tableName);

//        db.deleteAllTables();
//        db.logAllTables();
//        db.deleteTable();
//        db.createTable();
//        db.addItem(new DatabaseItem(155));

//        printItems(tableName); // Uncomment this if you want to get the items printed to logcat
//        clearItems(tableName);
//        db.close();
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