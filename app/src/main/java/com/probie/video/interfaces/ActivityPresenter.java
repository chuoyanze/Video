package com.probie.video.interfaces;

import android.app.Activity;
import android.view.View;

import com.probie.video.interfaces.Presenter;

/**
 * @author ：probie
 * @date ：Created in 2020/3/6 11:57
 * @description：
 * @modified By：
 * @version: $
 */
public interface ActivityPresenter extends Presenter {
    /**获取Activity
     * @must 在非抽象Activity中 return this;
     */
    public Activity getActivity();//无public导致有时自动生成的getActivity方法会缺少public且对此报错

}
