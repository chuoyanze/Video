package com.probie.video.base;

import android.os.Bundle;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.probie.video.interfaces.FragmentPresenter;

/**
 * @author ：probie
 * @date ：Created in 2020/3/6 14:25
 * @description：
 * @modified By：
 * @version: $
 */
public abstract class BaseFragment extends Fragment implements FragmentPresenter {

    private static final String TAG = "BaseFragment";

    /**
     * 添加该Fragment的Activity
     * @warn 不能在子类中创建
     */
    protected BaseActivity context = null;
    /**
     * 该Fragment全局视图
     * @must 非abstract子类的onCreateView中return view;
     * @warn 不能在子类中创建
     */
    protected View view = null;
    /**
     * 布局解释器
     * @warn 不能在子类中创建
     */
    protected LayoutInflater inflater = null;
    /**
     * 添加这个Fragment视图的布局
     * @warn 不能在子类中创建
     */

    @Nullable
    protected ViewGroup container = null;

    private boolean isAlive = false;
    private boolean isRunning = false;
    /**
     * @must 在非abstract子类的onCreateView中super.onCreateView且return view;
     */
    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        context = (BaseActivity) getActivity();
        isAlive = true;

        this.inflater = inflater;
        this.container = container;

        return view;
    }

    /**设置界面布局
     * @warn 最多调用一次
     * @param layoutResID
     * @use 在onCreateView后调用
     */
    public void setContentView(int layoutResID) {
        setContentView(inflater.inflate(layoutResID, container, false));
    }
    /**设置界面布局
     * @warn 最多调用一次
     * @param v
     * @use 在onCreateView后调用
     */
    public void setContentView(View v) {
        setContentView(v, null);
    }
    /**设置界面布局
     * @warn 最多调用一次
     * @param v
     * @param params
     * @use 在onCreateView后调用
     */
    public void setContentView(View v, ViewGroup.LayoutParams params) {
        view = v;
    }



    /**通过id查找并获取控件，使用时不需要强转
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    public <V extends View> V findView(int id) {
        return (V) view.findViewById(id);
    }
    /**通过id查找并获取控件，并setOnClickListener
     * @param id
     * @param l
     * @return
     */
    public <V extends View> V findView(int id, OnClickListener l) {
        V v = findView(id);
        v.setOnClickListener(l);
        return v;
    }
    /**通过id查找并获取控件，使用时不需要强转
     * @warn 调用前必须调用setContentView
     * @param id
     * @return
     */
    public <V extends View> V findViewById(int id) {
        return findView(id);
    }
    /**通过id查找并获取控件，并setOnClickListener
     * @param id
     * @param l
     * @return
     */
    public <V extends View> V findViewById(int id, OnClickListener l) {
        return findView(id, l);
    }


    @Override
    public final boolean isAlive() {
        return isAlive && context != null;// & ! isRemoving();导致finish，onDestroy内runUiThread不可用
    }
    @Override
    public final boolean isRunning() {
        return isRunning & isAlive();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "\n onResume <<<<<<<<<<<<<<<<<<<<<<<");
        super.onResume();
        isRunning = true;
        Log.d(TAG, "onResume >>>>>>>>>>>>>>>>>>>>>>>>\n");
    }

    @Override
    public void onPause() {
        Log.d(TAG, "\n onPause <<<<<<<<<<<<<<<<<<<<<<<");
        super.onPause();
        isRunning = false;
        Log.d(TAG, "onPause >>>>>>>>>>>>>>>>>>>>>>>>\n");
    }


    /**销毁并回收内存
     * @warn 子类如果要使用这个方法内用到的变量，应重写onDestroy方法并在super.onDestroy();前操作
     */
    @Override
    public void onDestroy() {

        if (view != null) {
            try {
                view.destroyDrawingCache();
            } catch (Exception e) {

            }
        }

        isAlive = false;
        isRunning = false;
        super.onDestroy();

        view = null;
        inflater = null;
        container = null;



        context = null;

    }


}
