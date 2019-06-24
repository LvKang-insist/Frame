package com.admin.core.util.storage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.admin.core.app.Latte;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Copyright (C)
 *
 * @file: LattePreference
 * @author: 345
 * @Time: 2019/4/21 11:25
 * @description: 提示：
 * Activity.getPreferences(int mode) 生成Activity名.xml 用于Activity内部存储
 * PreferenceManager.getDefaultsharedPreferences(Content) 生成 包名_preferences.xml
 * Context.getSharedPreferences(String name,int mode) 生成name.xml
 */
public class LattePreference {
    /**
     * 每个 应用都有一个默认的配置文件 preferences.xml ，使用getDefaultSharedPreferences 获取
     */
    private static final SharedPreferences PREFERENCES =
            PreferenceManager.getDefaultSharedPreferences(Latte.getApplication());
    //默认的key
    private static final String APP_PREFERENCES_KEY = "profile";


    private static SharedPreferences getAppPreference() {
        return PREFERENCES;
    }

    /**
     * @param val 要保存的数据
     */
    public static void setAppProfile(String val) {
        getAppPreference()
                .edit()
                .putString(APP_PREFERENCES_KEY, val)
                .apply();
    }

    public static String getAppProfile(){
        return getAppPreference().getString(APP_PREFERENCES_KEY,null);
    }


    public static JSONObject getAppProfileJson(){
        final String profile = getAppProfile();
        return JSON.parseObject(profile);
    }

    public static void removeAppProfile(){
        getAppPreference()
                .edit()
                .remove(APP_PREFERENCES_KEY)
                .apply();
    }

    public static void clearAppPreferences(){
        getAppPreference()
                .edit()
                .clear()
                .apply();
    }

    public static void setAppFlag(String key,boolean flag){
        getAppPreference()
                .edit()
                .putBoolean(key,flag)
                .apply();
    }

    public static boolean getAppFlag(String key){
        return getAppPreference().getBoolean(key,false);
    }

    /**
     * @param key 程序是否是 第一次启动
     * @return 如果使用过则是 true ，否则 为 false
     */
    public static boolean booleangetAppFlag(String key){
        return getAppPreference()
                .getBoolean(key,false);
    }

    /**
     * sp set 方法
     */
    public static void setCustomAppProfile(String key,String val) {
        getAppPreference()
                .edit()
                .putString(key, val)
                .apply();
    }

    /**
     *  sp get 方法
     */
    public static String getCustomAppProfile(String key) {
        return getAppPreference().getString(key,"");
    }
}
