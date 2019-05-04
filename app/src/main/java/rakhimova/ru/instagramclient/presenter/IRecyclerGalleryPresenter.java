package rakhimova.ru.instagramclient.presenter;

import rakhimova.ru.instagramclient.view.IViewHolder;

public interface IRecyclerGalleryPresenter {

    void bindView(IViewHolder iViewHolder);

    int getItemCount();

    void onClickDetail(IViewHolder iViewHolder);

}
