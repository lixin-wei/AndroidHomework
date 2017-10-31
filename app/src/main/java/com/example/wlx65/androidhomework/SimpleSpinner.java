package com.example.wlx65.androidhomework;


import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SimpleSpinner<T> extends Spinner {

    private ArrayAdapter<T> adapter;
    private String placeHolder;
    //初始化adapter, 并给spinner绑定adapter
    private void init(Context context) {
        adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item);
        this.setAdapter(adapter);
    }
    public SimpleSpinner(Context context) {
        super(context);
        init(context);
    }
    public SimpleSpinner(Context context, AttributeSet attr) {
        super(context, attr);
        init(context);
    }

    public void addItem(T item) {
        adapter.add(item);
    }
    public T getItem(int position) {
        return adapter.getItem(position);
    }
    public void clear() {
        adapter.clear();
    }
}
