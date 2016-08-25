package com.ifox.android.lab.video;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

/**
 * 总Fragment，包含两个子Fragment(实验教学视频，仪器使用视频)
 */
public class ParentFragment extends Fragment {
    private TextView search;
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mDatas;
    private TextView teachvideo;
    private TextView syvideo;
    private LinearLayout teachvideolist;
    private LinearLayout mTabline;
    private int mScreen1_2;
    private int mCurrentPageIndex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_parent, container, false);

        super.onCreate(savedInstanceState);
        mTabline = (LinearLayout) view.findViewById(R.id.id_iv_tabline);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        mScreen1_2 = dm.widthPixels / 2;
        ViewGroup.LayoutParams lp = mTabline.getLayoutParams();
        lp.width = mScreen1_2;
        mTabline.setLayoutParams(lp);
        search = (TextView) view.findViewById(R.id.serach);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(view.getContext(), Main5Activity.class);
                view.getContext().startActivity(it);
            }
        });
        mViewPager = (ViewPager) view.findViewById(R.id.id_viewpager);
        teachvideo = (TextView) view.findViewById(R.id.t_teachvideo);
        syvideo = (TextView) view.findViewById(R.id.syvideo);
        teachvideolist = (LinearLayout) view.findViewById(R.id.l_teachvideo);

        mDatas = new ArrayList<Fragment>();

        TeachVideoFragment tab01 = new TeachVideoFragment();
        SyVideoFragment tab02 = new SyVideoFragment();
        mDatas.add(tab01);
        mDatas.add(tab02);


        mAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
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
                        teachvideo.setTextSize(19);
                        syvideo.setTextSize(16);
                        break;
                    case 1:
                        syvideo.setTextSize(19);
                        teachvideo.setTextSize(16);
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

        return view;
    }
    protected void resetTextView() {
        syvideo.setTextSize(16);
        syvideo.setTextSize(16);
    }
}


