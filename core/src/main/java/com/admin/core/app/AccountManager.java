package com.admin.core.app;

import com.admin.core.util.storage.LattePreference;

/**
 * Copyright (C)
 *
 * @file: AccountManager
 * @author: 345
 * @Time: 2019/4/23 17:54
 * @description: ${DESCRIPTION}
 */
public class AccountManager {
    private enum SignTag{
        /**
         * 登录的状态
         */
        SIGN_TAG
    }

    /**
     * @param state 设置登陆的状态
     */
    public static void setSignState(boolean state){
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }
    /**
     * @return 返回用户是否登陆
     */
    private static boolean isSignIn(){
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    /**
     * @param checker 登录状态的回调
     */
    public static void checkAccount( IUserChecker checker){
        if (isSignIn()){
            checker.onSignIn();
        }else {
            checker.onNoSignIn();
        }
    }
}
