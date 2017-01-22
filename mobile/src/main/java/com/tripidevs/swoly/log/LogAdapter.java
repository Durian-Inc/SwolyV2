package com.tripidevs.swoly.log;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by maver on 1/17/2017.
 */

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.LogViewHolder>{

    ArrayList<LogCard> logs = new ArrayList<>();

    public LogAdapter(ArrayList<LogCard> logs){this.logs = logs;}


    @Override
    public LogAdapter.LogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(LogAdapter.LogViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class LogViewHolder extends RecyclerView.ViewHolder{
        TextView title;

        public LogViewHolder(View view){
            super(view);
        }
    }
}
