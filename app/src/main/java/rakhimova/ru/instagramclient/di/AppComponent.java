package rakhimova.ru.instagramclient.di;

import dagger.Component;
import rakhimova.ru.instagramclient.presenter.DetailPresenter;
import rakhimova.ru.instagramclient.presenter.GalleryPresenter;
import rakhimova.ru.instagramclient.view.DetailActivity;
import rakhimova.ru.instagramclient.view.adapter.GalleryAdapter;

@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(GalleryPresenter galleryPresenter);

    void inject(DetailPresenter detailPresenter);

    void inject(GalleryAdapter galleryAdapter);

    void inject(DetailActivity detailActivity);

}
