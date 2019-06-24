package com.admin.work.main.sort;

import android.os.Bundle;import android.view.View;

import androidx.annotation.Nullable;

import com.admin.core.deleggate.bottom.BottomItemDelegate;
import com.admin.work.R;
import com.admin.work.main.sort.content.ContentDelegate;
import com.admin.work.main.sort.list.VerticalListDelegate;


/**
 * Copyright (C)
 *
 * @file: SortDelegate
 * @author: 345
 * @Time: 2019/4/26 14:36
 * @description: 分类页面：共三个delegate，
 */
public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        final VerticalListDelegate listDelegate = new VerticalListDelegate();
        //左边的delegate
        getSupportDelegate().loadRootFragment(R.id.vertical_list_container,listDelegate);
        //设置右侧第一个分类显示，默认显示分类一
       getSupportDelegate(). loadRootFragment(R.id.sort_list_container, ContentDelegate.newInstance(1));
    }
}
