package com.ukjava.myapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ukjava.myapp.Constants;
import com.ukjava.myapp.R;
import com.ukjava.myapp.adapter.TabLayoutFragmentAdapter;
import com.ukjava.myapp.fragment.subfragment.HomeFragment;
import com.ukjava.myapp.fragment.subfragment.LikeFragment;
import com.ukjava.myapp.fragment.subfragment.LocationFragment;
import com.ukjava.myapp.fragment.subfragment.PersonFragment;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutFragment extends Fragment implements TabLayout.BaseOnTabSelectedListener {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private TextView mTextView;
    private List<String> mTabList = new ArrayList<>();
    private int[] mTabImgs = new int[]{R.drawable.home, R.drawable.location, R.drawable.like, R.drawable.person};
    private List<Fragment> mFragments = new ArrayList<>();
    private TabLayoutFragmentAdapter mAdapter;

    public static TabLayoutFragment newInstance(String s){
        TabLayoutFragment tabLayoutFragment = new TabLayoutFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS,s);
        tabLayoutFragment.setArguments(bundle);

        return tabLayoutFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tablayout, container, false);
        mTextView = view.findViewById(R.id.activity_text_view);
        Bundle bundle = getArguments();
        if (bundle != null){
            String s = bundle.getString(Constants.ARGS);
            if (!TextUtils.isEmpty(s)){
                mTextView.setText(s);
            }
        }

        mViewPager = view.findViewById(R.id.view_pager);
        mTabLayout = view.findViewById(R.id.tab_layout);
        initTabList();

        mAdapter = new TabLayoutFragmentAdapter(getChildFragmentManager(),
                mTabList, getActivity(), mFragments, mTabImgs);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

        for (int i=0;i<mTabLayout.getTabCount();i++){
            mTabLayout.getTabAt(i).setCustomView(mAdapter.getTabView(i));
        }

        mTabLayout.addOnTabSelectedListener(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initFragmentList();
    }

    private void initFragmentList() {
        mFragments.clear();
        mFragments.add(HomeFragment.newInstance(getString(R.string.item_home)));
        mFragments.add(LocationFragment.newInstance(getString(R.string.item_location)));
        mFragments.add(LikeFragment.newInstance(getString(R.string.item_like)));
        mFragments.add(PersonFragment.newInstance(getString(R.string.item_person)));
    }

    private void initTabList() {
        mTabList.clear();
        mTabList.add(getString(R.string.item_home));
        mTabList.add(getString(R.string.item_location));
        mTabList.add(getString(R.string.item_like));
        mTabList.add(getString(R.string.item_person));
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        setTabSelectedState(tab);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        setTabUnSelectedState(tab);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    private void setTabSelectedState(TabLayout.Tab tab) {
        View customView = tab.getCustomView();
        TextView tabText = (TextView) customView.findViewById(R.id.tv_tab_text);
        ImageView tabIcon = (ImageView) customView.findViewById(R.id.iv_tab_icon);
        tabText.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        String s = tabText.getText().toString();
        if (getString(R.string.item_home).equals(s)) {
            tabIcon.setImageResource(R.drawable.home_fill);
        } else if (getString(R.string.item_location).equals(s)) {
            tabIcon.setImageResource(R.drawable.location_fill);
        } else if (getString(R.string.item_like).equals(s)) {
            tabIcon.setImageResource(R.drawable.like_fill);
        } else if (getString(R.string.item_person).equals(s)) {
            tabIcon.setImageResource(R.drawable.person_fill);
        }
    }

    private void setTabUnSelectedState(TabLayout.Tab tab) {
        View customView = tab.getCustomView();
        TextView tabText = (TextView) customView.findViewById(R.id.tv_tab_text);
        ImageView tabIcon = (ImageView) customView.findViewById(R.id.iv_tab_icon);
        tabText.setTextColor(ContextCompat.getColor(getActivity(), R.color.black_1));
        String s = tabText.getText().toString();
        if (getString(R.string.item_home).equals(s)) {
            tabIcon.setImageResource(R.drawable.home);
        } else if (getString(R.string.item_location).equals(s)) {
            tabIcon.setImageResource(R.drawable.location);
        } else if (getString(R.string.item_like).equals(s)) {
            tabIcon.setImageResource(R.drawable.like);
        } else if (getString(R.string.item_person).equals(s)) {
            tabIcon.setImageResource(R.drawable.person);
        }
    }
}
