package com.example.wlx65.androidhomework;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.Calendar;


public class Ex3 extends MyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex3);

        //View 实例
        ListView listView1 = findViewById(R.id.list_view1);
        Button btn_alert_simple = findViewById(R.id.btn_alert_simple);
        Button btn_alert_complex = findViewById(R.id.btn_alert_complex);
        DatePicker datePicker1 = findViewById(R.id.date_picker1);
        Button btn_tp = findViewById(R.id.btn_tp);
        final ProgressBar pg_small = findViewById(R.id.pgbar_small);
        final ProgressBar pg_hori = findViewById(R.id.pgbar_hori);
        Button btn_inc = findViewById(R.id.btn_inc);
        Button btn_dec = findViewById(R.id.btn_dec);
        Button btn_vis = findViewById(R.id.btn_vis);
        SeekBar seek = findViewById(R.id.seek);

        //初始化区
        ArrayAdapter<String> adp = new ArrayAdapter<String>(
                this, android.R.layout.simple_dropdown_item_1line
        );
        for (int i = 0; i < 100;  ++i) {
            adp.add("item" + i);
        }
        listView1.setAdapter(adp);

        //日期设置
        Calendar cld = Calendar.getInstance();
        int year = cld.get(Calendar.YEAR);
        int month = cld.get(Calendar.MONTH);
        int day = cld.get(Calendar.DAY_OF_MONTH);
        datePicker1.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                showToast(Ex3.this, year + "-" + monthOfYear + "-" + dayOfMonth);
            }
        });
        //事件区

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showToast(Ex3.this, "item" + position + " clicked");
            }
        });
        //拖拽响应，取消父视图的事件拦截
        listView1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        btn_alert_simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dig = new AlertDialog.Builder(Ex3.this);
                dig.setMessage("提示框的文字");
                dig.setPositiveButton("确定", null);
                dig.setNeutralButton("帮助", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showToast(Ex3.this, "点击了帮助");
                    }
                });
                dig.setNegativeButton("取消", null);
                dig.show();
            }
        });
        btn_alert_complex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dig = new AlertDialog.Builder(Ex3.this);
                final String[] items = new String[] {
                        "数学", "英语", "物理"
                };
                boolean[] isChecked = new boolean[] {
                        false, false, false
                };
                dig.setMultiChoiceItems(items,isChecked ,new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                            showToast(Ex3.this, "你点击了: " + items[which]);
                        }
                });
                dig.show();
            }
        });
        btn_tp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cld = Calendar.getInstance();
                TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        showToast(Ex3.this, hourOfDay + ":" + minute);
                    }
                };
                TimePickerDialog tpd = new TimePickerDialog(
                        Ex3.this,
                        listener,
                        cld.get(Calendar.HOUR_OF_DAY),
                        cld.get(Calendar.MINUTE),
                        false
                );
                tpd.show();
            }
        });
        btn_vis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pg_small.getVisibility() == View.VISIBLE) {
                    pg_small.setVisibility(View.GONE);
                }
                else {
                    pg_small.setVisibility(View.VISIBLE);
                }
            }
        });
        btn_inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pg_hori.incrementProgressBy(10);
            }
        });
        btn_dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pg_hori.incrementProgressBy(-10);
            }
        });
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                showToast(Ex3.this, "Seek bar at " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                showToast(Ex3.this, "Seek bar stopped at " + seekBar.getProgress());
            }
        });
    }
}
