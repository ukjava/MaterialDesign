package com.ukjava.myapp.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ukjava.myapp.R;

import java.util.List;

public class TabLayoutFragmentAdapter extends FragmentPagerAdapter {
    private List<String> mTabList;
    private Context mContext;
    private List<Fragment> mFragments;
    private ImageView mTabIcon;
    private TextView mTabText;
    private int[] mTabImgs;
    private LinearLayout mTabView;

    public TabLayoutFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public TabLayoutFragmentAdapter(FragmentManager fm, List<String> tabList,
                                    Context context, List<Fragment> fragments,
                                    int[] tabImgs){
        super(fm);
        mTabList = tabList;
        mContext = context;
        mFragments = fragments;
        mTabImgs = tabImgs;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mTabList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTabList.get(position);
    }


    public View getTabView(int position) {
        View view = LinearLayout.inflate(mContext, R.layout.layout_tab_view, null);
        mTabView = view.findViewById(R.id.ll_tab_view);
        mTabText = view.findViewById(R.id.tv_tab_text);
        mTabIcon = view.findViewById(R.id.iv_tab_icon);

        mTabText.setText(getPageTitle(position));
        mTabIcon.setImageResource(mTabImgs[position]);

        if (0 == position) {//the default color of item home is green
            mTabText.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
            mTabIcon.setImageResource(R.drawable.home_fill);
        }

        return view;
    }
}
