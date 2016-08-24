package com.ifox.android.lab.bean;

import android.graphics.Bitmap;

/**
 * 教学资源模型
 */
public class EduBean {

    public int et_id;

    public String et_title;

    public  String et_content;

    public String et_visitTimes;

    public  String et_sendDate;

    public String et_attachName;

    public Bitmap et_attachAddress;

    public int getEt_id() {
        return et_id;
    }

    public void setEt_id(int et_id) {
        this.et_id = et_id;
    }

    public String getEt_title() {
        return et_title;
    }

    public void setEt_title(String et_title) {
        this.et_title = et_title;
    }

    public String getEt_content() {
        return et_content;
    }

    public void setEt_content(String et_content) {
        this.et_content = et_content;
    }

    public String getEt_visitTimes() {
        return et_visitTimes;
    }

    public void setEt_visitTimes(String et_visitTimes) {
        this.et_visitTimes = et_visitTimes;
    }

    public String getEt_sendDate() {
        return et_sendDate;
    }

    public void setEt_sendDate(String et_sendDate) {
        this.et_sendDate = et_sendDate;
    }

    public String getEt_attachName() {
        return et_attachName;
    }

    public void setEt_attachName(String et_attachName) {
        this.et_attachName = et_attachName;
    }

    public Bitmap getEt_attachAddress() {
        return et_attachAddress;
    }

    public void setEt_attachAddress(Bitmap et_attachAddress) {
        this.et_attachAddress = et_attachAddress;
    }

    public EduBean() {
    }
}
