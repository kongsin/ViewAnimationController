package com.kongsin.kognsin.animate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by kongsin on 3/25/2017.
 */

public class MainExample extends AppCompatActivity implements View.OnClickListener{
    private Button button, button2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_example);
        button = (Button) findViewById(R.id.first);
        button.setOnClickListener(this);
        button2 = (Button) findViewById(R.id.second);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == button){
            startActivity(new Intent(MainExample.this, MainActivity.class));
        } else if (v == button2){
            startActivity(new Intent(MainExample.this, Example2.class));
        }
    }
}
