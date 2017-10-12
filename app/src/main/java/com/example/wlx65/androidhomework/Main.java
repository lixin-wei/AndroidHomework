package com.example.wlx65.androidhomework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Main extends MyActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化控件
        Button button_ex1 = (Button) findViewById(R.id.button_ex1);
        Button button_ex2_1 = (Button) findViewById(R.id.button_ex2_1);
        Button button_ex2_2 = (Button) findViewById(R.id.button_ex2_2);
        //控件事件
        button_ex1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, Ex1.class);
                startActivity(intent);
            }
        });
        button_ex2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, Ex2_1.class);
                startActivity(intent);
            }
        });
        button_ex2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, Ex2_2.class);
                startActivity(intent);
            }
        });
    }
}
