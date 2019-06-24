package com.admin.core.ui.refresh;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.admin.core.net.RestClient;
import com.admin.core.net.callback.ISuccess;
import com.admin.core.ui.recycler.DataConverter;
import com.admin.core.ui.recycler.MultipleRecyclerAdapter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;


/**
 * Copyright (C)
 *
 * @file: RefreshHander
 * @author: 345
 * @Time: 2019/4/27 13:16
 * @description: 这是SwipeRefreshLayout内部的一个接口，用来监听 Refresh 的操作
 */
public class RefreshHander implements
        SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {

    private int mCount = 0;

    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PagingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private MultipleRecyclerAdapter mAdapter = null;
    private final DataConverter CONVERTER;


    private RefreshHander(SwipeRefreshLayout refreshLayout,
                          RecyclerView recyclerView,
                          DataConverter converter,
                          PagingBean bean) {
        REFRESH_LAYOUT = refreshLayout;
        //监听滑动事件
        REFRESH_LAYOUT.setOnRefreshListener(this);
        this.RECYCLERVIEW = recyclerView;
        this.CONVERTER = converter;
        this.BEAN = bean;
    }

    public static RefreshHander creawte(SwipeRefreshLayout swipeRefreshLayout,
                                        RecyclerView recyclerView,
                                        DataConverter converter) {
        return new RefreshHander(swipeRefreshLayout, recyclerView, converter, new PagingBean());

    }

    private void refresh() {
        //要开始加载了
        firstPage("index.json");
    }
    public void firstPage(String url) {
        BEAN.setDelayed(1000);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        if (REFRESH_LAYOUT.isRefreshing()) {
                            REFRESH_LAYOUT.setRefreshing(false);
                        }
                        if (mAdapter != null) {
                            if (mAdapter.getData().size() > 0) {
                                mAdapter.getData().clear();
                            }
                        }
                        final JSONObject object = JSON.parseObject(response);
                        BEAN.setTotal(object.getInteger("total"))
                                .setPageSize(object.getInteger("page_size"));
                        //设置Adapter
                        mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(response));
                        //该接口已经在本类中实现,当滑动到在最后一个item时回调
                        mAdapter.setOnLoadMoreListener(RefreshHander.this, RECYCLERVIEW);
                        //设置适配器
                        RECYCLERVIEW.setAdapter(mAdapter);
                        BEAN.addIndex();
                        mAdapter.loadMoreComplete();
                    }
                })
                .build()
                .get();
    }

    private void paging(String url) {
        final int pageSize = BEAN.getPageSize();
        final int currentCount = BEAN.getCurrentCount();
        final int total = BEAN.getmTotal();
        final int index = BEAN.getPageIndex();

        if (index > 2) {
            mAdapter.loadMoreEnd(false);
        } else {
            RestClient.builder()
                    .url(url + index + ".json")
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            mAdapter.setNewData(CONVERTER.setJsonData(response).convert());
                            //累加数量
                            BEAN.setCurrentCount(mAdapter.getData().size());
                            BEAN.addIndex();

                            mAdapter.notifyItemRangeChanged(0,mAdapter.getItemCount());
                        }
                    })
                    .build()
                    .get();
        }
    }

    @Override
    public void onRefresh() {
        refresh();
    }
    /**
     * 当滑动到最后一个item时 回调
     */
    @Override
    public void onLoadMoreRequested() {
        paging("refresh");
    }
}
