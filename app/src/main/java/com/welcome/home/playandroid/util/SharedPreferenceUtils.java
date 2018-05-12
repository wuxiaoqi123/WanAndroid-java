package com.welcome.home.playandroid.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.welcome.home.playandroid.app.PlayAndroidApplication;

/**
 * Created by wuxiaoqi on 2018/2/27.
 * sp 工具类
 */

public class SharedPreferenceUtils {

    private static final String FILENAME = "config";

    public static Context getContext() {
        return PlayAndroidApplication.appContext;
    }

    private static SharedPreferences getSharedPreferences() {
        return getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
    }

    public static Boolean getBooleanData(String key, Boolean value) {
        return getSharedPreferences().getBoolean(key, value);
    }

    public static void setBooleanData(String key, Boolean value) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putBoolean(key, value);
        edit.apply();
    }

    public static String getStringData(String key, String value) {
        return getSharedPreferences().getString(key, value);
    }

    public static void setStringData(String key, String value) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putString(key, value);
        edit.apply();
    }

    public static int getIntData(String key, int value) {
        return getSharedPreferences().getInt(key, value);
    }

    public static void setIntData(String key, int value) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putInt(key, value);
        edit.apply();
    }

    public static long getLongData(String key, long value) {
        return getSharedPreferences().getLong(key, value);
    }

    public static void setLongData(String key, long value) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putLong(key, value);
        edit.apply();
    }

    public static void setFloatData(String key, float value) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putFloat(key, value);
        edit.apply();
    }

    public static float getFloatData(String key, float value) {
        return getSharedPreferences().getFloat(key, value);
    }

    public static void clear() {
        getSharedPreferences().edit().clear().apply();
    }
}
