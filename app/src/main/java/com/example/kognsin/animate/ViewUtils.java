package com.example.kognsin.animate;

import android.view.View;

/**
 * Created by kognsin on 9/24/2016.
 */

public class ViewUtils {

    public static Pos getActualViewPosition(View view){
        int[] pos = new int[2];
        view.requestLayout();
        view.getLocationOnScreen(pos);
        return new Pos(pos);
    }

}
