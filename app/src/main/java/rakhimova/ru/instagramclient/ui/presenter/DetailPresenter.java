package rakhimova.ru.instagramclient.ui.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import rakhimova.ru.instagramclient.ui.view.DetailView;

@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailView> {

    public void onStart() {
        getViewState().loadPhoto();
    }
}
