package com.admin.work.main.sort.content;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C)
 *
 * @file: SectionDataConverter
 * @author: 345
 * @Time: 2019/5/4 10:26
 * @description: ${DESCRIPTION}
 */
public class SectionDataConverter {
    final List<SectionBean> convert(String json){
        final List<SectionBean> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(json).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String title = data.getString("section");
            /*
                当前商品组
             */
            //第一个参数：指定是不是分组的标题部分
            final SectionBean sectionBean = new SectionBean(true,title);
            sectionBean.setId(id);
            sectionBean.setIsMore(true);
            dataList.add(sectionBean);

            final JSONArray goods = data.getJSONArray("goods");
            //组内的商品内循环
            final int goodSize = goods.size();
            for (int j = 0; j < goodSize; j++) {
                final JSONObject contentItem = goods.getJSONObject(j);
                final int goodsId = contentItem.getInteger("goods_id");
                final String goodsName = contentItem.getString("goods_name");
                final String goodsThumb = contentItem.getString("goods_thumb");

                //设置内容
                final SectionContentItemEntity itemEntity = new SectionContentItemEntity();
                itemEntity.setGoodsId(goodsId);
                itemEntity.setGoodsName(goodsName);
                itemEntity.setGoodsThumb(goodsThumb);

                //添加内容
                dataList.add(new SectionBean(itemEntity));
            }
            //商品内容循环结束
        }
        //Section 循环结束
        return dataList;
    }
}
