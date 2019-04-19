package rakhimova.ru.instagramclient.ui.presenter;

import rakhimova.ru.instagramclient.ui.view.IViewHolder;

public interface IRecyclerGalleryPresenter {

    void bindView(IViewHolder iViewHolder);

    int getItemCount();

}
