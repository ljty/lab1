package com.ifox.android.lab.video;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.Toast;

import com.ifox.android.lab.R;
import com.ifox.android.lab.video.slidingTab.SlidingTabLayout;

public class Main3Activity extends AppCompatActivity implements ScrollTabHolder {
    VideoPlay videoView;
    public static final String TAG = Main3Activity.class.getSimpleName();
    private LinearLayout playpic;
    private ImageView shoucangpic;
    private ViewPager mViewPager;
    private View mHeader;
    private ImageView mTopImage;
    private SlidingTabLayout mNavigBar;
    private ViewPagerAdapter mAdapter;
    private LinearLayout shoucang;
    private TableLayout tableLayout;
    private LinearLayout download;
    private LinearLayout share;
    private LinearLayout linearLayout;
    private int mMinHeaderHeight;
    private int mHeaderHeight;
    private int mNumFragments;
    boolean isChanged = false;
    private int mScrollState;
    private android.support.v4.view.ViewPager view_pager;
    private com.ifox.android.lab.video.slidingTab.SlidingTabLayout navitag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);
        videoView = (VideoPlay) findViewById(R.id.common_videoView);
        initValues();
        playpic = (LinearLayout) findViewById(R.id.playpic);
        navitag = (com.ifox.android.lab.video.slidingTab.SlidingTabLayout) findViewById(R.id.navig_tab);
        view_pager = (android.support.v4.view.ViewPager) findViewById(R.id.view_pager);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        shoucangpic = (ImageView) findViewById(R.id.shoucangpic);
        shoucang = (LinearLayout) findViewById(R.id.shouchang);
        download = (LinearLayout) findViewById(R.id.download);
        share = (LinearLayout) findViewById(R.id.share);
        mTopImage = (ImageView) findViewById(R.id.image);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mNavigBar = (SlidingTabLayout) findViewById(R.id.navig_tab);
        mHeader = findViewById(R.id.header);

        if (savedInstanceState != null) {
        }
        shoucangpic.setBackgroundResource(R.drawable.shoucang);
        setupAdapter();
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

    public void doClick(View view) {
        switch (view.getId()) {
            case R.id.shouchang:
                if (isChanged) {
                    shoucangpic.setBackgroundResource(R.drawable.shoucang);
                    Toast.makeText(Main3Activity.this, "收藏取消！", Toast.LENGTH_SHORT).show();
                } else {
                    shoucangpic.setBackgroundResource(R.drawable.shoucang1);
                    Toast.makeText(Main3Activity.this, "收藏成功！", Toast.LENGTH_SHORT).show();
                }
                isChanged = !isChanged;
                break;

            case R.id.download:
                Intent intent = getIntent();
                Video video1 = (Video) intent.getSerializableExtra("content");
                Intent it = new Intent(Main3Activity.this, DownLoadActivity.class);
                it.putExtra("video", video1);
                Main3Activity.this.startActivity(it);
            case R.id.share:

        }
    }

    private void initValues() {
        int tabHeight = getResources().getDimensionPixelSize(R.dimen.tab_height);
        mMinHeaderHeight = getResources().getDimensionPixelSize(R.dimen.min_header_height);
        mHeaderHeight = getResources().getDimensionPixelSize(R.dimen.header_height);
        mNumFragments = 2;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
    private void setupAdapter() {
        if (mAdapter == null) {
            mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mNumFragments);
        }
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(mNumFragments);
        mNavigBar.setOnPageChangeListener(getViewPagerPageChangeListener());
        mNavigBar.setViewPager(mViewPager);
    }

    private ViewPager.OnPageChangeListener getViewPagerPageChangeListener() {
        ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int currentItem = mViewPager.getCurrentItem();
                if (positionOffsetPixels > 0) {
                    SparseArrayCompat<ScrollTabHolder> scrollTabHolders = mAdapter.getScrollTabHolders();
                    ScrollTabHolder fragmentContent;
                    if (position < currentItem) {
                        // Revealed the previous page
                        fragmentContent = scrollTabHolders.valueAt(position);
                        linearLayout.setVisibility(View.VISIBLE);
                        tableLayout.setVisibility(View.GONE);
                    } else {
                        // Revealed the next page
                        tableLayout.setVisibility(View.VISIBLE);
                        linearLayout.setVisibility(View.INVISIBLE);
                        fragmentContent = scrollTabHolders.valueAt(position);
                    }

                    fragmentContent.adjustScroll((int) (mHeader.getHeight() + mHeader.getTranslationY()),
                            mHeader.getHeight());
                }
            }

            @Override
            public void onPageSelected(int position) {
                SparseArrayCompat<ScrollTabHolder> scrollTabHolders = mAdapter.getScrollTabHolders();

                if (scrollTabHolders == null || scrollTabHolders.size() != mNumFragments) {
                    return;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                mScrollState = state;
            }
        };

        return listener;
    }

    @Override
    public void adjustScroll(int scrollHeight, int headerHeight) {
    }

    @Override
    public void onListViewScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount, int pagePosition) {
        if (mViewPager.getCurrentItem() == pagePosition) {
            scrollHeader(getScrollY(view));
        }
    }

    @Override
    public void onScrollViewScroll(ScrollView view, int x, int y, int oldX, int oldY, int pagePosition) {
        if (mViewPager.getCurrentItem() == pagePosition) {
            scrollHeader(view.getScrollY());
        }
    }

    @Override
    public void onRecyclerViewScroll(RecyclerView view, int scrollY, int pagePosition) {
        if (mViewPager.getCurrentItem() == pagePosition) {
            scrollHeader(scrollY);
        }
    }

    private void scrollHeader(int scrollY) {
    }

    private int getScrollY(AbsListView view) {
        View child = view.getChildAt(0);
        if (child == null) {
            return 0;
        }

        int firstVisiblePosition = view.getFirstVisiblePosition();
        int top = child.getTop();

        int headerHeight = 0;
        if (firstVisiblePosition >= 1) {
            headerHeight = mHeaderHeight;
        }

        return -top + firstVisiblePosition * child.getHeight() + headerHeight;
    }


    private static class ViewPagerAdapter extends FragmentPagerAdapter {

        private SparseArrayCompat<ScrollTabHolder> mScrollTabHolders;
        private int mNumFragments;

        public ViewPagerAdapter(FragmentManager fm, int numFragments) {
            super(fm);
            mScrollTabHolders = new SparseArrayCompat<>();
            mNumFragments = numFragments;
        }
        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            switch (position) {
                case 0:
                    fragment = FirstScrollViewFragment.newInstance(0);
                    break;

                case 1:
                    fragment = ListViewFragment.newInstance(1);
                    break;


                default:
                    throw new IllegalArgumentException("Wrong page given " + position);
            }
            return fragment;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Object object = super.instantiateItem(container, position);

            mScrollTabHolders.put(position, (ScrollTabHolder) object);

            return object;
        }

        @Override
        public int getCount() {
            return mNumFragments;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "视频简介";

                case 1:
                    return "相关评论";


                default:
                    throw new IllegalArgumentException("wrong position for the fragment in vehicle page");
            }
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        public SparseArrayCompat<ScrollTabHolder> getScrollTabHolders() {
            return mScrollTabHolders;
        }
    }
}
