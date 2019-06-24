package com.admin.work.main.personal.order;

import android.annotation.SuppressLint;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.admin.core.ui.recycler.MultipleFields;
import com.admin.core.ui.recycler.MultipleItemEntity;
import com.admin.core.ui.recycler.MultipleRecyclerAdapter;
import com.admin.core.ui.recycler.MultipleViewHolder;
import com.admin.work.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Copyright (C)
 *
 * @file: OrderListAdapter
 * @author: 345
 * @Time: 2019/5/8 13:51
 * @description: ${DESCRIPTION}
 */
public class OrderListAdapter extends MultipleRecyclerAdapter {

    protected OrderListAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(OrderListItemType.ITEM_ORDER_LIST, R.layout.item_order_list);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case OrderListItemType.ITEM_ORDER_LIST:
                final AppCompatImageView imageView = holder.getView(R.id.image_order_list);
                final AppCompatTextView title = holder.getView(R.id.tv_order_list_title);
                final AppCompatTextView price= holder.getView(R.id.tv_order_list_price);
                final AppCompatTextView time = holder.getView(R.id.tv_order_list_time);

                final String titleValue = entity.getField(MultipleFields.TITLE);
                final String timeVal = entity.getField(OrderItemFilelds.TIME);
                final double priceVal = entity.getField(OrderItemFilelds.PRICE);
                final String imageUrl = entity.getField(MultipleFields.IMAGE_URL);

                Glide.with(mContext)
                        .load(imageUrl)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                        .dontAnimate()
                        .into(imageView);

                title.setText(titleValue);
                price.setText("价格: "+String.valueOf(priceVal));
                time.setText("时间: "+String.valueOf(timeVal));
                break;
            default:
                break;
        }
    }
}
