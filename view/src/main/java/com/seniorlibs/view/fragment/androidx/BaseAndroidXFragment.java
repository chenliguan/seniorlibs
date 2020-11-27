package com.seniorlibs.view.fragment.androidx;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.seniorlibs.view.R;

public class BaseAndroidXFragment extends Fragment {

    private static final String TAG = "BaseAndroidXFragment";//过滤关键字 nice
    private static final String TAG_2 = " ---> :  ";

    public BaseAndroidXFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG + getClass().getSimpleName() + TAG_2, "onCreateView @");
        return inflater.inflate(R.layout.fragment_base_androidx, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setTag(R.id.fragment_root_view, this);
        Log.i(TAG + getClass().getSimpleName() + TAG_2, "onViewCreated");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG + getClass().getSimpleName() + TAG_2, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG + getClass().getSimpleName() + TAG_2, "onPause");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i(TAG + getClass().getSimpleName() + TAG_2, "onHiddenChanged");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i(TAG + getClass().getSimpleName() + TAG_2, "setUserVisibleHint");
    }
}