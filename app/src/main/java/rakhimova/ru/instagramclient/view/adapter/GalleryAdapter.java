package rakhimova.ru.instagramclient.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rakhimova.ru.instagramclient.App;
import rakhimova.ru.instagramclient.R;
import rakhimova.ru.instagramclient.model.GlideLoader;
import rakhimova.ru.instagramclient.presenter.IRecyclerGalleryPresenter;
import rakhimova.ru.instagramclient.view.IViewHolder;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private final IRecyclerGalleryPresenter presenter;

    @Inject
    GlideLoader glideLoader;

    public GalleryAdapter(IRecyclerGalleryPresenter presenter) {
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

        private int position = 0;

        private ViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public void setPhoto(String url) {
            glideLoader.loadImage(url, photoView);
        }

        @Override
        public int getPos() {
            return position;
        }
    }

}
