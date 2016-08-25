package com.ifox.android.lab.video;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ifox.android.lab.R;

public class Main5Activity extends Activity {
    private EditText mEditText;
    private LabelView mLabelView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        mEditText = (EditText) findViewById(R.id.et_input);
        mLabelView = (LabelView) findViewById(R.id.lv);
        button=(Button)findViewById(R.id.search);
        mLabelView.setLabels(new String[] {"单片机","EDA","ASP", "移动通信", "Linux", "嵌入式", "C语言"});
        mLabelView.setColorSchema(new int[]{Color.BLACK, Color.BLUE, Color.GREEN, Color.LTGRAY, Color.MAGENTA, Color.RED, Color.YELLOW});
        mLabelView.setSpeeds(new int[][]{{1, 2}, {1, 1}, {2, 1}, {2, 3}});
        mLabelView.setOnItemClickListener(new LabelView.OnItemClickListener() {
            public void onItemClick(int index, String label) {

                mEditText.setText(label);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchContent = mEditText.getText().toString();
                Intent intent = new Intent(Main5Activity.this, Main2Activity.class);
                intent.putExtra("content", searchContent);
                startActivity(intent);
            }
        });
    }
}
