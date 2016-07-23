package com.ifox.android.lab.utils;

import android.content.Context;

import com.ifox.android.lab.R;
import com.ifox.android.lab.bean.NewsBean;

import java.util.ArrayList;

/**
 * Created by 10368 on 2016/7/23.
 */
public class NewsUtils {

    public static ArrayList<NewsBean> getAllNews(Context context){
        ArrayList<NewsBean> arrayList=new ArrayList<NewsBean>();
        for(int i=0;i<100;i++){
            NewsBean newsBean=new NewsBean("第一实验楼放假时间安排"+i,context.getResources().getDrawable(R.drawable.news));
            arrayList.add(newsBean);
        }
        return arrayList;
    }
}
