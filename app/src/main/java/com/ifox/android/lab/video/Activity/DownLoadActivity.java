package com.ifox.android.lab.video.Activity;
/**
 * 下载页，包含两个fragment（正在下载downloadingFragment界面，已下载downedFragment界面）
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ifox.android.lab.R;
import com.ifox.android.lab.video.Fragment.DownLoadedFragment;
import com.ifox.android.lab.video.Fragment.DownLoadingFragrment;
import com.ifox.android.lab.video.Persondefined.Video;

import java.util.ArrayList;
import java.util.List;

public class DownLoadActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mDatas;
    private TextView downloading;
    private TextView downloaded;
    private LinearLayout downloadingLayout;
    private LinearLayout downloadedLayout;
    private LinearLayout mTabline;
    private int mScreen1_2;
    private int mCurrentPageIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load);//加载布局文件
        mTabline = (LinearLayout)findViewById(R.id.id_iv_tabline);//实例化标题栏的指示器
        DisplayMetrics dm = getResources().getDisplayMetrics();//获取屏幕宽度
        //将指示器的宽度设置为屏幕宽度的一半
        mScreen1_2 = dm.widthPixels / 2;
        ViewGroup.LayoutParams lp = mTabline.getLayoutParams();
        lp.width = mScreen1_2;
        mTabline.setLayoutParams(lp);
        downloading = (TextView)findViewById(R.id.downloading);//实例化正在下载标题栏TextView
        downloaded = (TextView) findViewById(R.id.downloaded);//实例化已下载标题栏TextView
        downloadingLayout=(LinearLayout)findViewById(R.id.downloadinglayout);//实例化正在下载标题栏布局
        downloadedLayout=(LinearLayout)findViewById(R.id.downloadedlayout);//实例化已下载标题栏布局
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        downloading.setTextColor(0xff56abe4);
        mDatas = new ArrayList<Fragment>();//创建一个Fragment集合
        //获取由SyFragment或teachFragment中视频列表项传递过来的video数据
        Intent it=getIntent();
        Video video1 = (Video) it.getSerializableExtra("video");
        //并用Bundle对象将video信息传递到downloadingFragment中去
        Bundle bundle=new Bundle();
        bundle.putSerializable("content", video1);
        DownLoadingFragrment downLoadingFragment = new DownLoadingFragrment();//创建正在下载Fragment
        downLoadingFragment.setArguments(bundle);//设置要发送对象
        getSupportFragmentManager().beginTransaction().add(R.id.downloadingfragment,new Fragment()).commit();//开启事务并提交
        DownLoadedFragment downLoadedFragment = new DownLoadedFragment();//实例化已下载Fragment
        //将实例化后的两个Fragment添加到集合中去
        mDatas.add(downLoadingFragment);
        mDatas.add(downLoadedFragment);
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {//实例化适配器
            @Override
            public int getCount() {
                return mDatas.size();//返回集合大小
           }

            @Override
            public Fragment getItem(int arg0) {
               return mDatas.get(arg0);//返回当前页
           }
       };
        mViewPager.setAdapter(mAdapter);//设置适配器
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {//为viewpager设置页面监听事件
            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    //当前页为正在下载页时，正在下载页的标题大小设置为19，已下载标题栏大小设置为16
                    case 0:
                        downloading.setTextColor(0xff56abe4);
                        downloaded.setTextColor(0xff808080);
                        break;
                    //当前页为已下载页时，正在下载页的标题大小设置为16，已下载标题栏大小设置为19
                    case 1:
                        downloading.setTextColor(0xff000000);
                        downloaded.setTextColor(0xff808080);
                        break;

                }

                mCurrentPageIndex = position;//当前页的position

            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPx) {
                Log.e("TAG", position + " , " + positionOffset + " , "
                        + positionOffsetPx);

                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabline
                        .getLayoutParams();

                if (mCurrentPageIndex == 0 && position == 0)// 0->1
                {
                    lp.leftMargin = (int) (positionOffset * mScreen1_2 + mCurrentPageIndex
                            * mScreen1_2);
                } else if (mCurrentPageIndex == 1 && position == 0)// 1->0
                {
                    lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_2 + (positionOffset - 1)
                            * mScreen1_2);
                }
                mTabline.setLayoutParams(lp);

            }

            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
        downloadingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(0);
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabline
                        .getLayoutParams();
                lp.leftMargin = 0;
                downloading.setTextColor(0xff56abe4);
                downloaded.setTextColor(0xff808080);
                mTabline.setLayoutParams(lp);
            }
        });
        downloadedLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(0);
                LinearLayout.LayoutParams lp1 = (LinearLayout.LayoutParams) mTabline
                        .getLayoutParams();
                lp1.rightMargin = 0;
                downloading.setTextColor(0xff808080);
                downloaded.setTextColor(0xff56abe4);
                mTabline.setLayoutParams(lp1);
                mViewPager.setCurrentItem(1);
            }
        });
    }

}



