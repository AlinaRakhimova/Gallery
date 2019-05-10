package rakhimova.ru.instagramclient.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import rakhimova.ru.instagramclient.App;
import rakhimova.ru.instagramclient.model.database.HitDao;
import rakhimova.ru.instagramclient.model.entity.Hit;
import rakhimova.ru.instagramclient.model.entity.Photo;
import rakhimova.ru.instagramclient.model.network.RetrofitApi;
import rakhimova.ru.instagramclient.view.GalleryView;
import rakhimova.ru.instagramclient.view.IViewHolder;

@InjectViewState
public class GalleryPresenter extends MvpPresenter<GalleryView> {

    private static final String TAG = "Libraries7";

    private RecyclerGalleryPresenter recyclerMainPresenter = new RecyclerGalleryPresenter();
    private RetrofitApi retrofitApi = new RetrofitApi();

    private List<Hit> hitList;
    private final HitDao hitDao;

    public GalleryPresenter() {
        hitDao = App.getAppDatabase().hitDao();
    }

    public RecyclerGalleryPresenter getRecyclerMainPresenter() {
        return recyclerMainPresenter;
    }

    public void onStart(boolean isFirstEnter) {
        if (isFirstEnter) {
            getPhotosFromServer();
        } else getPhotosFromDatabase();
    }

    private void getPhotosFromServer() {
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
        getViewState().setFirstEnter(false);
    }

    private Single<Long> insertListHits() {
        return Single.create((SingleOnSubscribe<Long>) emitter -> {
            List<Long> longList = hitDao.insertList(hitList);
            emitter.onSuccess(longList.get(0));
        }).subscribeOn(Schedulers.io());
    }

    private void getPhotosFromDatabase() {
        Disposable disposable = hitDao.getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(hits -> {
                    hitList = hits;
                    getViewState().updateRecyclerView();
                    Log.d(TAG, "Данные загружены из БД");
                }, throwable -> Log.e(TAG, "Ошибка загрузки из БД: " + throwable));
    }

    private class RecyclerGalleryPresenter implements IRecyclerGalleryPresenter {

        @Override
        public void bindView(IViewHolder holder) {
            holder.setPhoto(hitList.get(holder.getPos()).webformatURL);
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
            String url = hitList.get(holder.getPos()).webformatURL;
            getViewState().showDetailActivity(url);
        }
    }

}
