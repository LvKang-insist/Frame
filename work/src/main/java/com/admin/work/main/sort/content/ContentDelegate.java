package com.admin.work.main.sort.content;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.admin.core.deleggate.LatteDelegate;
import com.admin.work.R;
import com.admin.work.R2;

import java.util.List;

import butterknife.BindView;

/**
 * Copyright (C)
 *
 * @file: ContentDelegate
 * @author: 345
 * @Time: 2019/4/30 15:50
 * @description: ${DESCRIPTION}
 */
public class ContentDelegate extends LatteDelegate {

    private static final String ARG_CONTENT_ID = "CONTENT_ID";
    private int mContentId = -1;

    private List<SectionBean> mData = null;

    @BindView(R2.id.rv_list_content)
    RecyclerView mRecyclerView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mContentId = args.getInt(ARG_CONTENT_ID);
        }
    }

    /**
     * @param contentId 根据id 显示内容
     * @return 返回当前类实例
     */
    public static ContentDelegate newInstance(int contentId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID, contentId);
        final ContentDelegate delegate = new ContentDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_list_content;
    }
    private void initData(){

    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        initData();
    }
}
