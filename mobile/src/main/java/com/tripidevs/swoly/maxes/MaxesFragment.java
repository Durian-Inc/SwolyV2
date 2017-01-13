package com.tripidevs.swoly.maxes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tripidevs.swoly.R;

import java.util.ArrayList;

public class MaxesFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<MaxesCard> list = new ArrayList<>();
    ArrayList<Button> buttons = new ArrayList<>();
    String[] lifts;
    int[] maxes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifts = getResources().getStringArray(R.array.liftNames);
        maxes = getResources().getIntArray(R.array.liftWeights);
        int count = 0;
        for(String Lift : lifts){
            MaxesCard newMax = new MaxesCard(Lift,
                    String.valueOf(maxes[count]));
            count++;
            list.add(newMax);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_maxes,container,false);

        recyclerView = (RecyclerView) v.findViewById(R.id.maxesRecyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new MaxesAdapter(list);
        recyclerView.setAdapter(adapter);

        return v;
    }

    protected static void percentageClick(Button button, TextView
            weight){
        float newWeight, oldWeight = Float.parseFloat(weight.getText().toString
                ());
        float percentage = Float.parseFloat(button.getText().toString())/100;
        newWeight = (((oldWeight*percentage)-45)/2);
        weight.setText(String.valueOf(newWeight));
    }

}
