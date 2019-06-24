package com.admin.work.main.personal;

import android.view.View;

import com.admin.core.deleggate.LatteDelegate;
import com.admin.work.main.personal.list.ListBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

/**
 * Copyright (C)
 *
 * @file: PersonalClickListener
 * @author: 345
 * @Time: 2019/5/11 15:46
 * @description: ${DESCRIPTION}
 */
public class PersonalClickListener extends SimpleClickListener {

    private final LatteDelegate DELEGATE;

    public PersonalClickListener(LatteDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        int id = bean.getId();
        switch (id) {
            case 1:
                DELEGATE.getParentDelegate().getSupportDelegate().start(bean.getDelegate());
                break;
            case 2:
                DELEGATE.getParentDelegate().getSupportDelegate().start(bean.getDelegate());
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
