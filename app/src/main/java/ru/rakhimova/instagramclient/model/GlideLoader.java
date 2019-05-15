package ru.rakhimova.instagramclient.model;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class GlideLoader {

    private Context context;

    public GlideLoader(Context context) {
        this.context = context;
    }

    public void loadImage(String url, ImageView imageView) {
        Glide
                .with(context)
                .load(url)
                .into(imageView);
    }
}
