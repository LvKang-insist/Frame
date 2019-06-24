package com.admin.work.main.personal;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.core.deleggate.bottom.BottomItemDelegate;
import com.admin.work.R;
import com.admin.work.R2;
import com.admin.work.main.personal.addres.AddressDelegate;
import com.admin.work.main.personal.list.ListAdapter;
import com.admin.work.main.personal.list.ListBean;
import com.admin.work.main.personal.list.ListItemType;
import com.admin.work.main.personal.order.OrderListDelegate;
import com.admin.work.main.personal.profile.UserProfileDelegate;
import com.admin.work.main.personal.settings.SettingsDelegate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Copyright (C)
 *
 * @file: PersonalDelegate
 * @author: 345
 * @Time: 2019/5/8 10:13
 * @description: 我的
 */
public class PersonalDelegate extends BottomItemDelegate {


    @BindView(R2.id.rv_personal_setting)
    RecyclerView mRvSettings = null;

    public static final String ORDER_TYPE = "ORDER_TYPE";
    private Bundle mArgs = null;


    @OnClick(R2.id.tv_all_order)
    void onClickAllOrder(){
        mArgs.putString(ORDER_TYPE,"all");
        startOrderListByType();
    }

    @OnClick(R2.id.img_user_avatar)
    void onClickAvatar(){
        getParentDelegate().getSupportDelegate().start(new UserProfileDelegate());
    }

    private void startOrderListByType(){
        OrderListDelegate delegate = new OrderListDelegate();
        delegate.setArguments(mArgs);
        getParentDelegate().getSupportDelegate().start(delegate);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArgs = new Bundle();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final ListBean address = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(1)
                .setDelegate(new AddressDelegate())
                .setText("收获地址")
                .build();

        final ListBean system = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setDelegate(new SettingsDelegate())
                .setText("系统设置")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(address);
        data.add(system);

        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRvSettings.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRvSettings.setAdapter(adapter);
        mRvSettings.addOnItemTouchListener(new PersonalClickListener(this));
    }
}
