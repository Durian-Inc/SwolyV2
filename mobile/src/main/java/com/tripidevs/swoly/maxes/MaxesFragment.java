package com.tripidevs.swoly.maxes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
    static RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<MaxesCard> list = new ArrayList<>();
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
                createNewMax("Bench", "500");
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

    public void createNewMax(String name, String weight){
        MaxesCard newMax = new MaxesCard(name,weight);
        list.add(newMax);
        adapter.notifyDataSetChanged();
    }

}
