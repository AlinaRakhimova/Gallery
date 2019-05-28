package ru.rakhimova.instagramclient.presenter;

import ru.rakhimova.instagramclient.view.IViewHolder;

public interface IRecyclerFavoritePresenter {

    void bindView(IViewHolder iViewHolder);

    int getItemCount();

    void onClickFavorite(IViewHolder holder);

    void onClickDetail(IViewHolder iViewHolder);

}
