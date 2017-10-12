package com.example.wlx65.androidhomework;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static android.widget.CompoundButton.*;

public class Ex2_2 extends MyActivity {
    private class Area {
        String name;
        int id;
        Area(String name, int id) {
            this.name = name;
            this.id = id;
        }

        @Override
        public String toString() {
            return name;
        }
    }
    SimpleSpinner<Area> spin_province;
    SimpleSpinner<Area> spin_city;
    SimpleSpinner<Area> spin_area;
    Button btn_fire;
    RadioGroup rg_sex;
    CheckBox cb_chinese;
    CheckBox cb_math;
    CheckBox cb_english;
    ToggleButton tg_image;
    ImageView img_star;
    //显示科目复选框的汇总Toast
    private void showCheckBoxToast() {
        ArrayList<String> checkedList = new ArrayList<>();
        if (cb_chinese.isChecked()) {
            checkedList.add("语文");
        }
        if(cb_math.isChecked()) {
            checkedList.add("数学");
        }
        if(cb_english.isChecked()) {
            checkedList.add("英语");
        }
        StringBuilder text = new StringBuilder("选择了: ");
        for (int i = 0; i < checkedList.size(); ++i) {
            if (i != 0) text.append(", ");
            text.append(checkedList.get(i));
        }
        showToast(Ex2_2.this, text.toString());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex2_2);
        //控件实例化
        spin_province = findViewById(R.id.spin_province);
        spin_city = findViewById(R.id.spin_city);
        spin_area = findViewById(R.id.spin_area);
        btn_fire = findViewById(R.id.btn_fire);
        rg_sex = findViewById(R.id.rg_sex);
        cb_chinese = findViewById(R.id.cb_chinese);
        cb_math = findViewById(R.id.cb_math);
        cb_english = findViewById(R.id.cb_english);
        tg_image = findViewById(R.id.tg_image);
        img_star = findViewById(R.id.img_star);
        //地域数据库初始化
        AssetDataBaseHelper dbHelper = new AssetDataBaseHelper(this, "china_areas.db");
        try {
            dbHelper.crateDatabase();
        }
        catch (Exception e) {
            Log.e(TAG, "onCreate: error");
        }
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        //先填充省份
        Cursor cursor = db.rawQuery("SELECT * FROM provinces", null);
        if(cursor.moveToFirst()) {
            do {
                String str_province = cursor.getString(cursor.getColumnIndex("province"));
                int province_id = cursor.getInt(cursor.getColumnIndex("provinceid"));
                spin_province.addItem(new Area(str_province, province_id));
            }while(cursor.moveToNext());
        }
        cursor.close();
        //当选中省份，清空城市和地区，更新城市
        spin_province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //清空城市和地区
                spin_city.clear();
                spin_area.clear();
                //咨询并更新相应城市
                String province_id = Integer.toString(spin_province.getItem(i).id);
                Cursor cursor = db.rawQuery("SELECT * FROM cities WHERE provinceid = ?",
                        new String[] {province_id});
                if (cursor.moveToFirst()) {
                    do {
                        String str_city = cursor.getString(cursor.getColumnIndex("city"));
                        int city_id = cursor.getInt(cursor.getColumnIndex("cityid"));
                        spin_city.addItem(new Area(str_city, city_id));
                    }while(cursor.moveToNext());
                }
                //选两次，以防止不触发选中事件
                spin_city.setSelection(1, false);
                spin_city.setSelection(0, true);
                cursor.close();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //当选中城市，清空地区，更新地区
        spin_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //清空地区
                spin_area.clear();
                //咨询并更新相应地区
                String city_id = Integer.toString(spin_city.getItem(i).id);
                Cursor cursor = db.rawQuery("SELECT * FROM areas WHERE cityid = ?",
                        new String[] {city_id});
                if (cursor.moveToFirst()) {
                    do {
                        String str_area = cursor.getString(cursor.getColumnIndex("area"));
                        int area_id = cursor.getInt(cursor.getColumnIndex("areaid"));
                        spin_area.addItem(new Area(str_area, area_id));
                    }while(cursor.moveToNext());
                }
                cursor.close();
                //选两次，以防止不触发选中事件
                spin_area.setSelection(1, false);
                spin_area.setSelection(0, true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //按钮
        btn_fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spin_city.setSelection(1, true);
            }
        });
        //性别单选框
        rg_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.radio_female) {
                    showToast(Ex2_2.this, "女");
                }
                else {
                    showToast(Ex2_2.this, "男");
                }
            }
        });
        //科目多选框
        OnCheckedChangeListener listener_cb_change =  new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                showCheckBoxToast();
            }
        };
        cb_chinese.setOnCheckedChangeListener(listener_cb_change);
        cb_math.setOnCheckedChangeListener(listener_cb_change);
        cb_english.setOnCheckedChangeListener(listener_cb_change);
        //toggle button
        tg_image.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    img_star.setImageResource(android.R.drawable.btn_star_big_on);
                }
                else {
                    img_star.setImageResource(android.R.drawable.btn_star_big_off);
                }
            }
        });
    }
}

