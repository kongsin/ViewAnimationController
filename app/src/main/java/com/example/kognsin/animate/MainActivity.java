package com.example.kognsin.animate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.kanimationcontroller.BaseAnimationControl;
import com.example.kanimationcontroller.ImageAnimationControl;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout main;
    ImageView img, img2, img3;

    private static final String TAG = "MainActivity";
    private BaseAnimationControl imgAnim;
    private BaseAnimationControl imgAnim2;
    private BaseAnimationControl imgAnim3;

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
    }

    public void newInstance(){
        imgAnim = new ImageAnimationControl(img);
        imgAnim2 = new ImageAnimationControl(img2);
        imgAnim3 = new ImageAnimationControl(img3);
    }

    @Override
    public void onClick(final View v) {
        if (v == img){
            imgAnim.newAnimate().goToTop(main).moveToCenterHorizontal(main).start();
            imgAnim2.newAnimate().moveToCenterHorizontal(main).start();
            imgAnim3.newAnimate().moveToCenterHorizontal(main).startDelay(250);
        } else if (v == img2){
            imgAnim2.newAnimate().goToBottom(main).start();
        } else if (v == img3){
            imgAnim3.newAnimate().goToTop(main).start();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

}

