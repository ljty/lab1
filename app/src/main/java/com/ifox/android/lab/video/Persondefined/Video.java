package com.ifox.android.lab.video.Persondefined;

import java.io.Serializable;

/**
 * 自定义视频信息类
 */
public class Video  implements Serializable {
    private String videoContent;//视频类容
    private String videoname;//视频名字
    private String picurl;//视频图片
    private String videourl;//视频地址
    private String videoDate;//视频创建日期
    private String videoTime;//视频播放时长
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
