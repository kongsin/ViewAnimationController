package com.kongsin.kanimationcontroller;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.View;

/**
 * Created by kognsin on 9/27/2016.
 */

public class Height extends KValueAnimator {

    public Height(View view, float... value) {
        super(view, value);
    }

    @Override
    public void animateView(View view) {
        PropertyValuesHolder h = PropertyValuesHolder.ofFloat("H", view.getHeight(), mValue[0]);
        setValueAnimatorProperty(h);
        start();
    }

    @Override
    protected void onAnimationUpdate(ValueAnimator animation, View currentView) {
        float h = (float) animation.getAnimatedValue("H");
        currentView.getLayoutParams().height = (int) h;
        currentView.requestLayout();
        currentView.invalidate();
    }

    @Override
    public void setValue(float... value) {
        mValue = value;
    }

}
