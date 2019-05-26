package ru.rakhimova.instagramclient.di;

import dagger.Component;
import ru.rakhimova.instagramclient.model.database.RoomHelper;
import ru.rakhimova.instagramclient.model.network.PixabayApi;
import ru.rakhimova.instagramclient.presenter.DetailPresenter;
import ru.rakhimova.instagramclient.presenter.FavoritePresenter;
import ru.rakhimova.instagramclient.presenter.GalleryPresenter;
import ru.rakhimova.instagramclient.view.DetailActivity;
import ru.rakhimova.instagramclient.view.adapter.FavoriteAdapter;
import ru.rakhimova.instagramclient.view.adapter.GalleryAdapter;

@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(GalleryPresenter galleryPresenter);

    void inject(DetailPresenter detailPresenter);

    void inject(GalleryAdapter galleryAdapter);

    void inject(DetailActivity detailActivity);

    void inject(PixabayApi pixabayApi);

    void inject(RoomHelper roomHelper);

    void inject(FavoritePresenter presenter);

    void inject(FavoriteAdapter favoriteAdapter);
}
