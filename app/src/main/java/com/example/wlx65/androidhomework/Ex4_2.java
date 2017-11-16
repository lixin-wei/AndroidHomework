package com.example.wlx65.androidhomework;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.URI;


public class Ex4_2 extends MyActivity {
    final int SELECT_ITEM_REQUEST = 1000;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SELECT_ITEM_REQUEST) {
            showToast(this, "clicked: " + resultCode);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex4_2);
        Button btn_dial = findViewById(R.id.btn_dial);
        Button btn_login1 = findViewById(R.id.btn_login1);
        Button btn_login2 = findViewById(R.id.btn_login2);
        final EditText et_phone = findViewById(R.id.et_phone);
        final EditText et_user = findViewById(R.id.et_user);
        final EditText et_password = findViewById(R.id.et_password);

        btn_dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone_num = et_phone.getText().toString();
                Intent dial_intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone_num));
                startActivity(dial_intent);
            }
        });

        btn_login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ex4_2.this, Ex4_2_sub1.class);
                String user_name = et_user.getText().toString();
                String password = et_password.getText().toString();
                intent.putExtra("user_name", user_name);
                intent.putExtra("password", password);
                startActivity(intent);
            }
        });

        btn_login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ex4_2.this, Ex4_2_sub2.class);
                startActivityForResult(intent, SELECT_ITEM_REQUEST);
            }
        });
    }
}
