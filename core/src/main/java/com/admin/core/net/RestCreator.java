package com.admin.core.net;

import com.admin.core.app.ConfigType;
import com.admin.core.app.Latte;
import com.admin.core.net.rx.RxRestService;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Copyright (C)
 *
 * @file: RestCreator
 * @author: 345
 * @Time: 2019/4/17 19:19
 * @description: Creator：创造者
 */
public class RestCreator {
    /**
     * 参数容器
     */
    private static final class ParamsHolder{
         static final WeakHashMap<String ,Object> PARAMS = new WeakHashMap<>();
    }
    public static WeakHashMap<String,Object> getParams(){
        return ParamsHolder.PARAMS;
    }

    /**
     * 构建 全局Retrofit 客户端
     */
    private static final class RetrofitHolder{
        /**
         * 加载已经初始化的网络地址
         */
        private static final String BASE_URL = (String) Latte.getConfigurations().get(ConfigType.API_HOST.name());
        /**
         * 创建 Retrofit 的实例
         *  创建 Retrofit 的实例是需要通过 Retrofit.Builder创建，并调用baseUrl 设置 url
         */
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                //设置网络请求的 url地址
                .baseUrl(BASE_URL)
//                .client(OkHttpHolder.OK_HTTP_CLIENT)
                //依赖中引入的转换器
                .addConverterFactory(ScalarsConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    /**
     * 构建 Okhttp
     */
    private static final class OkHttpHolder{
        private static final int TIME_OUT = 60;
        public static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();

        private static final ArrayList<Interceptor> INTERCEPTORS = Latte.getConfiguration(ConfigType.INTERCEPTOR);

        private static OkHttpClient.Builder addInterceptor(){
            if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()){
                for (Interceptor interceptor : INTERCEPTORS){
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }
        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    /**
     * Service 接口
     */
    private static final class RestServiceHolder{
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }
    /**
     * @return 返回网络接口请求的实例
     */
    public static RestService getRestService(){
        return RestServiceHolder.REST_SERVICE;
    }

    /**
     * RxService 接口
     */
    private static final class RxRestServiceHolder{
        //创建 网络请求接口 的实例
        private static final RxRestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RxRestService.class);
    }

    public static RxRestService getRxRestService(){
        return RxRestServiceHolder.REST_SERVICE;
    }
}
