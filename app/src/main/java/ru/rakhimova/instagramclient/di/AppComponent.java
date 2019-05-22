package ru.rakhimova.instagramclient.di;

import dagger.Component;
import ru.rakhimova.instagramclient.model.network.RetrofitApi;
import ru.rakhimova.instagramclient.presenter.DetailPresenter;
import ru.rakhimova.instagramclient.presenter.GalleryPresenter;
import ru.rakhimova.instagramclient.view.DetailActivity;
import ru.rakhimova.instagramclient.view.adapter.GalleryAdapter;

@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(GalleryPresenter galleryPresenter);

    void inject(DetailPresenter detailPresenter);

    void inject(GalleryAdapter galleryAdapter);

    void inject(DetailActivity detailActivity);

    void inject(RetrofitApi retrofitApi);
}
