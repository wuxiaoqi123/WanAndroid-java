package com.welcome.home.playandroid.net.cache;

import java.util.List;
import java.util.WeakHashMap;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class MemoryCookie implements CookieJar {

    private WeakHashMap<HttpUrl, List<Cookie>> weakHashMap = new WeakHashMap<>();

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        weakHashMap.put(url, cookies);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        return weakHashMap.get(url);
    }
}
