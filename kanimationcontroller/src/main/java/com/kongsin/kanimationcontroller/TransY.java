package com.kongsin.kanimationcontroller;

import android.view.View;

/**
 * Created by kognsin on 9/27/2016.
 */

public class TransY implements IAnimateSet{
    private float value;

    public TransY(float value){
        this.value = value;
    }

    @Override
    public float getValue() {
        return value;
    }

    @Override
    public void animateView(View view) {
        view.animate().translationY(value);
    }

    @Override
    public void setValue(float... value) {
        this.value = value[0];
    }
}
