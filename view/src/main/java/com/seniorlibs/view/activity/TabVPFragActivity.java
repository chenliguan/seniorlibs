package com.seniorlibs.view.activity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.seniorlibs.view.R;
import com.seniorlibs.view.fragment.androidx.Frg_1;
import com.seniorlibs.view.fragment.androidx.Frg_2;
import com.seniorlibs.view.fragment.androidx.Frg_3;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/11/27.
 * Mender:
 * Modify:
 * Description: TabLayout + ViewPager + Fragment
 */
public class TabVPFragActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    List<Fragment> fragments = new ArrayList<>();
    List<String> titles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_viewpager);

        tabLayout = findViewById(R.id.tl_tabs);
        viewPager = findViewById(R.id.vp_content);

        fragments.add(new Frg_1());
        fragments.add(new Frg_2());
        fragments.add(new Frg_3());
        titles.add("fragment1");
        titles.add("fragment2");
        titles.add("fragment3");
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                super.destroyItem(container, position, object);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        });

        tabLayout.setupWithViewPager(viewPager);
    }

    private ViewPager findViewGroup(ViewParent parent) {
        if (parent == null) {
            return null;
        }
        if (parent instanceof ViewPager) {
            return (ViewPager) parent;
        }
        return findViewGroup(parent.getParent());
    }
}