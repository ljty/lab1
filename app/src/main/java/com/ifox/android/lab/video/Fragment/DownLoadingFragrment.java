package com.ifox.android.lab.video.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ifox.android.lab.R;
import com.ifox.android.lab.video.Activity.LocalVideoPlayActivity;
import com.ifox.android.lab.video.Adapter.DownLoadAdapter;
import com.ifox.android.lab.video.Download.Downloader;
import com.ifox.android.lab.video.Persondefined.Holder;
import com.ifox.android.lab.video.Persondefined.LoadInfo;
import com.ifox.android.lab.video.Persondefined.Video;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DownLoadingFragrment extends Fragment {

    private static final String PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/视频下载/";
    // 存放各个下载器
    private Map<String, Downloader> downloaders = new HashMap<String, Downloader>();
    // 存放与下载器对应的进度条
    private Map<String, ProgressBar> ProgressBars = new HashMap<String, ProgressBar>();
    Context context=null;
    ImageButton download;
    ImageButton pause;
    Bundle mVideoBundle;
    String Child_PATH;
    String resouceName;
  Handler mAdapterHandler;
public void getHandler(final Video video1) {
    mAdapterHandler = new Handler() {
        public void handleMessage(Message msg) {
            download = ((Holder) msg.obj).download;
            pause = ((Holder) msg.obj).pause;
            download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {//
                    String resouceName = video1.getVideoname();
                    int dot = video1.getVideoname().indexOf(".");
                    String Child_PATH = PATH + video1.getVideoname().substring(0, dot) + File.separator;
                    File FirstFile = new File(PATH);//新建一级主目录
                    if (!FirstFile.exists()) {//判断文件夹目录是否存在
                        FirstFile.mkdir();//如果不存在则创建
                    }
                    File SecondFile = new File(Child_PATH);//新建二级主目录
                    if (!SecondFile.exists()) {//判断文件夹目录是否存在
                        SecondFile.mkdir();//如果不存在则创建
                    }
                    File localfile = new File(SecondFile, resouceName);

                    String urlstr = video1.getVideourl();
                    String threadcount = "4";
                    DownloadTask downloadTask = new DownloadTask(v);
                    downloadTask.execute(urlstr, String.valueOf(localfile), threadcount);


                }
            });
            pause.setOnClickListener(new View.OnClickListener() {//
                @Override
                public void onClick(View v) {
                    LinearLayout layout = (LinearLayout) v.getParent();
                    String resouceName = ((TextView) layout.findViewById(R.id.listContent)).getText().toString();
                    //String urlstr = URL + resouceName;
                    String URL = video1.getVideourl();
                    downloaders.get(URL).pause();
                    ImageButton btn_start = (ImageButton) ((View) v.getParent()).findViewById(R.id.btn_start);
                    ImageButton btn_pause = (ImageButton) ((View) v.getParent()).findViewById(R.id.btn_pause);
                    btn_pause.setVisibility(View.GONE);
                    btn_start.setVisibility(View.VISIBLE);
                }
            });
        }
    };

}
    /**
     * 利用消息处理机制适时更新进度条
     */
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                String url = (String) msg.obj;
                int length = msg.arg1;
                ProgressBar bar = ProgressBars.get(url);
                if (bar != null) {
                    // 设置进度条按读取的length长度更新
                    bar.incrementProgressBy(length);
                    if (bar.getProgress() == bar.getMax()) {
                        LinearLayout layout = (LinearLayout) bar.getParent();
                        TextView resouceName = (TextView) layout.findViewById(R.id.listContent);
                        Toast.makeText(context, "[" + resouceName.getText() + "]下载完成！", Toast.LENGTH_SHORT).show();
                        // 下载完成后清除进度条并将map中的数据清空
                        layout.removeView(bar);
                        ProgressBars.remove(url);
                        downloaders.get(url).delete(url);
                        downloaders.get(url).reset();
                        downloaders.remove(url);

                        ImageButton btn_start = (ImageButton) layout.findViewById(R.id.btn_start);
                        ImageButton btn_pause = (ImageButton) layout.findViewById(R.id.btn_pause);
                        ImageButton btn_play = (ImageButton) layout.findViewById(R.id.btn_play);
                        btn_pause.setVisibility(View.GONE);
                        btn_start.setVisibility(View.GONE);
                        btn_play.setVisibility(View.VISIBLE);
                        btn_play.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent it = new Intent(context, LocalVideoPlayActivity.class);
                               context.startActivity(it);
                            }
                        });
                    }
                }
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_down_loading, container, false);
        ListView listView=(ListView)view.findViewById(R.id.list);

        List<Video> videos = new ArrayList<Video>();
        final Video video1= (Video)getArguments().get("content");
        videos.add(video1);
        getHandler(video1);
        DownLoadAdapter adapter = new DownLoadAdapter(view.getContext(),mAdapterHandler);
        adapter.setData(videos);
        listView.setAdapter(adapter);
        context = view.getContext();



        return view;
    } // 显示listView，这里可以随便添加
    class DownloadTask extends AsyncTask<String, Integer, LoadInfo> {
    Downloader downloader = null;
    View v = null;
    String urlstr = null;

    public DownloadTask(final View v) {
        this.v = v;
    }

    @Override
    protected void onPreExecute() {
        ImageButton btn_start = (ImageButton) ((View) v.getParent()).findViewById(R.id.btn_start);
        ImageButton btn_pause = (ImageButton) ((View) v.getParent()).findViewById(R.id.btn_pause);
        btn_start.setVisibility(View.GONE);
        btn_pause.setVisibility(View.VISIBLE);
    }

    @Override
    protected LoadInfo doInBackground(String... params) {
        urlstr = params[0];
        String localfile = params[1];
        int threadcount = Integer.parseInt(params[2]);
        // 初始化一个downloader下载器
        downloader = downloaders.get(urlstr);
        if (downloader == null) {
            downloader = new Downloader(urlstr, localfile, threadcount, context, mHandler);
            downloaders.put(urlstr, downloader);
        }
        if (downloader.isdownloading())
            return null;
        // 得到下载信息类的个数组成集合
        return downloader.getDownloaderInfors();
    }

    @Override
    protected void onPostExecute(LoadInfo loadInfo) {
        if (loadInfo != null) {
            // 显示进度条
            showProgress(loadInfo, urlstr, v);
            // 调用方法开始下载
            downloader.download();
        }
    }

}

;

/**
 * 显示进度条
 */
private void showProgress(LoadInfo loadInfo, String url, View v) {
    ProgressBar bar = ProgressBars.get(url);
    if (bar == null) {
        bar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        bar.setMax(loadInfo.getFileSize());
        bar.setProgress(loadInfo.getComplete());
        ProgressBars.put(url, bar);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 15);
        ((LinearLayout) ((LinearLayout) ((LinearLayout) ((LinearLayout) v.getParent()).getParent()).getParent()).getParent()).addView(bar, params);
    }
}
}
