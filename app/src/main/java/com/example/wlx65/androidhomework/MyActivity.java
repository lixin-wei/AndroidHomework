package com.example.wlx65.androidhomework;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MyActivity extends AppCompatActivity {
    public static String MY_ACTION = "com.hznu.test.msg";
    private Toast toast_current;
    //标题栏的返回按钮
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //返回按钮
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //导航栏颜色
        getWindow().setNavigationBarColor(ContextCompat.getColor(getBaseContext(), R.color.colorPrimaryDark));

    }

    //立即显示Toast，覆盖上一个
    public void showToast(Context context, String text) {
        if (toast_current != null) {
            toast_current.cancel();
        }
        toast_current = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast_current.show();
    }
}
