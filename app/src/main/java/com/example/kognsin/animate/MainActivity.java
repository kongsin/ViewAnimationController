package com.example.kognsin.animate;

import android.animation.Animator;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.kanimationcontroller.BaseAnimationControl;
import com.example.kanimationcontroller.ImageAnimationControl;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
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
        newInstance();
        mRan = new Random();
    }

    public void newInstance(){
        imgAnim1 = new ImageAnimationControl(img);
        imgAnim2 = new ImageAnimationControl(img2);
        imgAnim3 = new ImageAnimationControl(img3);
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
        newInstance();
        imgAnim1.newAnimate().goToTop(main).moveToCenterHorizontal(main).setDuration(80).start();
        imgAnim2.newAnimate().goToLeft(main).setDuration(80).start();
        imgAnim3.newAnimate().goToRight(main).setDuration(80).start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imgAnim1.newAnimate().setDuration(80).moveToCenterVertical(main).setDuration(80).setAnimationListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                randomCard();
                            }
                        }, 120);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).start();
            }
        }, 120);
    }

    private void randDom2(){
        newInstance();
        imgAnim2.newAnimate().goToTop(main).moveToCenterHorizontal(main).setDuration(80).start();
        imgAnim3.newAnimate().goToLeft(main).setDuration(80).start();
        imgAnim1.newAnimate().goToRight(main).setDuration(80).start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imgAnim2.newAnimate().setDuration(80).moveToCenterVertical(main).setDuration(80).setAnimationListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                randomCard();
                            }
                        }, 120);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).start();
            }
        }, 120);
    }

    private void randDom3(){
        newInstance();
        imgAnim3.newAnimate().goToTop(main).setDuration(80).moveToCenterHorizontal(main).start();
        imgAnim1.newAnimate().goToLeft(main).setDuration(80).start();
        imgAnim2.newAnimate().goToRight(main).setDuration(80).start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imgAnim3.newAnimate().setDuration(80).moveToCenterVertical(main).setDuration(80).setAnimationListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                randomCard();
                            }
                        }, 120);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).start();
            }
        }, 120);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

}

