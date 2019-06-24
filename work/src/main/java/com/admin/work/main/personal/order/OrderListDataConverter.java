package com.admin.work.main.personal.order;

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
 * @file: OrderListDataConverter
 * @author: 345
 * @Time: 2019/5/8 13:38
 * @description: ${DESCRIPTION}
 */
public class OrderListDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {

        final JSONArray array = JSON.parseObject(getJsonData()).getJSONArray("data");
        int size = array.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = array.getJSONObject(i);
            final String thumb = data.getString("thumb");
            final String title = data.getString("title");
            final String time = data.getString("time");
            final int id = data.getInteger("id");
            final double price = data.getInteger("price");

            MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(OrderListItemType.ITEM_ORDER_LIST)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.TITLE, title)
                    .setField(MultipleFields.IMAGE_URL, thumb)
                    .setField(OrderItemFilelds.PRICE, price)
                    .setField(OrderItemFilelds.TIME, time)
                    .build();
            ENTITLES.add(entity);
        }

        return ENTITLES;
    }
}
