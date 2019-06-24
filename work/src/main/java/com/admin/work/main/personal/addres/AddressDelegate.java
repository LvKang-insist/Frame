package com.admin.work.main.personal.addres;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.core.deleggate.LatteDelegate;
import com.admin.core.net.RestClient;
import com.admin.core.net.callback.ISuccess;
import com.admin.core.ui.recycler.MultipleItemEntity;
import com.admin.core.util.dimen.SetToolBar;
import com.admin.work.R;
import com.admin.work.R2;

import java.util.List;

import butterknife.BindView;


/**
 * Copyright (C)
 *
 * @file: AddressDelegate
 * @author: 345
 * @Time: 2019/5/11 11:29
 * @description: ${DESCRIPTION}
 */
public class AddressDelegate extends LatteDelegate implements ISuccess {

    @BindView(R2.id.rv_address)
    RecyclerView mRecyclerView = null;

    @BindView(R2.id.address_toolbar)
    Toolbar mToolbar = null;


    @Override
    public Object setLayout() {
        return R.layout.delegate_address;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        SetToolBar.setToolBar(mToolbar);
        RestClient.builder()
                .url("address.json")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final List<MultipleItemEntity> data  = new AddressDataConverter()
                .setJsonData(response)
                .convert();
        final AddressAdapter adapter = new AddressAdapter(data);
        mRecyclerView.setAdapter(adapter);
    }
}
