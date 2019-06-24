package com.admin.work.main.personal.settings;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import com.admin.core.deleggate.LatteDelegate;
import com.admin.work.R;

/**
 * Copyright (C)
 *
 * @file: NameDelegate
 * @author: 345
 * @Time: 2019/5/8 20:24
 * @description: ${DESCRIPTION}
 */
public class NameDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.dalegate_name;
    }
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
