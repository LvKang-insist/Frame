package com.admin.core.ui.refresh;

/**
 * Copyright (C)
 *
 * @file: PagingBean
 * @author: 345
 * @Time: 2019/4/27 20:51
 * @description: $
 */
public final class PagingBean {
    /**
     * 当前是第几页
     */
    private int mPageIndex = 0;
    /**
     * 总数据条数
     */
    private int mTotal = 0;
    /**
     * 一页显示几条数据
     */
    private int mPageSize = 0;
    /**
     * 当前已经显示了几条数据
     */
    private int mCurrentCount = 0;
    /**
     * 当前
     */
    private int Delayed = 0;

    public int getPageIndex() {
        return mPageIndex;
    }

    public PagingBean setPageIndex(int mPageIndex) {
        this.mPageIndex = mPageIndex;
        return this;
    }

    public int getmTotal() {
        return mTotal;
    }

    public PagingBean setTotal(int mTotal) {
        this.mTotal = mTotal;
        return this;
    }

    public int getPageSize() {
        return mPageSize;
    }

    public PagingBean setPageSize(int mPageSize) {
        this.mPageSize = mPageSize;
        return this;
    }

    public int getCurrentCount() {
        return mCurrentCount;
    }

    public PagingBean setCurrentCount(int mCurrentCount) {
        this.mCurrentCount = mCurrentCount;
        return this;
    }

    public int getDelayed() {
        return Delayed;
    }

    public PagingBean setDelayed(int delayed) {
        Delayed = delayed;
        return this;
    }

    PagingBean addIndex(){
        mPageIndex++;
        return this;
    }
}
