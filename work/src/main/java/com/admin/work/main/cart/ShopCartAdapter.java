package com.admin.work.main.cart;

import android.graphics.Color;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.admin.core.app.Latte;
import com.admin.core.ui.recycler.MultipleFields;
import com.admin.core.ui.recycler.MultipleItemEntity;
import com.admin.core.ui.recycler.MultipleRecyclerAdapter;
import com.admin.core.ui.recycler.MultipleViewHolder;
import com.admin.work.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

/**
 * Copyright (C)
 *
 * @file: ShopCartAdapter
 * @author: 345
 * @Time: 2019/5/6 8:39
 * @description: ${DESCRIPTION}
 */
public class ShopCartAdapter extends MultipleRecyclerAdapter {

    private boolean mIsSelectedAll = false;
    private ICartItemListener mCartItemListener = null;
    private double mTotalPrice = 0.00;

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    void setCartItemListener(ICartItemListener listener){
         this.mCartItemListener = listener;
    }
    void setIsSelectedAll(boolean isSelectedAll){
        this.mIsSelectedAll = isSelectedAll;
    }

    public void createPrice(){
        mTotalPrice = 0;
        //初始化总价
        for (MultipleItemEntity entity: getData()){
            if (entity.getField(ShopCartItemFields.IS_SELECTED)) {
                final double price = entity.getField(ShopCartItemFields.PRICE);
                final int count = entity.getField(ShopCartItemFields.COUNT);
                final double total = price * count;
                mTotalPrice += total;
            }
        }
        if (mCartItemListener!= null){
            mCartItemListener.onItemClick(mTotalPrice);
        }
    }

     ShopCartAdapter(List<MultipleItemEntity> data) {
        super(data);
        //添加购物车item布局
        addItemType(ShopCartItemType.SHOP_CART_ITEM, R.layout.item_shop_cart);
    }
    @Override
    protected void convert(MultipleViewHolder holder, final MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ShopCartItemType.SHOP_CART_ITEM:
                //先取出所有值
                final int id = entity.getField(MultipleFields.ID);
                final String thumb = entity.getField(MultipleFields.IMAGE_URL);
                final String title = entity.getField(ShopCartItemFields.TITLE);
                final String desc = entity.getField(ShopCartItemFields.DESC);
                final int count = entity.getField(ShopCartItemFields.COUNT);
                final double price = entity.getField(ShopCartItemFields.PRICE);

                //取出所有控件
                final AppCompatImageView imgThumb = holder.getView(R.id.image_item_shop_cart);
                final AppCompatTextView tvTitle = holder.getView(R.id.tv_item_shop_cart_title);
                final AppCompatTextView tvDesc = holder.getView(R.id.tv_item_shop_cart_desc);
                final AppCompatTextView tvPrice = holder.getView(R.id.tv_item_shop_cart_price);
                final IconTextView iconMinus = holder.getView(R.id.icon_item_minus);
                final IconTextView iconPlus = holder.getView(R.id.icon_item_plus);
                final AppCompatTextView tvCount = holder.getView(R.id.tv_item_shop_cart_count);
                final IconTextView iconIsSelected = holder.getView(R.id.icon_item_shop_cart);
                //赋值
                tvTitle.setText(title);
                tvDesc.setText(desc);
                tvCount.setText(String.valueOf(count));
                tvPrice.setText(String.valueOf(price*count));
                Glide.with(mContext)
                        .load(thumb)
                        .into(imgThumb);

                //在左侧勾勾渲染之前改变全选与否的状态
                entity.setField(ShopCartItemFields.IS_SELECTED,mIsSelectedAll);
                final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);

                //根据数据状态显示 左侧勾勾
                if (isSelected) {
                    iconIsSelected.setTextColor(ContextCompat.getColor(Latte.getApplication(), R.color.app_main));
                } else {
                    iconIsSelected.setTextColor(Color.GRAY);
                }
                //添加左侧 勾勾点击事件
                iconIsSelected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final boolean currentSelect = entity.getField(ShopCartItemFields.IS_SELECTED);
                        if (currentSelect) {
                            iconIsSelected.setTextColor(Color.GRAY);
                            entity.setField(ShopCartItemFields.IS_SELECTED, false);
                        } else {
                            iconIsSelected.setTextColor(ContextCompat.getColor(Latte.getApplication(), R.color.app_main));
                            entity.setField(ShopCartItemFields.IS_SELECTED, true);
                        }
                        createPrice();
                    }
                });
                //添加加减事件
                iconMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int count = entity.getField(ShopCartItemFields.COUNT);
                        if (count >1){
                            count--;
                            tvCount.setText(String.valueOf(count));
                            entity.setField(ShopCartItemFields.COUNT,count);
                            createPrice();
                            if (mCartItemListener != null){
                                final double itemTotal = count* price;
                                tvPrice.setText(String.valueOf(itemTotal));
                                mCartItemListener.onItemClick(mTotalPrice);
                            }
                        }
                    }
                });
                iconPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int count = entity.getField(ShopCartItemFields.COUNT);
                        if (count >=1){
                            count++;
                            tvCount.setText(String.valueOf(count));
                            entity.setField(ShopCartItemFields.COUNT,count);
                            createPrice();
                            if (mCartItemListener != null){
                                final double itemTotal = count* price;
                                tvPrice.setText(String.valueOf(itemTotal));
                                mCartItemListener.onItemClick(mTotalPrice);
                            }
                        }
                    }
                });
                break;
            default:
                break;
        }
    }
}
