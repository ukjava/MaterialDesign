package com.ukjava.myapp;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ukjava.myapp.fragment.NavigationFragment;
import com.ukjava.myapp.fragment.TabLayoutFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationFragment mNavigationFragment;
    private TabLayoutFragment mTabLayoutFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToolbar = findViewById(R.id.tool_bar);
        mNavigationView = findViewById(R.id.navigation_view);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,
                R.string.drawer_layout_open,R.string.drawer_layout_close);

        mDrawerToggle.syncState();
        mDrawerToggle.setDrawerIndicatorEnabled(true);

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerLayout.setStatusBarBackgroundColor(ContextCompat.getColor(this,
                R.color.colorPrimaryDark));

        //获取侧边菜单控件
        View headerView = mNavigationView.getHeaderView(0);
        headerView.setOnClickListener(this);
        mNavigationView.setItemIconTintList(null);//这样设置后icon和title的颜色就是默认的了
        mNavigationView.setNavigationItemSelectedListener(this);
        mNavigationView.setItemTextColor(ContextCompat.getColorStateList(this,
                R.color.bg_drawer_navigation));
        mNavigationView.setItemIconTintList(ContextCompat.getColorStateList(this,
                R.color.bg_drawer_navigation));

        //选择条目的方法
        setNavigationViewChecked(0);
        //设置当前页面
        setCurrentFragment();
    }

    private void setCurrentFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();//获取Fragment管理者并开启一个事务
        mNavigationFragment = NavigationFragment.newInstance(getString(R.string.navigation_navigation_bar));//实例化fragment对象
        transaction.replace(R.id.frame_content, mNavigationFragment).commit();
    }

    private void setNavigationViewChecked(int position) {
        mNavigationView.getMenu().getItem(position).setChecked(true);
        for (int i=0;i<mNavigationView.getMenu().size();i++){
            if (i!=position){
                mNavigationView.getMenu().getItem(i).setChecked(false);
            }
        }

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (menuItem.getItemId()) {
            case R.id.menu_bottom_navigation_bar:
                if (mNavigationFragment == null) {
                    mNavigationFragment = NavigationFragment.newInstance(getString(R.string.navigation_navigation_bar));
                }
                transaction.replace(R.id.frame_content, mNavigationFragment);
                Snackbar.make(mDrawerLayout, "NavigationBar", Snackbar.LENGTH_SHORT).show();
                setNavigationViewChecked(0);
                break;

            case R.id.menu_tab_layout:
                if(mTabLayoutFragment == null){
                    mTabLayoutFragment = TabLayoutFragment.newInstance(getString(R.string.navigation_tab_layout));
                }
                transaction.replace(R.id.frame_content, mTabLayoutFragment);
                setNavigationViewChecked(3);
                Snackbar.make(mDrawerLayout, "TabLayout + ViewPager", Snackbar.LENGTH_SHORT).show();
                break;

        }
        mDrawerLayout.closeDrawers();
        transaction.commit();

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.settings:
                Snackbar.make(mDrawerLayout, "Settings", Snackbar.LENGTH_SHORT).show();
                return true;
            case R.id.share:

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
