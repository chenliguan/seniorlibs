package com.seniorlibs.view.fragment.androidx;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.seniorlibs.view.R;

public class Frg_6 extends BaseAndroidXFragment {

    private static final String TAG = "Tab_Frg_6";

    public Frg_6() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frg_6, container, false);
        v.findViewById(R.id.tv_frg_6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Frg_6 点击", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
}