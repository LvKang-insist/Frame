package com.admin.work.main.sort.content;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Copyright (C)
 *
 * @file: SectionBean
 * @author: 345
 * @Time: 2019/5/4 10:27
 * @description: 分组布局：实体类必须继承自SectionEntity
 */
public class SectionBean extends SectionEntity<SectionContentItemEntity> {

    private boolean mIsMore = false;
    private int mId = -1;

    public boolean isIsMore() {
        return mIsMore;
    }

    public void setIsMore(boolean mIsMore) {
        this.mIsMore = mIsMore;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

     SectionBean(SectionContentItemEntity sectionContentItemEntity) {
        super(sectionContentItemEntity);
    }

    /**
     * @param isHeader 是否为标题
     * @param header 标题
     */
    SectionBean(boolean isHeader, String header) {
        super(isHeader, header);
    }
}
