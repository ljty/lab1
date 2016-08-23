package com.ifox.android.lab.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ifox.android.lab.R;
import com.ifox.android.lab.ShowEduActivity;
import com.ifox.android.lab.adapter.EduAdapter;
import com.ifox.android.lab.bean.EduBean;
import com.ifox.android.lab.utils.EduUtils;

import java.util.ArrayList;

/**
 * 教学资源托管
 */
public class EduFragment extends Fragment {

    private ListView mEdulv;

    private Context mContext;

    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {

            ArrayList<EduBean> allEdu = (ArrayList<EduBean>) msg.obj;

            if(allEdu != null && allEdu .size()>0)
            {
                //3.创建一个adapter设置给listview
                EduAdapter eduAdapter = new EduAdapter(mContext,allEdu);
                mEdulv.setAdapter(eduAdapter);
            }
        };
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_edu, null);

        mContext = getActivity();

        mEdulv = (ListView)v.findViewById(R.id.edulv);

        //1.先去数据库中获取缓存的新闻数据展示到listview
        ArrayList<EduBean> allEdu_database = EduUtils.getAllEduForDatabase(mContext);

        if(allEdu_database != null && allEdu_database.size()>0){
            //创建一个adapter设置给listview

            EduAdapter eduAdapter = new EduAdapter(mContext, allEdu_database);
            mEdulv.setAdapter(eduAdapter);
        }

        new Thread(new Runnable() {

            @Override
            public void run() {

                ArrayList<EduBean> allEdu = EduUtils.getAllEduForNetWork(mContext);
                //请求网络数据
                //通过handler将msg发送到主线程去更新Ui
                Message msg = Message.obtain();
                msg.obj = allEdu;
                handler.sendMessage(msg);

            }
        }).start();
        mEdulv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(mContext,ShowEduActivity.class);
                EduBean bean = (EduBean)parent.getItemAtPosition(position);
                String et_title = bean.et_title;
                String et_content = bean.et_content;

                it.putExtra("n_title",et_title);
                it.putExtra("n_content",et_content);
                startActivity(it);
            }
        });
        return v;
    }
}
