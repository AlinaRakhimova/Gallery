package ru.rakhimova.instagramclient.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SkipStrategy.class)
public interface GalleryView extends MvpView {

    void showDetailActivity(int id);

    void updateRecyclerView();

    void showProgressBar();

    void hideProgressBar();

    void showToast(String message);
}
