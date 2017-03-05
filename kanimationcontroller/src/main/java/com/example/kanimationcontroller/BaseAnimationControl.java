package com.example.kanimationcontroller;

import android.animation.Animator;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kognsin on 9/24/2016.
 */

public class BaseAnimationControl {

    protected View mView;
    private static final String TAG = "BaseAnimationControl";
    protected List<HashMap<String, Float>> positionHistory = new ArrayList<>();
    protected List<IAnimateSet> animateSets = new ArrayList<>();
    private Animator.AnimatorListener mAnimatorListener;

    public BaseAnimationControl(View view) {
        this.mView = view;
    }

    private void addPositionHistory(float x, float y) {
        HashMap<String, Float> map = new HashMap<>();
        map.put("X", x);
        map.put("Y", y);
        positionHistory.add(map);
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

    public BaseAnimationControl newAnimate(){
        mView.animate().setDuration(250);
        animateSets.clear();
        positionHistory.clear();
        return this;
    }

    public BaseAnimationControl setDuration(int duration){
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

    public void setAnimationListener(Animator.AnimatorListener listener){
        mAnimatorListener = listener;
    }

    public BaseAnimationControl start(){
        addPositionHistory(getX(), getY());
        for (IAnimateSet animateSet : animateSets) {
            animateSet.animateView(mView, mAnimatorListener);
        }
        mView.animate().start();
        return this;
    }

    public BaseAnimationControl startDelay(int milliseconds){
        addPositionHistory(getX(), getY());
        for (IAnimateSet animateSet : animateSets) {
            animateSet.animateView(mView, mAnimatorListener);
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

    public BaseAnimationControl goToLeft(ViewGroup rootView){
        animateSets.add(new X(rootView.getLeft()));
        return this;
    }

    public BaseAnimationControl goToRight(ViewGroup rootView){
        float screenW = rootView.getRight();
        float destVal = screenW - getScaledWidth();
        animateSets.add(new X(destVal));
        return this;
    }

    public BaseAnimationControl goToTop(ViewGroup rootView){
        animateSets.add(new Y(rootView.getTop()));
        return this;
    }

    public BaseAnimationControl goToBottom(ViewGroup rootView){
        float screenHeight = rootView.getBottom();
        float destVal = screenHeight - getScaledHeight();
        animateSets.add(new Y(destVal));
        return this;
    }

    public BaseAnimationControl moveToCenterHorizontal(ViewGroup rootView){
        float screenW = rootView.getWidth();
        float destVal = (screenW / 2) - (getScaledWidth() / 2);
        animateSets.add(new X(destVal));
        return this;
    }

    public BaseAnimationControl stackToTopOf(BaseAnimationControl _baseAnimationControl){
        float value;
        if (_baseAnimationControl instanceof ImageAnimationControl){
            value = _baseAnimationControl.getY() - _baseAnimationControl.getScaledHeight();
            if (_baseAnimationControl.getHeight() > _baseAnimationControl.getScaledHeight()){
                value += ((_baseAnimationControl.getHeight() - _baseAnimationControl.getScaledHeight()) / 2);
            } else {
                value -=((_baseAnimationControl.getHeight() - _baseAnimationControl.getScaledHeight()) / 2);
            }
        } else {
            value = _baseAnimationControl.getY() - _baseAnimationControl.getHeight();
        }
        if (getHeight() > getScaledHeight()){
            value += ((getHeight() - getScaledHeight())/2);
        } else {
            value -= ((getScaledHeight() - getHeight())/2);
        }
        animateSets.add(new Y(value));
        return this;
    }

    public BaseAnimationControl stackToBottomOf(BaseAnimationControl _baseAnimationControl){
        float value;
        if (_baseAnimationControl instanceof ImageAnimationControl){
            value = _baseAnimationControl.getY() + _baseAnimationControl.getScaledHeight();
            if (_baseAnimationControl.getHeight() > _baseAnimationControl.getScaledHeight()){
                value -= ((_baseAnimationControl.getHeight() - _baseAnimationControl.getScaledHeight()) / 2);
            } else {
                value +=((_baseAnimationControl.getHeight() - _baseAnimationControl.getScaledHeight()) / 2);
            }
        } else {
            value = _baseAnimationControl.getY() + _baseAnimationControl.getScaledHeight();
        }
        if (getHeight() > getScaledHeight()){
            value -= ((getHeight() - getScaledHeight())/2);
        } else {
            value += ((getScaledHeight() - getHeight())/2);
        }
        animateSets.add(new Y(value));
        return this;
    }

    public BaseAnimationControl stackToRightOf(BaseAnimationControl _baseAnimationControl){
        float value;
        if (_baseAnimationControl instanceof ImageAnimationControl){
            value = _baseAnimationControl.getX() + _baseAnimationControl.getScaledWidth();
        } else {
            value = _baseAnimationControl.getX() + _baseAnimationControl.getScaledWidth();
        }
        if (getWidth() > getScaledWidth()){
            value -= ((getWidth() - getScaledWidth())/2);
        } else {
            value += ((getScaledWidth() - getWidth())/2);
        }
        animateSets.add(new X(value));
        return this;
    }

    public BaseAnimationControl stackToLeftOf(BaseAnimationControl _baseAnimationControl){
        float value;
        if (_baseAnimationControl instanceof ImageAnimationControl){
            value = _baseAnimationControl.getX() - _baseAnimationControl.getScaledWidth();
        } else {
            value = _baseAnimationControl.getX() - _baseAnimationControl.getWidth();
        }
        if (getWidth() > getScaledWidth()){
            value += ((getWidth() - getScaledWidth())/2);
        } else {
            value -= ((getScaledWidth() - getWidth())/2);
        }
        animateSets.add(new X(value));
        return this;
    }

    public BaseAnimationControl space(){

        return this;
    }

    public BaseAnimationControl y(float value){
        animateSets.add(new Y(value - ((getHeight() - getScaledHeight())/2)));
        return this;
    }

    public BaseAnimationControl x(float value){
        animateSets.add(new X(value - ((getWidth() - getScaledWidth())/2)));
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
