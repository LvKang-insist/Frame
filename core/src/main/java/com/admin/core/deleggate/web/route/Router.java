package com.admin.core.deleggate.web.route;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import android.webkit.URLUtil;
import android.webkit.WebView;

import androidx.core.content.ContextCompat;

import com.admin.core.deleggate.LatteDelegate;
import com.admin.core.deleggate.web.WebDelegate;
import com.admin.core.deleggate.web.WebDelegateImpl;

/**
 * Copyright (C)
 *
 * @file: Router
 * @author: 345
 * @Time: 2019/5/4 16:29
 * @description: ${DESCRIPTION}
 */
public class Router {
    private Router(){}

    private static class Holder{
        private static final Router INSTANCE = new Router();
    }

    public static Router getInstance(){
        return Holder.INSTANCE;
    }

    public final boolean  handleWebUrl(WebDelegate delegate, String url){
        //如果是 电话协议
        if (url.contains("tel:")){
            callPhone(delegate.getContext(),url);
            return true;
        }
        //拿到父碎片
        final LatteDelegate topDelegate = delegate.getTopDelegate();
        final WebDelegateImpl webDelegate = WebDelegateImpl.create(url);
        topDelegate.getSupportDelegate().start(webDelegate);
        return true;
    }

    /**
     * 加载一个网页
     * @param webView
     * @param url
     */
    private void loadWebPage(WebView webView,String url){
        if (webView != null){
            webView.loadUrl(url);
        }else {
            throw new NullPointerException("WebView is null");
        }
    }

    /**
     * 将地址封装为 一个本地的url，加载本地的html 页面
     */
    private void loadLocalPage(WebView webView,String url){
        loadWebPage(webView,"file:///android_asset/"+url);
    }

    public  void loadPage(WebView webView,String url){
        // 如果url 为网络url 或者 url 为一个资源的url
        if (URLUtil.isNetworkUrl(url)|| URLUtil.isAssetUrl(url)){
            loadWebPage(webView,url);
        }else {
            loadLocalPage(webView,url);
        }
    }
    public final void loadPage(WebDelegate delegate,String url){
        loadPage(delegate.getWebView(),url);
    }


    private void callPhone(Context context ,String url){
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(url);
        intent.setData(data);
        ContextCompat.startActivity(context,intent,null);
    }
}
