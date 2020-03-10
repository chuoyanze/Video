package com.probie.video.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.probie.video.interfaces.ActivityPresenter;

/**
 * @author ：probie
 * @date ：Created in 2020/3/6 11:35
 * @description：
 * @modified By：
 * @version: $
 */
public abstract class BaseActivity  extends AppCompatActivity  implements ActivityPresenter {


    private String TAG ="BaseActivity";

    /**
     * 该Activity实例，命名为context是因为大部分方法都只需要context，写成context使用更方便
     * @warn 不能在子类中创建
     */
    protected BaseActivity context = null;
    /**
     * 该Activity的界面，即contentView
     * @warn 不能在子类中创建
     */
    protected View view = null;
    /**
     * 布局解释器
     * @warn 不能在子类中创建
     */
    protected LayoutInflater inflater = null;
    /**
     * Fragment管理器
     * @warn 不能在子类中创建
     */
    protected FragmentManager fragmentManager = null;
    protected Intent intent = null;

    private boolean isAlive = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        context = (BaseActivity) getActivity();
        inflater = getLayoutInflater();
        isAlive = true;
        initView();
        initData();
        initEvent();
    }


    @Override
    public Activity getActivity() {
        return this; //必须return this;
    }


    /**在UI线程中运行，建议用这个方法代替runOnUiThread
     * @param action
     */
    public final void runUiThread(Runnable action) {
        if (isAlive == false) {
            Log.w(TAG, "runUiThread  isAlive() == false >> return;");
            return;
        }
        runOnUiThread(action);
    }

    /**
     * 运行一个线程
     * @param runnable
     */
    public void runThread(Runnable runnable){

        new Thread(runnable).start();
    }


    @Override
    protected void onDestroy() {

        isAlive = false;
        super.onDestroy();
        fragmentManager = null;
        inflater = null;

        context = null;
    }
}
