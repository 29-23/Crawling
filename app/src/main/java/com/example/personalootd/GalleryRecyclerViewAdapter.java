package com.example.personalootd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GalleryRecyclerViewAdapter extends RecyclerView.Adapter<GalleryRecyclerViewHolder>{

    private ArrayList<GalleryRecyclerItem> mItemList;

    public GalleryRecyclerViewAdapter(ArrayList<GalleryRecyclerItem> a_list){
        mItemList = a_list;
    }

    @NonNull
    @Override
    public GalleryRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new GalleryRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryRecyclerViewHolder holder, int position) {
        /*final GalleryRecyclerItem item = mItemList.get(position);

        holder.img.setImageResource(item.getImageResId());*/

        holder.mItem = mItemList.get(position);
        holder.mImageView.setImageURI(mItemList.get(position).uri);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }*/
            }
        });

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

}
