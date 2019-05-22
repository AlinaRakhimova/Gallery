package ru.rakhimova.instagramclient.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.rakhimova.instagramclient.App;
import ru.rakhimova.instagramclient.R;
import ru.rakhimova.instagramclient.presenter.GalleryPresenter;
import ru.rakhimova.instagramclient.view.adapter.GalleryAdapter;

public class MainActivity extends MvpAppCompatActivity implements GalleryView {

    public static final int SPAN_COUNT = 2;
    public static final String ID = "id";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.list)
    RecyclerView photoRecycler;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @InjectPresenter
    GalleryPresenter presenter;

    private GalleryAdapter galleryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        App.getAppComponent().inject(presenter);
        initUI();
        leakAsyncTask();
        forceCrash();
    }

    private void leakAsyncTask() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                for (int i = 0; i < 30; i++) {
                    SystemClock.sleep(1000);
                }

                return null;
            }
        }.execute();
    }

    public void forceCrash() {
        throw new RuntimeException("This is a crash");
    }

    private void initUI() {
        setSupportActionBar(toolbar);
        showPhotoRecycler();
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(photoRecycler);
    }

    private void showPhotoRecycler() {
        photoRecycler.setLayoutManager(new GridLayoutManager(this, SPAN_COUNT));
        galleryAdapter = new GalleryAdapter(presenter.getRecyclerGalleryPresenter());
        photoRecycler.setAdapter(galleryAdapter);
    }

    @Override
    public void showDetailActivity(int id) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(ID, id);
        startActivity(intent);
    }

    @Override
    public void updateRecyclerView() {
        galleryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(ProgressBar.INVISIBLE);
    }

}
