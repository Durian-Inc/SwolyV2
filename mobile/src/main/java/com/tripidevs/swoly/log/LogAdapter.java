package com.tripidevs.swoly.log;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tripidevs.swoly.R;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by maver on 1/17/2017.
 */

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.LogViewHolder>{

    ArrayList<LogCard> logs = new ArrayList<>();
    ArrayList<Integer> progress = new ArrayList<>();

    public LogAdapter(ArrayList<LogCard> logs){this.logs = logs;}


    @Override
    public LogAdapter.LogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .log_card, parent, false);
        LogViewHolder logViewHolder = new LogViewHolder(view);
        return logViewHolder;
    }

    @Override
    public void onBindViewHolder(LogAdapter.LogViewHolder holder, int position) {
        LogCard log = logs.get(position);
        holder.title.setText(log.getTitle());
        progress = log.getProgress();
//
//        for(short i=0; i<holder.textWeights.size(); i++){
//            holder.textWeights.get(i).setText(String.valueOf(progress.get(i)));
//        }

    }

    @Override
    public int getItemCount() {
        return logs.size();
    }

    public static class LogViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        RelativeLayout relativeLayoutWeights;
        ArrayList<TextView> textWeights = new ArrayList<>();
        public LogViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.logTextTitle);
//
//            relativeLayoutWeights = (RelativeLayout) view.findViewById(R.id.relativeWeights);
//            for(short i =0; i<relativeLayoutWeights.getChildCount(); i++){
//                textWeights.add((TextView)relativeLayoutWeights.getChildAt(i));
//            }
        }
    }
}
