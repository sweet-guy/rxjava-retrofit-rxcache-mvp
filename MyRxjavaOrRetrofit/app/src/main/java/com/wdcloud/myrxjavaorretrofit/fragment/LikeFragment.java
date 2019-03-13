package com.wdcloud.myrxjavaorretrofit.fragment;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.wdcloud.myrxjavaorretrofit.R;
import com.wdcloud.myrxjavaorretrofit.adapter.MyPagerAdapter;
import com.wdcloud.myrxjavaorretrofit.base.BaseFragment;
import com.wdcloud.myrxjavaorretrofit.indicator.MagicIndicator;
import com.wdcloud.myrxjavaorretrofit.indicator.ViewPagerHelper;
import com.wdcloud.myrxjavaorretrofit.indicator.buildins.UIUtil;
import com.wdcloud.myrxjavaorretrofit.indicator.buildins.commonnavigator.CommonNavigator;
import com.wdcloud.myrxjavaorretrofit.indicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.wdcloud.myrxjavaorretrofit.indicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.wdcloud.myrxjavaorretrofit.indicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.wdcloud.myrxjavaorretrofit.indicator.buildins.commonnavigator.indicators.UShapePagerIndicator;
import com.wdcloud.myrxjavaorretrofit.indicator.buildins.commonnavigator.titles.ClipPagerTitleView;
import com.wdcloud.myrxjavaorretrofit.util.ScreenUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by Umbrella on 2018/12/25.
 */

public class LikeFragment extends BaseFragment {
    List<Fragment> fragmentList;
    List<String> list_Title;
    //    @BindView(R.id.tab)
//    TabLayout tab;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.Magicindicator)
    MagicIndicator Magicindicator;
    Unbinder unbinder;

    //    @BindView(R.id.button)
//    Button bt;
    @Override
    protected int getContentView() {
        return R.layout.like_layout;
    }

    @Override
    protected void init() {
        fragmentList = new ArrayList<>();
        list_Title=new ArrayList<>();
        list_Title.add("备课");
        list_Title.add("听课");
        fragmentList.add(new TabTwoFragment());
        fragmentList.add(new TabTwoFragment());
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getFragmentManager(), getContext(), fragmentList, list_Title);
        viewpager.setAdapter(myPagerAdapter);
        Magicindicator.setBackgroundResource(R.drawable.bg_ushape_corner_blue);
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        final List<String> mDataList = Arrays.asList("备课", "听课");
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, int index) {
                ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
                clipPagerTitleView.setText(mDataList.get(index));
                clipPagerTitleView.setTextSize(ScreenUtil.dp2px(23));
                clipPagerTitleView.setTextColor(Color.parseColor("#FF3A9AFF"));
                clipPagerTitleView.setClipColor(Color.WHITE);
                clipPagerTitleView.onSelected(index, 2);
                clipPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewpager.setCurrentItem(index);
                    }
                });
                return clipPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                UShapePagerIndicator indicator = new UShapePagerIndicator(context);
                float navigatorHeight = ScreenUtil.dp2px(40);
                float borderWidth = UIUtil.dip2px(context, 1);
                float lineHeight = navigatorHeight - 2 * borderWidth;
                indicator.setLineHeight(lineHeight);
                indicator.setRoundRadius(lineHeight / 2);
                indicator.setYOffset(borderWidth);
                indicator.setColors(Color.parseColor("#FF3A9AFF"));
                return indicator;
            }
        });
        Magicindicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(Magicindicator, viewpager);
//        list_Title = new ArrayList<>();
//        fragmentList.add(new TabOneFragment());
//        fragmentList.add(new TabTwoFragment());
//        fragmentList.add(new TabTwoFragment());
//        fragmentList.add(new TabTwoFragment());
//        list_Title.add("one");
//        list_Title.add("two");
//        list_Title.add("two121321312fsdfs");
//        list_Title.add("two121321312fsdfsdf");
//        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getFragmentManager(), getContext(), fragmentList, list_Title);
//        viewpager.setAdapter(myPagerAdapter);
//        tab.setupWithViewPager(viewpager);
//        new TabLayoutUtil().reflex(tab);
//        TabOneFragment currentFragment = (TabOneFragment) myPagerAdapter.currentFragment;
//        Button bt = currentFragment.getView().findViewById(R.id.button);
//        bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tab.getTabAt(3).select();
//            }
//        });
    }

    public static LikeFragment newInstance() {
        LikeFragment fragment = new LikeFragment();
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
