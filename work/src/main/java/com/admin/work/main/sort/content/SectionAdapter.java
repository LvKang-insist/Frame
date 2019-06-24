package com.admin.work.main.sort.content;


import androidx.appcompat.widget.AppCompatImageView;

import com.admin.work.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Copyright (C)
 *
 * @file: SectionAdapter
 * @author: 345
 * @Time: 2019/5/4 10:47
 * @description: RecyclerView分组 适配器
 */
public class SectionAdapter extends BaseSectionQuickAdapter<SectionBean, BaseViewHolder> {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      每个组的 item id
     * @param sectionHeadResId 每个组的 标题 id
     * @param data             数据
     */
    SectionAdapter(int layoutResId, int sectionHeadResId, List<SectionBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }


    /**
     * 头部
     */
    @Override
    protected void convertHead(BaseViewHolder helper, SectionBean item) {
        helper.setText(R.id.header, item.header);
        //是否显示加载更多
        helper.setVisible(R.id.more, item.isIsMore());
        helper.addOnClickListener(R.id.more);
    }

    /**
     * item
     */
    @Override
    protected void convert(BaseViewHolder helper, SectionBean item) {
        // item.t 返回SectionBean 类型
        final String thumb = item.t.getGoodsThumb();
        final String name = item.t.getGoodsName();
        final String goodsId = item.t.getGoodsThumb();

        final SectionContentItemEntity entity = item.t;
        helper.setText(R.id.tv, name);
        final AppCompatImageView goodsImageView = helper.getView(R.id.iv);

        Glide.with(mContext)
                .load(thumb)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(goodsImageView);
    }
}
