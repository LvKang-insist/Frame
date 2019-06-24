package com.admin.core.deleggate.web.event;

import android.util.Log;

/**
 * Copyright (C)
 *
 * @file: UndefineEvent
 * @author: 345
 * @Time: 2019/5/5 8:39
 * @description: ${DESCRIPTION}
 */
public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        Log.e("UndefineEvent", params );
        return null;
    }
}
