package com.ukjava.mymaterialdesignmz;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    //DrawerLayout的作用就是左侧滑出、右侧滑出菜单。
    // 需要和ActionBarDrawerToggle来配合使用
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mToggle;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private Meizhi[] meizhis = {new Meizhi("AngleBaby", R.drawable.angle), new Meizhi("古力娜扎", R.drawable.gulinazha),
            new Meizhi("林允儿", R.drawable.linyuner), new Meizhi("刘亦菲", R.drawable.liuyifei),
            new Meizhi("孙俪", R.drawable.suili), new Meizhi("佟丽娅", R.drawable.tongliya),
            new Meizhi("杨幂", R.drawable.yangmi), new Meizhi("赵丽颖", R.drawable.zhaoliyin),
            new Meizhi("李冰冰", R.drawable.libingbing), new Meizhi("唐嫣", R.drawable.tangyan)};

    private List<Meizhi> beautyList = new ArrayList<>();
    private MeizhiAdapter mAdpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();
        mAdpter = new MeizhiAdapter(beautyList);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mRecyclerView.setAdapter(mAdpter);

        setSupportActionBar(mToolbar);

        //给NavigationView设置item选择事件
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Snackbar snackbar = Snackbar.make(mNavigationView,item.getTitle(),
                        Snackbar.LENGTH_SHORT);

                snackbar.setAction("敬请期待！", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
                snackbar.setActionTextColor(Color.BLUE);
                snackbar.show();
;                return true;
            }
        });

        //给左侧菜单按钮并加按钮特效
        mToggle = new ActionBarDrawerToggle(MainActivity.this,
                mDrawerLayout,mToolbar,R.string.open,R.string.close);
        mToggle.syncState();
        mDrawerLayout.addDrawerListener(mToggle);

        //利用SwipeRefreshLayout实现下拉刷新功能
        mSwipeRefreshLayout.setColorSchemeColors(Color.RED,Color.YELLOW,
                Color.BLUE,Color.GREEN);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });


    }

    /***************************
     * 创建Toolbar上的菜单
     ***************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    /***************************
     * 给菜单设置点击事件
     ***************************/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup:
                Toast.makeText(MainActivity.this, "backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    private void refreshData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        mAdpter.notifyDataSetChanged();
                        mSwipeRefreshLayout.setRefreshing(false);//刷新图标取消
                        Toast.makeText(MainActivity.this,"更新成功！",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }

    private void initData() {
        beautyList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            //会产生伪随机的正整数
            int index = random.nextInt(meizhis.length);
            beautyList.add(meizhis[index]);
        }
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.dl_main);
        mNavigationView = findViewById(R.id.nv_left);
        mRecyclerView = findViewById(R.id.rv_main);
        mSwipeRefreshLayout = findViewById(R.id.srl_main);
    }
}
