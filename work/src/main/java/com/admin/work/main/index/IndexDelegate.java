package com.admin.work.main.index;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;

import com.admin.core.deleggate.bottom.BottomItemDelegate;
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
 * @file: IndexDelegate
 * @author: 345
 * @Time: 2019/4/26 14:28
 * @description: ${DESCRIPTION}
 */
public class IndexDelegate extends BottomItemDelegate {

    @BindView(R2.id.tb_index)
    Toolbar mToobar = null;
    @OnClick(R2.id.icon_index_scan)
    void onScan(){
        startScanWithCheck(this.getParentDelegate());
        CallbackManager.getInstance()
                .addCallback(CallBackType.ON_SCAN, new IGlobalCallback() {
                    @Override
                    public void executeCallBack(Object args) {
                        String str = (String) args;
                        Toast.makeText(_mActivity, str, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        SetToolBar.setToolBar(mToobar);
    }

    /**
     * 懒加载时 回调的方法，当前界面显示时，会回调该方法
     */
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }
}
