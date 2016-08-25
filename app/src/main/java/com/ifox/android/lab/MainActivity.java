package com.ifox.android.lab;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ifox.android.lab.fragment.EduFragment;
import com.ifox.android.lab.fragment.NewsFragment;
import com.ifox.android.lab.fragment.VideoFragment;
import com.ifox.android.lab.utils.EduUtils;
import com.ifox.android.lab.utils.NewsUtils;

import static com.ifox.android.lab.R.id.toolbar;

/**
 * 主活动，管理 fragment
 */
public class MainActivity extends AppCompatActivity {

    private NewsFragment nf;

    private EduFragment ef;

    private VideoFragment vf;

    private int firstSelectedPosition = 0;

    private NavigationView nav_view;

    private Context context = this;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolBar();
        navMenu();
        bottomBar();
        date();
    }

    // 顶部标题栏
    private void toolBar(){
        mToolbar = (Toolbar) findViewById(toolbar);
        mToolbar.setTitle("lab");
        mToolbar.setTitleTextAppearance(this, R.style.Theme_ToolBar_Base_Title);
        setTitleCenter(mToolbar);
        mToolbar.inflateMenu(R.menu.menu);
        //action menu操作菜单按钮的点击事件
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.share){
                    Toast.makeText(context,"搜索",Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }

    // 侧边菜单
    private void navMenu(){
        nav_view= (NavigationView) findViewById( R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_edit:
                        // do something
                        Toast.makeText(context,R.string.edit,Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.menu_about:
                        // do something
                        Toast.makeText(context,R.string.about,Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.menu_settings:
                        // do something
                        Toast.makeText(context,R.string.setting,Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }

    // 底部导航栏
    private void bottomBar(){
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_white_24dp, " 公告 ").setActiveColor(R.color.mediumblue))
                .addItem(new BottomNavigationItem(R.drawable.ic_book_white_24dp, " 教学资源 ").setActiveColor(R.color.purple))
                .addItem(new BottomNavigationItem(R.drawable.ic_tv_white_24dp, " 教学视频 ").setActiveColor(R.color.orange))
                .setFirstSelectedPosition(firstSelectedPosition)
                .initialise();
        setDefaultFragment();
        // 设置底部导航栏的切换
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                switch (position) {
                    case 0:
                        nf = new NewsFragment();
                        transaction.replace(R.id.fragment, nf);
                        break;

                    case 1:
                        ef = new EduFragment();
                        transaction.replace(R.id.fragment, ef);
                        break;

                    case 2:
                        vf = new VideoFragment();
                        transaction.replace(R.id.fragment, vf);
                        break;
                }
                transaction.commit();
            }

            @Override
            public void onTabUnselected(int position) {
            }

            @Override
            public void onTabReselected(int position) {
            }
        });
    }

    // 从服务器获取数据
    private void date() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NewsUtils.getAllNewsForNetWork(getApplicationContext());
                EduUtils.getAllEduForNetWork(getApplicationContext());
            }
        }).start();
    }

    // 默认页设置
    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        NewsFragment nf = new NewsFragment();
        transaction.replace(R.id.fragment, nf);
        transaction.commit();
    }

    // 设置标题栏文本居中
    public void setTitleCenter(Toolbar toolbar){
        int childCount = toolbar.getChildCount();
        for(int i = 0 ;i < childCount;i++){
            View child = toolbar.getChildAt(i);
            if(child instanceof TextView){
                TextView childTitle = (TextView)child;
                if(childTitle.getText().equals(toolbar.getTitle())){
                    int deviceWidth = getWindowManager().getDefaultDisplay().getWidth();
                    Paint p = childTitle.getPaint();
                    float textWidth = p.measureText(childTitle.getText().toString());
                    float tx = (deviceWidth - textWidth) / 2.0f - toolbar.getContentInsetLeft();
                    childTitle.setTranslationX(tx);
                    break;
                }
            }
        }
    }
}