package com.example.kanimationcontroller;

import android.animation.Animator;
import android.view.View;
import android.view.animation.Animation;

/**
 * Created by kognsin on 9/27/2016.
 */

public class Y implements IAnimateSet{
    private float value;

    @Override
    public float getValue() {
        return value;
    }

    public Y(float value){
        this.value = value;
    }

    @Override
    public void setView(View view) {
        view.setY(value);
    }

    @Override
    public void animateView(View view, Animator.AnimatorListener listener) {
        view.animate().y(value).setListener(listener);
    }

    @Override
    public void setValue(float... value) {
        this.value = value[0];
    }
}
