package rakhimova.ru.instagramclient.ui.entity;

public class ItemPhoto {

    private String title;
    private int imageUrl;

    public ItemPhoto(String title, int imageUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public int getImageUrl() {
        return imageUrl;
    }
}