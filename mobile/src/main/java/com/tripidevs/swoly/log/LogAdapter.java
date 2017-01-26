package com.tripidevs.swoly.log;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tripidevs.swoly.R;

import java.util.ArrayList;

/**
 * Created by maver on 1/17/2017.
 */

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.LogViewHolder>{
    final String unit = "lbs";
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
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LogCard log = logs.get(position);
        holder.title.setText(log.getTitle()+": ");
        progress = log.getProgress();

        for(short i=0; i<progress.size(); i++){
            TextView newWeight = new TextView(holder.linearLayoutWeigghts.getContext());
            newWeight.setText(String.valueOf(progress.get(i))+"lbs");
            newWeight.setTextSize(42);
            newWeight.setLayoutParams(lparams);
            holder.linearLayoutWeigghts.addView(newWeight);
        }

    }

    @Override
    public int getItemCount() {
        return logs.size();
    }

    public static class LogViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        LinearLayout linearLayoutWeigghts;
        public LogViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.logTextTitle);
            linearLayoutWeigghts =(LinearLayout) view.findViewById(R.id.linearWeights);

        }
    }
}
