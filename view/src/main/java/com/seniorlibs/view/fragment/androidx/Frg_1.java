package com.seniorlibs.view.fragment.androidx;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.seniorlibs.view.R;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/11/27.
 * Mender:
 * Modify:
 * Description: Fragment
 */
public class Frg_1 extends BaseAndroidXFragment {

    private static final String TAG = "Tab_Frg_1";

    public Frg_1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frg_1, container, false);
        v.findViewById(R.id.tv_frg_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Frg_1 点击", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
}