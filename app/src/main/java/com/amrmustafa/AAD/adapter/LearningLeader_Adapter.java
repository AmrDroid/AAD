package com.amrmustafa.AAD.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.amrmustafa.AAD.R;
import com.amrmustafa.AAD.model.LearningLeader_Model;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;
public class LearningLeader_Adapter extends RecyclerView.Adapter<LearningLeader_Adapter.MyviewHolder> {

    Context context;
    List<LearningLeader_Model> learnerList;

    public LearningLeader_Adapter(Context context, List<LearningLeader_Model> learnerList) {
        this.context = context;
        this.learnerList = learnerList;
    }

    public void setLearnerList(List<LearningLeader_Model> learnerList) {
        this.learnerList = learnerList;
        notifyDataSetChanged();
    }

    @Override
    public LearningLeader_Adapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_adapter,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(LearningLeader_Adapter.MyviewHolder holder, int position) {
        holder.title.setText(learnerList.get(position).getName().toString());
        holder.desc.setText(learnerList.get(position).gethours()+" Larning Hours , "+learnerList.get(position).getCountry());

        Glide.with(context).load(learnerList.get(position).getbadgeUrl()).apply(RequestOptions.centerCropTransform()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        if(learnerList != null){
            return learnerList.size();
        }
        return 0;

    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView desc;
        ImageView image;

        public MyviewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            desc = (TextView)itemView.findViewById(R.id.desc);
            image = (ImageView)itemView.findViewById(R.id.image);
        }
    }
}


