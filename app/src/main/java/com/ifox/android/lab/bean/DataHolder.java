package com.ifox.android.lab.bean;

/**
 * 静态Holder传递数据
 */
public class DataHolder {

    private NewsBean newsBean;

    private EduBean eduBean;

    private static final DataHolder holder = new DataHolder();

    public NewsBean getNewsBeanData() {
        return newsBean;
    }

    public void setNewsBeanData(NewsBean newsBean) {
        this.newsBean = newsBean;
    }

    public EduBean getEduBeanData(){
        return eduBean;
    }

    public void setEduBeanData(EduBean eduBean) {
        this.eduBean = eduBean;
    }

    public static DataHolder getInstance() {
        return holder;
    }

}
