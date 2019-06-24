package com.admin.core.deleggate.bottom;

import android.widget.Toast;

import com.admin.core.R;
import com.admin.core.app.Latte;
import com.admin.core.deleggate.LatteDelegate;

/**
 * Copyright (C)
 *
 * @file: BottomItemDelegate
 * @author: 345
 * @Time: 2019/4/25 19:26
 * @description: 导航栏对应的 页面
 */
public abstract class BottomItemDelegate extends LatteDelegate {
  /*  private long mExitTime = 0;
    private static final int EXIT_TIME = 2000;*/
   /* @Override
    public void onResume() {
        super.onResume();
        final View rootView = getView();
        if (rootView != null) {
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }*/
   /* @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mExitTime) > EXIT_TIME) {
                Toast.makeText(_mActivity, "双击退出" + getString(com.wang.avi.R.string.app_name), Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                _mActivity.finish();
            }
            return true;
        }
        return false;
    }*/

    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出" + Latte.getApplication().getString(R.string.app_name), Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
