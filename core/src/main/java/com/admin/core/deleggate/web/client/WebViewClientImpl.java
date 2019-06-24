package com.admin.core.deleggate.web.client;

import android.graphics.Bitmap;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.admin.core.app.ConfigType;
import com.admin.core.app.Latte;
import com.admin.core.deleggate.web.IPageLoadListener;
import com.admin.core.deleggate.web.WebDelegate;
import com.admin.core.deleggate.web.route.Router;
import com.admin.core.ui.loader.LatteLoader;
import com.admin.core.util.storage.LattePreference;


/**
 * Copyright (C)
 *
 * @file: WebViewClientImpl
 * @author: 345
 * @Time: 2019/5/4 16:26
 * @description: WebViewClient 类，处理各种通知，请求事件
 */
public class WebViewClientImpl extends WebViewClient {
    private final WebDelegate DELEGATE;

    private IPageLoadListener mIPageLoadListener = null;

    public void setPageLoadListener(IPageLoadListener loadListener) {
        this.mIPageLoadListener = loadListener;
    }

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    /**
     *      作用：打开网页时 不调用系统的浏览器，而是在WebView中显示，在网页上所有的加载都经过这个方法，
     * 这个方法我们可以做很多操作
     *      该方法会在加载一个新的网页时调用.
     * @return 返回false 表示 不消费该事件，又系统决定怎样加载新的网页
     *          返回true 表示 消费该事件，可以自定义加载该网页
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return Router.getInstance().handleWebUrl(DELEGATE, url);
//        return true;
    }

    /**
     *      作用：开始载入页面调用的，我们可以设定一个loading 页面，告诉用户程序在等待响应
     */
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadStart();
        }
        LatteLoader.showLoading(view.getContext());
    }

    /**
     * 同步 浏览器cookie
     */
    private void syncCookie() {
        final CookieManager manager = CookieManager.getInstance();
        /*
            注意，这里的Cookie 和API 请求中的Cookie是不一样的，这个在网页中不可见.
         */
        final String weHost = Latte.getConfiguration(ConfigType.WEB_HOST);
        if (weHost != null) {
            final String cookieStr = manager.getCookie(weHost);
            if (manager.hasCookies()) {
                if (cookieStr != null && !"".equals(cookieStr)) {
                    LattePreference.setCustomAppProfile("cookie", cookieStr);
                }
            }
        }
    }

    /**
     *      作用：在页面加载结束时调用，可以关闭 loading 条，切换程序动作
     * @param view
     * @param url
     */
    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        syncCookie();
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadEnd();
        }
        LatteLoader.stopLoading();
    }
}
