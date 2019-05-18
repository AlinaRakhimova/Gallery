package ru.rakhimova.instagramclient.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.rakhimova.instagramclient.UserPreferences;
import ru.rakhimova.instagramclient.model.database.HitDao;
import ru.rakhimova.instagramclient.model.entity.Hit;
import ru.rakhimova.instagramclient.model.entity.Photo;
import ru.rakhimova.instagramclient.model.network.RetrofitApi;
import ru.rakhimova.instagramclient.view.GalleryView;
import ru.rakhimova.instagramclient.view.IViewHolder;

@InjectViewState
public class GalleryPresenter extends MvpPresenter<GalleryView> {

    private static final String TAG = "Libraries7";

    private List<Hit> hitList;

    @Inject
    RetrofitApi retrofitApi;

    @Inject
    HitDao hitDao;

    @Inject
    UserPreferences userPreferences;
    private RecyclerGalleryPresenter recyclerGalleryPresenter;

    public GalleryPresenter() {
        recyclerGalleryPresenter = new RecyclerGalleryPresenter();
    }

    public RecyclerGalleryPresenter getRecyclerGalleryPresenter() {
        return recyclerGalleryPresenter;
    }

    @Override
    public void onFirstViewAttach() {
        if (getFirstEnter()) {
            getPhotosFromServer();
        } else getPhotosFromDatabase();
    }

    public List<Hit> getHitList() {
        return hitList;
    }

    public void getPhotosFromServer() {
        Observable<Photo> single = retrofitApi.requestServer();
        Disposable disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(photos -> {
            hitList = photos.hits;
            getViewState().updateRecyclerView();
            saveHitsList();
        }, throwable -> Log.e(TAG, "Ошибка получения данных с сервера"));
    }

    private void saveHitsList() {
        Disposable disposable = insertListHits().observeOn(AndroidSchedulers.mainThread())
                .subscribe(id -> {
                    Log.d(TAG, "Фотографии сохранены в БД: " + id);
                    saveFirstEnter();
                }, throwable -> Log.e(TAG, "Ошибка сохранения в БД: " + throwable));
    }

    private void saveFirstEnter() {
        setFirstEnter(false);
    }

    private Single<Long> insertListHits() {
        return Single.create((SingleOnSubscribe<Long>) emitter -> {
            List<Long> longList = hitDao.insertList(hitList);
            emitter.onSuccess(longList.get(0));
        }).subscribeOn(Schedulers.io());
    }

    public void getPhotosFromDatabase() {
        Disposable disposable = hitDao.getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(hits -> {
                    hitList = hits;
                    getViewState().updateRecyclerView();
                    Log.d(TAG, "Данные загружены из БД");
                }, throwable -> Log.e(TAG, "Ошибка загрузки из БД: " + throwable));
    }

    private boolean getFirstEnter() {
        return userPreferences.isFirstEnter();
    }

    private void setFirstEnter(boolean firstEnter) {
        userPreferences.setFirstEnter(firstEnter);
    }

    public class RecyclerGalleryPresenter implements IRecyclerGalleryPresenter {

        @Override
        public void bindView(IViewHolder holder) {
            holder.setPhoto(hitList.get(holder.getPos()).getWebformatURL());
        }

        @Override
        public int getItemCount() {
            if (hitList != null) {
                return hitList.size();
            }
            return 0;
        }

        @Override
        public void onClickDetail(IViewHolder holder) {
            int id = hitList.get(holder.getPos()).getId();
            getViewState().showDetailActivity(id);
        }
    }

}
