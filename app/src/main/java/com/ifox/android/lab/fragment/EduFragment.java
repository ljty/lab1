package com.ifox.android.lab.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ifox.android.lab.R;
import com.ifox.android.lab.ShowEduActivity;
import com.ifox.android.lab.adapter.EduAdapter;
import com.ifox.android.lab.bean.EduBean;
import com.ifox.android.lab.bean.DataHolder;
import com.ifox.android.lab.utils.EduUtils;

import java.util.ArrayList;

/**
 * 教学资源托管
 */
public class EduFragment extends Fragment {

    private ListView mEdulv;

    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_edu, null);

        // 为适配器设置内容
        mContext = getActivity();
        mEdulv = (ListView)v.findViewById(R.id.edulv);
        ArrayList<EduBean> allEdu_database = EduUtils.getAllEduForDatabase(mContext);
        if(allEdu_database != null && allEdu_database.size()>0){
            EduAdapter eduAdapter = new EduAdapter(mContext, allEdu_database);
            mEdulv.setAdapter(eduAdapter);
        }

        onClick();
        return v;
    }

    // 设置适配器Item点击事件
    private void onClick() {
        mEdulv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext,ShowEduActivity.class);
                EduBean bean = (EduBean)parent.getItemAtPosition(position);
                DataHolder.getInstance().setEduBeanData(bean);
                startActivity(intent);
            }
        });
    }
}
