package com.seniorlibs.view.fragment.androidx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.seniorlibs.baselib.utils.LogUtils;
import com.seniorlibs.view.R;
import com.seniorlibs.view.activity.SetResultActivity;

import java.util.ArrayList;
import java.util.List;

import static com.seniorlibs.view.activity.TabVPFragActivity.INTENT_RESULT;
import static com.seniorlibs.view.activity.TabVPFragActivity.REQUEST_CODE;

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
                Toast.makeText(v.getContext(), "Frg_2 点击 跳转到SetResultActivity", Toast.LENGTH_SHORT).show();
                startActivityForResult(new Intent(getActivity(), SetResultActivity.class), REQUEST_CODE);
//                getActivity().startActivityForResult(new Intent(getActivity(), SetResultActivity.class), REQUEST_CODE);
//                ActivityCompat.startActivityForResult(getActivity(), new Intent(getActivity(), SetResultActivity.class), REQUEST_CODE, null);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        LogUtils.e(TAG, "onActivityResultB1 + requestCode:" + requestCode + " resultCode：" + resultCode);

        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE:
                    String result = data.getStringExtra(INTENT_RESULT);
                    LogUtils.e(TAG, "onActivityResultB2 + result:" + result);
                    break;
                default:
                    break;
            }
        }
    }
}