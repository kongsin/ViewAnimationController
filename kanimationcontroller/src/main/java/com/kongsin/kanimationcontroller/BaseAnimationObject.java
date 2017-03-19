package com.kongsin.kanimationcontroller;

import android.animation.Animator;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kognsin on 9/24/2016.
 */

public class BaseAnimationObject {

    protected View mView;
    private static final String TAG = "BaseAnimationObject";
    protected List<IAnimateSet> animateSets = new ArrayList<>();

    public BaseAnimationObject(View view) {
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

    public float getScaledHeight(){
        for (IAnimateSet animateSet : animateSets) {
            if (animateSet instanceof ScaleY){
                return (getHeight() * animateSet.getValue());
            }
        }
        return getHeight();
    }

    public BaseAnimationObject newAnimate(){
        mView.animate().setDuration(250);
        animateSets.clear();
        return this;
    }

    public BaseAnimationObject setDuration(int duration){
        mView.animate().setDuration(duration);
        return this;
    }

    public float getScaledWidth(){
        for (IAnimateSet animateSet : animateSets) {
            if (animateSet instanceof ScaleX){
                return (getWidth() * animateSet.getValue());
            }
        }
        return getWidth();
    }

    public float getX(){
        for (IAnimateSet animateSet : animateSets) {
            if (animateSet instanceof X || animateSets instanceof TransX){
                float x = animateSet.getValue();
                if (getWidth() > getScaledWidth()){
                    x += ((getWidth() - getScaledWidth()) / 2);
                } else {
                    x -= ((getScaledWidth() - getWidth()) / 2);
                }
                return x;
            }
        }
        return mView.getX();
    }

    public float getY(){
        for (IAnimateSet animateSet : animateSets) {
            if (animateSet instanceof Y || animateSet instanceof TransY){
                float y = (animateSet).getValue();
                if (getHeight() > getScaledHeight()){
                    y += ((getHeight() - getScaledHeight()) / 2);
                } else {
                    y -= ((getScaledHeight() - getHeight()) / 2);
                }
                return y;
            }
        }
        return mView.getY();
    }

    public BaseAnimationObject start(){
        start(null);
        return this;
    }

    public BaseAnimationObject startDelay(int millisec){
        startDelay(millisec, null);
        return this;
    }

    public BaseAnimationObject start(Animator.AnimatorListener listener){
        for (IAnimateSet animateSet : animateSets) {
            animateSet.animateView(mView);
        }
        mView.animate().setListener(listener).start();
        return this;
    }

    public View getView() {
        return mView;
    }

    public BaseAnimationObject startDelay(long milliseconds, final Animator.AnimatorListener listener){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                start(listener);
            }
        }, milliseconds);
        return this;
    }

    public BaseAnimationObject gone(){
        animateSets.add(new Gone());
        return this;
    }

    public BaseAnimationObject reset(){
        animateSets.add(new Reset());
        return this;
    }

    public BaseAnimationObject flip(int decree){
        animateSets.add(new RotationY(decree));
        return this;
    }

    public BaseAnimationObject goToLeft(ViewGroup parent){
        animateSets.add(new X(parent.getLeft()));
        return this;
    }

    public BaseAnimationObject goToRight(ViewGroup parent){
        float screenW = parent.getRight();
        float destVal = screenW - getScaledWidth();
        animateSets.add(new X(destVal));
        return this;
    }

    public BaseAnimationObject goToTop(ViewGroup parent){
        float top = parent.getTop();
        float scale = (getScaledHeight() - getHeight());
        animateSets.add(new Y(top + (scale / 2)));
        return this;
    }

    public BaseAnimationObject goToBottom(ViewGroup parent){
        float screenHeight = parent.getBottom();
        float destVal = screenHeight - (getHeight() + ((getScaledHeight() - getHeight()) / 2));
        animateSets.add(new Y(destVal));
        return this;
    }

    public BaseAnimationObject moveToCenterHorizontal(ViewGroup parent){
        float screenW = parent.getWidth();
        float destVal = (screenW / 2) - (getScaledWidth() / 2);
        animateSets.add(new X(destVal));
        return this;
    }

    public BaseAnimationObject moveToCenterVertical(ViewGroup parent){
        float screenH = parent.getHeight();
        float destVal = (screenH / 2) - (getScaledHeight() / 2);
        animateSets.add(new Y(destVal));
        return this;
    }

    public BaseAnimationObject stackToTopOf(BaseAnimationObject _baseAnimationObject){
        float value = _baseAnimationObject.getY() - _baseAnimationObject.getScaledHeight();
        if (getHeight() > getScaledHeight()){
            value += ((getHeight() - getScaledHeight())/2);
        } else {
            value -= ((getScaledHeight() - getHeight())/2);
        }
        animateSets.add(new Y(value));
        animateSets.add(new X(_baseAnimationObject.getX()));
        return this;
    }

    public BaseAnimationObject stackToBottomOf(BaseAnimationObject _baseAnimationObject){
        float value = _baseAnimationObject.getY() + _baseAnimationObject.getScaledHeight();
        if (getHeight() > getScaledHeight()){
            value -= ((getHeight() - getScaledHeight())/2);
        } else {
            value += ((getScaledHeight() - getHeight())/2);
        }
        animateSets.add(new Y(value));
        animateSets.add(new X(_baseAnimationObject.getX()));
        return this;
    }

    public BaseAnimationObject stackToRightOf(BaseAnimationObject _baseAnimationObject){
        float value = _baseAnimationObject.getX() + _baseAnimationObject.getScaledWidth();
        if (getWidth() > getScaledWidth()){
            value -= ((getWidth() - getScaledWidth())/2);
        } else {
            value += ((getScaledWidth() - getWidth())/2);
        }
        animateSets.add(new X(value));
        animateSets.add(new Y(_baseAnimationObject.getY()));
        return this;
    }

    public BaseAnimationObject stackToLeftOf(BaseAnimationObject _baseAnimationObject){
        float value = _baseAnimationObject.getX() - _baseAnimationObject.getScaledWidth();
        if (getWidth() > getScaledWidth()){
            value += ((getWidth() - getScaledWidth())/2);
        } else {
            value -= ((getScaledWidth() - getWidth())/2);
        }
        animateSets.add(new X(value));
        animateSets.add(new Y(_baseAnimationObject.getY()));
        return this;
    }

    public BaseAnimationObject margin(int value){
        return this;
    }

    public BaseAnimationObject y(float value){
        animateSets.add(new Y(value - ((getHeight() - getScaledHeight())/2)));
        return this;
    }

    public BaseAnimationObject x(float value){
        animateSets.add(new X(value - ((getWidth() - getScaledWidth())/2)));
        return this;
    }

    public BaseAnimationObject marginLeft(float value){
        for (IAnimateSet animateSet : animateSets) {
            if (animateSet instanceof X){
                animateSet.setValue(animateSet.getValue() + value);
                break;
            }
        }
        return this;
    }

    public BaseAnimationObject marginRight(float value){
        for (IAnimateSet animateSet : animateSets) {
            if (animateSet instanceof X){
                animateSet.setValue(animateSet.getValue() - value);
                break;
            }
        }
        return this;
    }

    public BaseAnimationObject marginTop(float value){
        for (IAnimateSet animateSet : animateSets) {
            if (animateSet instanceof Y){
                animateSet.setValue(animateSet.getValue() + value);
                return this;
            }
        }
        animateSets.add(new Y(getY() + value));
        return this;
    }

    public BaseAnimationObject marginBottom(float value){
        for (IAnimateSet animateSet : animateSets) {
            if (animateSet instanceof Y){
                animateSet.setValue(animateSet.getValue() - value);
                return this;
            }
        }
        animateSets.add(new Y(getY() - value));
        return this;
    }

    public BaseAnimationObject scaleX(float value){
        animateSets.add(new ScaleX(value));
        return this;
    }

    public BaseAnimationObject scaleY(float value){
        animateSets.add(new ScaleY(value));
        return this;
    }

    public BaseAnimationObject translationX(float value){
        animateSets.add(new TransX(value));
        return this;
    }

    public BaseAnimationObject translationY(float value){
        animateSets.add(new TransY(value));
        return this;
    }

}
