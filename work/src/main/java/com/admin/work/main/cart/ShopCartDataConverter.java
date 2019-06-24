package com.admin.work.main.cart;

import com.admin.core.ui.recycler.DataConverter;
import com.admin.core.ui.recycler.MultipleFields;
import com.admin.core.ui.recycler.MultipleItemEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

/**
 * Copyright (C)
 *
 * @file: ShopCartDataConverter
 * @author: 345
 * @Time: 2019/5/6 8:23
 * @description: ${DESCRIPTION}
 */
public class ShopCartDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {

        ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");

        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final String thumb = data.getString("thumb");
            final String desc = data.getString("desc");
            final String title = data.getString("title");
            final int id = data.getInteger("id");
            final int count = data.getInteger("count");
            final double price = data.getDouble("price");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE,ShopCartItemType.SHOP_CART_ITEM)
                    .setField(MultipleFields.ID,id)
                    .setField(MultipleFields.IMAGE_URL,thumb)
                    .setField(ShopCartItemFields.TITLE,title)
                    .setField(ShopCartItemFields.DESC,desc)
                    .setField(ShopCartItemFields.COUNT,count)
                    .setField(ShopCartItemFields.PRICE,price)
                    //item 是否被点击，默认false
                    .setField(ShopCartItemFields.IS_SELECTED,false)
                    //设置每个条目的 position
                    .setField(ShopCartItemFields.POSITION,i)
                    .build();
            dataList.add(entity);
        }
        return dataList;
    }
}
