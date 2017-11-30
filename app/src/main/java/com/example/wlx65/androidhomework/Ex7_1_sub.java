package com.example.wlx65.androidhomework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.wlx65.androidhomework.MyActivity;
import com.example.wlx65.androidhomework.R;

public class Ex7_1_sub extends MyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex7_1_sub);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("大标题");
        toolbar.setSubtitle("小标题");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
