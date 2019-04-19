package rakhimova.ru.instagramclient.ui.model;

import java.util.ArrayList;

import rakhimova.ru.instagramclient.R;
import rakhimova.ru.instagramclient.ui.model.entity.ItemPhoto;

public class PhotoData {

    public ArrayList<ItemPhoto> getPhotoList() {
        ArrayList<ItemPhoto> itemsPhoto = new ArrayList<>();
        itemsPhoto.add(new ItemPhoto("Forest", R.drawable.forest));
        itemsPhoto.add(new ItemPhoto("Field", R.drawable.field));
        itemsPhoto.add(new ItemPhoto("Lake", R.drawable.lake));
        return itemsPhoto;
    }

}
