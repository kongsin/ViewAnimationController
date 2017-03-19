package com.kongsin.kanimationcontroller;

import android.view.View;

/**
 * Created by kognsin on 9/27/2016.
 */

public interface IAnimateSet {
    void animateView(View view);
    float getValue();
    void setValue(float...value);
}
