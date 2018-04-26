package com.welcome.home.playandroid.net.http;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.welcome.home.playandroid.net.config.NetWorkConfiguration;
import com.welcome.home.playandroid.net.interceptor.LogInterceptor;
import com.welcome.home.playandroid.util.NetworkUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wuxiaoqi on 2018/4/24.
 * 网络请求工具类
 */

public class HttpUtils {

    private static final String TAG = "HttpUtils";

    private static HttpUtils mInstance;

    private OkHttpClient mOkHttpClient;

    private NetWorkConfiguration configuration;

    private Context mContext;

    public void init(Context context) {
        this.mContext = context.getApplicationContext();
        if (configuration == null) {
            configuration = new NetWorkConfiguration(context);
        }
        if (configuration.getIsCache()) {

            mOkHttpClient = new OkHttpClient.Builder()
//                   网络缓存拦截器
                    .addInterceptor(interceptor)
                    .addNetworkInterceptor(interceptor)
//                    自定义网络Log显示
                    .addInterceptor(new LogInterceptor())
                    .cache(configuration.getDiskCache())
                    .connectTimeout(configuration.getConnectTimeOut(), TimeUnit.SECONDS)
                    .connectionPool(configuration.getConnectionPool())
                    .retryOnConnectionFailure(true)
                    .build();
        } else {
            mOkHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new LogInterceptor())
                    .connectTimeout(configuration.getConnectTimeOut(), TimeUnit.SECONDS)
                    .connectionPool(configuration.getConnectionPool())
                    .retryOnConnectionFailure(true)
                    .build();

        }
    }

    public void init(Context context, String baseUrl) {
        init(context);
        configuration.baseUrl(baseUrl);
    }

    public HttpUtils setLoadDiskCache(boolean isCache) {
        if (configuration == null) {
            configuration = new NetWorkConfiguration(mContext);
        }
        configuration.isDiskCache(isCache);
        return this;
    }

    public HttpUtils setLoadMemoryCache(boolean isCache) {
        if (configuration == null) {
            configuration = new NetWorkConfiguration(mContext);
        }
        configuration.isMemoryCache(isCache);
        return this;
    }

    private HttpUtils() {
    }

    final Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            /**
             *  断网后是否加载本地缓存数据
             *
             */
            if (!NetworkUtil.isNetworkAvailable(mContext) && configuration.getIsDiskCache()) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
//            加载内存缓存数据
            else if (configuration.getIsMemoryCache()) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            /**
             *  加载网络数据
             */
            else {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_NETWORK)
                        .build();
            }
            Response response = chain.proceed(request);
//            有网进行内存缓存数据
            if (NetworkUtil.isNetworkAvailable(mContext) && configuration.getIsMemoryCache()) {
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + configuration.getmemoryCacheTime())
                        .removeHeader("Pragma")
                        .build();
            } else {
//              进行本地缓存数据
                if (configuration.getIsDiskCache()) {
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + configuration.getDiskCacheTime())
                            .removeHeader("Pragma")
                            .build();
                }
            }
            return response;
        }
    };

    /**
     * 设置网络配置参数
     *
     * @param configuration
     */
    public void setConFiguration(NetWorkConfiguration configuration) {
        if (configuration == null) {
            throw new IllegalArgumentException("ImageLoader configuration can not be initialized with null");
        } else {
            if (configuration == null) {
                Log.d(TAG, "Initialize NetWorkConfiguration with configuration");
                configuration = configuration;
            } else {
                Log.e(TAG, "Try to initialize NetWorkConfiguration which had already been initialized before. To re-init NetWorkConfiguration with new configuration ");
            }
        }
        if (configuration != null) {
            Log.i(TAG, "ConFiguration" + configuration.toString());
        }
    }

    public RetrofitClient getRetrofitClient() {
        return new RetrofitClient(configuration.getBaseUrl(), mOkHttpClient);
    }

    public static class Singleton {
        @SuppressLint("StaticFieldLeak")
        static HttpUtils instance = new HttpUtils();
    }

    public static HttpUtils getInstance() {
        return Singleton.instance;
    }

}
