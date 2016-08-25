package com.ifox.android.lab.video;

import java.io.Serializable;

/**
 * Created by LK on 2016/7/15.
 */
public class Video  implements Serializable {
    private String videoContent;
    private String videoname;
    private String picurl;
    private String videourl;
    private String videoDate;
    private String videoTime;
    public Video(String videoContent,String videourl,String picurl,String videoDate,String videoTime,String videoname){
        this.videoname=videoname;
        this.videoContent=videoContent;
        this.picurl=picurl;
        this.videourl=videourl;
        this.videoDate=videoDate;
        this.videoTime=videoTime;
    }
    public String getVideoname() {
        return videoname;
    }

    public void setVideoname(String videoname) {
        this.videoname = videoname;
    }
    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

  public Video (){

    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }
    public String getVideoContent() {
        return videoContent;
    }

    public void setVideoContent(String videoContent) {
        this.videoContent = videoContent;
    }


    public String getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(String videoTime) {
        this.videoTime = videoTime;
    }

    public String getVideoDate() {
        return videoDate;
    }

    public void setVideoDate(String videoDate) {
        this.videoDate = videoDate;
    }
}
