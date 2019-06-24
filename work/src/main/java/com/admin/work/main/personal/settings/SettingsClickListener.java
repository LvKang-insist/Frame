package com.admin.work.main.personal.settings;

import android.view.View;

import com.admin.core.deleggate.LatteDelegate;
import com.admin.work.main.personal.list.ListBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;


/**
 * Copyright (C)
 *
 * @file: SettingsClickListener
 * @author: 345
 * @Time: 2019/5/12 10:02
 * @description: ${DESCRIPTION}
 */
public class SettingsClickListener extends SimpleClickListener {

    private final LatteDelegate DELEGATE;

    public SettingsClickListener(LatteDelegate delegate) {
        this.DELEGATE = delegate;
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        int id = bean.getId();
        switch (id) {
            case 1:

                break;
            case 2:
                DELEGATE.getSupportDelegate().start(bean.getDelegate());
                break;
            default:
                break;
        }
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
