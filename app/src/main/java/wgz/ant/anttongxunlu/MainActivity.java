package wgz.ant.anttongxunlu;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import wgz.ant.anttongxunlu.adapter.FragmentAdapter;
import wgz.ant.anttongxunlu.fragment.Fragment1;
import wgz.ant.anttongxunlu.fragment.Fragment2;
import wgz.ant.anttongxunlu.util.SpUtil;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager mViewpager;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private List<Fragment> fragments;
    private List<Map<String, Object>> peos;//联系人列表
    private List<Map<String , Object>> list3 ;
    SpUtil spUtil;
    private TextView rank,name;
    private CoordinatorLayout rootlayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        rootlayout = (CoordinatorLayout) findViewById(R.id.rootLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getHeaderCount();
        View view =  navigationView.getHeaderView(0);
      /*  TextView phone = (TextView)view.findViewById(R.id.head_phone);
        phone.setText(getphonesp());
        rank = (TextView) view.findViewById(R.id.head_rank);
        name = (TextView) view.findViewById(R.id.head_name);*/
        // tabLayout = (TabLayout) findViewById(R.id.tabs);
        initAllQuery();
        rootlayout = (CoordinatorLayout) findViewById(R.id.rootLayout);
        initAllQuery();
        mViewpager = (ViewPager) findViewById(R.id.viewpager_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("蚂蚁集团通讯录");
        setSupportActionBar(toolbar);
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragments = new ArrayList<>();
        fragments.add(fragment1);
        fragments.add(fragment2);
        List<String> titles = new ArrayList<>();
        titles.add("组织机构");
        titles.add("联系人");
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);

        mViewpager.setAdapter(adapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


    }



    private void initAllQuery() {

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
