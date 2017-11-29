package com.example.wlx65.androidhomework;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

public class Ex5 extends MyActivity {
    TextView tv;
    Button btn1;
    Button btn2;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex5);
        tv = findViewById(R.id.tv1);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        lv = findViewById(R.id.lv1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu pm = new PopupMenu(Ex5.this, btn1);
                getMenuInflater().inflate(R.menu.ex5_choose_font, pm.getMenu());
                pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        if(id == R.id.item_red) {
                            tv.setTextColor(Color.RED);
                        }
                        else if(id == R.id.item_blue) {
                            tv.setTextColor(Color.BLUE);
                        }
                        else if(id == R.id.item_green) {
                            tv.setTextColor(Color.GREEN);
                        }
                        return false;
                    }
                });
                pm.show();
            }
        });
        final PopupMenu pm_btn2 = new PopupMenu(Ex5.this, btn2);
        getMenuInflater().inflate(R.menu.ex5_menu2, pm_btn2.getMenu());
        pm_btn2.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.isChecked())item.setChecked(false);
                else item.setChecked(true);
                int id = item.getItemId();
                if(id == R.id.item_traffic) {
                    showToast(Ex5.this, "Traffic");
                }
                else if(id == R.id.item_map) {
                    showToast(Ex5.this, "Map");
                }
                else if(id == R.id.item_satellite) {
                    showToast(Ex5.this, "Satellite");
                }
                return false;
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pm_btn2.show();
            }
        });

        ArrayList<String> listItems=new ArrayList<String>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        lv.setAdapter(adapter);
        adapter.add("我喜欢");
        adapter.add("我讨厌");
        adapter.add("我不在乎");
        registerForContextMenu(lv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.add_item) {
            showToast(Ex5.this, "Click Add.");
        }
        else {
            showToast(Ex5.this, "Click Remove.");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.ex5_menu3, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int selected = 0;
        for(int i=0 ; i<lv.getCount() ; ++i) {
            if(lv.getItemIdAtPosition(i) == info.id) {
                selected = i;
                break;
            }
        }
        String parentStr = lv.getItemAtPosition(selected).toString();
        String sonStr = "";
        if(item.getItemId() == R.id.item_red) sonStr = "红色";
        else if(item.getItemId() == R.id.item_green) sonStr = "绿色";
        else if(item.getItemId() == R.id.item_blue) sonStr = "蓝色";
        tv.setText(parentStr + sonStr);
        return super.onContextItemSelected(item);
    }
}
