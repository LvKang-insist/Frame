package com.admin.work.main.index;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;

import com.admin.annotations.annotations.StaticString;
import com.admin.core.deleggate.bottom.BottomItemDelegate;
import com.admin.core.net.rx.RxRestClient;
import com.admin.core.util.callback.CallBackType;
import com.admin.core.util.callback.CallbackManager;
import com.admin.core.util.dimen.SetToolBar;
import com.admin.work.R;
import com.admin.work.R2;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;

/**
 * Copyright (C)
 *
 * @file: IndexDelegate
 * @author: 345
 * @Time: 2019/4/26 14:28
 * @description: ${DESCRIPTION}
 */
@SuppressWarnings("AlibabaAvoidCommentBehindStatement")
public class IndexDelegate extends BottomItemDelegate {

    private static final String TAG = "IndexDelegate";

    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;


    @OnClick(R2.id.icon_index_scan)
    void onScan() {
        startScanWithCheck(this.getParentDelegate());
        CallbackManager.getInstance()
                .addCallback(CallBackType.ON_SCAN, (args -> {
                    String str = (String) args;
                    Toast.makeText(_mActivity, str, Toast.LENGTH_SHORT).show();
                }));
    }

    @StaticString("张三")
    private String url ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @SuppressLint("CheckResult")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        SetToolBar.setToolBar(mToolbar);


        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.SECONDS);//连接超时时间
        builder.writeTimeout(5, TimeUnit.SECONDS);//写操作 超时时间
        builder.readTimeout(5, TimeUnit.SECONDS); //写操作 超时时间

        ViewConfiguration.get(getContext()).getScaledTouchSlop();


        Button button = rootView.findViewById(R.id.index_btn);
        button.setOnClickListener((view) -> {

            RxRestClient.builder()
                    .url("index.php")
                    .raw("{\"name\":\"李四\"}")
                    .build()
                    .post()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(String s) {
                            Log.e(TAG, "onNext: "+s );
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        });
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
