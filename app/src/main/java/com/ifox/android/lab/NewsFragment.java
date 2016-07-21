package com.ifox.android.lab;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 10368 on 2016/7/20.
 */
public class NewsFragment extends Fragment{    @Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View v=inflater.inflate(R.layout.fragment_news,null);
    return v;
}
}
