package com.seniorlibs.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.seniorlibs.baselib.utils.LogUtils;
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

    private static final String TAG = "Tab_TabVPFragActivity";

    /**
     * 跳转回调code
     */
    public static final int REQUEST_CODE = 1001;

    public static final String INTENT_RESULT = "intent_result";

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private Frg_2 frg_2 = new Frg_2();
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_viewpager);

        tabLayout = findViewById(R.id.tl_tabs);
        viewPager = findViewById(R.id.vp_content);

        fragments.add(new Frg_1());
        fragments.add(frg_2);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        LogUtils.e(TAG, "onActivityResultA1 + requestCode:" + requestCode + " resultCode：" + resultCode);

        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE:
                    String result = data.getStringExtra(INTENT_RESULT);
                    LogUtils.e(TAG, "onActivityResultA2 + result:" + result);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 跳转到SetResultActivity
     *
     * @param v
     */
    public void toSetResultActivity(View v) {
//        startActivityForResult(new Intent(this, SetResultActivity.class), REQUEST_CODE);
//        ActivityCompat.startActivityForResult(this, new Intent(this, SetResultActivity.class), REQUEST_CODE, null);
//        frg_2.startActivityForResult(new Intent(this, SetResultActivity.class), REQUEST_CODE);
    }
}