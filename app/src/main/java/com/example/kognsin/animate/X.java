package com.example.kognsin.animate;

import android.view.View;

/**
 * Created by kognsin on 9/27/2016.
 */

public class X implements IAnimateSet{
    private float value;

    public float getValue() {
        return value;
    }

    public X(float value){
        this.value = value;
    }

    @Override
    public void setView(View view) {
        view.setX(value);
    }

    @Override
    public void animateView(View view) {
        view.animate().x(value);
    }
}
