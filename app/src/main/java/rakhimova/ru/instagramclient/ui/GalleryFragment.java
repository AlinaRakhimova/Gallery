package rakhimova.ru.instagramclient.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.helper.ItemTouchHelper;
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
    private GalleryAdapter galleryAdapter;

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
        galleryAdapter = new GalleryAdapter(createPhotoList());
        showPhotoRecycler();
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(photoRecycler);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                int dragFlags = 0;
                int swipeFlags = ItemTouchHelper.END;
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                galleryAdapter.delete(viewHolder.getAdapterPosition());
            }

            @Override
            public boolean isItemViewSwipeEnabled() {
                return true;
            }
        });
        itemTouchHelper.attachToRecyclerView(photoRecycler);
        return view;
    }

    private void showPhotoRecycler() {
        if (mColumnCount <= 1) {
            photoRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        } else {
            photoRecycler.setLayoutManager(new GridLayoutManager(getActivity(), mColumnCount));
        }
        photoRecycler.setAdapter(galleryAdapter);
    }

    private ArrayList<ItemPhoto> createPhotoList() {
        ArrayList<ItemPhoto> itemsPhoto = new ArrayList<>();
        itemsPhoto.add(new ItemPhoto("Forest", R.drawable.forest));
        itemsPhoto.add(new ItemPhoto("Field", R.drawable.field));
        itemsPhoto.add(new ItemPhoto("Lake", R.drawable.lake));
        return itemsPhoto;
    }

}
