package com.ifox.android.lab.utils;

import android.content.Context;

import com.ifox.android.lab.R;
import com.ifox.android.lab.bean.EduBean;

import java.util.ArrayList;

/**
 * Created by 10368 on 2016/7/21.
 * 教学资源伪数据
 */
public class EduUtils {

    public static ArrayList<EduBean> getAllEdu(Context context){
        ArrayList<EduBean> arrayList=new ArrayList<EduBean>();
        for(int i=0;i<100;i++){
            EduBean eduBean=new EduBean("示波器的使用方法"+i,context.getResources().getDrawable(R.drawable.edu));
            arrayList.add(eduBean);
        }
        return arrayList;
    }
}
