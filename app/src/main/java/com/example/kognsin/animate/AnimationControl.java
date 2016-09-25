package com.example.kognsin.animate;

import android.animation.Animator;
import android.os.Handler;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;

/**
 * Created by kognsin on 9/24/2016.
 */

public class AnimationControl {

    private ViewPropertyAnimator animator;
    private View mView;
    private Float scaleX, scaleY;
    private static final String TAG = "AnimationControl";
    private AnimatorObj animatorObj = new AnimatorObj();

    public static AnimationControl with(View view) {
        AnimationControl animateControl = new AnimationControl();
        animateControl.mView = view;
        animateControl.animator = view.animate();
        return animateControl;
    }

    protected class Start{

        private View mView;
        private Then mThen;
        private boolean gone = false;
        private int goneDelay = 0;
        private boolean show = false;

        private Then mLocalThen = new Then() {
            @Override
            public void animated(View view, Pos pos) {
                if (gone){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mView.setVisibility(View.GONE);
                        }
                    }, (goneDelay > 0 && goneDelay < 50) ? 50 : goneDelay);
                }
                if (show) {
                    mView.setX(0);
                    mView.setY(0);
                    mView.requestLayout();
                    mView.setVisibility(View.VISIBLE);
                }
            }
        };

        private Animator.AnimatorListener listener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (mThen != null)mThen.animated(mView, ViewUtils.getActualViewPosition(mView));
                if (mLocalThen != null) mLocalThen.animated(mView, ViewUtils.getActualViewPosition(mView));
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        };

        public Start(View view){
            this.mView = view;
            mView.animate().setListener(listener);
        }

        public Start(){

        }

        public void setView(View mView) {
            this.mView = mView;
            this.mView.animate().setListener(listener);
        }

        public Start then(final Then then){
            mThen = then;
            return this;
        }

        public Start gone(){
            gone = true;
            return this;
        }

        public Start gone(int delay){
            goneDelay = delay;
            gone = true;
            return this;
        }

        public Start show(){
            show = true;
            return this;
        }
    }

    public Start start(){
        return new Start(mView);
    }

    public Start startDelayed(int milliseconds){
        animator.setStartDelay(milliseconds);
        return new Start(mView);
    }

    public AnimationControl stackToBottom(final View anotherView){
        if (mView instanceof ImageView){
            Pos ints = ViewUtils.getActualViewPosition(anotherView);
            if (scaleY != null){
                animatorObj.Y = ints.getY() - (ViewUtils.getAnimatedSize(mView, scaleY) * 0.5F);
            } else {
                animatorObj.Y = ints.getY();
            }
        } else {
            animatorObj.Y = anotherView.getY();
        }
        animator.y(animatorObj.Y);
        return this;
    }

    public AnimationControl destinationWasResized(float fromY, float scaledW){
        float resTo = (fromY * scaledW);
        animatorObj.Y -= (fromY-resTo);
        animator.y(animatorObj.Y);
        return this;
    }

    public AnimationControl y(float value){
        animatorObj.Y = value;
        animator.y(animatorObj.Y);
        return this;
    }

    public AnimationControl x(float value){
        animatorObj.X = value;
        animator.x(animatorObj.X);
        return this;
    }

    public AnimationControl scaleX(float value){
        scaleY = value;
        animator.scaleX(value);
        return this;
    }

    public AnimationControl scaleY(float value){
        scaleY = value;
        animator.scaleY(value);
        return this;
    }

    public AnimationControl translationX(float value){
        animator.translationX(value);
        return this;
    }

    public AnimationControl translationY(float value){
        animator.translationY(value);
        return this;
    }

    public interface Then {
        void animated(View view, Pos pos);
    }

    public class AnimatorObj{
        public float X;
        public float Y;
    }

}
