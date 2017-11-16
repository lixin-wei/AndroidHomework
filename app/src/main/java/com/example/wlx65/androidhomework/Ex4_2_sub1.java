package com.example.wlx65.androidhomework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.widget.TextView;

public class Ex4_2_sub1 extends MyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex4_2_sub1);

        ConstraintLayout layout = findViewById(R.id.root);
        Intent intent = getIntent();
        TextView text = new TextView(Ex4_2_sub1.this);
        text.append("username: " + intent.getStringExtra("user_name") + "\n");
        text.append("password: " + intent.getStringExtra("password"));
        layout.addView(text);
    }
}
