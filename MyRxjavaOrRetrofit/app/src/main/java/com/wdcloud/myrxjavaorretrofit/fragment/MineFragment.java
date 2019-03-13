package com.wdcloud.myrxjavaorretrofit.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wdcloud.myrxjavaorretrofit.R;
import com.wdcloud.myrxjavaorretrofit.base.BaseFragment;
import com.wdcloud.myrxjavaorretrofit.indicator.MagicIndicator;
import com.wdcloud.myrxjavaorretrofit.indicator.ViewPagerHelper;
import com.wdcloud.myrxjavaorretrofit.indicator.buildins.commonnavigator.CommonNavigator;
import com.wdcloud.myrxjavaorretrofit.indicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.wdcloud.myrxjavaorretrofit.indicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.wdcloud.myrxjavaorretrofit.indicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.wdcloud.myrxjavaorretrofit.indicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import com.wdcloud.myrxjavaorretrofit.indicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import com.wdcloud.myrxjavaorretrofit.indicator.buildins.commonnavigator.titles.SimplePagerTitleView;
import com.wdcloud.myrxjavaorretrofit.util.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Umbrella on 2018/12/25.
 */

public class MineFragment extends BaseFragment {
    @BindView(R.id.courseInfotabhead)
    MagicIndicator courseInfotabhead;
    Unbinder unbinder;
    @BindView(R.id.course_infoviewpager)
    ViewPager courseInfoviewpager;
    Unbinder unbinder1;
    private List<Fragment> fragments;
    private ViewPager.OnPageChangeListener onPageChangeListener;
    @Override
    protected int getContentView() {
        return R.layout.mine_layout;
    }

    @Override
    protected void init() {
        initFragments();
        CourseInfoPagerAdapter tabAdapter = new CourseInfoPagerAdapter(getActivity().getSupportFragmentManager());
        courseInfoviewpager.setOffscreenPageLimit(5);
        courseInfoviewpager.setAdapter(tabAdapter);
        onPageChangeListener=new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                courseInfoviewpager.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
        courseInfoviewpager.addOnPageChangeListener(onPageChangeListener);
        initIndicator();
    }

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    private void initIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                if (index == 0) {
                    simplePagerTitleView.setText("Home");
                }
                if (index == 1) {
                    simplePagerTitleView.setText("Like");
                }
                simplePagerTitleView.setNormalColor(Color.parseColor("#ff333333"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#ff3A9AFF"));
                simplePagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        courseInfoviewpager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setColors(Color.parseColor("#FF3A9AFF"));
//                indicator.setRoundRadius(ScreenUtil.dp2px(2));
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setLineHeight(ScreenUtil.dp2px(4));
                return indicator;
            }

        });
        courseInfotabhead.setNavigator(commonNavigator);
        ViewPagerHelper.bind(courseInfotabhead, courseInfoviewpager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    private void initFragments() {
        if (fragments == null) {
            fragments = new ArrayList<>();
            fragments.add(HomeFragment.newInstance());
            fragments.add(LikeFragment.newInstance());
        }
    }
    private class CourseInfoPagerAdapter extends FragmentPagerAdapter {


        public CourseInfoPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
