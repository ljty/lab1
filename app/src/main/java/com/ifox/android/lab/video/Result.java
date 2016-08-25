package com.ifox.android.lab.video;

import java.util.List;

/**
 * Created by LK on 2016/7/15.
 */
public class Result {
    private List<Video> videos;
    private int result;
    public int getResult() {
        return result;
    }
    public void setResult(int result) {
        this.result = result;
    }
    public List<Video> getVideos() {
        return videos;
    }
    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
