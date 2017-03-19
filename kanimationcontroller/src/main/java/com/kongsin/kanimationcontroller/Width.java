package com.kongsin.kanimationcontroller;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.View;

/**
 * Created by kognsin on 9/27/2016.
 */

public class Width extends KValueAnimator {

    public Width(View view, float... value) {
        super(view, value);
    }

    @Override
    public void animateView(View view) {
        PropertyValuesHolder w = PropertyValuesHolder.ofFloat("W", view.getWidth(), mValue[0]);
        setValueAnimatorProperty(w);
        start();
    }

    @Override
    protected void onAnimationUpdate(ValueAnimator animation, View currentView) {
        float w = (float) animation.getAnimatedValue("W");
        currentView.getLayoutParams().width = (int) w;
        currentView.requestLayout();
        currentView.invalidate();
    }

    @Override
    public void setValue(float... value) {
        mValue = value;
    }

}
