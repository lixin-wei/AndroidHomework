package com.example.wlx65.androidhomework;

import android.app.ActionBar;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class Ex2_2 extends ActivityWithBack {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex2_2);
        //控件实例化
        final SimpleSpinner<Area> spin_province = findViewById(R.id.spin_province);
        final SimpleSpinner<Area> spin_city = findViewById(R.id.spin_city);
        final SimpleSpinner<Area> spin_area = findViewById(R.id.spin_area);
        Button btn_fire = (Button) findViewById(R.id.btn_fire);
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
    }
}

