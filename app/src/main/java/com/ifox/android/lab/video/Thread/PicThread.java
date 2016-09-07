package com.ifox.android.lab.video.Thread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 接收JsonAdapter传递过来的数据，并将发送网络请求，获取到图片并显示到列表项中
 */
public class PicThread extends Thread {
    private String url;
    private ImageView imageView;
    private Handler handler;
    public PicThread(String url, ImageView imageView, Handler handler){
        this.handler=handler;//接收handler用于更新UI
        this.imageView=imageView;//接收图片显示组件
        this.url=url;//接收图片地址
    }
    public void run(){
        try {
            //根据图片地址发送网络请求，取到图片
            URL httpurl=new URL(url);
            try {
                HttpURLConnection connection=(HttpURLConnection)httpurl.openConnection();
                connection.setRequestMethod("GET");
                connection.setReadTimeout(5000);
                InputStream in=connection.getInputStream();
                final Bitmap bitmap= BitmapFactory.decodeStream(in);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(bitmap);
                    }
                });//将图片设置到imageView中
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
