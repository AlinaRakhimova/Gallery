package ru.rakhimova.instagramclient.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.rakhimova.instagramclient.presenter.GalleryPresenter;

@Singleton
@Component(modules = TestModule.class)
public interface TestComponent {

    void inject(GalleryPresenter presenter);

}
