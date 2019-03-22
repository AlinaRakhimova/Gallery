package rakhimova.ru.instagramclient.ui;

public class ItemPhoto {

    private String title;
    private int imageUrl;

    ItemPhoto(String title, int imageUrl) {
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