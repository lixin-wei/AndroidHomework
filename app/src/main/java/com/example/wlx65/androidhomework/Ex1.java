package com.example.wlx65.androidhomework;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Ex1 extends ActivityWithBack {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex1);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
