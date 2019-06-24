package com.admin.work.main.personal.settings;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import com.admin.core.deleggate.LatteDelegate;
import com.admin.core.net.RestClient;
import com.admin.core.net.callback.ISuccess;
import com.admin.core.util.dimen.SetToolBar;
import com.admin.work.R;
import com.admin.work.R2;
import com.alibaba.fastjson.JSON;

import butterknife.BindView;

/**
 * Copyright (C)
 *
 * @file: AboutDelegate
 * @author: 345
 * @Time: 2019/5/12 10:04
 * @description: 关于
 */
public class AboutDelegate extends LatteDelegate {

    @BindView(R2.id.about_toolbar)
    Toolbar mToolbar = null;

    @BindView(R2.id.tv_info)
    AppCompatTextView mTextView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_about;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        SetToolBar.setToolBar(mToolbar);
        RestClient.builder()
                .url("about.json")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final String info = JSON.parseObject(response).getString("data");
                        mTextView.setText(info);
                    }
                })
                .build()
                .get();
    }
}
