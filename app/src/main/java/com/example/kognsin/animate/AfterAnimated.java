package com.example.kognsin.animate;

import android.animation.Animator;
import android.view.View;

/**
 * Created by kognsin on 9/27/2016.
 */

public class AfterAnimated {

    private View mView;

    private Animator.AnimatorListener listener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {

        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };

    public AfterAnimated(View view){
        this.mView = view;
        this.mView.animate().setListener(listener);
    }

    public void gone(){
        mView.setVisibility(View.GONE);
    }
}
