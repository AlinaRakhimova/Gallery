package ru.rakhimova.instagramclient.transformation;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

public class CubeOutDepthTransformation implements ViewPager.PageTransformer {

    @Override
    public void transformPage(@NonNull View page, float position) {

        if (position < -1) {
            page.setAlpha(0);
        } else if (position <= 0) {
            page.setAlpha(1);
            page.setPivotX(page.getWidth());
            page.setRotationY(-90 * Math.abs(position));
        } else if (position <= 1) {
            page.setAlpha(1);
            page.setPivotX(0);
            page.setRotationY(90 * Math.abs(position));
        } else {
            page.setAlpha(0);
        }

        if (Math.abs(position) <= 0.5) {
            page.setScaleY(Math.max(0.4f, 1 - Math.abs(position)));
        } else if (Math.abs(position) <= 1) {
            page.setScaleY(Math.max(0.4f, 1 - Math.abs(position)));
        }
    }

}
