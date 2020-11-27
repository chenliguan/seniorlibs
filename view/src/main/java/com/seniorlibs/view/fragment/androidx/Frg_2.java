package com.seniorlibs.view.fragment.androidx;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.seniorlibs.view.R;

import java.util.ArrayList;
import java.util.List;

public class Frg_2 extends BaseAndroidXFragment {

    private static final String TAG = "Tab_Frg_2";

    private static FragmentPagerAdapter pagerAdapter = null;
    private View view = null;
    private List<Fragment> listPagerViews = null;

    public Frg_2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_frg_2, container, false);
        view.findViewById(R.id.tv_frg_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Frg_2 点击", Toast.LENGTH_SHORT).show();
            }
        });
        initViewPager();
        return view;
    }

    private void initViewPager() {
        listPagerViews = new ArrayList<>();
        listPagerViews.add(new Frg_4());
        listPagerViews.add(new Frg_5());
        listPagerViews.add(new Frg_6());
        ViewPager viewPager2 = (ViewPager) view.findViewById(R.id.frg_2_vp);
        pagerAdapter = new FragmentPagerAdapter(this.getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return listPagerViews.get(position);
            }

            @Override
            public int getCount() {
                return listPagerViews.size();
            }

            @Override
            public void setPrimaryItem(ViewGroup container, int position, Object object) {
                super.setPrimaryItem(container, position, object);
            }
        };
        viewPager2.setAdapter(pagerAdapter);
    }
}