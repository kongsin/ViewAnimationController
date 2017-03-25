package com.kongsin.kognsin.animate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.kongsin.kanimationcontroller.AnimationQueue;
import com.kongsin.kanimationcontroller.BaseAnimationObject;

public class Example2 extends AppCompatActivity implements View.OnClickListener{

    private boolean isCollapse = false;
    private LinearLayout content, root;
    private Button button;
    private int tempRootHeight;
    private int tempContentHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example2);
        root = (LinearLayout) findViewById(R.id.root_layout);
        content = (LinearLayout) findViewById(R.id.content_group);
        button = (Button) findViewById(R.id.button);
        root.postDelayed(new Runnable() {
            @Override
            public void run() {
                tempRootHeight = root.getHeight();
                tempContentHeight = content.getHeight();
            }
        }, 100);
        root.setOnClickListener(this);
    }

    private void collapse(){

        BaseAnimationObject _root = new BaseAnimationObject(root);
        _root.height(button.getHeight() + button.getPaddingTop() + button.getPaddingBottom() + toDp(8));

        BaseAnimationObject _content = new BaseAnimationObject(content);
        _content.height(0);

        BaseAnimationObject _button = new BaseAnimationObject(button);
        _button.moveToCenterVertical(_root);

        AnimationQueue queue = new AnimationQueue(0, _root);
        queue.nextQueue(0, _content);
        queue.nextQueue(0, _button);
        queue.startTogether();

    }

    private void expand(){

        BaseAnimationObject _root = new BaseAnimationObject(root);
        _root.height(tempRootHeight);

        final BaseAnimationObject _content = new BaseAnimationObject(content);
        _content.height(tempContentHeight);

        final BaseAnimationObject _button = new BaseAnimationObject(button);
        _button.stackToBottomOf(_content).marginTop(toDp(16)).start();

        AnimationQueue queue = new AnimationQueue(0, _root);
        queue.nextQueue(0, _content);
        queue.nextQueue(0, _button);
        queue.startTogether();

    }


    private int toDp(int val){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, val, getResources().getDisplayMetrics());
    }

    @Override
    public void onClick(View v) {
        if(v == root){
            if (isCollapse){
                isCollapse = false;
                expand();
            } else {
                isCollapse = true;
                collapse();
            }
        }
    }
}
