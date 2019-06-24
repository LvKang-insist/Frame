package com.admin.frame;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import com.admin.core.activitys.ProxyActivity;
import com.admin.core.deleggate.LatteDelegate;
import com.admin.core.ui.loader.ILauncherListener;
import com.admin.core.ui.loader.OnLauncherFinishTag;
import com.admin.work.main.EcBottomDelegate;
import com.admin.work.sign.ISignListener;
import com.admin.work.sign.SignInDelegate;
import com.gyf.immersionbar.ImmersionBar;

public class ExampleActivity extends ProxyActivity implements
        ISignListener , ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!= null){
            actionBar.hide();
        }
        //沉浸式状态栏
        ImmersionBar.with(this).init();

    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new SignInDelegate();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag onLauncherFinishTag) {
        switch (onLauncherFinishTag) {
            case SIGNED:
                getSupportDelegate().startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                getSupportDelegate().startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }

    @Override
    public void onSignInSuccess() {
        getSupportDelegate().startWithPop(new EcBottomDelegate());
    }
    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功,请点击下方按钮进行登录", Toast.LENGTH_SHORT).show();
    }
}
