package rakhimova.ru.instagramclient.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import rakhimova.ru.instagramclient.R;
import rakhimova.ru.instagramclient.ui.adapter.GalleryAdapter;

public class GalleryFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    @BindView(R.id.list)
    RecyclerView photoRecycler;
    private int mColumnCount = 1;

    public GalleryFragment() {
    }

    public static GalleryFragment newInstance() {
        return new GalleryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_list, container, false);
        showPhotoRecycler();
        return view;
    }

    private void showPhotoRecycler() {
        if (mColumnCount <= 1) {
            photoRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        } else {
            photoRecycler.setLayoutManager(new GridLayoutManager(getActivity(), mColumnCount));
        }
        photoRecycler.setAdapter(new GalleryAdapter(createPhotoList()));
    }

    private ArrayList<ItemPhoto> createPhotoList() {
        ArrayList<ItemPhoto> itemsPhoto = new ArrayList<>();
        itemsPhoto.add(new ItemPhoto("Forest", R.drawable.forest));
        itemsPhoto.add(new ItemPhoto("Field", R.drawable.field));
        itemsPhoto.add(new ItemPhoto("Lake", R.drawable.lake));
        return itemsPhoto;
    }

}
