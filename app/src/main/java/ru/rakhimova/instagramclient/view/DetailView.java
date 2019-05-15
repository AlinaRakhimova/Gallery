package ru.rakhimova.instagramclient.view;

import com.arellomobile.mvp.MvpView;

public interface DetailView extends MvpView {

    void loadPhoto(String url);

}
