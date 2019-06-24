package com.admin.core.net.rx;

import android.annotation.SuppressLint;

import com.admin.core.util.storage.LattePreference;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Copyright (C)
 *
 * @file: AddCookieInterceptor
 * @author: 345
 * @Time: 2019/5/5 10:39
 * @description: ${DESCRIPTION}
 */
public final class AddCookieInterceptor implements Interceptor {
    @SuppressLint("CheckResult")
    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        Observable.just(LattePreference.getCustomAppProfile("cookie"))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String cookie) throws Exception {
                        //给原生的 API 请求附带上WebView拦截下来的Cookie
                        builder.addHeader("Cookie",cookie);
                    }
                });
        return chain.proceed(builder.build());
    }
}
