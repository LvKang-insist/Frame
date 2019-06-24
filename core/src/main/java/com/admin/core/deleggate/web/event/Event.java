package com.admin.core.deleggate.web.event;

import android.content.Context;
import android.webkit.WebView;

import com.admin.core.deleggate.web.WebDelegate;

/**
 * Copyright (C)
 *
 * @file: Event
 * @author: 345
 * @Time: 2019/5/5 8:34
 * @description: ${DESCRIPTION}
 */
public abstract class Event implements IEvent {
    private Context mContext = null;
    private String mAction = null;
    private WebDelegate mDelegate = null;
    private String mUrl = null;
    private WebView mWebView = null;

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String mAction) {
        this.mAction = mAction;
    }

    public WebDelegate getDelegate() {
        return mDelegate;
    }

    public void setDelegate(WebDelegate mDelegate) {
        this.mDelegate = mDelegate;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public WebView getWebView() {
        return mDelegate.getWebView();
    }

    public void setWebView(WebView mWebView) {
        this.mWebView = mWebView;
    }
}
