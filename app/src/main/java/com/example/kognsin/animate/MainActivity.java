package com.example.kognsin.animate;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kanimationcontroller.BaseAnimationControl;
import com.example.kanimationcontroller.ImageAnimationControl;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout main, root;
    TextView top, bottpm, action;
    ImageView img;
    private static final String TAG = "MainActivity";

    public void init(){
        setContentView(R.layout.activity_main);
        main = (LinearLayout) findViewById(R.id.main);
        root = (LinearLayout) findViewById(R.id.root);
        img = (ImageView) findViewById(R.id.img);
        top = (TextView) findViewById(R.id.top);
        bottpm = (TextView) findViewById(R.id.bottom);
        action = (TextView) findViewById(R.id.action);
        img.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {

        BaseAnimationControl actionAnim = new BaseAnimationControl(action);
        actionAnim.y(-action.getHeight()).gone().start();

        BaseAnimationControl bottomAnim = new BaseAnimationControl(bottpm);
        bottomAnim.y((main.getHeight()/2) - img.getHeight() / 2).startDelay(50);

        BaseAnimationControl imgAnim = new ImageAnimationControl(img);
        imgAnim.scaleX(0.6F).scaleY(0.6F).stackToBottomOf(bottomAnim).startDelay(50);

        BaseAnimationControl topAnim = new BaseAnimationControl(top);
        topAnim.stackToBottomOf(imgAnim).startDelay(50);

        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                //init();
                BaseAnimationControl bottomAnim = new BaseAnimationControl(bottpm);
                bottomAnim.y((main.getHeight()/2) - img.getHeight() / 2).startDelay(50);

                BaseAnimationControl imgAnim = new ImageAnimationControl(img);
                imgAnim.scaleX(1.6F).scaleY(1.6F).stackToBottomOf(bottomAnim).startDelay(50);

                BaseAnimationControl topAnim = new BaseAnimationControl(top);
                topAnim.stackToBottomOf(imgAnim).startDelay(50);
            }
        }, 1000);

        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                //init();
                BaseAnimationControl bottomAnim = new BaseAnimationControl(bottpm);
                bottomAnim.y(0).startDelay(50);

                BaseAnimationControl imgAnim = new ImageAnimationControl(img);
                imgAnim.scaleX(1.6F).scaleY(1.6F).stackToBottomOf(bottomAnim).startDelay(100);

                BaseAnimationControl topAnim = new BaseAnimationControl(top);
                topAnim.stackToBottomOf(imgAnim).startDelay(150);
            }
        }, 2000);

        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                //init();
                BaseAnimationControl bottomAnim = new BaseAnimationControl(bottpm);
                bottomAnim.y(450).startDelay(50);

                BaseAnimationControl imgAnim = new ImageAnimationControl(img);
                imgAnim.scaleX(1.6F).scaleY(1.6F).stackToBottomOf(bottomAnim).startDelay(100);

                BaseAnimationControl topAnim = new BaseAnimationControl(top);
                topAnim.stackToBottomOf(imgAnim).startDelay(150);
            }
        }, 3000);

        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                //init();
                BaseAnimationControl imgAnim = new ImageAnimationControl(img);
                imgAnim.scaleX(1.6F).scaleY(1.6F).y(0).startDelay(50);

                BaseAnimationControl topAnim = new BaseAnimationControl(top);
                topAnim.stackToBottomOf(imgAnim).startDelay(100);

                BaseAnimationControl bottomAnim = new BaseAnimationControl(bottpm);
                bottomAnim.stackToBottomOf(topAnim).startDelay(150);

            }
        }, 4000);

        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                init();
            }
        }, 5000);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    /*        BaseAnimationControl.with(action).y(-action.getHeight()).start().gone();
        BaseAnimationControl.with(top).y(0).startDelayed(50);
        BaseAnimationControl.with(img).y(top.getHeight() - ((img.getHeight() - (img.getHeight() * 0.4F)) / 2)).startDelayed(50);
        BaseAnimationControl.with(img).X(0.4F).Y(0.4F).startDelayed(200);
        BaseAnimationControl.with(bottpm).y(top.getHeight() + (img.getHeight() * 0.4F)).startDelayed(300);*/

}

