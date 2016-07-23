package com.ifox.android.lab.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by 10368 on 2016/7/23.
 */
public class NewsBean {

    private String news;

    private Drawable icon;

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public NewsBean(String news,Drawable icon) {
        this.icon = icon;
        this.news = news;
    }
}
