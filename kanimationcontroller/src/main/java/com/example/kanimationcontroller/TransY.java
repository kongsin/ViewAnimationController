package com.example.kanimationcontroller;

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
    public void setView(View view) {
        view.setTranslationY(value);
    }

    @Override
    public void animateView(View view) {
        view.animate().translationY(value);
    }
}
