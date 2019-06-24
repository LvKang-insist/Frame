package com.admin.work.main.personal.settings;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.core.deleggate.LatteDelegate;
import com.admin.core.util.callback.CallBackType;
import com.admin.core.util.callback.CallbackManager;
import com.admin.core.util.callback.IGlobalCallback;
import com.admin.core.util.dimen.SetToolBar;
import com.admin.work.R;
import com.admin.work.R2;
import com.admin.work.main.personal.list.ListAdapter;
import com.admin.work.main.personal.list.ListBean;
import com.admin.work.main.personal.list.ListItemType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Copyright (C)
 *
 * @file: SettingsDelegate
 * @author: 345
 * @Time: 2019/5/11 21:13
 * @description:  设置页面
 */
public class SettingsDelegate extends LatteDelegate {

    @BindView(R2.id.settings_toolbar)
    Toolbar mToolbar = null;
    @BindView(R2.id.rv_settings)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_settings;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        SetToolBar.setToolBar(mToolbar);
        final ListBean push = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_SWTCH)
                .setId(1)
                .setOncheckedchangelistener(new CompoundButton.OnCheckedChangeListener() {
                    @SuppressWarnings("unchecked")
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        IGlobalCallback callBack = CallbackManager.getInstance()
                                .getCallBack(CallBackType.PUSH);
                        if (callBack!= null){
                            callBack.executeCallBack(isChecked);
                        }
                    }
                })
                .setText("消息推送")
                .build();

        final ListBean about = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setDelegate(new AboutDelegate())
                .setId(2)
                .setText("关于")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(push);
        data.add(about);

        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new SettingsClickListener(this));
    }
}
