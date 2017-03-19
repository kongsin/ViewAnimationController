package com.kongsin.kognsin.animate;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.kongsin.kanimationcontroller.AnimationQueue;
import com.kongsin.kanimationcontroller.BaseAnimationObject;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AnimationQueue.AnimatedCallback {
    RelativeLayout main;
    ImageView img, img2, img3;

    private static final String TAG = "MainActivity";
    private Random mRan;

    public void init(){
        setContentView(R.layout.activity_main);
        main = (RelativeLayout) findViewById(R.id.main);
        img = (ImageView) findViewById(R.id.card1);
        img2 = (ImageView) findViewById(R.id.card2);
        img3 = (ImageView) findViewById(R.id.card3);
        main.setOnClickListener(this);
        mRan = new Random();
        firstSetupView();
    }

    @Override
    public void onClick(final View v) {

    }

    private int getRandomMax() {
        return mRan.nextInt(10 - 5) + 5;
    }



    public void firstSetupView(){
        main.post(new Runnable() {
            @Override
            public void run() {

                // STEP1
                BaseAnimationObject b1 = new BaseAnimationObject(img).moveToCenterVertical(main).moveToCenterHorizontal(main);

                BaseAnimationObject b2 = new BaseAnimationObject(img2);
                b2.stackToLeftOf(b1).marginRight(50);

                BaseAnimationObject b3 = new BaseAnimationObject(img3);
                b3.stackToRightOf(b1).marginLeft(50);

                AnimationQueue animationQueue = new AnimationQueue();
                animationQueue.nextQueue(0, b1);
                animationQueue.nextQueue(0, b2);
                animationQueue.nextQueue(0, b3);
                animationQueue.startTogether();

                // STEP2
                BaseAnimationObject b12 = new BaseAnimationObject(img);
                BaseAnimationObject b22 = new BaseAnimationObject(img2);
                b22.stackToTopOf(b12).marginBottom(50);

                BaseAnimationObject b32 = new BaseAnimationObject(img3);
                b32.stackToBottomOf(b12).marginTop(50);

                AnimationQueue animationQueue2 = new AnimationQueue();
                animationQueue2.nextQueue(0, b12);
                animationQueue2.nextQueue(0, b22);
                animationQueue2.nextQueue(0, b32);
                animationQueue2.startTogetherDelay(1000);

                // STEP3
                BaseAnimationObject b13 = new BaseAnimationObject(img);
                BaseAnimationObject b23 = new BaseAnimationObject(img2);
                b23.stackToRightOf(b13).marginLeft(50);

                BaseAnimationObject b33 = new BaseAnimationObject(img3);
                b33.stackToLeftOf(b13).marginRight(50);

                AnimationQueue animationQueue3 = new AnimationQueue();
                animationQueue3.nextQueue(0, b13);
                animationQueue3.nextQueue(0, b23);
                animationQueue3.nextQueue(0, b33);
                animationQueue3.startTogetherDelay(2000);

                // STEP4
                BaseAnimationObject b14 = new BaseAnimationObject(img);
                BaseAnimationObject b24 = new BaseAnimationObject(img2);
                b24.moveToCenterHorizontal(main);

                BaseAnimationObject b34 = new BaseAnimationObject(img3);
                b34.moveToCenterHorizontal(main);

                AnimationQueue animationQueue4 = new AnimationQueue();
                animationQueue4.nextQueue(0, b14);
                animationQueue4.nextQueue(0, b24);
                animationQueue4.nextQueue(0, b34);
                animationQueue4.setCallback(MainActivity.this);
                animationQueue4.startTogetherDelay(3000);
            }
        });
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
                firstSetupView();
            }
        }, 1000);
    }

    @Override
    public void eachQueueFinished(BaseAnimationObject control) {

    }
}

