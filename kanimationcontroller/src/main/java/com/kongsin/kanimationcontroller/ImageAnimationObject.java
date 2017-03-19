package com.kongsin.kanimationcontroller;

import android.view.View;

/**
 * Created by kognsin on 9/27/2016.
 */
@Deprecated
public class ImageAnimationObject extends BaseAnimationObject {
    private static final String TAG = "ImageAnimationObject";

    public ImageAnimationObject(View view) {
        super(view);
    }

    @Override
    public BaseAnimationObject stackToBottomOf(BaseAnimationObject _baseAnimationObject) {
        float val = _baseAnimationObject.getY() - ((getHeight() - getScaledWidth())/2);
        val += _baseAnimationObject.getScaledHeight();
        animateSets.add(new Y(val));
        animateSets.add(new X(_baseAnimationObject.getX()));
        return this;
    }

    @Override
    public BaseAnimationObject stackToRightOf(BaseAnimationObject _baseAnimationObject) {
        float val = _baseAnimationObject.getX() - ((getWidth() - getScaledWidth())/2);
        val += _baseAnimationObject.getScaledWidth();
        animateSets.add(new X(val));
        animateSets.add(new Y(_baseAnimationObject.getY()));
        return this;
    }
}
