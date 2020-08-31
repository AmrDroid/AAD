package com.amrmustafa.AAD.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.amrmustafa.AAD.R;
import com.amrmustafa.AAD.model.Skill_IQ_Model;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class IQ_Adapter extends RecyclerView.Adapter<IQ_Adapter.MyviewHolder> {

    Context context;
    List<Skill_IQ_Model> iqList;

    public IQ_Adapter(Context context, List<Skill_IQ_Model> iqList) {
        this.context = context;
        this.iqList = iqList;
    }

    public void setIQList(List<Skill_IQ_Model> iqList) {
        this.iqList = iqList;
        notifyDataSetChanged();
    }

    @Override
    public IQ_Adapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_adapter,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(IQ_Adapter.MyviewHolder holder, int position) {
        holder.title.setText(iqList.get(position).getName().toString());
        holder.desc.setText(iqList.get(position).getScore()+" Skill IQ Score , "+iqList.get(position).getCountry());

        Glide.with(context).load(iqList.get(position).getbadgeUrl()).apply(RequestOptions.centerCropTransform()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        if(iqList != null){
            return iqList.size();
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

