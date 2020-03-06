package com.probie.video.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.probie.video.R;
import com.probie.video.base.BaseFragment;
import com.probie.video.model.Img;
import com.probie.video.model.Tame;

import java.util.List;

public class HomeFragment extends BaseFragment {
    private Tame tame;
    private List<Img> imgs;

    public HomeFragment(Tame tame , List<Img> imgs){
        this.imgs =imgs;
        this.tame = tame;
    }

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

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }
}
