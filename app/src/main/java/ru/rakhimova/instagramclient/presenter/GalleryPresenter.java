package ru.rakhimova.instagramclient.presenter;

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
import ru.rakhimova.instagramclient.model.UserPreferences;
import ru.rakhimova.instagramclient.model.database.HitDao;
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
    HitDao hitDao;

    @Inject
    UserPreferences userPreferences;

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
        getViewState().showProgressBar();
        Observable<Photo> single = pixabayApi.requestServer();
        Disposable disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(photos -> {
            hitList = photos.hits;
            ifRequestSuccess();
            saveHitsList();
        }, throwable -> getViewState().showToast("Ошибка получения данных с сервера: " + throwable.getMessage()));
    }

    public void ifRequestSuccess() {
        getViewState().updateRecyclerView();
        getViewState().hideProgressBar();
    }

    private void saveHitsList() {
        Disposable disposable = insertListHits().observeOn(AndroidSchedulers.mainThread())
                .subscribe(id -> {
                    getViewState().showToast("Фотографии сохранены в БД");
                    saveFirstEnter();
                }, throwable -> getViewState().showToast("Ошибка сохранения в БД: " + throwable));
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
        getViewState().showProgressBar();
        Disposable disposable = hitDao.getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(hits -> {
                    hitList = hits;
                    ifRequestSuccess();
                    getViewState().showToast("Данные загружены из БД");
                }, throwable -> getViewState().showToast("Ошибка загрузки из БД: " + throwable));
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
        public void onClickDetail(IViewHolder holder) {
            int id = hitList.get(holder.getPos()).getId();
            getViewState().showDetailActivity(id);
        }
    }

}
