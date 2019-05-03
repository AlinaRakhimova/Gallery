package rakhimova.ru.instagramclient.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import rakhimova.ru.instagramclient.R;
import rakhimova.ru.instagramclient.ui.presenter.GalleryPresenter;
import rakhimova.ru.instagramclient.ui.view.adapter.GalleryAdapter;

public class GalleryFragment extends MvpAppCompatFragment implements GalleryView {

    public static final int SPAN_COUNT = 2;
    public static final String URL = "url";

    @BindView(R.id.list)
    RecyclerView photoRecycler;

    @InjectPresenter
    GalleryPresenter presenter;
    private GalleryAdapter galleryAdapter;

    public GalleryFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_list, container, false);
        ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    private void initUI() {
        showPhotoRecycler();
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(photoRecycler);
    }

    private void showPhotoRecycler() {
        photoRecycler.setLayoutManager(new GridLayoutManager(getActivity(), SPAN_COUNT));
        galleryAdapter = new GalleryAdapter(getActivity(), presenter.getRecyclerMainPresenter());
        photoRecycler.setAdapter(galleryAdapter);
    }

    @Override
    public void showDetailActivity(String url) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        //   intent.putExtra("title", title);
        intent.putExtra(URL, url);
        startActivity(intent);
    }

    @Override
    public void updateRecyclerView() {
        galleryAdapter.notifyDataSetChanged();
    }

}
