package com.probie.video.ui;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.probie.video.R;
import com.probie.video.base.BaseActivity;
import com.probie.video.config.Config;
import com.probie.video.fragment.HomeFragment;
import com.probie.video.model.Img;
import com.probie.video.model.Tame;
import com.probie.video.util.HtmlParse;
import com.probie.video.util.Log;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity {

    private static String TAG = "HomeActivity";

    private Handler handler;
    private Toolbar toolbar;

    /**启动这个Activity的Intent
     * @param context
     * @return
     */
    public static Intent createIntent(Context context) {
        return new Intent(context, HomeActivity.class);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG,"HomeActivity");
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_home);
        Log.i(TAG,"initView");
        toolbar = findViewById(R.id.toolbar_top);
        toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
        toolbar.setTitle("首页");
        setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("首页");
        initHandler();

    }

    @Override
    public void initData() {
        Log.i(TAG,"initData");

        runThread(new Runnable() {
            @Override
            public void run() {
                Document document = HtmlParse.getDocument(Config.BASE_URL);
                if (document!=null){
                    Message msg = handler.obtainMessage();
                    msg.what =2;
                    msg.obj = document;
                    handler.sendMessage(msg);
                    Log.e(TAG,document.toString());

                }
            }
        });




    }

    @Override
    public void initEvent() {
        Log.i(TAG,"initEvent");

    }

    public void initHandler(){

        handler = new Handler(){

            @Override
            public void handleMessage(@NonNull final Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case  1:
                        HomeFragment fragment =(HomeFragment) msg.obj;
                        Log.e("handleMessage",fragment.toString());
                        fragmentManager
                                .beginTransaction()
                                .add(R.id.linearlayout_home,fragment)
                                .show(fragment)
                                .commitAllowingStateLoss();
                        break;

                    case 2:
                        Document document = (Document) msg.obj;
                        initHome(document);
                        break;


                }
            }
        };
    }


    public void initHome(Document document ){

        Elements tames = document.getElementById("contrainer").getElementsByClass("tame");
        Log.e(TAG,tames.toString());
        Elements imgs  = document.getElementsByClass("imgs");
        Log.e(TAG,imgs.toString());
        List<Tame> tameData = new ArrayList();
        List<List> imgsData = new ArrayList<>();
        for (int i =0 ;i<tames.size();i++){
            Tame tame = new Tame();
            tame.setText(tames.get(i).select("h2").text());
            Log.e(TAG,tame.getText());
            tame.setHref(Config.BASE_URL+tames.get(i).select("a").first().attr("href"));
            Log.e(TAG,tame.getHref());
            tame.setMore(tames.get(i).select("span").text());
            Log.e(TAG,tame.getMore());
            tameData.add(tame);
            Elements lis = imgs.get(i).select("li");
            List<Img> li = new ArrayList<>();
            for (int j =0;j<lis.size();j++){
                //Log.e(TAG,lis.get(j).toString());
                Img img = new Img();
                img.setHref(Config.BASE_URL+ lis.get(j).select("a").first().attr("href"));
                Log.e(TAG,img.getHref());
                img.setEpisode(lis.get(j).select("p").last().text() );
                Log.e(TAG,img.getEpisode());
                img.setSrc(lis.get(j).select("img").attr("src"));
                Log.e(TAG,img.getSrc());
                img.setTitle(lis.get(j).select("img").attr("alt")) ;
                Log.e(TAG,img.getTitle());
                li.add(img);
            }
            HomeFragment homeFragment = new HomeFragment(tame,li);
            Message message = handler.obtainMessage();
            message.what = 1;
            message.obj = homeFragment;
            handler.sendMessage(message);
            // imgsData.add(li);
        }

    }


}
