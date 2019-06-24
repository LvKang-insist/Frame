package com.admin.core.deleggate.web;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.RequiresApi;

/**
 * Copyright (C)
 *
 * @file: WebViewInitializer
 * @author: 345
 * @Time: 2019/5/4 17:02
 * @description: 初始化 WebView
 */
public class WebViewInitializer {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint({"NewApi", "SetJavaScriptEnabled"})
    public WebView createWebView(WebView webView){
        //是否允许web 内容调试
        WebView.setWebContentsDebuggingEnabled(true);

        //cookie
        final CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.setAcceptThirdPartyCookies(webView,true);
        CookieManager.setAcceptFileSchemeCookies(true);

        //不能横向滚动
        webView.setHorizontalScrollBarEnabled(true);
        //不能纵向滚动
        webView.setVerticalScrollBarEnabled(false);
        //允许截图
        webView.setDrawingCacheEnabled(true);
        //屏蔽长按事件
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

        //初始化 WebSettings
        final WebSettings settings = webView.getSettings();
        //开放js 的通道
        settings.setJavaScriptEnabled(true);
        final String ua = settings.getUserAgentString();
        settings.setUserAgentString(ua+"Latte");

        //设置自适应屏幕，两者结合使用
        //将图片调整大小适合 webView 的大小
        settings.setUseWideViewPort(true);
        //缩放至屏幕的大小
        settings.setLoadWithOverviewMode(true);


        // 缩放操作
        //支持缩放，默认为true,下面的那个是前提
         settings.setSupportZoom(true);
         //设置内置的缩放控件,若为false，则webView 不可缩放
         settings.setBuiltInZoomControls(true);
         //隐藏原生的缩放控件
         settings.setDisplayZoomControls(false);

        //文件权限
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setAllowContentAccess(true);
        //缓存相关
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        return webView;
    }
}
