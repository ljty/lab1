package com.ifox.android.lab.video;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ifox.android.lab.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TempActivity extends ListActivity {

    private static final String PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/实验视频下载/";

    // 存放各个下载器
    private Map<String, Downloader> downloaders = new HashMap<String, Downloader>();
    // 存放与下载器对应的进度条
    private Map<String, ProgressBar> ProgressBars = new HashMap<String, ProgressBar>();
    private ImageView back;

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
                        Toast.makeText(TempActivity.this, "[" + resouceName.getText() + "]下载完成！", Toast.LENGTH_SHORT).show();
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
                                Intent it = new Intent(TempActivity.this, LocalVideoActivity.class);
                                TempActivity.this.startActivity(it);
                            }
                        });
                    }
                }
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load);
        showListView();
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TempActivity.this.finish();
            }
        });

    }

    // 显示listView，这里可以随便添加
    private void showListView() {
        Intent intent = getIntent();
        Video video1 = (Video) intent.getSerializableExtra("video");
        List<Video> videos = new ArrayList<Video>();
        videos.add(video1);
        DownLoadAdapter adapter = new DownLoadAdapter(this,null);
        adapter.setData(videos);
        setListAdapter(adapter);
    }

    /**
     * 响应开始下载按钮的点击事件
     */
    public void startDownload(View v) {
        Intent intent = getIntent();
        Video video = (Video) intent.getSerializableExtra("video");
        String resouceName = video.getVideoname();
        int dot = video.getVideoname().indexOf(".");
        String Child_PATH = PATH + video.getVideoname().substring(0, dot) + File.separator;
        File FirstFile = new File(PATH);//新建一级主目录
        if (!FirstFile.exists()) {//判断文件夹目录是否存在
            FirstFile.mkdir();//如果不存在则创建
        }
        File SecondFile = new File(Child_PATH);//新建二级主目录
        if (!SecondFile.exists()) {//判断文件夹目录是否存在
            SecondFile.mkdir();//如果不存在则创建
        }
        File localfile = new File(SecondFile, resouceName);

                String urlstr = video.getVideourl();
                String threadcount = "4";
                DownloadTask downloadTask = new DownloadTask(v);
                downloadTask.execute(urlstr, String.valueOf(localfile), threadcount);

    }



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
                downloader = new Downloader(urlstr, localfile, threadcount, TempActivity.this, mHandler);
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
            bar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
            bar.setMax(loadInfo.getFileSize());
            bar.setProgress(loadInfo.getComplete());
            ProgressBars.put(url, bar);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 15);
            ((LinearLayout) ((LinearLayout) ((LinearLayout) ((LinearLayout) v.getParent()).getParent()).getParent()).getParent()).addView(bar, params);
        }
    }

    /**
     * 响应暂停下载按钮的点击事件
     */
    public void pauseDownload(View v) {
        LinearLayout layout = (LinearLayout) v.getParent();
        String resouceName = ((TextView) layout.findViewById(R.id.listContent)).getText().toString();
        //String urlstr = URL + resouceName;
        Intent intent = getIntent();
        Video video = (Video) intent.getSerializableExtra("video");
        String URL = video.getVideourl();
        downloaders.get(URL).pause();
        ImageButton btn_start = (ImageButton) ((View) v.getParent()).findViewById(R.id.btn_start);
        ImageButton btn_pause = (ImageButton) ((View) v.getParent()).findViewById(R.id.btn_pause);
        btn_pause.setVisibility(View.GONE);
        btn_start.setVisibility(View.VISIBLE);
    }
}