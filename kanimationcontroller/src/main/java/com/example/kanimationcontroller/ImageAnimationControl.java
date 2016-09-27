package com.example.kanimationcontroller;

import android.view.View;

/**
 * Created by kognsin on 9/27/2016.
 */

public class ImageAnimationControl extends BaseAnimationControl {
    private static final String TAG = "ImageAnimationControl";

    public ImageAnimationControl(View view) {
        super(view);
    }

    @Override
    public BaseAnimationControl stackToBottomOf(BaseAnimationControl _baseAnimationControl) {
        float val = _baseAnimationControl.getY() - ((getHeight() - getScaledHeight())/2);
        val += _baseAnimationControl.getScaledHeight();
        animateSets.add(new Y(val));
        return this;
    }
}
