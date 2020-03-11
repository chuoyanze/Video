package com.probie.video.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.probie.video.R;
import com.probie.video.adapter.EpisodeAdapter;
import com.probie.video.base.BaseFragment;
import com.probie.video.model.Episode;
import com.probie.video.view.AllDisplayGridView;

import java.util.List;


public class EpisodeFragment extends BaseFragment {

    private String route;
    private List<Episode> data;
    private TextView textRoute;
    private EpisodeAdapter adapter;
    private AllDisplayGridView gridView;

    public EpisodeFragment(String route , List<Episode> data){

        this.route = route;
        this.data = data ;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
         setContentView(R.layout.fragment_episode);
         initView();
         initData();
         initEvent();
         return view;
    }

    @Override
    public void initView() {
        textRoute = findView(R.id.text_route);
        gridView = findView(R.id.gride_episode);

    }

    @Override
    public void initData() {
        adapter = new EpisodeAdapter(getContext(),data);
        textRoute.setText(route);
        gridView.setAdapter(adapter);

    }

    @Override
    public void initEvent() {

    }
}
