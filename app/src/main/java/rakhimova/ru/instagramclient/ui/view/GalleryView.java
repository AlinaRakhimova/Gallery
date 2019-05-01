package rakhimova.ru.instagramclient.ui.view;

import com.arellomobile.mvp.MvpView;

public interface GalleryView extends MvpView {

    void showDetailActivity(String title, int url);

    void showDetailActivity(String title, String url);

}
