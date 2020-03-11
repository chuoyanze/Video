package com.probie.video.fragment;


import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.probie.video.R;
import com.probie.video.adapter.HomeAdapter;
import com.probie.video.base.BaseFragment;
import com.probie.video.model.Img;
import com.probie.video.model.Tame;
import com.probie.video.util.Log;
import com.probie.video.view.AllDisplayGridView;

import java.util.List;

public class HomeFragment extends BaseFragment {
    private String TAG = "HomeFragment";
    private Tame tame;
    private List<Img> imgs;
    private TextView action;
    private TextView more ;
    private AllDisplayGridView gridView;
    private HomeAdapter adapter;

    public HomeFragment(Tame tame , List<Img> imgs){
        this.imgs =imgs;
        this.tame = tame;


    }
    /* Log.i(TAG,tame.getHref());
            Log.i(TAG,imgs.get(0).getHref());*/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
         setContentView(R.layout.fragment_home);
        initView();
        initData();
        initEvent();
        return view;
    }

    @Override
    public void initView() {
        action =  findView(R.id.tag_action);
        more = findView(R.id.tag_more);
        gridView = findView(R.id.gride_fragment);

    }

    @Override
    public void initData() {
        action.setText(tame.getText());
        more.setText(tame.getMore());
        adapter = new HomeAdapter(getContext(),imgs);
        gridView.setAdapter(adapter);
    }

    @Override
    public void initEvent() {

    }
}
