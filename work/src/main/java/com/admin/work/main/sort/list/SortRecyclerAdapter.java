package com.admin.work.main.sort.list;

import android.graphics.Color;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.admin.core.deleggate.LatteDelegate;
import com.admin.core.ui.recycler.ItemType;
import com.admin.core.ui.recycler.MultipleFields;
import com.admin.core.ui.recycler.MultipleItemEntity;
import com.admin.core.ui.recycler.MultipleRecyclerAdapter;
import com.admin.core.ui.recycler.MultipleViewHolder;
import com.admin.work.R;
import com.admin.work.main.sort.SortDelegate;
import com.admin.work.main.sort.content.ContentDelegate;

import java.util.List;

import me.yokeyword.fragmentation.SupportHelper;

/**
 * Copyright (C)
 *
 * @file: SortRecyclerAdapter
 * @author: 345
 * @Time: 2019/4/30 17:47
 * @description: ${DESCRIPTION}
 */
public class SortRecyclerAdapter extends MultipleRecyclerAdapter {

    private final SortDelegate DELEGATE;
    private int mPrePosition = 0;

    public SortRecyclerAdapter(List<MultipleItemEntity> data, SortDelegate delegate) {
        super(data);
        this.DELEGATE = delegate;
        addItemType(ItemType.VERTICAL_MENU_LIST, R.layout.item_vertical_menu_list);
    }

    @Override
    protected void convert(final MultipleViewHolder holder, final MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ItemType.VERTICAL_MENU_LIST:
                final String text = entity.getField(MultipleFields.NAME);
                final boolean isClicked = entity.getField(MultipleFields.TAG);
                final AppCompatTextView name = holder.getView(R.id.tv_vertical_item_name);
                final View line = holder.getView(R.id.view_line);
                final View itemView = holder.itemView;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //获取到点击的条目
                        final int currentPosition = holder.getAdapterPosition();
                        if (mPrePosition != currentPosition) {
                            //还原上一个
                            getData().get(mPrePosition).setField(MultipleFields.TAG, false);
                            //刷新上一个条目
                            notifyItemChanged(mPrePosition);

                            //保存选中的item
                            entity.setField(MultipleFields.TAG, true);
                            notifyItemChanged(currentPosition);
                            //记录当前选中的条目
                            mPrePosition = currentPosition;

                            //拿到当前条目的id
                            final int contentId = getData().get(currentPosition).getField(MultipleFields.ID);
                            showContent(contentId);
                        }
                    }
                });
                if (!isClicked) {
                    line.setVisibility(View.INVISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.we_char_black));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.item_background));
                } else {
                    line.setVisibility(View.VISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.app_main));
                    line.setBackgroundColor(ContextCompat.getColor(mContext, R.color.app_main));
                    itemView.setBackgroundColor(Color.WHITE);
                }
                holder.setText(R.id.tv_vertical_item_name, text);
                break;
            default:
                break;
        }
    }

    private void showContent(int contentId){
        final ContentDelegate delegate = ContentDelegate.newInstance(contentId);
        switchContent(delegate);
    }

    private void switchContent(ContentDelegate delegate){
        // 找到 ContentDelegate
        final LatteDelegate contentDelegate = SupportHelper.findFragment(DELEGATE.getChildFragmentManager(),ContentDelegate.class);
        if (contentDelegate != null){
            //加载 fragment
            contentDelegate.getSupportDelegate().replaceFragment(delegate,false);
        }
    }
}
