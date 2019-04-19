package rakhimova.ru.instagramclient.ui.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import rakhimova.ru.instagramclient.ui.model.PhotoData;
import rakhimova.ru.instagramclient.ui.model.entity.ItemPhoto;
import rakhimova.ru.instagramclient.ui.view.GalleryView;
import rakhimova.ru.instagramclient.ui.view.IViewHolder;

@InjectViewState
public class GalleryPresenter extends MvpPresenter<GalleryView> {

    private RecyclerGalleryPresenter recyclerMainPresenter = new RecyclerGalleryPresenter();

    public RecyclerGalleryPresenter getRecyclerMainPresenter() {
        return recyclerMainPresenter;
    }

    private class RecyclerGalleryPresenter implements IRecyclerGalleryPresenter {

        private PhotoData data = new PhotoData();
        private List<ItemPhoto> photoList = data.getPhotoList();

        @Override
        public void bindView(IViewHolder holder) {
            holder.setPhoto(photoList.get(holder.getPos()));
        }

        @Override
        public int getItemCount() {
            return photoList.size();
        }

    }

}
