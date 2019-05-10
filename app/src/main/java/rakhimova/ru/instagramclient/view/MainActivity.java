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
import rakhimova.ru.instagramclient.presenter.GalleryPresenter;
import rakhimova.ru.instagramclient.view.adapter.GalleryAdapter;

public class MainActivity extends MvpAppCompatActivity implements GalleryView {

    public static final int SPAN_COUNT = 2;
    public static final String ID = "id";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.list)
    RecyclerView photoRecycler;

    @InjectPresenter
    GalleryPresenter presenter;

    private GalleryAdapter galleryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initUI();
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

}
