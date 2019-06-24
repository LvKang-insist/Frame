package com.admin.work.main.personal.order;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.core.deleggate.LatteDelegate;
import com.admin.core.net.RestClient;
import com.admin.core.net.callback.ISuccess;
import com.admin.core.util.dimen.SetToolBar;
import com.admin.work.R;
import com.admin.work.R2;
import com.admin.work.main.personal.PersonalDelegate;

import butterknife.BindView;

/**
 * Copyright (C)
 *
 * @file: OrderListDelegate
 * @author: 345
 * @Time: 2019/5/8 14:04
 * @description: 订单管理
 */
public class OrderListDelegate extends LatteDelegate {

    private String mType = null;

    @BindView(R2.id.tb_shop_cart)
    Toolbar mToolbar = null;
    @BindView(R2.id.rv_order_list)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        assert args != null;
        mType = args.getString(PersonalDelegate.ORDER_TYPE);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        SetToolBar.setToolBar(mToolbar);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .loader(getContext())
                .url("order_list.json")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                    }
                })
                .build()
                .get();
    }
}
