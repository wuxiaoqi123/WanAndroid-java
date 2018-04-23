package com.welcome.home.playandroid.net.config;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.ConnectionPool;

/**
 * Created by wuxiaoqi on 2018/4/23.
 * 网络配置
 */

public class NetWorkConfiguration {

    private static final String TAG = "play_android";

    private boolean isCache;
    private boolean isDiskCache;
    private boolean isMemoryCache;

    private int memoryCacheTime;
    private int diskCacheTime;

    private int maxDiskCacheSize;

    private Cache diskCache;

    private int connectTimeout;

    private ConnectionPool connectionPool;

    private InputStream[] certificates;

    private Context context;

    private String baseUrl;

    public NetWorkConfiguration(Context context) {
        this.isCache = false;
        this.isDiskCache = false;
        this.isMemoryCache = false;
        this.memoryCacheTime = 60;
        this.diskCacheTime = 60 * 60 * 24 * 28;
        this.maxDiskCacheSize = 30 * 1024 * 1024;
        this.context = context.getApplicationContext();
        this.diskCache = new Cache(new File(this.context.getCacheDir(), "network"), maxDiskCacheSize);
        this.connectTimeout = 10000;
        this.connectionPool = new ConnectionPool(50, 60, TimeUnit.SECONDS);
        certificates = null;
        baseUrl = null;
    }

    public boolean getIsDiskCache() {
        return this.isDiskCache;
    }

    public NetWorkConfiguration isCache(boolean isCache) {
        this.isCache = isCache;
        return this;
    }

    public boolean getIsCache() {
        return this.isCache;
    }

    public NetWorkConfiguration isDiskCache(boolean diskcache) {
        this.isDiskCache = diskcache;
        return this;
    }

    /**
     * 是否进行内存缓存
     *
     * @param memorycache
     * @return
     */
    public NetWorkConfiguration isMemoryCache(boolean memorycache) {
        this.isMemoryCache = memorycache;
        return this;
    }

    public boolean getIsMemoryCache() {
        return this.isMemoryCache;
    }

    /**
     * 设置内存缓存时间
     *
     * @param memorycachetime
     * @return
     */
    public NetWorkConfiguration memoryCacheTime(int memorycachetime) {
        if (memorycachetime <= 0) {

            Log.e(TAG, " configure memoryCacheTime  exception!");
            return this;
        }
        this.memoryCacheTime = memorycachetime;
        return this;
    }

    public int getmemoryCacheTime() {
        return this.memoryCacheTime;
    }

    /**
     * 设置本地缓存时间
     *
     * @param diskcahetime
     * @return
     */
    public NetWorkConfiguration diskCacheTime(int diskcahetime) {
        if (diskcahetime <= 0) {
            Log.e(TAG, " configure diskCacheTime  exception!");
            return this;
        }
        this.diskCacheTime = diskcahetime;
        return this;
    }

    public int getDiskCacheTime() {
        return this.diskCacheTime;
    }

    /**
     * 设置本地缓存路径以及 缓存大小
     *
     * @param saveFile         本地路径
     * @param maxDiskCacheSize 大小
     * @return
     */
    public NetWorkConfiguration diskCaChe(File saveFile, int maxDiskCacheSize) {
        if (!saveFile.exists() && maxDiskCacheSize <= 0) {
            Log.e(TAG, " configure connectTimeout  exception!");
            return this;
        }
        diskCache = new Cache(saveFile, maxDiskCacheSize);
        return this;
    }

    public Cache getDiskCache() {
        return this.diskCache;
    }

    /**
     * 设置网络超时时间
     *
     * @param timeout
     * @return
     */
    public NetWorkConfiguration connectTimeOut(int timeout) {
        if (connectTimeout <= 0) {
            Log.e(TAG, " configure connectTimeout  exception!");
            return this;
        }
        this.connectTimeout = timeout;
        return this;
    }

    public int getConnectTimeOut() {
        return this.connectTimeout;
    }

    /**
     * 设置网络线程池
     *
     * @param connectionCount 线程个数
     * @param connectionTime  连接时间
     * @param unit            时间单位
     * @return
     */
    public NetWorkConfiguration connectionPool(int connectionCount, int connectionTime, TimeUnit unit) {
        /**
         *    线程池 线程个数和连接时间设置过小
         */
        if (connectionCount <= 0 && connectionTime <= 0) {
            Log.e(TAG, " configure connectionPool  exception!");
            return this;
        }
        this.connectionPool = new ConnectionPool(connectionCount, connectionTime, unit);
        return this;
    }

    public ConnectionPool getConnectionPool() {
        return this.connectionPool;
    }

    /**
     * 设置Https客户端带证书访问
     *
     * @param certificates
     * @return
     */
    public NetWorkConfiguration certificates(InputStream... certificates) {
        if (certificates != null) {
            this.certificates = certificates;
        } else {
            Log.e(TAG, "no  certificates");
        }
        return this;
    }

    public InputStream[] getCertificates() {
        return this.certificates;
    }

    /**
     * 设置网络BaseUrl地址
     *
     * @param url
     * @return
     */
    public NetWorkConfiguration baseUrl(String url) {
        if (url != null) {
            this.baseUrl = url;
        } else {
            Log.e(TAG, "NetWorkConfiguration no  baseUrl");
        }
        return this;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    @Override
    public String toString() {
        return "NetWorkConfiguration{" +
                "isCache=" + isCache +
                ", isDiskCache=" + isDiskCache +
                ", isMemoryCache=" + isMemoryCache +
                ", memoryCacheTime=" + memoryCacheTime +
                ", diskCacheTime=" + diskCacheTime +
                ", maxDiskCacheSize=" + maxDiskCacheSize +
                ", diskCache=" + diskCache +
                ", connectTimeout=" + connectTimeout +
                ", connectionPool=" + connectionPool +
                ", certificates=" + Arrays.toString(certificates) +
                ", context=" + context +
                ", baseUrl='" + baseUrl + '\'' +
                '}';
    }
}
