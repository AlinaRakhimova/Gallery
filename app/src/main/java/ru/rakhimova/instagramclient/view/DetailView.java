package ru.rakhimova.instagramclient.view;

import com.arellomobile.mvp.MvpView;

public interface DetailView extends MvpView {

    void setFavoriteImage(boolean isFavorite);

    void loadPhoto(String title, String url);

    void showToast(String message);

}
