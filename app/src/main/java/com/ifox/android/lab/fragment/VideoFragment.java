package com.ifox.android.lab.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ifox.android.lab.R;
import com.ifox.android.lab.video.Fragment.SyVideoFragment;
import com.ifox.android.lab.video.Fragment.TeachVideoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 总Fragment，包含两个子Fragment(实验教学视频，仪器使用视频)
 */
public class VideoFragment extends Fragment {
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mDatas;
    private TextView teachvideo;//设置实验教学视频标题栏内容
    private TextView syvideo;//设置仪器使用视频标题栏内容
    private LinearLayout teachvideolist;//用于显示实验教学视频列表项
    private LinearLayout mTabline;//标题栏下的下划线
    private LinearLayout teachvideo1;//实验教学视频标题栏
    private LinearLayout syvideo1;//仪器使用视频标题栏
    private int mScreen1_2;//用于设置下划线的宽度
    private int mCurrentPageIndex;//当前显示页的页数

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_parent, container, false);
        super.onCreate(savedInstanceState);
        mTabline = (LinearLayout) view.findViewById(R.id.id_iv_tabline);
        DisplayMetrics dm = getResources().getDisplayMetrics();//拿到屏幕宽度
        mScreen1_2 = dm.widthPixels / 2;//使下划线的宽度为屏幕的一半
        ViewGroup.LayoutParams lp = mTabline.getLayoutParams();
        lp.width = mScreen1_2;
        mTabline.setLayoutParams(lp);//设置下划线的布局参数
        //实例化组件
        mViewPager = (ViewPager) view.findViewById(R.id.id_viewpager);
        teachvideo1=(LinearLayout)view.findViewById(R.id.l_teachvideo);
        syvideo1 = (LinearLayout) view.findViewById(R.id.syvideo1);
        teachvideo = (TextView) view.findViewById(R.id.t_teachvideo);
        syvideo = (TextView) view.findViewById(R.id.syvideo);
        teachvideolist = (LinearLayout) view.findViewById(R.id.l_teachvideo);
        teachvideo.setTextColor(0xff56abe4);
        //创建一个List集合，并把两个Fragment(实验教学视频列表，仪器使用视频列表)添加进来
        mDatas = new ArrayList<>();
        TeachVideoFragment tab01 = new TeachVideoFragment();
        SyVideoFragment tab02 = new SyVideoFragment();
        mDatas.add(tab01);
        mDatas.add(tab02);
        mAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            public int getCount() {
                return mDatas.size();
            }
            public Fragment getItem(int arg0) {
                return mDatas.get(arg0);
            }
        };
        mViewPager.setAdapter(mAdapter);
        //mviewPager设置监听事件
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    //设置各个标题栏字体大小
                    case 0:
                        teachvideo.setTextColor(0xff56abe4);
                        syvideo.setTextColor(0xff808080);
                        break;
                    case 1:
                        syvideo.setTextColor(0xff56abe4);
                        teachvideo.setTextColor(0xff808080);
                        break;
                }
                mCurrentPageIndex = position;
            }
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
        onClick();
        return view;
    }

    public void onClick(){//给两个标题栏设置点击事件
        teachvideo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(0);//当前页设置为实验视频页
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabline
                        .getLayoutParams();
                lp.leftMargin = 0;
                teachvideo.setTextColor(0xff56abe4);
                syvideo.setTextColor(0xff808080);
                teachvideo.setTextSize(19);
                mTabline.setLayoutParams(lp);
            }
        });
        syvideo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(1);//当前页设置为仪器使用视频页
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabline
                        .getLayoutParams();
                lp.rightMargin = 0;
                syvideo.setTextColor(0xff56abe4);
                teachvideo.setTextColor(0xff808080);
                syvideo.setTextSize(19);
                mTabline.setLayoutParams(lp);
            }
        });
    }

    }



