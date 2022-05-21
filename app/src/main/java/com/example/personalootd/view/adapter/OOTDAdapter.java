package com.example.personalootd.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalootd.R;
import com.example.personalootd.view.OOTDItem;

import java.util.List;

public class OOTDAdapter extends RecyclerView.Adapter<OOTDAdapter.OOTDViewHolder> {

    private int itemLayout;
    private List<OOTDItem> mItemList;

    public OOTDAdapter(List<OOTDItem> itemList, int itemLayout){

        this.mItemList = itemList;
        this.itemLayout = itemLayout;
    }

    public List<OOTDItem> getmItemList() {
        return mItemList;
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


        /*  Gallery Adapter Code 복붙.. 가져오는 코드 새로 만들어야 함
        Glide.with(mActivity)
                .load(viewHolder.getImgPath())
                .centerCrop()
                .transition(withCrossFade())
                .into(viewHolder.itemImg);

         */
        viewHolder.itemImg.setImageResource(ootdItem.getImg());

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
                        //
                    }
                }
            });

        }


    }
}
