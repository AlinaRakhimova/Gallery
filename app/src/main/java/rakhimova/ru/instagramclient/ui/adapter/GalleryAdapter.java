package rakhimova.ru.instagramclient.ui.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.chip.Chip;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import rakhimova.ru.instagramclient.R;
import rakhimova.ru.instagramclient.ui.entity.ItemPhoto;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private ArrayList<ItemPhoto> itemsPhoto;
    private View view;
    private View viewDetails;

    public GalleryAdapter(ArrayList<ItemPhoto> items) {
        itemsPhoto = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_photo, parent, false);
        viewDetails = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_photo_heart_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleView.setText(itemsPhoto.get(position).getTitle());
        holder.photoView.setImageResource(itemsPhoto.get(position).getImageUrl());
        holder.favoriteView.setOnClickListener(v -> showHeartAnimation());
    }

    private void showHeartAnimation() {
        ConstraintLayout constraintLayout = view.findViewById(R.id.root);
        ConstraintLayout detailsLayout = viewDetails.findViewById(R.id.root);
        ConstraintSet mainConstraint = new ConstraintSet();
        ConstraintSet detailsConstraint = new ConstraintSet();
        mainConstraint.clone(constraintLayout);
        detailsConstraint.clone(detailsLayout);

        TransitionSet set = new TransitionSet();
        set.addTransition(new Fade());
        set.setDuration(1200);
        set.setInterpolator(new AccelerateInterpolator());

        TransitionManager.beginDelayedTransition(constraintLayout, set);
        detailsConstraint.applyTo(constraintLayout);
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
        private final Chip favoriteView;

        private ViewHolder(final View view) {
            super(view);
            titleView = view.findViewById(R.id.title);
            photoView = view.findViewById(R.id.content_photo);
            favoriteView = view.findViewById(R.id.chip_favorite);
        }
    }

}

