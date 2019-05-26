package ru.rakhimova.instagramclient.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.rakhimova.instagramclient.R;
import ru.rakhimova.instagramclient.di.App;
import ru.rakhimova.instagramclient.model.GlideLoader;
import ru.rakhimova.instagramclient.presenter.IRecyclerFavoritePresenter;
import ru.rakhimova.instagramclient.view.IViewHolder;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private final IRecyclerFavoritePresenter presenter;

    @Inject
    GlideLoader glideLoader;

    public FavoriteAdapter(IRecyclerFavoritePresenter presenter) {
        App.getAppComponent().inject(this);
        this.presenter = presenter;
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
        holder.position = position;
        presenter.bindView(holder);
        holder.photoView.setOnClickListener(v -> presenter.onClickDetail(holder));
        holder.favorite.setOnClickListener(v -> presenter.onClickFavorite(holder));
    }

    @Override
    public int getItemCount() {
        return presenter.getItemCount();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements IViewHolder {

        @BindView(R.id.content_photo)
        ImageView photoView;

        @BindView(R.id.title)
        TextView titleView;

        @BindView(R.id.favorite_border)
        ImageButton favoriteBorder;

        @BindView(R.id.favorite)
        ImageButton favorite;

        private int position = 0;

        private ViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
            setFavoriteImage(true);
        }

        @Override
        public void setPhoto(String title, String url) {
            titleView.setText(title);
            glideLoader.loadImage(url, photoView);
        }

        @Override
        public int getPos() {
            return position;
        }

        @Override
        public void setFavoriteImage(boolean isFavorite) {
            if (isFavorite) {
                favoriteBorder.setVisibility(View.INVISIBLE);
                favorite.setVisibility(View.VISIBLE);
            }
        }
    }

}
