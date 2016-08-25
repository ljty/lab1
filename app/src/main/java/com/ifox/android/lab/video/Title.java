package com.ifox.android.lab.video;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ifox.android.lab.R;

/**
 * Created by LK on 2016/7/31.
 */
public class Title extends RelativeLayout {
    private TopClickListener topClickListener;
    private TextView textView;
    private ImageButton back;
    private ImageButton search;
    private String titleText;
    private float titleTextSize;
    private int titleColor;
    private Drawable backColor;
    private Drawable searchColor;
    private LayoutParams titleparam, backparam,searchparam;
    public interface TopClickListener{
        public void backClickEvent();
        public  void searchClickEvent();
    }
    public void setTopClickListener(TopClickListener topClickListener){
         this.topClickListener=topClickListener;
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public Title(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.title);
        titleColor=ta.getColor(R.styleable.title_titletextcolor, 0);
        titleText=ta.getString(R.styleable.title_titletext);
        titleTextSize=ta.getDimension(R.styleable.title_titletextsize, 0);
        backColor=ta.getDrawable(R.styleable.title_backcolor);
        searchColor=ta.getDrawable(R.styleable.title_searchcolor);
        ta.recycle();
        textView=new TextView(context);
        textView.setText(titleText);
        textView.setTextColor(titleColor);
        textView.setTextSize(titleTextSize);
        setBackgroundColor(0xff33475f);
        titleparam= new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 50);
        back=new ImageButton(context);
        back.setBackground(backColor);
            back.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    topClickListener.backClickEvent();
                }
            });
        backparam=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 80);
        search=new ImageButton(context);
        search.setBackground(searchColor);
        searchparam=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 80);
        search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                topClickListener.searchClickEvent();
            }
        });
        backparam.addRule(ALIGN_PARENT_LEFT);
        titleparam.addRule(CENTER_IN_PARENT);
        searchparam.addRule(ALIGN_PARENT_RIGHT);

        setPadding(10, 30, 10, 30);
        addView(back, backparam);
        addView(textView, titleparam);
        addView(search, searchparam);
    }

}
