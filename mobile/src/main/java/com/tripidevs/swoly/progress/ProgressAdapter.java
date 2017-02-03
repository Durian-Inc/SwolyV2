package com.tripidevs.swoly.progress;

<<<<<<< HEAD
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tripidevs.swoly.R;

import java.util.ArrayList;

=======
>>>>>>> 629e7b2b4e4321d9c44785a240495f4eb66cb9fc
/**
 * Created by maver on 1/17/2017.
 */

<<<<<<< HEAD
public class ProgressAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    final String unit = "lbs";
    ArrayList<ProgressCard> logs = new ArrayList<>();
    ArrayList<Integer> progress = new ArrayList<>();

    public ProgressAdapter(ArrayList<ProgressCard> logs){this.logs = logs;}

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        switch (viewType){
            case 0:
                view = inflater.inflate(R.layout.log_card, parent, false);
                viewHolder = new LogViewHolder(view);
                break;
            case 2:
                view = inflater.inflate(R.layout.log_progress_card, parent, false);
                viewHolder = new ProgressViewHolder(view);
                break;
            default:
                view = inflater.inflate(R.layout.log_card, parent, false);
                viewHolder = new LogViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case 0:
                LogViewHolder logViewHolder = (LogViewHolder) holder;
                LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                ProgressCard log = logs.get(position);
                ((LogViewHolder) holder).setWeightTitle(log.getTitle());
                progress = log.getProgress();
                for(short i=0; i<progress.size(); i++){
                    TextView newWeight = new TextView(((LogViewHolder) holder).getLinearLayout().getContext());
                    newWeight.setText(String.valueOf(progress.get(i))+"lbs");
                    newWeight.setTextSize(42);
                    newWeight.setLayoutParams(lparams);
                    logViewHolder.linearLayoutWeigghts.addView(newWeight);
                }
                break;
            case 2:
                ProgressViewHolder progressViewHolder = (ProgressViewHolder) holder;
                progressViewHolder.setWeightTitle(logs.get(position).getTitle());
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

        public void setWeightTitle(String title){
            this.title.setText(title);
        }

        public LinearLayout getLinearLayout(){
            return this.linearLayoutWeigghts;
        }
    }

    @Override
    public int getItemViewType(int position){
       return position % 2 * 2;
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView currWeight;
        public ProgressViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.title_log_progress);
            currWeight = (TextView) view.findViewById(R.id.logProgressWeight);
        }
        public void setWeightTitle(String title){
            this.title.setText(title);
        }
        public void setWeight(String weight){
            this.currWeight.setText(weight);
        }
    }
=======
public class ProgressAdapter {
>>>>>>> 629e7b2b4e4321d9c44785a240495f4eb66cb9fc
}
