package com.ifox.android.lab.video;

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

import java.util.ArrayList;
import java.util.List;

public class DownLoadActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mDatas;
    private TextView downloading;
    private TextView downloaded;
    private LinearLayout mTabline;
    private int mScreen1_2;
    private int mCurrentPageIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load);
        mTabline = (LinearLayout)findViewById(R.id.id_iv_tabline);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        mScreen1_2 = dm.widthPixels / 2;
        ViewGroup.LayoutParams lp = mTabline.getLayoutParams();
        lp.width = mScreen1_2;
        mTabline.setLayoutParams(lp);
        downloading = (TextView)findViewById(R.id.downloading);
        downloaded = (TextView) findViewById(R.id.downloaded);
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        mDatas = new ArrayList<Fragment>();
        Intent it=getIntent();
        Video video1 = (Video) it.getSerializableExtra("video");
        Bundle bundle=new Bundle();
        bundle.putSerializable("content", video1);
        DownLoadingFragrment downLoadingFragment = new DownLoadingFragrment();
        downLoadingFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.downloadingfragment,new Fragment()).commit();
        DownLoadedFragment downLoadedFragment = new DownLoadedFragment();
        mDatas.add(downLoadingFragment);
        mDatas.add(downLoadedFragment);
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mDatas.size();
           }

            @Override
            public Fragment getItem(int arg0) {
               return mDatas.get(arg0);
           }
       };
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
           @Override
            public void onPageSelected(int position) {
               resetTextView();
                switch (position) {
                    case 0:
                       downloading.setTextSize(19);
                       downloaded.setTextSize(16);
                        break;
                   case 1:
                        downloading.setTextSize(19);
                        downloaded.setTextSize(16);
                       break;

                }

                mCurrentPageIndex = position;

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

           @Override
            public void onPageScrollStateChanged(int arg0) {
               // TODO Auto-generated method stub

            }
        });


    }

    protected void resetTextView() {
        downloading.setTextSize(16);
        downloaded.setTextSize(16);
   }
    public void doClick(View v){
        switch (v.getId())
        {
           case R.id.downloading:
                mViewPager.setCurrentItem(0);
                break;
           case R.id.downloaded:
                mViewPager.setCurrentItem(1);
                break;
       }
   }

}



