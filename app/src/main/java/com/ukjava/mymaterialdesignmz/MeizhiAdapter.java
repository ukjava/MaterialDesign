package com.ukjava.mymaterialdesignmz;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MeizhiAdapter extends RecyclerView.Adapter<MeizhiAdapter.ViewHolder> {
    private Context context;
    private List<Meizhi> mMeizhiList;

    public MeizhiAdapter(List<Meizhi> meizhiList){
        mMeizhiList = meizhiList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null){
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.fruit_item,
                parent, false);

        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Meizhi meizhi = mMeizhiList.get(position);
               Intent intent = new Intent(context,MeizhiActivity.class);
               intent.putExtra(MeizhiActivity.FRUIT_NAME,meizhi.getName());
               intent.putExtra(MeizhiActivity.FRUIT_IMAGE_ID,meizhi.getImageId());
               context.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Meizhi meizhi = mMeizhiList.get(position);
        holder.fruitName.setText(meizhi.getName());
        Glide.with(context).load(meizhi.getImageId()).into(holder.fruitImage);
    }

    @Override
    public int getItemCount() {
        return mMeizhiList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView fruitImage;
        TextView fruitName;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            fruitImage = itemView.findViewById(R.id.fruit_image);
            fruitName = itemView.findViewById(R.id.fruit_name);

        }
    }
}
