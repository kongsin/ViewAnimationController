package com.kongsin.kanimationcontroller;

import android.view.View;

/**
 * Created by kognsin on 9/27/2016.
 */

public class TransX implements IAnimateSet{
    private float value;

    public TransX(float value){
        this.value = value;
    }

    @Override
    public float getValue() {
        return value;
    }

    @Override
    public void animateView(View view) {
        view.animate().translationX(value);
    }

    @Override
    public void setValue(float... value) {
        this.value = value[0];
    }
}
