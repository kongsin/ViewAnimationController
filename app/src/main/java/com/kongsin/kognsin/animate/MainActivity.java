package com.kongsin.kognsin.animate;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.kongsin.kanimationcontroller.AnimationQueue;
import com.kongsin.kanimationcontroller.BaseAnimationControl;
import com.kongsin.kanimationcontroller.ImageAnimationControl;

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
                BaseAnimationControl b1 = new BaseAnimationControl(img).moveToCenterVertical(main).moveToCenterHorizontal(main);

                BaseAnimationControl b2 = new BaseAnimationControl(img2);
                b2.stackToLeftOf(b1).marginRight(50);

                BaseAnimationControl b3 = new BaseAnimationControl(img3);
                b3.stackToRightOf(b1).marginLeft(50);

                AnimationQueue animationQueue = new AnimationQueue();
                animationQueue.nextQueue(0, b1);
                animationQueue.nextQueue(0, b2);
                animationQueue.nextQueue(0, b3);
                animationQueue.startTogether();

                // STEP2
                BaseAnimationControl b12 = new BaseAnimationControl(img);
                BaseAnimationControl b22 = new BaseAnimationControl(img2);
                b22.stackToTopOf(b12).marginBottom(50);

                BaseAnimationControl b32 = new BaseAnimationControl(img3);
                b32.stackToBottomOf(b12).marginTop(50);

                AnimationQueue animationQueue2 = new AnimationQueue();
                animationQueue2.nextQueue(0, b12);
                animationQueue2.nextQueue(0, b22);
                animationQueue2.nextQueue(0, b32);
                animationQueue2.startTogetherDelay(1000);

                // STEP3
                BaseAnimationControl b13 = new BaseAnimationControl(img);
                BaseAnimationControl b23 = new BaseAnimationControl(img2);
                b23.stackToRightOf(b13).marginLeft(50);

                BaseAnimationControl b33 = new BaseAnimationControl(img3);
                b33.stackToLeftOf(b13).marginRight(50);

                AnimationQueue animationQueue3 = new AnimationQueue();
                animationQueue3.nextQueue(0, b13);
                animationQueue3.nextQueue(0, b23);
                animationQueue3.nextQueue(0, b33);
                animationQueue3.startTogetherDelay(2000);

                // STEP4
                BaseAnimationControl b14 = new BaseAnimationControl(img);
                BaseAnimationControl b24 = new BaseAnimationControl(img2);
                b24.moveToCenterHorizontal(main);

                BaseAnimationControl b34 = new BaseAnimationControl(img3);
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
    public void eachQueueFinished(BaseAnimationControl control) {

    }
}

