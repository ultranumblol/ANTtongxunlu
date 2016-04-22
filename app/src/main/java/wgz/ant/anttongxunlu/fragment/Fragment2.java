package wgz.ant.anttongxunlu.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wgz.ant.anttongxunlu.R;

/**
 * Created by qwerr on 2016/4/22.
 */
public class Fragment2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2,null);
        initview(view);

        return  view;
    }

    private void initview(View view) {

    }
}
