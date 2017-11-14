package com.example.wlx65.androidhomework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


public class Ex4_1 extends MyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex4_1);
        Log.d("Ex4", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Ex4", "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Ex4", "onReStart");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d("Ex4", "onPostResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Ex4", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Ex4", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Ex4", "onDestroy");
    }
}
