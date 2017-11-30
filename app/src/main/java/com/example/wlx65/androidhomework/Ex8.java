package com.example.wlx65.androidhomework;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Ex8 extends MyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex8);
        Button btn_single = findViewById(R.id.btn_single);
        Button btn_multi = findViewById(R.id.btn_multi);
        Button btn_end = findViewById(R.id.btn_end);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        final ProgressBar pg1 = findViewById(R.id.pg1);
        final EditText text1 = findViewById(R.id.et1);

        btn_single.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                int sum = 0;
                for(int i=0 ; i<1000000000 ; ++i) {
                    sum += i;
                }
                text1.setText(Math.random() + "asd");
            }
        });
        final Runnable f = new Runnable() {
            @Override
            public void run() {
                int sum = 0;
                for(int i=0 ; i<1000000000 ; ++i) {
                    sum += i;
                }
            }
        };
        final Thread thread = new Thread(f);
        btn_multi.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                thread.start();
                text1.setText(Math.random() + "asd");
            }
        });
        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread.interrupt();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable f = new Runnable() {
                    @Override
                    public void run() {
                        while(pg1.getProgress() < 100) {
                            pg1.incrementProgressBy(3);
                            try {
                                Thread.sleep(1000);
                            }
                            catch (InterruptedException e) {
                                Log.e("asd", "run: ", e);
                            }
                        }
                    }
                };
                Thread t = new Thread(f);
                t.start();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable f = new Runnable() {
                    @Override
                    public void run() {
                        while(pg1.getProgress() < 100) {
                            pg1.incrementProgressBy(5);
                            try {
                                Thread.sleep(1000);
                            }
                            catch (InterruptedException e) {
                                Log.e("asd", "run: ", e);
                            }
                        }
                    }
                };
                Thread t = new Thread(f);
                t.start();
            }
        });
    }
}
