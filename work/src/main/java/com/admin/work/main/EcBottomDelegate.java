package com.admin.work.main;

import android.graphics.Color;
import com.admin.core.deleggate.bottom.BaseBottomDelegate;
import com.admin.core.deleggate.bottom.BottomItemDelegate;
import com.admin.core.deleggate.bottom.BottomTabBean;
import com.admin.core.deleggate.bottom.ItemBuilder;
import com.admin.work.main.cart.ShopCartDelegate;
import com.admin.work.main.discover.DiscoverDelegate;
import com.admin.work.main.index.IndexDelegate;
import com.admin.work.main.personal.PersonalDelegate;
import com.admin.work.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * Copyright (C)
 *
 * @file: EcBottomDelegate
 * @author: 345
 * @Time: 2019/4/26 14:26
 * @description: ${DESCRIPTION}
 */
public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}","主页"),new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}","分类"),new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}","发现"),new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}","购物车"),new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}","我的"),new PersonalDelegate());
        return builder.addItem(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
