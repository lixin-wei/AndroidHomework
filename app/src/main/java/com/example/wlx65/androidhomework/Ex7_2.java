package com.example.wlx65.androidhomework;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

public class Ex7_2 extends MyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex7_2);

        TabLayout tableLayout = findViewById(R.id.tab);
        ViewPager viewPager = findViewById(R.id.view_paper);

        final List<String> titleList = new ArrayList<>();
        titleList.add("头条"); titleList.add("娱乐"); titleList.add("体育");
//        viewPager.setAdapter(new TabPagerAdapter(viewList, titleList));
//        tableLayout.setupWithViewPager(viewPager);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if(position == 0) {
                    return new Ex7_2_fg1();
                }
                else if(position == 1) {
                    return new Ex7_2_fg2();
                }
                else if(position == 2) {
                    return new Ex7_2_fg3();
                }
                return null;
            }

            @Override
            public int getCount() {
                return titleList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titleList.get(position);
            }
        });
        tableLayout.setupWithViewPager(viewPager);
    }
}
