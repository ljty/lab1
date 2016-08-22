package com.ifox.android.lab.bean;

/**
 * 公告模型
 */
public class NewsBean {

    public int n_id;
    public String n_title;
    public String  n_content;
    public String n_visitTimes;
    public String  n_sendDate;
    public String n_attachName;
    public String n_attachAddress;

    public int getN_id() {
        return n_id;
    }

    public void setN_id(int n_id) {
        this.n_id = n_id;
    }

    public String getN_title() {
        return n_title;
    }

    public void setN_title(String n_title) {
        this.n_title = n_title;
    }

    public String getN_content() {
        return n_content;
    }

    public void setN_content(String n_content) {
        this.n_content = n_content;
    }

    public String getN_visitTimes() {
        return n_visitTimes;
    }

    public void setN_visitTimes(String n_visitTimes) {
        this.n_visitTimes = n_visitTimes;
    }

    public String getN_sendDate() {
        return n_sendDate;
    }

    public void setN_sendDate(String n_sendDate) {
        this.n_sendDate = n_sendDate;
    }

    public String getN_attachName() {
        return n_attachName;
    }

    public void setN_attachName(String n_attachName) {
        this.n_attachName = n_attachName;
    }

    public String getN_attachAddress() {
        return n_attachAddress;
    }

    public void setN_attachAddress(String n_attachAddress) {
        this.n_attachAddress = n_attachAddress;
    }

    public NewsBean() {
    }
}
