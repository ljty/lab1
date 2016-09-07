package com.ifox.android.lab.video.Activity;
/**
 * 1：搜索页，用户通过相应条件搜索出相应的视频
 * 2：界面中设置了一些动态的标签，表示一些经常检索的信息词
 */
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ifox.android.lab.R;
import com.ifox.android.lab.video.Persondefined.LabelView;

public class SearchActivity extends Activity {
    private EditText mEditText;
    private LabelView mLabelView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videosearch);

        mEditText = (EditText) findViewById(R.id.et_input);//实例化文本编辑框，显示搜索内容
        mLabelView = (LabelView) findViewById(R.id.lv);    //实例化标签组件
        button=(Button)findViewById(R.id.search);          //实例化搜索按钮
        mLabelView.setLabels(new String[] {"单片机","EDA","ASP", "移动通信",
                "Linux", "嵌入式", "C语言"});               //添加几个常见的检索词
        mLabelView.setColorSchema(new int[]{
                Color.BLACK, Color.BLUE, Color.GREEN, Color.LTGRAY,
                Color.MAGENTA, Color.RED, Color.YELLOW});  //设置检索词对应的颜色
        mLabelView.setSpeeds(new int[][]{{1, 2}, {1, 1}, {2, 1}, {2, 3}});//设置检索词的移动速度
        mLabelView.setOnItemClickListener(new LabelView.OnItemClickListener() {//点击事件添加
            public void onItemClick(int index, String label) {

                mEditText.setText(label);//将点击的标签内容添加到文本框中去
            }
        });
        button.setOnClickListener(new View.OnClickListener() {//为按钮设置点击事件，开始搜索
            @Override
            public void onClick(View v) {
                String searchContent = mEditText.getText().toString();
                //跳转到搜索结果页SearchVideoListActivity,并将搜索内容传递过去
                Intent intent = new Intent(SearchActivity.this, SearchVideoListActivity.class);
                intent.putExtra("content", searchContent);
                startActivity(intent);
            }
        });
    }
}
