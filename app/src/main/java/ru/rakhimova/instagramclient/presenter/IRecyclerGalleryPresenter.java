package ru.rakhimova.instagramclient.presenter;

import ru.rakhimova.instagramclient.view.IViewHolder;

public interface IRecyclerGalleryPresenter {

    void bindView(IViewHolder iViewHolder);

    int getItemCount();

    void onClickDetail(IViewHolder iViewHolder);

}
