package ru.rakhimova.instagramclient.view;

public interface IViewHolder {

    void setPhoto(String title, String url);

    int getPos();

    void setFavoriteImage(boolean isFavorite);

}
