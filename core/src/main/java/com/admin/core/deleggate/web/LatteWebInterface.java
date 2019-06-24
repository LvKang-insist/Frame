package com.admin.core.deleggate.web;

import android.webkit.JavascriptInterface;

import com.admin.core.deleggate.web.event.Event;
import com.admin.core.deleggate.web.event.EventManager;
import com.alibaba.fastjson.JSON;


/**
 * Copyright (C)
 *
 * @file: LatteWebInterface
 * @author: 345
 * @Time: 2019/5/4 15:45
 * @description: ${DESCRIPTION}
 */
public class LatteWebInterface {
    private final WebDelegate DELEGATE;


    public LatteWebInterface(WebDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }

    static LatteWebInterface crate(WebDelegate delegate) {
        return new LatteWebInterface(delegate);
    }

    @JavascriptInterface
    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        if (event!= null){
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;
    }
}
