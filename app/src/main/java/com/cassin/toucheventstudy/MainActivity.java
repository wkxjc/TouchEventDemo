package com.cassin.toucheventstudy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import static android.view.View.OVER_SCROLL_NEVER;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Fragment> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VerticalViewPager viewPager = findViewById(R.id.view_pager);
        list.add(new HomeFragment());
        list.add(new DetailFragment());
        viewPager.setOffscreenPageLimit(2);//缓存相邻的两个页面，防止切换时重新加载fragment
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        viewPager.setPageTransformer(true, new DefaultTransformer());
        viewPager.setOverScrollMode(OVER_SCROLL_NEVER);
    }
}
