package rakhimova.ru.instagramclient.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import rakhimova.ru.instagramclient.R;
import rakhimova.ru.instagramclient.UserPreferences;
import rakhimova.ru.instagramclient.presenter.GalleryPresenter;
import rakhimova.ru.instagramclient.view.adapter.GalleryAdapter;

public class MainActivity extends MvpAppCompatActivity implements GalleryView {

    public static final int SPAN_COUNT = 2;
    public static final String URL = "url";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.list)
    RecyclerView photoRecycler;

    @InjectPresenter
    GalleryPresenter presenter;

    private GalleryAdapter galleryAdapter;
    private UserPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        preferences = new UserPreferences(this);
        initUI();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onStart(getFirstEnter());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public boolean getFirstEnter() {
        return preferences.isFirstEnter();
    }

    @Override
    public void setFirstEnter(boolean firstEnter) {
        preferences.setFirstEnter(firstEnter);
    }

    private void initUI() {
        setSupportActionBar(toolbar);
        showPhotoRecycler();
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(photoRecycler);
    }

    private void showPhotoRecycler() {
        photoRecycler.setLayoutManager(new GridLayoutManager(this, SPAN_COUNT));
        galleryAdapter = new GalleryAdapter(this, presenter.getRecyclerMainPresenter());
        photoRecycler.setAdapter(galleryAdapter);
    }

    @Override
    public void showDetailActivity(String url) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(URL, url);
        startActivity(intent);
    }

    @Override
    public void updateRecyclerView() {
        galleryAdapter.notifyDataSetChanged();
    }

}
