package ru.rakhimova.instagramclient.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.rakhimova.instagramclient.model.UserPreferences;
import ru.rakhimova.instagramclient.model.database.RoomHelper;
import ru.rakhimova.instagramclient.model.entity.FavoriteHit;
import ru.rakhimova.instagramclient.model.entity.Hit;
import ru.rakhimova.instagramclient.model.entity.Photo;
import ru.rakhimova.instagramclient.model.network.PixabayApi;
import ru.rakhimova.instagramclient.view.GalleryView;
import ru.rakhimova.instagramclient.view.IViewHolder;

@InjectViewState
public class GalleryPresenter extends MvpPresenter<GalleryView> {

    private List<Hit> hitList;

    @Inject
    PixabayApi pixabayApi;
    private RecyclerGalleryPresenter recyclerGalleryPresenter;

    @Inject
    UserPreferences userPreferences;

    @Inject
    RoomHelper roomHelper;

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

    @SuppressLint("CheckResult")
    public void getPhotosFromServer() {
        getViewState().showProgressBar();
        Observable<Photo> single = pixabayApi.requestServer();
        single.observeOn(AndroidSchedulers.mainThread()).subscribe(photos -> {
            hitList = photos.hits;
            ifRequestSuccess();
            saveHitsList();
        }, throwable -> getViewState().showToast("Ошибка получения данных с сервера: " + throwable.getMessage()));
    }

    public void ifRequestSuccess() {
        getViewState().updateRecyclerView();
        getViewState().hideProgressBar();
    }

    @SuppressLint("CheckResult")
    private void saveHitsList() {
        saveHitListObservable().observeOn(AndroidSchedulers.mainThread())
                .subscribe(id -> {
                    getViewState().showToast("Фотографии сохранены в БД");
                    saveFirstEnter();
                }, throwable -> getViewState().showToast("Ошибка сохранения в БД: " + throwable));
    }

    private void saveFirstEnter() {
        setFirstEnter(false);
    }

    private Single<Long> saveHitListObservable() {
        return Single.create((SingleOnSubscribe<Long>) emitter -> {
            List<Long> longList = roomHelper.insertPhotoList(hitList);
            emitter.onSuccess(longList.get(0));
        }).subscribeOn(Schedulers.io());
    }

    @SuppressLint("CheckResult")
    private void getPhotosFromDatabase() {
        getViewState().showProgressBar();
        roomHelper.getPhotoList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(hits -> {
                    hitList = hits;
                    ifRequestSuccess();
                    getViewState().showToast("Данные загружены из БД");
                }, throwable -> getViewState().showToast("Ошибка загрузки из БД: " + throwable));
    }

    @SuppressLint("CheckResult")
    private void insertFavoritePhotoToDatabase(int idPhoto) {
        insertFavoritePhotoToDatabaseObservable(idPhoto).observeOn(AndroidSchedulers.mainThread())
                .subscribe(hits -> getViewState().showToast("Фото сохранено в Избранное"),
                        throwable -> getViewState().showToast("Ошибка сохранения в БД: " + throwable));
    }

    private Single<Long> insertFavoritePhotoToDatabaseObservable(int idPhoto) {
        return Single.create((SingleOnSubscribe<Long>) emitter -> {
            FavoriteHit favoriteHit = new FavoriteHit();
            favoriteHit.setIdPhoto(idPhoto);
            Long id = roomHelper.insertFavoritePhoto(favoriteHit);
            emitter.onSuccess(id);
        }).subscribeOn(Schedulers.io());
    }

    @SuppressLint("CheckResult")
    private void deleteFavoritePhotoFromDatabase(int idPhoto) {
        deleteFavoritePhotoToDatabaseObservable(idPhoto).observeOn(AndroidSchedulers.mainThread())
                .subscribe(hits -> getViewState().showToast("Фото удалено из Избранного"),
                        throwable -> getViewState().showToast("Ошибка удаления из БД: " + throwable));
    }

    private Single<Integer> deleteFavoritePhotoToDatabaseObservable(int idPhoto) {
        return Single.create((SingleOnSubscribe<Integer>) emitter -> {
            int id = roomHelper.deleteFavoritePhoto(idPhoto);
            emitter.onSuccess(id);
        }).subscribeOn(Schedulers.io());
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
            Hit hit = hitList.get(holder.getPos());
            holder.setPhoto(hit.getTitle(), hit.getWebformatURL());
        }

        @Override
        public int getItemCount() {
            if (hitList != null) {
                return hitList.size();
            }
            return 0;
        }

        @Override
        public void onClickNoFavorite(IViewHolder holder) {
            holder.setFavoriteImage(true);
        }

        @Override
        public void onClickFavorite(IViewHolder holder) {
            holder.setFavoriteImage(false);
        }

        @Override
        public void addPhotoToFavorite(IViewHolder holder) {
            insertFavoritePhotoToDatabase(hitList.get(holder.getPos()).getId());
        }

        @Override
        public void deletePhotoFromFavorite(IViewHolder holder) {
            deleteFavoritePhotoFromDatabase(hitList.get(holder.getPos()).getId());
        }

        @Override
        public void onClickDetail(IViewHolder holder) {
            int id = hitList.get(holder.getPos()).getId();
            getViewState().showDetailActivity(id);
        }
    }

}
