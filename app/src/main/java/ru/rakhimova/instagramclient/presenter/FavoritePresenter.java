package ru.rakhimova.instagramclient.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.rakhimova.instagramclient.model.database.RoomHelper;
import ru.rakhimova.instagramclient.model.entity.Hit;
import ru.rakhimova.instagramclient.view.FavoriteView;
import ru.rakhimova.instagramclient.view.IViewHolder;

@InjectViewState
public class FavoritePresenter extends MvpPresenter<FavoriteView> {

    private List<Hit> hitList;
    private RecyclerFavoritePresenter recyclerFavoritePresenter;

    @Inject
    RoomHelper roomHelper;

    public FavoritePresenter() {
        recyclerFavoritePresenter = new RecyclerFavoritePresenter();
    }

    public RecyclerFavoritePresenter getRecyclerFavoritePresenter() {
        return recyclerFavoritePresenter;
    }

    @Override
    public void onFirstViewAttach() {
        getPhotosFromDatabase();
    }

    @SuppressLint("CheckResult")
    private void getPhotosFromDatabase() {
        getViewState().showProgressBar();
        roomHelper.getFavoriteList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(hits -> {
                    hitList = hits;
                    ifRequestSuccess();
                }, throwable -> getViewState().showToast("Ошибка загрузки из БД: " + throwable));
    }

    @SuppressLint("CheckResult")
    private void deleteFavoritePhotoFromDatabase(int idPhoto) {
        deleteFavoritePhotoToDatabaseObservable(idPhoto).observeOn(AndroidSchedulers.mainThread())
                .subscribe(hits -> {
                            getViewState().updateRecyclerView();
                            getViewState().showToast("Фото удалено из Избранного");
                        },
                        throwable -> getViewState().showToast("Ошибка удаления из БД: " + throwable));
    }

    private Single<Integer> deleteFavoritePhotoToDatabaseObservable(int idPhoto) {
        return Single.create((SingleOnSubscribe<Integer>) emitter -> {
            int id = roomHelper.deleteFavoritePhoto(idPhoto);
            emitter.onSuccess(id);
        }).subscribeOn(Schedulers.io());
    }

    private void ifRequestSuccess() {
        getViewState().updateRecyclerView();
        getViewState().hideProgressBar();
    }

    public class RecyclerFavoritePresenter implements IRecyclerFavoritePresenter {

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
        public void onClickFavorite(IViewHolder holder) {
            deleteFavoritePhotoFromDatabase(hitList.get(holder.getPos()).getId());
            hitList.remove(holder.getPos());
        }

        @Override
        public void onClickDetail(IViewHolder holder) {
            int id = hitList.get(holder.getPos()).getId();
            getViewState().showDetailActivity(id);
        }
    }

}
