package com.example.wlx65.androidhomework;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");

        String action = intent.getAction();
        if(action.equals(MyActivity.MY_ACTION)) {
            String msg = intent.getStringExtra("msg");
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
        else if(action.equals(Intent.ACTION_DATE_CHANGED)) {
            Toast.makeText(context, "日期改变", Toast.LENGTH_SHORT).show();
        }
        else if(action.equals(Intent.ACTION_TIME_CHANGED)) {
            Toast.makeText(context, "时间改变", Toast.LENGTH_SHORT).show();
        }
    }
}
