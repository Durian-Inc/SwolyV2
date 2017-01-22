package com.tripidevs.swoly.log;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tripidevs.swoly.DBHandler;
import com.tripidevs.swoly.DatabaseItem;
import com.tripidevs.swoly.R;

import java.util.ArrayList;

public class LogFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<String> names;
    ArrayList<LogCard> list = new ArrayList<>();
    ArrayList<DatabaseItem> items = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.log_fragment,container,false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            DBHandler dbHandler = new DBHandler(getActivity());
            names = dbHandler.listAllTables();
            DBHandler currentTable;
            for(String currentName: names){
                LogCard newLog = new LogCard(createTitle(currentName));
                currentTable = new DBHandler(getContext(), currentName);
                items = currentTable.getAllItems();
                for(DatabaseItem item: items){
                    if(item.getValue() != -1)
                        newLog.addItem(item.getValue());
                }
                list.add(newLog);
            }
            dbHandler.close();
        }
        catch (Exception e){

        }

    }

    @Override
    public void onStart() {
        super.onStart();
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new LogAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    protected static String createTitle(String oldString){
        String newString="";
        newString+=Character.toUpperCase(oldString.toCharArray()[0]);
        for(int i = 1; i<oldString.length(); i++){
            if(oldString.toCharArray()[i] == '_'){
                newString+=' ';
                newString+=Character.toUpperCase(oldString.toCharArray()[i+1]);
                i++;
            }
            else
                newString+=oldString.toCharArray()[i];
        }
        return newString;
    }
}