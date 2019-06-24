package com.admin.core.deleggate.web;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.admin.core.deleggate.web.chromeclient.WebChromeClientImpl;
import com.admin.core.deleggate.web.client.WebViewClientImpl;
import com.admin.core.deleggate.web.route.RouteKeys;
import com.admin.core.deleggate.web.route.Router;

/**
 * Copyright (C)
 *
 * @file: WebDelegateImpl
 * @author: 345
 * @Time: 2019/5/4 16:01
 * @description: ${DESCRIPTION}
 */
public class WebDelegateImpl  extends WebDelegate{

    private IPageLoadListener mIPageLoadListener = null;
    /**
     * @param url webView 的显示的内容地址
     * @return 放回当前碎片的对象。
     */
    public static WebDelegateImpl create(String url){
        final Bundle args= new Bundle();
        args.putString(RouteKeys.URL.name(),url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }
    @Override
    public Object setLayout() {
        return getWebView();
    }

    public void setPageLoadListener(IPageLoadListener loadListener){
        this.mIPageLoadListener = loadListener;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        if (getUrl() != null){
            //用原生的 方式模拟web跳转并进行页面加载
            Router.getInstance().loadPage(this,getUrl());
        }
    }

    /**
     * @return 返回IWebViewInitializer 接口的实例
     */
    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    // 实现 IWebViewInitializer 接口的三个方法,
    /**
     * 初始化WebView
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().createWebView(webView);
    }
    /**
     *  处理webView 的各种事件
     */
    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl client = new WebViewClientImpl(this);
        //传入 IPageLoadListener 接口的实例
        client.setPageLoadListener(mIPageLoadListener);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }
}
