package com.wdcloud.myrxjavaorretrofit.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Umbrella on 2018/12/29.
 */

public class MyPagerAdapter extends FragmentPagerAdapter{
    private Context context;
    private List<Fragment> fragmentList;
    private List<String> list_Title;
    public Fragment currentFragment;
    public MyPagerAdapter(FragmentManager fm, Context context, List<Fragment> fragmentList, List<String> list_Title) {
        super(fm);
        this.context = context;
        this.fragmentList = fragmentList;
        this.list_Title = list_Title;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        this.currentFragment= (Fragment) object;
        super.setPrimaryItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return list_Title.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list_Title.get(position);
    }
}
