package com.admin.work.sign;

import android.util.Log;

import com.admin.core.app.AccountManager;
import com.admin.core.net.RestClient;
import com.admin.work.litepal.AccountTable;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C)
 *
 * @file: SignHandler
 * @author: 345
 * @Time: 2019/4/22 19:39
 * @description: ${DESCRIPTION}
 */
public class SignHandler {
    private static final String CODE = "code";
    private static final String RESULT = "result";

    /**
     *  进行注册
     */
    public static void onSignUp(AccountTable account, ISignListener signListener){
//        if (account.save()) {

//        }
    }
    /**
     * 进行登录
     */
    public static void onSignIn(AccountTable account,ISignListener signListener){
        final int code = 200;
        final String res = "success";
        Map<String,String> map = new HashMap<>(10);
        map.put("email",account.getEmail());
        map.put("password",account.getPassword());
        String json = JSON.toJSONString(map);
        Log.e("-----------", "onSignIn: "+json );
        RestClient.builder()
                .url("signAccount.php")
                .raw(JSON.toJSONString(map))
                .success((result)->{
                    Log.e("------", "onSignIn: "+result );
                    JSONObject jsonObject = JSON.parseObject(result);
                    if (jsonObject.getInteger(CODE).equals(code)){
                        if (res.equals(jsonObject.getString(RESULT))){
                            AccountManager.setSignState(true);
                            signListener.onSignInSuccess();
                        }
                    }
                })
                .build()
                .post();
    }
}
