package ru.rakhimova.instagramclient.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.rakhimova.instagramclient.model.database.RoomHelper;
import ru.rakhimova.instagramclient.view.DetailView;

@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailView> {

    private int id;

    @Inject
    RoomHelper roomHelper;

    public void onStart(int id) {
        this.id = id;
        getDetailHitFromDatabase();
    }

    @SuppressLint("CheckResult")
    private void getDetailHitFromDatabase() {
        roomHelper.getHit(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(hit -> {
                    String url = hit.getWebformatURL();
                    String title = hit.getTitle();
                    getViewState().loadPhoto(title, url);
                    getViewState().showToast("Данные загружены из БД");
                }, throwable -> getViewState().showToast("Ошибка загрузки из БД: " + throwable.getMessage()));
    }

    public void onClickFavorite() {
        getViewState().setFavoriteImage(false);
    }

    public void onClickNoFavorite() {
        getViewState().setFavoriteImage(true);
    }

}
