package com.admin.work.main.sort.content;

/**
 * Copyright (C)
 *
 * @file: SectionContentItemEntity
 * @author: 345
 * @Time: 2019/5/4 10:28
 * @description: 组内的商品信息
 */
public class SectionContentItemEntity {
    private int mGoodsId = 0;
    private String mGoodsName = null;
    private String mGoodsThumb = null;

    public String getGoodsThumb() {
        return mGoodsThumb;
    }

    public void setGoodsThumb(String mGoodsThumb) {
        this.mGoodsThumb = mGoodsThumb;
    }

    public int getGoodsId() {
        return mGoodsId;
    }

    public void setGoodsId(int goodsId) {
        this.mGoodsId = goodsId;
    }

    public String getGoodsName() {
        return mGoodsName;
    }

    public void setGoodsName(String mGoodsName) {
        this.mGoodsName = mGoodsName;
    }
}
