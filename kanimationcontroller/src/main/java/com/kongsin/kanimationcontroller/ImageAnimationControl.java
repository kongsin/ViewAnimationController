package com.kongsin.kanimationcontroller;

import android.view.View;

/**
 * Created by kognsin on 9/27/2016.
 */
@Deprecated
public class ImageAnimationControl extends BaseAnimationControl {
    private static final String TAG = "ImageAnimationControl";

    public ImageAnimationControl(View view) {
        super(view);
    }

    @Override
    public BaseAnimationControl stackToBottomOf(BaseAnimationControl _baseAnimationControl) {
        float val = _baseAnimationControl.getY() - ((getHeight() - getScaledWidth())/2);
        val += _baseAnimationControl.getScaledHeight();
        animateSets.add(new Y(val));
        animateSets.add(new X(_baseAnimationControl.getX()));
        return this;
    }

    @Override
    public BaseAnimationControl stackToRightOf(BaseAnimationControl _baseAnimationControl) {
        float val = _baseAnimationControl.getX() - ((getWidth() - getScaledWidth())/2);
        val += _baseAnimationControl.getScaledWidth();
        animateSets.add(new X(val));
        animateSets.add(new Y(_baseAnimationControl.getY()));
        return this;
    }
}
