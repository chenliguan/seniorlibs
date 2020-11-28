package com.seniorlibs.view.fragment.androidx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.seniorlibs.baselib.utils.LogUtils;
import com.seniorlibs.view.R;

import static com.seniorlibs.view.activity.TabVPFragActivity.INTENT_RESULT;
import static com.seniorlibs.view.activity.TabVPFragActivity.REQUEST_CODE;

public class BaseAndroidXFragment extends Fragment {

    private static final String TAG = "BaseAndroidXFragment";//过滤关键字 nice
    private static final String TAG_2 = " ---> :  ";

    public BaseAndroidXFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogUtils.i(TAG + getClass().getSimpleName() + TAG_2, "onCreateView @");
        return inflater.inflate(R.layout.fragment_base_androidx, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setTag(R.id.fragment_root_view, this);
        LogUtils.i(TAG + getClass().getSimpleName() + TAG_2, "onViewCreated");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.i(TAG + getClass().getSimpleName() + TAG_2, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.i(TAG + getClass().getSimpleName() + TAG_2, "onPause");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        LogUtils.i(TAG + getClass().getSimpleName() + TAG_2, "onHiddenChanged");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtils.i(TAG + getClass().getSimpleName() + TAG_2, "setUserVisibleHint");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        LogUtils.e(TAG, "onActivityResultC1 + requestCode:" + requestCode + " resultCode：" + resultCode);

        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE:
                    String result = data.getStringExtra(INTENT_RESULT);
                    LogUtils.e(TAG, "onActivityResultC2 + result:" + result);
                    break;
                default:
                    break;
            }
        }
    }
}