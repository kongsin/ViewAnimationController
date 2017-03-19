package com.kongsin.kanimationcontroller;

import android.view.View;

/**
 * Created by kognsin on 9/27/2016.
 */

public class Alpha implements IAnimateSet{
    private float value;

    @Override
    public float getValue() {
        return value;
    }

    public Alpha(float value){
        this.value = value;
    }

    @Override
    public void animateView(View view) {
        view.animate().alpha(value);
    }

    @Override
    public void setValue(float... value) {
        this.value = value[0];
    }
}
