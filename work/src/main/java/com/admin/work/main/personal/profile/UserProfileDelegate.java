package com.admin.work.main.personal.profile;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.core.deleggate.LatteDelegate;
import com.admin.work.R;
import com.admin.work.R2;
import com.admin.work.main.personal.list.ListAdapter;
import com.admin.work.main.personal.list.ListBean;
import com.admin.work.main.personal.list.ListItemType;
import com.admin.work.main.personal.settings.NameDelegate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Copyright (C)
 *
 * @file: UserProfileDelegate
 * @author: 345
 * @Time: 2019/5/8 19:32
 * @description: 个人中心
 */
public class UserProfileDelegate extends LatteDelegate {


    @BindView(R2.id.rv_user_profile)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_user_profileq;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final ListBean image = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_AVATAR)
                .setId(1)
                .setImageUrl("http://47.106.101.44:8080/data/friends.png")
                .build();

        final ListBean name = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setDelegate(new NameDelegate())
                .setText("姓名")
                .setValue("张三")
                .build();

        final ListBean gender = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(3)
                .setText("性别")
                .setValue("未设置")
                .build();

        final ListBean birth = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(4)
                .setText("生日")
                .setValue("未设置")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(image);
        data.add(name);
        data.add(gender);
        data.add(birth);

        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRecyclerView.setAdapter(adapter);

        //点击事件
        mRecyclerView.addOnItemTouchListener(new UserProfileClickListener(this));
    }
}
