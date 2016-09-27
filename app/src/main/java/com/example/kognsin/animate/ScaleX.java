package com.example.kognsin.animate;

import android.view.View;

/**
 * Created by kognsin on 9/27/2016.
 */

public class ScaleX implements IAnimateSet{
    private float value;

    public float getValue() {
        return value;
    }

    public ScaleX(float value){
        this.value = value;
    }

    @Override
    public void setView(View view) {
        view.setScaleX(value);
    }

    @Override
    public void animateView(View view) {
        view.animate().scaleX(value);
    }
}
