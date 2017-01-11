package wlhwh.example.com.offical_navigation_drawers;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Fragment mGitFragment;
    private Fragment mCsdnFragment;
    private Fragment mDeveloperFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找到Toolbar这个控件
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        //调用getSupportActionBar()方法将Toolbar的实例导入
        setSupportActionBar(mToolbar);

        //通过id找到悬浮按钮控件
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //设置悬浮按钮的点击事件
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        //调用setNavigationItemSelectedListener()方法
        //调用setCheckdItem方法将第一个设置为默认选中
        mNavigationView.setCheckedItem(R.id.nav_git);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_git) {
                    changeFragment(getmGitFragment(),false);
                } else if (id == R.id.nav_csdn) {
                    changeFragment(getmCsdnFragment(),false);
                } else if (id == R.id.nav_androiddev){
                    changeFragment(getmDeveloperFragment(),false);
                }
                mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    //使用onCreateOptionsMenu()这个方法加载顶部的Toolbar.xml文件
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    //调用onOptionsItemSelected()方法，对MenuItem的点击事件进行处理
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //如果点击到setting，则直接返回页面
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")




    //这是分别替换的不同的fragment（后期可以总结为一个方法）
    public Fragment getmGitFragment() {
        if (mGitFragment == null) {
            mGitFragment = new GitFragment();
        }
        return mGitFragment;
    }
    public Fragment getmCsdnFragment(){
        if (mCsdnFragment == null){
            mCsdnFragment = new CsdnFragment();
        }
        return mCsdnFragment;
    }
    public Fragment getmDeveloperFragment(){
        if (mDeveloperFragment == null){
            mDeveloperFragment = new DeveloperFragment();
        }
        return mDeveloperFragment;
    }
    /**
     * 替换fragment ,这是一个方法
     */
    public void changeFragment(Fragment f, boolean init) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_root, f);
        if (!init) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}
