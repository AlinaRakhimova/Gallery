package rakhimova.ru.instagramclient.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SkipStrategy.class)
public interface GalleryView extends MvpView {

    void showDetailActivity(int id);

    void updateRecyclerView();

}
