package com.admin.frame;

import android.app.Application;

import com.admin.core.app.Latte;
import com.admin.frame.event.TestEvent;
import com.admin.work.icon.FontEcModule;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import org.litepal.LitePal;

/**
 * Copyright (C)
 * 文件名称: ExampleApp
 * 创建人: 345
 * 创建时间: 2019/4/14 20:29
 * 描述: 继承自 Application ，用于进行初始化
 */
public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Latte.init(this)
                .WithApiHost("http://192.168.167.2:8090/Frame_Api/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withJavaScriptInterface("latte")
                .withWebEvent("test",new TestEvent())
                .withWebHost("https:www.baidu.com")
                .configure();
        //初始化数据库
        LitePal.initialize(this);

        initStetho();
    }
    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }

}
