package com.example.kognsin.animate;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        AnimationControl.with(action).y(-action.getHeight()).start().gone();
        AnimationControl.with(top).y(0).startDelayed(50);
        AnimationControl.with(img).y(top.getHeight() - ((img.getHeight() - (img.getHeight() * 0.4F)) / 2)).startDelayed(50);
        AnimationControl.with(img).scaleX(0.4F).scaleY(0.4F).startDelayed(200);
        AnimationControl.with(bottpm).y(top.getHeight() + (img.getHeight() * 0.4F)).startDelayed(300);
        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                init();
            }
        }, 2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    /*        AnimationControl.with(action).y(-action.getHeight()).start().gone();
        AnimationControl.with(top).y(0).startDelayed(50);
        AnimationControl.with(img).y(top.getHeight() - ((img.getHeight() - (img.getHeight() * 0.4F)) / 2)).startDelayed(50);
        AnimationControl.with(img).scaleX(0.4F).scaleY(0.4F).startDelayed(200);
        AnimationControl.with(bottpm).y(top.getHeight() + (img.getHeight() * 0.4F)).startDelayed(300);*/

}

