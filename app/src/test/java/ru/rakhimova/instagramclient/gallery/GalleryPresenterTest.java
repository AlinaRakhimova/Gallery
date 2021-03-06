package ru.rakhimova.instagramclient.gallery;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;
import ru.rakhimova.instagramclient.di.DaggerTestComponent;
import ru.rakhimova.instagramclient.di.TestComponent;
import ru.rakhimova.instagramclient.di.TestModule;
import ru.rakhimova.instagramclient.model.UserPreferences;
import ru.rakhimova.instagramclient.model.database.HitDao;
import ru.rakhimova.instagramclient.model.entity.Hit;
import ru.rakhimova.instagramclient.model.entity.Photo;
import ru.rakhimova.instagramclient.model.network.PixabayApi;
import ru.rakhimova.instagramclient.presenter.GalleryPresenter;
import ru.rakhimova.instagramclient.view.GalleryView;

@RunWith(MockitoJUnitRunner.class)
public class GalleryPresenterTest {

    private static ArrayList<Hit> hitList;
    @Mock
    GalleryView galleryView;
    private GalleryPresenter presenter;

    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                __ -> Schedulers.trampoline());
    }

    @BeforeClass
    public static void createHitList() {
        hitList = new ArrayList<>();
        Hit hit = new Hit();
        hit.setId(1);
        hit.setWebformatURL("https://pixabay.com/photos/car-vehicle-automotive-automobile-4148514/");
        hitList.add(hit);
    }

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        presenter = Mockito.spy(new GalleryPresenter());
    }

    @Test
    public void getPhotosFromServer_isCorrect() {
        TestComponent component = DaggerTestComponent.builder()
                .testModule(new TestModule() {
                    @Override
                    public PixabayApi getRetrofitApi() {
                        PixabayApi pixabayApi = super.getRetrofitApi();
                        Photo photo = new Photo();
                        photo.hits = hitList;
                        Mockito.when(pixabayApi.requestServer()).thenReturn(Observable.just(photo));
                        return pixabayApi;
                    }

                    @Override
                    public UserPreferences getUserPreferences() {
                        UserPreferences userPreferences = super.getUserPreferences();
                        Mockito.when(userPreferences.isFirstEnter()).thenReturn(true);
                        return userPreferences;
                    }
                }).build();

        component.inject(presenter);
        presenter.attachView(galleryView);
        presenter.getPhotosFromServer();
        Mockito.verify(presenter).ifRequestSuccess();
    }

    @Test
    public void getPhotosFromDatabase_isCorrect() {
        TestComponent component = DaggerTestComponent.builder()
                .testModule(new TestModule() {

                    @Override
                    public HitDao getHitDao() {
                        HitDao hitDao = super.getHitDao();
                        Mockito.when(hitDao.getAll()).thenReturn(Single.just(hitList));
                        return hitDao;
                    }
                }).build();

        component.inject(presenter);
        presenter.attachView(galleryView);
        Mockito.verify(presenter).ifRequestSuccess();
    }

}
