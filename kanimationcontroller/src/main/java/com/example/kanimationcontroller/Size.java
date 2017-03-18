package com.example.kanimationcontroller;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.View;

/**
 * Created by kognsin on 9/27/2016.
 */

public class Size extends KValueAnimator {

    public Size(View view, float... value) {
        super(view, value);
    }


    @Override
    public void setView(View view) {

    }

    @Override
    public void animateView(View view) {
        PropertyValuesHolder w = PropertyValuesHolder.ofFloat("W", mValue[0]);
        PropertyValuesHolder h = PropertyValuesHolder.ofFloat("H", mValue[1]);
        setValueAnimatorProperty(w, h);
    }

    @Override
    protected void onAnimationUpdate(ValueAnimator animation, View currentView) {
        float w = (float) animation.getAnimatedValue("W");
        float h = (float) animation.getAnimatedValue("H");
        currentView.getLayoutParams().width = (int) w;
        currentView.getLayoutParams().height = (int) h;
        currentView.requestLayout();
        currentView.invalidate();
    }

    @Override
    public void setValue(float... value) {
        mValue = value;
    }

}
