package com.admin.work.main.discover;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.admin.core.deleggate.bottom.BottomItemDelegate;
import com.admin.core.deleggate.web.WebDelegateImpl;
import com.admin.work.R;


/**
 * Copyright (C)
 *
 * @file: DiscoverDelegate
 * @author: 345
 * @Time: 2019/5/4 15:17
 * @description: ${DESCRIPTION}
 */
public class DiscoverDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegata_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @SuppressWarnings("AlibabaRemoveCommentedCode")
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
//        final WebDelegateImpl delegate = WebDelegateImpl.create("http://47.106.101.44:8080/data/html/index.html");
        final WebDelegateImpl delegate = WebDelegateImpl.create("https://www.baidu.com");
        delegate.setTopDelegate(getParentDelegate());
        getSupportDelegate().loadRootFragment(R.id.web_discovery_container,delegate);
    }
}
