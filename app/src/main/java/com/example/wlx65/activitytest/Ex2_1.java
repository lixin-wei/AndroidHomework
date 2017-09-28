package com.example.wlx65.activitytest;

import android.app.Activity;
import android.support.annotation.AnyRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

import static android.content.ContentValues.TAG;

public class Ex2_1 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex2_1);

        final EditText et_height = (EditText) findViewById(R.id.et_height);
        final EditText et_weight = (EditText) findViewById(R.id.et_weight);
        final EditText et_username = (EditText) findViewById(R.id.et_username);
        final EditText et_password = (EditText) findViewById(R.id.et_password);
        Button btn_cal = (Button)findViewById(R.id.btn_cal);
        Button btn_reset = (Button)findViewById(R.id.btn_reset);

        //计算BMI并在Toast中显示
        btn_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double height = Double.parseDouble(et_height.getText().toString())/100;
                    double weight = Double.parseDouble(et_weight.getText().toString());
                    double BMI = weight / (height * height);
                    Log.i(TAG, String.format("height:%.2f, weight:%.2f", height, weight));
                    String healthInfo;
                    if(BMI >= 40) {
                        healthInfo = "极度超重";
                    }
                    else if (BMI >= 30) {
                        healthInfo = "严重超重";
                    }
                    else if (BMI >= 25) {
                        healthInfo = "超重";
                    }
                    else if (BMI >= 19) {
                        healthInfo = "健康体重";
                    }
                    else {
                        healthInfo = "体重偏低";
                    }
                    Toast.makeText(Ex2_1.this,
                            String.format(Locale.CHINA, "BMI: %.2f, %s", BMI, healthInfo),
                            Toast.LENGTH_SHORT)
                            .show();
                }
                catch (Exception e) {
                    Toast.makeText(Ex2_1.this,
                            "输入不合法",
                            Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
        //重置输入
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_height.setText("");
                et_weight.setText("");
                et_username.setText("");
                et_password.setText("");
            }
        });
    }
}
