package wgz.ant.anttongxunlu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import wgz.ant.anttongxunlu.adapter.RecycleAdapter;
import wgz.ant.anttongxunlu.db.DatabaseHelper;

public class GroupManagerActivity extends AppCompatActivity {

    DatabaseHelper dbh ;
    List<Map<String, Object>> data1 = new ArrayList<Map<String, Object>>();
    private TextView maddgroup;
    private RecyclerView grouplist;
    RecycleAdapter adapter;
    CoordinatorLayout gmrootview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_manager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("分组管理");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();
    }

    private void init() {
        gmrootview = (CoordinatorLayout) findViewById(R.id.gmana_rootview);
        //maddgroup = (TextView) findViewById(R.id.addgroup);
        grouplist = (RecyclerView) findViewById(R.id.recycler_view);
       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab3);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //数据是使用Intent返回
                Intent intent = new Intent();
                //把返回数据存入Intent
                intent.putExtra("result", "该刷新了");
                //设置返回数据
                setResult(RESULT_OK, intent);
                finish();
            }
        });*/
        flush();

    }

    private void flush() {

    }

    @Override
    public void finish() {
        //数据是使用Intent返回
        Intent intent = new Intent();
        //把返回数据存入Intent
        intent.putExtra("result", "该刷新了");
        //设置返回数据
        setResult(RESULT_OK, intent);
        super.finish();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home){
            finish();
            return true;

        }
        return onOptionsItemSelected(item);
    }
}
