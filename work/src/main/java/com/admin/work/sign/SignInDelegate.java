package com.admin.work.sign;

import android.app.Activity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.admin.core.deleggate.LatteDelegate;
import com.admin.work.R;
import com.admin.work.R2;
import com.admin.work.litepal.AccountTable;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Copyright (C)
 * @file: SignInDelegate
 * @author: 345
 * @Time: 2019/4/22 14:34
 * @description: 登录界面
 */
public class SignInDelegate extends LatteDelegate {

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;

    ISignListener mIsignListener = null;
    private AccountTable account;

    /**
     * @param activity 当前的Activity
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //判断 当前的 activity 有没有实现这个接口
        if (activity instanceof ISignListener){
            //向上转型
            mIsignListener = (ISignListener) activity;
        }
    }
    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn(){
        if (checkForm()){
            SignHandler.onSignIn(account,mIsignListener);
        }
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    void onCLickWeiChart(){
        Toast.makeText(_mActivity, "调起 微信登录失败", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink(){
        getSupportDelegate().start(new SignUpDelegate());
    }

    private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();
        boolean isPass = true;

        //判断邮箱格式是否正确
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请至少填写 6位数的密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }
        if (isPass){
            account = new AccountTable();
            account.setEmail(email);
            account.setPassword(password);
        }
        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
    }
}
