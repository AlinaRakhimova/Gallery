package rakhimova.ru.instagramclient.view;

import com.arellomobile.mvp.MvpView;

public interface GalleryView extends MvpView {

    void showDetailActivity(String url);

    void updateRecyclerView();

    void setFirstEnter(boolean firstEnter);
}
