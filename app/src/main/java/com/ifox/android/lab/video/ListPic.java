package com.ifox.android.lab.video;

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
 * Created by LK on 2016/7/15.
 */
public class ListPic extends Thread {
    private String url;
    private ImageView imageView;
    private Handler handler;
    public ListPic(String url, ImageView imageView, Handler handler){
        this.handler=handler;
        this.imageView=imageView;
        this.url=url;
    }
    public void run(){
        try {

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
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
