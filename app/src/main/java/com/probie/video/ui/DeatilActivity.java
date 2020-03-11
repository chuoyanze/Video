package com.probie.video.ui;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.facebook.drawee.view.SimpleDraweeView;
import com.probie.video.R;
import com.probie.video.adapter.EpisodeAdapter;
import com.probie.video.base.BaseActivity;
import com.probie.video.config.Config;
import com.probie.video.fragment.EpisodeFragment;
import com.probie.video.model.Episode;
import com.probie.video.util.HtmlParse;
import com.probie.video.util.Log;
import androidx.appcompat.widget.Toolbar;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class DeatilActivity extends BaseActivity {

    private static final String TAG ="DeatilActivity" ;
    private SimpleDraweeView imageview;
    private Handler handler;
    LinearLayout tagLinear;
    private TextView tagText;
    private Toolbar toolbar;


    /**启动这个Activity的Intent
     * @param context
     * @return
     */
    public static Intent createIntent(Context context) {
        return new Intent(context, DeatilActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initView() {
        Log.e(TAG,"initView");

        setContentView(R.layout.activity_deatil);
        toolbar = findViewById(R.id.toolbar_top);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        initHandler();
        imageview = findViewById(R.id.image_deatil) ;
        tagLinear = findViewById(R.id.linear_tag);
        Log.e(TAG,getIntent().getStringExtra("DEATIL_HREF"));
        Log.e(TAG,getIntent().getStringExtra("IMG_SRC"));


    }

    @Override
    public void initData() {
        Log.e(TAG,"initData");
        getSupportActionBar().setTitle(getIntent().getStringExtra("TITLE"));
        Uri uri = Uri.parse(getIntent().getStringExtra("IMG_SRC"));
        imageview.setImageURI(uri);
        runThread(new Runnable() {
            @Override
            public void run() {
                Document document = HtmlParse.getDocument(getIntent().getStringExtra("DEATIL_HREF"));
               if (document!=null){
                   Log.e(TAG,document.toString());
                   Log.e(TAG,document.getElementsByClass("alex").select("span").text());
                   Message message = handler.obtainMessage();
                   message.what = 1;
                   message.obj = document;
                   handler.sendMessage(message);
               }

            }
        });

    }

    @Override
    public void initEvent() {
        Log.e(TAG,"initEvent");


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                Log.e(TAG,"finish");
                finish();
                break;
        }

        return true;
    }

    public void initHandler(){

        handler = new Handler(){

            @Override
            public void handleMessage(@NonNull final Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case  1:
                        Document document = (Document)msg.obj;
                       if (document!=null){
                           initEpisode(document);
                       }


                    case 2:

                        break;


                }
            }
        };
    }


    public void initEpisode(Document document){
        Elements elements = document.getElementsByClass("alex").select("span");
        Log.e(TAG,elements.toString());
        for (int i =0;i<elements.size();i++){
            TextView textView = new TextView(context);
            textView.setText(elements.get(i).text());
            Log.e(TAG,elements.get(i).text());
            tagLinear.addView(textView);
        }
        Elements movurl = document.getElementsByClass("movurl");
        Log.e(TAG,movurl.toString());
        Elements routes = document.getElementsByClass("menu0");
        Log.e(TAG,routes.toString());
        String route;
        for (int i =0;i<movurl.size();i++){
            route = routes.get(i).select("li").first().text();
            Elements lis = movurl.get(i).select("li");
            List<Episode> data = new ArrayList<Episode>();
            for (int j = 0;j<lis.size();j++){
                Episode episode = new Episode();
                episode.setEpisode(lis.get(j).select("a").text());
                Log.e(TAG,episode.getEpisode());
                episode.setHref(Config.BASE_URL+lis.get(j).select("a").attr("href"));
                Log.e(TAG,episode.getHref());
                data.add(episode);
            }

            EpisodeFragment fragment = new EpisodeFragment(route,data);
            fragmentManager
                    .beginTransaction()
                    .add(R.id.linear_episode,fragment)
                    .show(fragment)
                    .commitAllowingStateLoss();
        }

    }

}
