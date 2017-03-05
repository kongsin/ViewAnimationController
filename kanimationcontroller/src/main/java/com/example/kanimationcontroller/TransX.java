package com.example.kanimationcontroller;

import android.animation.Animator;
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
    public void setView(View view) {
        view.setTranslationX(value);
    }

    @Override
    public void animateView(View view, Animator.AnimatorListener listener) {
        view.animate().translationX(value).setListener(listener);
    }

    @Override
    public void setValue(float... value) {

    }
}
