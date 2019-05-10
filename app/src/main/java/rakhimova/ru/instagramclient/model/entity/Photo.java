package rakhimova.ru.instagramclient.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Photo {

    @Expose
    @SerializedName("totalHits")
    public int totalHits;

    @Expose
    @SerializedName("hits")
    public List<Hit> hits;

//    private String title;
//    private int imageUrl;
//
//    public Photo(String title, int imageUrl) {
//        this.title = title;
//        this.imageUrl = imageUrl;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public int getImageUrl() {
//        return imageUrl;
//    }
}