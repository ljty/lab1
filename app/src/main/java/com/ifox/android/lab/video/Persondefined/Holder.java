package com.ifox.android.lab.video.Persondefined;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifox.android.lab.R;

/**
 * 创建了一个下载列表项的具体信息类，并实力化了其中的组件
 */
public class Holder {
    public TextView videoContent;
    public TextView videoDate;
    public TextView videoTime;
    public ImageView videoPic;
    public ImageButton download;
    public ImageButton pause;
    public ImageButton play;

    public Holder(View view) {
        videoContent = (TextView) view.findViewById(R.id.listContent);
        videoDate = (TextView) view.findViewById(R.id.date);
        videoTime = (TextView) view.findViewById(R.id.playTime);
        videoPic = (ImageView) view.findViewById(R.id.listpic);
        download = (ImageButton) view.findViewById(R.id.btn_start);
        pause = (ImageButton) view.findViewById(R.id.btn_pause);
        play = (ImageButton) view.findViewById(R.id.btn_play);

    }
}
