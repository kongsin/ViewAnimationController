package com.example.kognsin.animate;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.kanimationcontroller.BaseAnimationControl;
import com.example.kanimationcontroller.IAnimateSet;
import com.example.kanimationcontroller.ImageAnimationControl;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout main, root;
    ImageView img;
    Button btn1, btn2;

    private static final String TAG = "MainActivity";
    private BaseAnimationControl imgAnim;
    private BaseAnimationControl btn1Anim;
    private BaseAnimationControl btn2Anim;

    public void init(){
        setContentView(R.layout.activity_main);
        main = (LinearLayout) findViewById(R.id.main);
        root = (LinearLayout) findViewById(R.id.root);
        img = (ImageView) findViewById(R.id.img);
        btn1 = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button1);
        img.setOnClickListener(this);
    }

    public void newInstance(){
        imgAnim = new ImageAnimationControl(img);
        btn1Anim = new BaseAnimationControl(btn1);
        btn2Anim = new BaseAnimationControl(btn2);
    }

    @Override
    public void onClick(final View v) {
        newInstance();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imgAnim.newAnimate().scaleX(2.0F).scaleY(2.0F).x(0).startDelay(50);
                btn1Anim.newAnimate().y(imgAnim.getY()).stackToRightOf(imgAnim).startDelay(100);
                btn2Anim.newAnimate().y(imgAnim.getY()).stackToRightOf(btn1Anim).startDelay(150);
            }
        }, 50);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imgAnim.newAnimate().scaleX(1).scaleY(1).x(100).startDelay(50);
                btn1Anim.newAnimate().y(imgAnim.getY()).stackToRightOf(imgAnim).startDelay(100);
                btn2Anim.newAnimate().y(imgAnim.getY()).stackToRightOf(btn1Anim).startDelay(100);
            }
        }, 800);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imgAnim.newAnimate().scaleX(1).scaleY(1).x(main.getWidth() - imgAnim.getScaledWidth()).startDelay(50);
                btn1Anim.newAnimate().y(imgAnim.getY()).stackToLeftOf(imgAnim).startDelay(100);
                btn2Anim.newAnimate().y(imgAnim.getY()).stackToLeftOf(btn1Anim).startDelay(150);
            }
        }, 1600);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imgAnim.newAnimate().scaleX(1.0F).scaleY(1.0F).x(main.getWidth() - imgAnim.getScaledWidth()).startDelay(50);
                btn1Anim.newAnimate().y(imgAnim.getY()).stackToLeftOf(imgAnim).startDelay(100);
                btn2Anim.newAnimate().y(imgAnim.getY()).stackToLeftOf(btn1Anim).startDelay(150);
            }
        }, 2400);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imgAnim.newAnimate().x((main.getWidth() / 2) - (imgAnim.getScaledWidth() / 2)).startDelay(100);
                btn1Anim.newAnimate().x(imgAnim.getX()).stackToBottomOf(imgAnim).startDelay(100);
                btn2Anim.newAnimate().x(imgAnim.getX()).stackToBottomOf(btn1Anim).startDelay(150);
            }
        }, 3000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btn1Anim.newAnimate().x(imgAnim.getX()).stackToTopOf(imgAnim).startDelay(100);
                btn2Anim.newAnimate().x(imgAnim.getX()).stackToTopOf(btn1Anim).startDelay(150);
            }
        }, 3800);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imgAnim.newAnimate().scaleX(2.0F).scaleY(2.0F).startDelay(50);
                btn1Anim.newAnimate().stackToTopOf(imgAnim).startDelay(50);
                btn2Anim.newAnimate().stackToTopOf(btn1Anim).startDelay(50);
            }
        }, 4600);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imgAnim.newAnimate().scaleX(1.0F).scaleY(1.0F).startDelay(50);
                btn1Anim.newAnimate().stackToTopOf(imgAnim).startDelay(50);
                btn2Anim.newAnimate().stackToTopOf(btn1Anim).startDelay(50);
            }
        }, 5400);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btn1Anim.newAnimate().scaleX(2.0F).scaleY(2.0F).stackToTopOf(imgAnim).startDelay(50);
                btn2Anim.newAnimate().stackToTopOf(btn1Anim).startDelay(50);
            }
        }, 6200);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btn1Anim.newAnimate().scaleX(1.0F).scaleY(1.0F).stackToTopOf(imgAnim).startDelay(50);
                btn2Anim.newAnimate().stackToTopOf(btn1Anim).startDelay(50);
            }
        }, 7000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btn1Anim.newAnimate().stackToBottomOf(imgAnim).startDelay(50);
                btn2Anim.newAnimate().stackToBottomOf(btn1Anim).startDelay(50);
            }
        }, 7800);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btn1Anim.newAnimate().stackToBottomOf(imgAnim).startDelay(50);
                btn2Anim.newAnimate().stackToBottomOf(btn1Anim).startDelay(50);
            }
        }, 8600);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imgAnim.newAnimate().scaleX(2.0F).scaleY(2.0F).startDelay(50);
                btn1Anim.newAnimate().stackToBottomOf(imgAnim).startDelay(50);
                btn2Anim.newAnimate().stackToBottomOf(btn1Anim).startDelay(50);
            }
        }, 9400);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imgAnim.newAnimate().scaleX(1.0F).scaleY(1.0F).startDelay(50);
                btn1Anim.newAnimate().stackToBottomOf(imgAnim).startDelay(50);
                btn2Anim.newAnimate().stackToBottomOf(btn1Anim).startDelay(50);
            }
        }, 10200);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btn1Anim.newAnimate().scaleX(2.0F).scaleY(2.0F).stackToBottomOf(imgAnim).startDelay(50);
                btn2Anim.newAnimate().stackToBottomOf(btn1Anim).startDelay(50);
            }
        }, 11000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btn1Anim.newAnimate().scaleX(1.0F).scaleY(1.0F).stackToBottomOf(imgAnim).startDelay(50);
                btn2Anim.newAnimate().stackToBottomOf(btn1Anim).startDelay(50);
            }
        }, 11800);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btn2Anim.newAnimate().scaleX(2.0F).scaleY(2.0F).stackToBottomOf(btn1Anim).startDelay(50);
            }
        }, 12600);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btn2Anim.newAnimate().scaleX(1.0F).scaleY(1.0F).stackToBottomOf(btn1Anim).startDelay(50);
            }
        }, 13200);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

}

