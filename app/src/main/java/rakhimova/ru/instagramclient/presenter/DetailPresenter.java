package rakhimova.ru.instagramclient.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import rakhimova.ru.instagramclient.view.DetailView;

@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailView> {

    public void onStart() {
        getViewState().loadPhoto();
    }
}
