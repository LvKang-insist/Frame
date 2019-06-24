package com.admin.core.deleggate.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.annotation.Nullable;

import com.admin.core.app.ConfigType;
import com.admin.core.app.Latte;
import com.admin.core.deleggate.LatteDelegate;
import com.admin.core.deleggate.web.route.RouteKeys;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Copyright (C)
 *
 * @file: WebDelegate
 * @author: 345
 * @Time: 2019/5/4 15:25
 * @description: ${DESCRIPTION}
 */
public abstract class WebDelegate extends LatteDelegate implements IWebViewInitializer {

    private WebView mWebView = null;
    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue<>();

    private String mUrl = null;
    /**
     * WebView 的状态
     */
    private boolean mIsWebViewAbailable = false;
    private LatteDelegate mTopDelegate = null;


    public WebDelegate() {
    }

    public abstract IWebViewInitializer setInitializer();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mUrl = args.getString(RouteKeys.URL.name());
        initWebView();
    }

    @SuppressLint({"JavascriptInterface", "AddJavascriptInterface"})
    private void initWebView() {
        if (mWebView != null) {
            //调用此方法从ViewGroup中删除所有子视图。
            mWebView.removeAllViews();
            //销毁webView
            mWebView.destroy();
        } else {
            final IWebViewInitializer initializer = setInitializer();
            if (initializer != null) {
                //软引用，避免内存泄露，创建WebView 的对象
                final WeakReference<WebView> webViewWeakReference =
                        new WeakReference<>(new WebView(getContext()), WEB_VIEW_QUEUE);
                mWebView = webViewWeakReference.get();
                //调用接口的方法,
                //初始化WebView
                mWebView = initializer.initWebView(mWebView);
                //处理各种通知，请求事件
                mWebView.setWebViewClient(initializer.initWebViewClient());
                mWebView.setWebChromeClient(initializer.initWebChromeClient());

                final String name = Latte.getConfiguration(ConfigType.JAVASCRIPT_INTERFACE);
                mWebView.addJavascriptInterface(LatteWebInterface.crate(this), name);
                mIsWebViewAbailable = true;
            } else {
                throw new NullPointerException("Initializer is null");
            }
        }
    }

    /**
     *  设置碎片
     */
    public void setTopDelegate(LatteDelegate delegate) {
        mTopDelegate = delegate;
    }
    /**
     * 获取碎片
     */
    public LatteDelegate getTopDelegate(){
        if (mTopDelegate ==null){
            mTopDelegate = this;
        }
        return mTopDelegate;
    }

    /**
     *  获取 webView
     */
    public WebView getWebView() {
        if (mWebView == null) {
            throw new NullPointerException("WebView IS NULL");
        }
        return mIsWebViewAbailable ? mWebView : null;
    }

    public String getUrl() {
        if (mUrl == null) {
            throw new NullPointerException("WebView IS NULL");
        }
        return mUrl;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebViewAbailable = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }


}
