package ru.rakhimova.instagramclient.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.rakhimova.instagramclient.App;
import ru.rakhimova.instagramclient.model.database.HitDao;
import ru.rakhimova.instagramclient.view.DetailView;

@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailView> {

    private static final String TAG = "Libraries7";

    @Inject
    HitDao hitDao;

    private int id;
    private String url;

    public DetailPresenter() {
        App.getAppComponent().inject(this);
    }

    public void onStart(int id) {
        this.id = id;
        getDetailHitFromDatabase();
    }

    private void getDetailHitFromDatabase() {
        Disposable disposable = hitDao.getDetailHit(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(hit -> {
                    url = hit.getWebformatURL();
                    getViewState().loadPhoto(url);
                    Log.d(TAG, "Данные загружены из БД");
                }, throwable -> Log.e(TAG, "Ошибка загрузки из БД: " + throwable));
    }
}
