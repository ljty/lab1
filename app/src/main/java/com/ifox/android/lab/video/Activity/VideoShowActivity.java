package com.ifox.android.lab.video.Activity;
/**
 * 视频详情页，包含两个子Fragment（内容简介Fragment，相关评论Fragment）
 * 可播放视频，可看到具体的视频信息，并可发表评论
 */
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ifox.android.lab.R;
import com.ifox.android.lab.video.Fragment.FirstScrollViewFragment;
import com.ifox.android.lab.video.Fragment.ListViewFragment;
import com.ifox.android.lab.video.Persondefined.Video;
import com.ifox.android.lab.video.Persondefined.VideoPlay;

import java.util.ArrayList;
import java.util.List;

public class VideoShowActivity extends FragmentActivity {
 private FragmentPagerAdapter mAdapter;
        private TextView content;
        private TextView pinluntext;
        private LinearLayout contentLayout;
        private LinearLayout pinlunLayout;
        private VideoPlay videoView;
        private LinearLayout playpic;
        private ImageView shoucangpic;
        private ViewPager mViewPager;
        private LinearLayout shoucang;
        private TableLayout tableLayout;
        private LinearLayout mTabline;
        private LinearLayout download;
        private LinearLayout share;
        private LinearLayout linearLayout;
        private int mScreen1_2;
        private int mCurrentPageIndex;
        private List<Fragment> mDatas;
        boolean isChanged = false;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.videoshow);
            mTabline = (LinearLayout) findViewById(R.id.id_iv_tabline);
            videoView = (VideoPlay) findViewById(R.id.common_videoView);
            playpic = (LinearLayout) findViewById(R.id.playpic);
            linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
            tableLayout = (TableLayout) findViewById(R.id.tableLayout);
            shoucangpic = (ImageView) findViewById(R.id.shoucangpic);
            shoucang = (LinearLayout) findViewById(R.id.shouchang);
            download = (LinearLayout) findViewById(R.id.download);
            share = (LinearLayout) findViewById(R.id.share);
            mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
            DisplayMetrics dm = getResources().getDisplayMetrics();
            mScreen1_2 = dm.widthPixels / 2;
            ViewGroup.LayoutParams lp = mTabline.getLayoutParams();
            lp.width = mScreen1_2;
            mTabline.setLayoutParams(lp);
            content = (TextView) findViewById(R.id.content);//实例化正在下载标题栏TextView
            pinluntext = (TextView) findViewById(R.id.pinluntext);//实例化已下载标题栏TextView
            contentLayout = (LinearLayout) findViewById(R.id.contentLayout);//实例化正在下载标题栏布局
            pinlunLayout = (LinearLayout) findViewById(R.id.pinlunLayout);//实例化已下载标题栏布局
            shoucangpic.setBackgroundResource(R.drawable.shoucang);
            mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
            content.setTextColor(0xff56abe4);
            mDatas = new ArrayList<>();//创建一个Fragment集合
            FirstScrollViewFragment fistFragment = new FirstScrollViewFragment();
            ListViewFragment listFragment = new ListViewFragment();//实例化已下载Fragment
            //将实例化后的两个Fragment添加到集合中去
            mDatas.add(fistFragment);
            mDatas.add(listFragment);
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
                    //  resetTextView();//初始化标题栏字体大小
                    switch (position) {
                        //当前页为正在下载页时，正在下载页的标题大小设置为19，已下载标题栏大小设置为16
                        case 0:
                            content.setTextColor(0xff56abe4);
                            pinluntext.setTextColor(0xff808080);
                            linearLayout.setVisibility(View.GONE);
                            tableLayout.setVisibility(View.VISIBLE);
                            break;
                        //当前页为已下载页时，正在下载页的标题大小设置为16，已下载标题栏大小设置为19
                        case 1:
                            linearLayout.setVisibility(View.VISIBLE);
                            tableLayout.setVisibility(View.GONE);
                            content.setTextColor(0xff808080);
                            pinluntext.setTextColor(0xff56abe4);
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
            playpic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = getIntent();
                    Video video = (Video) intent.getSerializableExtra("content");
                    String url = video.getVideourl();
                    playpic.setVisibility(View.INVISIBLE);
                    videoView.setVisibility(View.VISIBLE);
                    videoView.start(url, true);

                }
            });

        }

        public void onConfigurationChanged(Configuration newConfig) {
            super.onConfigurationChanged(newConfig);
            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE);
                tableLayout.setVisibility(View.INVISIBLE);
                linearLayout.setVisibility(View.INVISIBLE);
                videoView.setFullScreen();
            } else {
                videoView.setNormalScreen();
                getWindow().getDecorView().setSystemUiVisibility(View.VISIBLE);
                if (mViewPager.getCurrentItem() == 0) {
                    tableLayout.setVisibility(View.VISIBLE);
                } else {
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }
        }



        public void doClick(View v) {//为标题栏添加点击事件，用于切换界面
            switch (v.getId()) {
                case R.id.contentLayout://切换到正在下载页
                    //  resetTextView();
                    linearLayout.setVisibility(View.GONE);
                    tableLayout.setVisibility(View.VISIBLE);
                    mViewPager.setCurrentItem(0);
                    LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabline
                            .getLayoutParams();
                    lp.leftMargin = 0;
                    content.setTextColor(0xff56abe4);
                    pinluntext.setTextColor(0xff808080);
                    mTabline.setLayoutParams(lp);
                    break;
                case R.id.pinlunLayout://切换到已下载页
                    //  resetTextView();
                    linearLayout.setVisibility(View.VISIBLE);
                    tableLayout.setVisibility(View.GONE);
                    mViewPager.setCurrentItem(0);
                    LinearLayout.LayoutParams lp1 = (LinearLayout.LayoutParams) mTabline
                            .getLayoutParams();
                    lp1.rightMargin = 0;
                    pinluntext.setTextColor(0xff56abe4);
                    content.setTextColor(0xff808080);
                    mTabline.setLayoutParams(lp1);
                    mViewPager.setCurrentItem(1);
                    break;
                case R.id.shouchang:
                    if (isChanged) {
                        shoucangpic.setBackgroundResource(R.drawable.shoucang);
                        Toast.makeText(VideoShowActivity.this, "收藏取消！", Toast.LENGTH_SHORT).show();
                    } else {
                        shoucangpic.setBackgroundResource(R.drawable.shoucang1);
                        Toast.makeText(VideoShowActivity.this, "收藏成功！", Toast.LENGTH_SHORT).show();
                    }
                    isChanged = !isChanged;
                    break;

                case R.id.download:

                    Intent intent = getIntent();
                    Video video1 = (Video) intent.getSerializableExtra("content");
                    Intent it = new Intent(VideoShowActivity.this, DownLoadActivity.class);
                    it.putExtra("video", video1);
                    VideoShowActivity.this.startActivity(it);
                case R.id.share:

            }
        }


    }

