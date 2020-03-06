package com.probie.video.util;

import com.probie.video.config.Config;

/**
 * @author ：probie
 * @date ：Created in 2020/3/6 12:44
 * @description：
 * @modified By：
 * @version: $
 */
public class Log {

    public static void i(String tag,String message){
        if (Config.IS_DEBUG){
            android.util.Log.i(tag,message);
        }
    }

    public static void e(String tag,String message){
        if (Config.IS_DEBUG){
            android.util.Log.e(tag,message);
        }
    }

}
