package rakhimova.ru.instagramclient.ui.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import rakhimova.ru.instagramclient.ui.model.entity.Hit;
import rakhimova.ru.instagramclient.ui.model.entity.Photo;
import rakhimova.ru.instagramclient.ui.model.network.RetrofitApi;
import rakhimova.ru.instagramclient.ui.view.GalleryView;
import rakhimova.ru.instagramclient.ui.view.IViewHolder;

@InjectViewState
public class GalleryPresenter extends MvpPresenter<GalleryView> {

    private static final String TAG = "Libraries7";

    private RecyclerGalleryPresenter recyclerMainPresenter = new RecyclerGalleryPresenter();
    private RetrofitApi retrofitApi = new RetrofitApi();
    private List<Hit> hitList;

    public RecyclerGalleryPresenter getRecyclerMainPresenter() {
        return recyclerMainPresenter;
    }

    @Override
    protected void onFirstViewAttach() {
        getAllPhoto();
    }

    private void getAllPhoto() {
        Observable<Photo> single = retrofitApi.requestServer();

        Disposable disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(photos -> {
            hitList = photos.hits;
            getViewState().updateRecyclerView();
        }, throwable -> Log.e(TAG, "onError"));
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
