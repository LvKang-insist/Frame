package com.admin.work.main.cart;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.core.deleggate.bottom.BottomItemDelegate;
import com.admin.core.ui.recycler.MultipleItemEntity;
import com.admin.core.util.dimen.SetToolBar;
import com.admin.work.R;
import com.admin.work.R2;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Copyright (C)
 *
 * @file: ShopCartDelegate
 * @author: 345
 * @Time: 2019/5/5 20:23
 * @description: ${DESCRIPTION}
 */
public class ShopCartDelegate extends BottomItemDelegate
        implements ICartItemListener {
    private ShopCartAdapter mAdapter = null;

    private boolean stubFlag = false;

    @BindView(R2.id.shop_toolbar)
    Toolbar mToolbar = null;
    @BindView(R2.id.rv_shop_cart)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView mIconSelectAll = null;
    @BindView(R2.id.stub_no_item)
    ViewStubCompat mStubNoItem = null;
    @BindView(R2.id.tv_shop_cart_total_price)
    AppCompatTextView mTotalPrice = null;

    /**
     * 全选
     */
//    @OnClick(R2.id.icon_shop_cart_select_all)
    void onClickSelectAll() {
        final int tag = (int) mIconSelectAll.getTag();
        //tag 为0 表示选中状态，
        if (tag == 0) {
            mIconSelectAll.setTextColor(ContextCompat.getColor(getContext(), R.color.app_main));
            mIconSelectAll.setTag(1);
            mAdapter.setIsSelectedAll(true);
            //更新RecyclerView 的显示状态
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        } else {
            mIconSelectAll.setTextColor(Color.GRAY);
            mIconSelectAll.setTag(0);
            mAdapter.setIsSelectedAll(false);
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        }
        mAdapter.createPrice();
    }

//    @OnClick(R2.id.tv_top_shop_cart_remove_selected)
    void onClickRemoveSelectedItem() {
        final List<MultipleItemEntity> data = mAdapter.getData();
        //要删除的数据
        List<MultipleItemEntity> deleteEntity = new ArrayList<>();
        for (MultipleItemEntity entity : data) {
            final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
            if (isSelected) {
                deleteEntity.add(entity);
            }
        }
        for (MultipleItemEntity entity : deleteEntity) {
            //拿到要删除条目的 position
            final int entityPosition = entity.getField(ShopCartItemFields.POSITION);
            if (entityPosition <= mAdapter.getItemCount()) {
                mAdapter.remove(entityPosition);
                //更新数据
                mAdapter.notifyItemRangeChanged(entityPosition, mAdapter.getItemCount());
                final int size = mAdapter.getData().size();
                //重新设置 position，让他和 item 的下标对应
                for (int i = 0; i < size; i++) {
                    data.get(i).setField(ShopCartItemFields.POSITION, i);
                }
            }
        }
        // 更新价格
        mAdapter.createPrice();
        checkItemCount();
    }

//    @OnClick(R2.id.tv_top_shop_cart_clear)
    void onClickClear() {
        mAdapter.getData().clear();
        mAdapter.notifyDataSetChanged();
        //更新价格
        mAdapter.createPrice();
        checkItemCount();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_shopcart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        SetToolBar.setToolBar(mToolbar);
        mIconSelectAll.setTag(0);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
       /* final ArrayList<MultipleItemEntity> data = new ShopCartDataConverter()
//                .setJsonData("")
                .convert();
        mAdapter = new ShopCartAdapter(data);
        //设置监听价格的回调
        mAdapter.setCartItemListener(ShopCartDelegate.this);
        mAdapter.createPrice();
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        checkItemCount();*/
    }

    @Override
    public void onItemClick(double totalPrice) {
        mTotalPrice.setText(String.valueOf(totalPrice));
    }


    private void checkItemCount() {
        if (stubFlag) {
            return;
        }
        stubFlag = true;
        final int count = mAdapter.getItemCount();
        if (count == 0) {
            //ViewStub 的inflate 方法就是将自己冲父view中移除，然后就会加载layout指定的布局了
            //注意在ViewStub 控件中，要使用layout 属性指定 待加载的布局
            @SuppressLint("RestrictedApi") View stubView = mStubNoItem.inflate();
            final AppCompatTextView tvToBuy = stubView.findViewById(R.id.tv_stub_to_buy);
            tvToBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "购物了", Toast.LENGTH_SHORT).show();
                }
            });
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }
}
