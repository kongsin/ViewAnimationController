package com.example.kognsin.animate;

import android.util.Log;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kognsin on 9/24/2016.
 */

public class BaseAnimationControl {

    protected View mView;
    private static final String TAG = "BaseAnimationControl";
    protected List<IAnimateSet> animateSets = new ArrayList<>();

    public BaseAnimationControl(View view) {
        this.mView = view;
    }

    public List<IAnimateSet> getAnimateSets() {
        return animateSets;
    }

    public boolean isGone(){
        for (IAnimateSet animateSet : animateSets) {
            if (animateSet instanceof Gone) return true;
        }
        return false;
    }

    public int getHeight(){
       return isGone() ? 0 : mView.getHeight();
    }

    public int getWidth(){
        return isGone() ? 0 : mView.getWidth();
    }

    protected float getScaledHeight(){
        for (IAnimateSet animateSet : animateSets) {
            if (animateSet instanceof ScaleY){
                return (getHeight() * ((ScaleY) animateSet).getValue());
            }
        }
        return getHeight();
    }

    protected float getScaledWidth(){
        for (IAnimateSet animateSet : animateSets) {
            if (animateSet instanceof ScaleX){
                return (getWidth() * ((ScaleX) animateSet).getValue());
            }
        }
        return getWidth();
    }

    public float getX(){
        for (IAnimateSet animateSet : animateSets) {
            if (animateSet instanceof X){
                return ((X) animateSet).getValue();
            }
        }
        return mView.getX();
    }

    public float getY(){
        for (IAnimateSet animateSet : animateSets) {
            if (animateSet instanceof Y){
                float y = ((Y) animateSet).getValue();
                if (getHeight() > getScaledHeight()){
                    y += ((getHeight() - getScaledHeight()) / 2);
                } else {
                    y -= ((getScaledHeight() - getHeight()) / 2);
                }
                return y;
            }
        }
        return mView.getX();
    }

    public BaseAnimationControl start(){
        for (IAnimateSet animateSet : animateSets) {
            animateSet.animateView(mView);
        }
        mView.animate().start();
        return this;
    }

    public BaseAnimationControl startDelay(int milliseconds){
        for (IAnimateSet animateSet : animateSets) {
            animateSet.animateView(mView);
        }
        mView.animate().setStartDelay(milliseconds).start();
        return this;
    }

    public BaseAnimationControl gone(){
        animateSets.add(new Gone());
        return this;
    }

    public BaseAnimationControl reset(){
        animateSets.add(new Reset());
        return this;
    }

    public BaseAnimationControl stackToBottomOf(BaseAnimationControl _baseAnimationControl){
        float value = _baseAnimationControl.getY() + _baseAnimationControl.getScaledHeight();
        if (getHeight() > getScaledHeight()){
            value -= ((getHeight() - getScaledHeight())/2);
        } else {
            value += ((getScaledHeight() - getHeight())/2);
        }
        animateSets.add(new Y(value));
        return this;
    }

    public BaseAnimationControl y(float value){
        animateSets.add(new Y(value - ((getHeight() - getScaledHeight())/2)));
        return this;
    }

    public BaseAnimationControl x(float value){
        animateSets.add(new X(value));
        return this;
    }

    public BaseAnimationControl scaleX(float value){
        animateSets.add(new ScaleX(value));
        return this;
    }

    public BaseAnimationControl scaleY(float value){
        animateSets.add(new ScaleY(value));
        return this;
    }

    public BaseAnimationControl translationX(float value){
        animateSets.add(new TransX(value));
        return this;
    }

    public BaseAnimationControl translationY(float value){
        animateSets.add(new TransY(value));
        return this;
    }

    public interface Then {
        void animated(View view, Pos pos);
    }

}
