package com.admin.work.main.personal.order;

import android.view.View;

import com.admin.core.deleggate.LatteDelegate;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;


/**
 * Copyright (C)
 *
 * @file: OrderListClickListener
 * @author: 345
 * @Time: 2019/5/12 11:09
 * @description: ${DESCRIPTION}
 */
public class OrderListClickListener extends SimpleClickListener {

    private LatteDelegate DELEGATE;

    public OrderListClickListener(LatteDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        DELEGATE.getSupportDelegate().start(new OrderCommentDelegate());
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
