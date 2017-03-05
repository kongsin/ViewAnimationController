package com.example.kognsin.animate;

import android.animation.Animator;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.kanimationcontroller.AnimationQueue;
import com.example.kanimationcontroller.BaseAnimationControl;
import com.example.kanimationcontroller.ImageAnimationControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, BaseAnimationControl.AnimatedCallback {
    LinearLayout main;
    ImageView img, img2, img3;

    private static final String TAG = "MainActivity";
    private BaseAnimationControl imgAnim1;
    private BaseAnimationControl imgAnim2;
    private BaseAnimationControl imgAnim3;
    private Random mRan;
    private int mCount = 0;
    private int mLastRes = 0;

    public void init(){
        setContentView(R.layout.activity_main);
        main = (LinearLayout) findViewById(R.id.main);
        img = (ImageView) findViewById(R.id.card1);
        img2 = (ImageView) findViewById(R.id.card2);
        img3 = (ImageView) findViewById(R.id.card3);
        img.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        mRan = new Random();
    }

    @Override
    public void onClick(final View v) {
        mCount = 0;
        randomCard();
    }

    private void randomCard() {
        if (mCount > 10) return;
        while (true) {
            int res = mRan.nextInt(3) + 1;
            if (res != mLastRes) {
                mLastRes = res;
                Log.i(TAG, "randomCard: " + res);
                switch (res) {
                    case 1:
                        randDom1();
                        break;
                    case 2:
                        randDom2();
                        break;
                    case 3:
                        randDom3();
                        break;
                }
                mCount ++;
                break;
            }
        }
    }

    private void randDom1(){
        AnimationQueue animationQueue = new AnimationQueue(0, new ImageAnimationControl(img).goToTop(main).moveToCenterHorizontal(main).setDuration(50));
        animationQueue.nextQueue(0,new ImageAnimationControl(img2).goToLeft(main).setDuration(50).start());
        animationQueue.nextQueue(0,new ImageAnimationControl(img3).goToRight(main).setDuration(50).start());
        animationQueue.nextQueue(0, new ImageAnimationControl(img).moveToCenterVertical(main).setDuration(50));
        animationQueue.setCallback(this);
        animationQueue.start();
    }

    private void randDom2(){
        AnimationQueue animationQueue = new AnimationQueue(0, new ImageAnimationControl(img2).goToTop(main).moveToCenterHorizontal(main).setDuration(50));
        animationQueue.nextQueue(0,new ImageAnimationControl(img3).goToLeft(main).setDuration(50).start());
        animationQueue.nextQueue(0,new ImageAnimationControl(img).goToRight(main).setDuration(50).start());
        animationQueue.nextQueue(0, new ImageAnimationControl(img2).moveToCenterVertical(main).setDuration(50));
        animationQueue.setCallback(this);
        animationQueue.start();
    }

    private void randDom3(){
        AnimationQueue animationQueue = new AnimationQueue(0, new ImageAnimationControl(img3).goToTop(main).moveToCenterHorizontal(main).setDuration(50));
        animationQueue.nextQueue(0,new ImageAnimationControl(img).goToLeft(main).setDuration(50).start());
        animationQueue.nextQueue(0,new ImageAnimationControl(img2).goToRight(main).setDuration(50).start());
        animationQueue.nextQueue(0, new ImageAnimationControl(img3).moveToCenterVertical(main).setDuration(50));
        animationQueue.setCallback(this);
        animationQueue.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    public void finished() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                randomCard();
            }
        }, 120);
    }
}

