package com.admin.work.main.personal.order;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.admin.core.deleggate.LatteDelegate;
import com.admin.core.ui.widget.AutoPhotoLayout;
import com.admin.core.ui.widget.StarLayout;
import com.admin.core.util.callback.CallBackType;
import com.admin.core.util.callback.CallbackManager;
import com.admin.core.util.callback.IGlobalCallback;
import com.admin.core.util.dimen.SetToolBar;
import com.admin.work.R;
import com.admin.work.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Copyright (C)
 *
 * @file: OrderCommentDelegate
 * @author: 345
 * @Time: 2019/5/12 11:07
 * @description: 评价晒单
 */
public class OrderCommentDelegate extends LatteDelegate {

    @BindView(R2.id.tb_shop_cart)
    Toolbar mToolbar = null;
    @BindView(R2.id.custom_star_layout)
    StarLayout mStarLayout = null;
    @BindView(R2.id.custom_auto_photo_layout)
    AutoPhotoLayout mAutoPhotoLayout = null;

    @OnClick(R2.id.top_tv_comment_commit)
    void onCLickSubmit(){
        Toast.makeText(getContext(), mStarLayout.getStarCount()+" ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_comment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        SetToolBar.setToolBar(mToolbar);

        mAutoPhotoLayout.setDelegate(this);
        CallbackManager.getInstance().addCallback(CallBackType.ON_CROP, new IGlobalCallback<Uri>() {
            @Override
            public void executeCallBack(Uri args) {
                Toast.makeText(getContext(), args.getPath(), Toast.LENGTH_SHORT).show();
                mAutoPhotoLayout.onCropTarget(args);
            }
        });
    }
}
