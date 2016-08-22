package com.ifox.android.lab.bean;

import android.graphics.drawable.Drawable;

/**
 * 教学资源模型
 */
public class EduBean {

    private String title;

    private Drawable icon;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public EduBean() {
    }
}
