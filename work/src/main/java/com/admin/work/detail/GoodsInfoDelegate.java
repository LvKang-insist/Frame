package com.admin.work.detail;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.admin.core.deleggate.LatteDelegate;
import com.admin.work.R;
import com.admin.work.R2;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import butterknife.BindView;

public class GoodsInfoDelegate extends LatteDelegate {

    @BindView(R2.id.tv_goods_info_title)
    AppCompatTextView mGoodsInfoTitle = null;
    @BindView(R2.id.tv_goods_info_desc)
    AppCompatTextView mGoodsInfoDesc = null;
    @BindView(R2.id.tv_goods_info_price)
    AppCompatTextView mGoodsInfoPrice = null;

    private static final String ARG_GOODS_DATA = "ARG_GOODS_DATA";
    private JSONObject mData = null;
    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_info;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        final String goodsData = args.getString(ARG_GOODS_DATA);
        mData = JSON.parseObject(goodsData);
    }

    public static GoodsInfoDelegate create(String goodsInfo){
        final Bundle args  = new Bundle();
        args.putString(ARG_GOODS_DATA,goodsInfo);
        final GoodsInfoDelegate delegate = new GoodsInfoDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final String name = mData.getString("name");
        final String desc = mData.getString("description");
        final double price = mData.getDouble("price");
        mGoodsInfoTitle.setText(name);
        mGoodsInfoDesc.setText(desc);
        mGoodsInfoPrice.setText(String.valueOf(price));
    }
}
