package ru.rakhimova.instagramclient.presenter;

import ru.rakhimova.instagramclient.view.IViewHolder;

public interface IRecyclerGalleryPresenter {

    void bindView(IViewHolder iViewHolder);

    int getItemCount();

    void onClickNoFavorite(IViewHolder holder);

    void onClickFavorite(IViewHolder holder);

    void addPhotoToFavorite(IViewHolder holder);

    void deletePhotoFromFavorite(IViewHolder holder);

    void onClickDetail(IViewHolder iViewHolder);

}
