package com.ifox.android.lab.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ifox.android.lab.R;

/**
 * Created by 10368 on 2016/7/20.
 * 教学视频托管
 */
public class VideoFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_video,null);
        return v;
    }
}
