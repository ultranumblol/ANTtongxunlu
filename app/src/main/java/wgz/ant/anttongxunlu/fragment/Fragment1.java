package wgz.ant.anttongxunlu.fragment;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wgz.ant.anttongxunlu.R;
import wgz.ant.anttongxunlu.adapter.MyListAdapter;
import wgz.ant.anttongxunlu.adapter.TreeListViewAdapter;
import wgz.ant.anttongxunlu.bean.FileBean;
import wgz.ant.anttongxunlu.db.DatabaseHelper;
import wgz.ant.anttongxunlu.util.SpUtil;


/**
 * Created by qwerr on 2016/4/22.
 */
public class Fragment1 extends Fragment {

    private List<FileBean> mDatas = new ArrayList<FileBean>();
    private ImageView ivDeleteText2;
    private EditText etSearch2;
    DatabaseHelper dbh;
    private TreeListViewAdapter mAdapter;
    private NestedScrollView reboundScrollView,reboundScrollView2;
    private List<Map<String, Object>> peos;//联系人列表
    private List<Map<String, Object>> peos2;
    private ListView mTree,listView2;
    private View mProgressView;
    private List<Map<String, Object>> constest;
    SpUtil spUtil;
    MyListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1,null);

        initview(view);
        return  view;
    }

    private void initview(View view) {
        dbh = new DatabaseHelper(getActivity());
        reboundScrollView = (NestedScrollView) view.findViewById(R.id.zuzhi_sv);
        reboundScrollView2 = (NestedScrollView) view.findViewById(R.id.zuzhi_sv2);
        mProgressView = view.findViewById(R.id.frag1_progress);
        mTree = (ListView) view.findViewById(R.id.list_1_1);
        listView2 = (ListView) view.findViewById(R.id.list_1_2);
        ivDeleteText2 = (ImageView)view.findViewById(R.id.ivDeleteText2);
        etSearch2 = (EditText)view.findViewById(R.id.etSearch2);
        //文字删除监听
        ivDeleteText2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                etSearch2.setText("");
            }
        });
        //搜索框输入文字监听
        etSearch2.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    ivDeleteText2.setVisibility(View.GONE);
                    reboundScrollView.setVisibility(View.VISIBLE);
                    reboundScrollView2.setVisibility(View.GONE);
                } else {
                    ivDeleteText2.setVisibility(View.VISIBLE);
                    reboundScrollView.setVisibility(View.GONE);
                    reboundScrollView2.setVisibility(View.VISIBLE);
                }

                initAllQuery();
                SearchTask searchTask = new SearchTask();
                searchTask.execute();

                //search1();


            }
        });
        //短按打电话监听
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                final TextView name = (TextView) arg1.findViewById(R.id.name);
                final TextView phone = (TextView) arg1.findViewById(R.id.number);
                final TextView rank = (TextView) arg1.findViewById(R.id.rank);
                //添加数据到数据库最近联系人
                insert2(name.getText().toString(), phone.getText().toString(), 2 + "", rank.getText().toString());
                Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri
                        .parse("tel:" + phone.getText().toString()));
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(dialIntent);

            }
        });
        initDatas();
    }

    private void initDatas() {

    }

    /**
     * 插入数据到最近联系人
     * @param name
     * @param phone
     */
    private void insert2(String name,String phone,String pid,String rank){
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = sDateFormat.format(new java.util.Date());
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues cv = new ContentValues();//实例化一个cv 用来装数据
        cv.put("name", name);
        cv.put("phone", phone);
        cv.put("pid",pid);
        cv.put("rank", rank);
        cv.put("date", date);
        db.insert("content1", null, cv);//插入操作
        db.close();

    }
    /**
     * 更新data数据
     * @param constest
     * @param data
     */
    private ArrayList<Map<String, Object>> getmDataSub(List<Map<String, Object>> constest, String data)
    {

        ArrayList<Map<String, Object>> data2 = new ArrayList<Map<String, Object>>();
        data2.clear();
        int length = constest.size();

        for(int i = 0; i < length; i++){

            if (constest.get(i).get("phone").toString().contains(data)||
                    constest.get(i).get("name").toString().contains(data)) {
                Map<String,Object> item = new HashMap<String,Object>();
                item.put("name",peos.get(i).get("name").toString());
                item.put("phone", peos.get(i).get("phone").toString());
                item.put("ranke", peos.get(i).get("ranke").toString());
                data2.add(item);
            }
        }
        //Log.i("xml","getmDataSub方法后peos"+constest.toString());
        //更新
        return data2;
    }
    private List<Map<String, Object>> search1(){
        String input = etSearch2.getText().toString();
        //Log.i("xml","search方法： constest有："+constest.toString());
        peos2=getmDataSub(peos, input);//获取更新数据
        return peos2;

    }
    private void initAllQuery() {
        spUtil = new SpUtil();
        peos = spUtil.getInfo(getActivity(), "huancun" + 0);
    }
    public class SearchTask extends AsyncTask {
        public SearchTask() {
        }

        @Override
        protected Object doInBackground(Object[] params) {
            List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
            list =search1();
            return list;
        }

        @Override
        protected void onPostExecute(Object o) {
            peos2 = (List<Map<String, Object>>) o;
            adapter = new MyListAdapter(peos2,getActivity());
            listView2.setAdapter(adapter);


        }
        @Override
        protected void onCancelled() {

        }
    }
}
