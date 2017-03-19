package com.kongsin.kanimationcontroller;

import android.animation.Animator;
import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kongsin on 3/5/2017.
 */

public class AnimationQueue implements Animator.AnimatorListener {

    private List<HashMap<String, Object>>   animationControls = new ArrayList<>();
    private int                             currentQueue = 0;
    private AnimatedCallback                mAnimateCallbacl;
    private static final String             TAG = "AnimationQueue";
    private int                             playCount = 0;

    public AnimationQueue(){

    }

    public AnimationQueue(int startDaelayTime, BaseAnimationObject animationControl){
        nextQueue(startDaelayTime, animationControl);
    }

    public AnimationQueue startByQueue(){
        final BaseAnimationObject baseAnimationObject = ((BaseAnimationObject)animationControls.get(currentQueue).get("animObj"));
        int delay = (int) animationControls.get(currentQueue).get("delay");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                baseAnimationObject.start(AnimationQueue.this);
            }
        }, delay);
        return this;
    }

    public AnimationQueue startByQueueDelay(long millisec){
        ((BaseAnimationObject)animationControls.get(currentQueue).get("animObj")).startDelay(millisec, this);
        return this;
    }

    public void startTogetherDelay(long millisec){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startTogether();
            }
        }, millisec);
    }

    public void startTogether(){
        for (HashMap<String, Object> animationControl : animationControls) {
            BaseAnimationObject baseAnimationObject = (BaseAnimationObject) animationControl.get("animObj");
            int delay = (int) animationControl.get("delay");
            baseAnimationObject.startDelay(delay, new CustomAnimatorCallback(baseAnimationObject) {
                @Override
                public void onAnimationEnd(Animator animator, BaseAnimationObject animationControl) {
                    if (mAnimateCallbacl != null) mAnimateCallbacl.eachQueueFinished(animationControl);
                    playCount++;
                    if (playCount >= animationControls.size()){
                        if (mAnimateCallbacl != null) mAnimateCallbacl.finished();
                    }
                }
            });
        }
    }

    public AnimationQueue nextQueue(int delayTime, BaseAnimationObject control){
        HashMap<String, Object> obj = new HashMap<>();
        obj.put("animObj", control);
        obj.put("delay", delayTime);
        animationControls.add(obj);
        return this;
    }

    public AnimationQueue clearQueue(){
        animationControls.clear();
        return this;
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        if (mAnimateCallbacl != null) {
            mAnimateCallbacl.eachQueueFinished((BaseAnimationObject) animationControls.get(currentQueue).get("animObj"));
        }
        currentQueue++;
        if (currentQueue < animationControls.size()){
            startByQueue();
        } else {
            Log.i(TAG, "onAnimationEnd: ");
            if (mAnimateCallbacl != null) {
                mAnimateCallbacl.finished();
            }
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }

    public void setCallback(AnimatedCallback animateCallbacl) {
        this.mAnimateCallbacl = animateCallbacl;
    }

    public interface AnimatedCallback {
        void finished();
        void eachQueueFinished(BaseAnimationObject control);
    }

}
