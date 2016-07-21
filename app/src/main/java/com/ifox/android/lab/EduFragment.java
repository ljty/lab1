package com.ifox.android.lab;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ifox.android.lab.adapter.EduAdapter;
import com.ifox.android.lab.bean.EduBean;
import com.ifox.android.lab.utils.EduUtils;

import java.util.ArrayList;

/**
 * Created by 10368 on 2016/7/20.
 * 教学资源fragment托管
 */
public class EduFragment extends Fragment{

    private ListView medulv;

    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_edu,null);

        mContext=getActivity();
        medulv= (ListView) v.findViewById(R.id.edulv);
        ArrayList<EduBean> allEdu= EduUtils.getAllEdu(mContext);
        EduAdapter eduAdapter=new EduAdapter(mContext,allEdu);
        medulv.setAdapter(eduAdapter);
        medulv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(mContext,EduWritingActivity.class);
                startActivity(it);
            }
        });
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
