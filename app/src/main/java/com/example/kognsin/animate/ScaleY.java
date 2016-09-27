package com.example.kognsin.animate;

import android.view.View;

/**
 * Created by kognsin on 9/27/2016.
 */

public class ScaleY implements IAnimateSet{
    private float value;

    public float getValue() {
        return value;
    }

    public ScaleY(float value){
        this.value = value;
    }


    @Override
    public void setView(View view) {
        view.setScaleY(value);
    }

    @Override
    public void animateView(View view) {
        view.animate().scaleY(value);
    }
}
