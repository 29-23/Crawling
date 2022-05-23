package com.example.personalootd.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.personalootd.OnOOTDClickListener;
import com.example.personalootd.R;
import com.example.personalootd.view.OOTDItem;
import com.example.personalootd.view.activity.MainActivity;

import java.util.List;

public class OOTDAdapter extends RecyclerView.Adapter<OOTDAdapter.OOTDViewHolder> {

    private int itemLayout;
    private List<OOTDItem> mItemList;
    private Context mContext;

    private OnOOTDClickListener onOOTDClickListener;

    public OOTDAdapter(List<OOTDItem> itemList, int itemLayout, Context context){

        this.mItemList = itemList;
        this.itemLayout = itemLayout;
        this.mContext = context;
    }

    public List<OOTDItem> getmItemList() {
        return mItemList;
    }

    public void setOnItemClickListener(OnOOTDClickListener onOOTDClickListener) {
        this.onOOTDClickListener = onOOTDClickListener;
    }

    @NonNull
    @Override
    public OOTDViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(itemLayout, viewGroup, false);
        return new OOTDViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OOTDViewHolder viewHolder, int position) {

        final OOTDItem ootdItem = mItemList.get(position);

        Glide.with(viewHolder.itemView)
                .load(ootdItem.getImage())
                .into(viewHolder.itemImg);

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public class OOTDViewHolder extends RecyclerView.ViewHolder{

        public ImageView itemImg;

        public OOTDViewHolder(View itemView) {
            super(itemView);

            itemImg = (ImageView) itemView.findViewById(R.id.img_ootd);
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    final int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        MainActivity activity = (MainActivity)mContext;
                        activity.goInfoFr();
                    }
                }
            });

        }


    }
}
