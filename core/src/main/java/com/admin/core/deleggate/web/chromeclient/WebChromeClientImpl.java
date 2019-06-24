package com.admin.core.deleggate.web.chromeclient;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Copyright (C)
 *
 * @file: WebChromeClientImpl
 * @author: 345
 * @Time: 2019/5/4 17:15
 * @description: ${DESCRIPTION}
 */
public class WebChromeClientImpl extends WebChromeClient {
    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return super.onJsAlert(view, url, message, result);
    }

}
