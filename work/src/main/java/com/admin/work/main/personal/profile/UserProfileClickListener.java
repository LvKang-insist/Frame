package com.admin.work.main.personal.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

import com.admin.core.deleggate.LatteDelegate;
import com.admin.core.ui.date.DateDialogUtil;
import com.admin.core.util.callback.CallBackType;
import com.admin.core.util.callback.CallbackManager;
import com.admin.core.util.callback.IGlobalCallback;
import com.admin.work.R;
import com.admin.work.main.personal.list.ListBean;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

/**
 * Copyright (C)
 *
 * @file: UserProfileClickListener
 * @author: 345
 * @Time: 2019/5/8 20:17
 * @description: 个人中心的点击事件1
 */
public class UserProfileClickListener extends SimpleClickListener {

    private LatteDelegate DELEGATE;
    private String[] mGenders = new String[]{"男", "女", "保密"};

    public UserProfileClickListener(LatteDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, final View view, int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        final int id = bean.getId();
        switch (id) {
            case 1:
                //照相机 或选择图片
                CallbackManager.getInstance()
                        .addCallback(CallBackType.ON_CROP, new IGlobalCallback() {
                            @Override
                            public void executeCallBack(Object args) {
                                Uri arg  = (Uri) args;
                                ImageView avatar = view.findViewById(R.id.img_arrow_avatar);
                                Glide.with(DELEGATE)
                                        .load(arg)
                                        .into(avatar);

                            }
                        });
                DELEGATE.startCameraWithCheck();
                break;
            case 2:
                final LatteDelegate nameDelegate = bean.getDelegate();
                DELEGATE.getSupportDelegate().start(nameDelegate);
                break;
            case 3:
                //设置一个单选对话框
                getGenderDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final AppCompatTextView textView = view.findViewById(R.id.tv_arrow_value);
                        textView.setText(mGenders[which]);
                    }
                });
                break;
            case 4:
                //日期选择
                final DateDialogUtil dateDialogUtil = new DateDialogUtil();
                dateDialogUtil.setDateListener(new DateDialogUtil.IDateListener() {
                    @Override
                    public void onDateChange(String date) {
                        final TextView textView = view.findViewById(R.id.tv_arrow_value);
                        textView.setText(date);
                    }
                });
                dateDialogUtil.showDialog(DELEGATE.getContext());
                break;
            default:
                break;

        }
    }

    private void getGenderDialog(DialogInterface.OnClickListener listener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(DELEGATE.getContext());
        builder.setSingleChoiceItems(mGenders, 0, listener);
        builder.show();
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
