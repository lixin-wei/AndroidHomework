package com.example.wlx65.androidhomework;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Ex_json extends MyActivity {

    Button btn_request_weather;
    EditText et_city_name;
    ListView list_weather;
    EditText et_username;
    EditText et_password;
    Button btn_post;
    TextView tv_res;

    OkHttpClient client = new OkHttpClient();
    Handler mainHandler = new Handler(Looper.getMainLooper());

    Call post(String url, RequestBody body, Callback callback) {
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }
    Call get(String url, Callback callback) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_json);

        btn_request_weather = findViewById(R.id.btn_request_weather);
        et_city_name = findViewById(R.id.et_city_name);
        list_weather = findViewById(R.id.list_weather);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_post = findViewById(R.id.btn_post);
        tv_res = findViewById(R.id.tv_res);

        //获取天气按钮点击事件
        btn_request_weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = et_city_name.getText().toString();
                //异步获取信息
                get("http://wthrcdn.etouch.cn/weather_mini?city=" + cityName, new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        final String json_str = response.body().string();
                        //解析JSON
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    ArrayAdapter<String> adp = new ArrayAdapter<String>(
                                            Ex_json.this, android.R.layout.simple_dropdown_item_1line
                                    );
                                    JSONObject jsonObject = new JSONObject(json_str).getJSONObject("data");
                                    JSONArray arr = jsonObject.getJSONArray("forecast");
                                    for (int i = 0; i < arr.length(); ++i) {
                                        JSONObject day = arr.getJSONObject(i);
                                        String date = day.getString("date");
                                        String high = day.getString("high");
                                        String low = day.getString("low");
                                        String dir = day.getString("fengxiang");
                                        String type = day.getString("type");
                                        adp.add(date + ","+high+ "," + low+ "," + dir+ ","+ type);
                                        //System.out.println(date + ","+high+ "," + low+ "," + dir+ ","+ type);
                                    }
                                    list_weather.setAdapter(adp);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    mainHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            showToast(Ex_json.this, "解析错误");
                                        }
                                    });
                                }
                            }
                        });
                    }
                });
            }
        });

        //登录post按钮点击事件
        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //构建body
                RequestBody body = new FormBody.Builder()
                        .add("user_id", et_username.getText().toString())
                        .add("password", et_password.getText().toString())
                        .build();
                post("https://d-star.xyz/android/login.php", body, new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        final String body = response.body().string();
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                showToast(Ex_json.this, body);
                            }
                        });
                        System.out.println(body);
                    }
                });
            }
        });
    }

}
