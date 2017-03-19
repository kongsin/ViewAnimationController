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
    BaseAnimationObject main;
    ImageView img, img2, img3;

    private static final String TAG = "MainActivity";
    private Random mRan;

    public void init(){
        setContentView(R.layout.activity_main);
        main = new BaseAnimationObject(findViewById(R.id.main));
        img = (ImageView) findViewById(R.id.card1);
        img2 = (ImageView) findViewById(R.id.card2);
        img3 = (ImageView) findViewById(R.id.card3);
        main.getView().setOnClickListener(this);
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
        main.getView().post(new Runnable() {
            @Override
            public void run() {

                // STEP1
                BaseAnimationObject b1 = new BaseAnimationObject(img);
                BaseAnimationObject b2 = new BaseAnimationObject(img2);
                BaseAnimationObject b3 = new BaseAnimationObject(img3);

                b1.moveToCenterVertical(main).moveToCenterHorizontal(main);
                b2.stackToLeftOf(b1).marginRight(50);
                b3.stackToRightOf(b1).marginLeft(50);

                AnimationQueue animationQueue = new AnimationQueue();
                animationQueue.nextQueue(0, b1);
                animationQueue.nextQueue(0, b2);
                animationQueue.nextQueue(0, b3);
                animationQueue.startTogether();

                // STEP2
                AnimationQueue animationQueue2 = new AnimationQueue();
                animationQueue2.nextQueue(0, b1.newAnimate());
                animationQueue2.nextQueue(0, b2.newAnimate().stackToTopOf(b1).moveToCenterHorizontal(main).marginBottom(50));
                animationQueue2.nextQueue(0, b3.newAnimate().stackToBottomOf(b1).moveToCenterHorizontal(main).marginTop(50));
                animationQueue2.startTogetherDelay(1000);

                // STEP3

                AnimationQueue animationQueue3 = new AnimationQueue();
                animationQueue3.nextQueue(0, b1.newAnimate());
                animationQueue3.nextQueue(0, b2.newAnimate().stackToRightOf(b1).moveToCenterVertical(main).marginLeft(50));
                animationQueue3.nextQueue(0, b3.newAnimate().stackToLeftOf(b1).moveToCenterVertical(main).marginRight(50));
                animationQueue3.startTogetherDelay(2000);

                // STEP4
                AnimationQueue animationQueue4 = new AnimationQueue();
                animationQueue4.nextQueue(0, b1.newAnimate());
                animationQueue4.nextQueue(0, b2.newAnimate().stackToBottomOf(b1).moveToCenterHorizontal(main).marginTop(50));
                animationQueue4.nextQueue(0, b3.newAnimate().stackToTopOf(b1).moveToCenterHorizontal(main).marginBottom(50));
                animationQueue4.startTogetherDelay(3000);

                // STEP5
                AnimationQueue animationQueue5 = new AnimationQueue();
                animationQueue5.nextQueue(0, b1.newAnimate().rotationXBy(180));
                animationQueue5.nextQueue(0, b2.newAnimate().stackToLeftOf(b1).moveToCenterVertical(main).marginRight(50));
                animationQueue5.nextQueue(0, b3.newAnimate().stackToRightOf(b1).moveToCenterVertical(main).marginLeft(50));
                animationQueue5.setCallback(MainActivity.this);
                animationQueue5.startTogetherDelay(4000);
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
        firstSetupView();
    }

    @Override
    public void eachQueueFinished(BaseAnimationObject control) {

    }
}

