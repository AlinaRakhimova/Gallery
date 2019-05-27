package ru.rakhimova.instagramclient.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.rakhimova.instagramclient.R;
import ru.rakhimova.instagramclient.di.App;
import ru.rakhimova.instagramclient.presenter.FavoritePresenter;
import ru.rakhimova.instagramclient.view.adapter.FavoriteAdapter;

import static ru.rakhimova.instagramclient.model.Constants.ID;

public class FavoriteActivity extends MvpAppCompatActivity implements FavoriteView {

    @Inject
    Context context;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.list)
    RecyclerView photoRecycler;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.fab_favorite)
    FloatingActionButton favorite;

    @InjectPresenter
    FavoritePresenter presenter;

    private FavoriteAdapter favoriteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        App.getAppComponent().inject(presenter);
        initUI();
    }

    private void initUI() {
        showPhotoRecycler();
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(photoRecycler);
        favorite.hide();
    }

    private void showPhotoRecycler() {
        photoRecycler.setLayoutManager(new LinearLayoutManager(this));
        favoriteAdapter = new FavoriteAdapter(presenter.getRecyclerFavoritePresenter());
        photoRecycler.setAdapter(favoriteAdapter);
    }

    @Override
    public void showDetailActivity(int id) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(ID, id);
        startActivity(intent);
    }

    @Override
    public void updateRecyclerView() {
        favoriteAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(ProgressBar.INVISIBLE);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
