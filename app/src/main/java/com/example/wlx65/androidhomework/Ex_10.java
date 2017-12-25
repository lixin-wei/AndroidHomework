package com.example.wlx65.androidhomework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Ex_10 extends MyActivity {

    EditText et_msg;
    Button btn_post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_10);

        et_msg = findViewById(R.id.et_msg);
        btn_post = findViewById(R.id.btn_post);

        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(MY_ACTION);
                intent.putExtra("msg", et_msg.getText().toString());
                sendBroadcast(intent);
            }
        });
    }
}
