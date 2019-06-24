package com.admin.work.main.sort.list;

import com.admin.core.ui.recycler.DataConverter;
import com.admin.core.ui.recycler.ItemType;
import com.admin.core.ui.recycler.MultipleFields;
import com.admin.core.ui.recycler.MultipleItemEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

/**
 * Copyright (C)
 *
 * @file: VerticalListDataConverter
 * @author: 345
 * @Time: 2019/4/30 16:18
 * @description: ${DESCRIPTION
 */
public final class VerticalListDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {

        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();

        final JSONArray dataArray = JSON.parseObject(getJsonData())
                .getJSONObject("data")
                .getJSONArray("list");

        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                    .setField(MultipleFields.ID,id)
                    .setField(MultipleFields.NAME,name)
                    .setField(MultipleFields.TAG,false)
                    .build();
            dataList.add(entity);
            dataList.get(0).setField(MultipleFields.TAG,true);
        }
        return dataList;
    }
}
