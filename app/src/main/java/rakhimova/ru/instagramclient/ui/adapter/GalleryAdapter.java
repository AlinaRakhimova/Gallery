package rakhimova.ru.instagramclient.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import rakhimova.ru.instagramclient.R;
import rakhimova.ru.instagramclient.ui.ItemPhoto;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private ArrayList<ItemPhoto> itemsPhoto;

    public GalleryAdapter(ArrayList<ItemPhoto> items) {
        itemsPhoto = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_photo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleView.setText(itemsPhoto.get(position).getTitle());
        holder.photoView.setImageResource(itemsPhoto.get(position).getImageUrl());

    }

    @Override
    public int getItemCount() {
        return itemsPhoto.size();
    }

    public void delete(int adapterPosition) {
        itemsPhoto.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView photoView;
        private final TextView titleView;

        private ViewHolder(final View view) {
            super(view);
            titleView = view.findViewById(R.id.title);
            photoView = view.findViewById(R.id.content_photo);
        }
    }

}

