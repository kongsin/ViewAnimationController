package com.example.kanimationcontroller;

import android.graphics.Rect;
import android.view.View;

/**
 * Created by kognsin on 9/24/2016.
 */

public class ViewUtils {

    public static Pos getActualViewPosition(View view){
        Rect rect = new Rect();
        view.requestLayout();
        view.getLocalVisibleRect(rect);
        return new Pos(rect);
    }

    public static float getAnimatedSize(View imageView, float resizesWith){
        float size = imageView.getHeight() * resizesWith;
        return imageView.getHeight()-size;
    }

}
