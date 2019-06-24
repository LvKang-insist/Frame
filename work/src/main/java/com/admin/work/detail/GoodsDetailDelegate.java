package com.admin.work.detail;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.admin.core.deleggate.LatteDelegate;
import com.admin.core.net.RestClient;
import com.admin.core.net.callback.ISuccess;
import com.admin.core.ui.animation.BezierAnimation;
import com.admin.core.ui.animation.BezierUtil;
import com.admin.core.ui.banner.HolderCreate;
import com.admin.core.ui.widget.CircleTextView;
import com.admin.work.R;
import com.admin.work.R2;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

;

/**
 * Copyright (C)
 *
 * @file: GoodsDetailDelegate
 * @author: 345
 * @Time: 2019/4/29 13:31
 * @description: 商品详情
 */
public class GoodsDetailDelegate extends LatteDelegate implements AppBarLayout.OnOffsetChangedListener, BezierUtil.AnimationListener {


    @BindView(R2.id.goods_detail_toolbar)
    Toolbar toolbar = null;
    @BindView(R2.id.tab_layout)
    TabLayout mTabLayout = null;
    @BindView(R2.id.view_pager)
    ViewPager mViewPager = null;
    @BindView(R2.id.detail_banner)
    ConvenientBanner<String> mBanner = null;
    @BindView(R2.id.collapsing_toolbar_detail)
    CollapsingToolbarLayout mCollapsingToolBarLayout = null;
    @BindView(R2.id.app_bar_detail)
    AppBarLayout mAppbar = null;

    /**
     * 底部
     */
    @BindView(R2.id.icon_favor)
    IconTextView mIconFavor = null;
    @BindView(R2.id.tv_shopping_cart_amount)
    CircleTextView mCircleTextView = null;
    @BindView(R2.id.rl_add_shop_cart)
    RelativeLayout mRlAddShopCart = null;
    @BindView(R2.id.icon_shop_cart)
    IconTextView mIconShopCart = null;

    private static final String ARG_GOODS_ID = "ARG_GOODS_ID";
    private int mGoodsId = -1;

    private String mGoodsThumbUrl = null;
    private int mShopCount = 0;

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate()
            .override(100, 100);

    @OnClick(R2.id.rl_add_shop_cart)
    void onClickAddShopCart() {
        //加载动画的图片
        final CircleImageView animImg = new CircleImageView(getContext());
        Glide.with(this)
                .load(mGoodsThumbUrl)
                .apply(OPTIONS)
                .into(animImg);
        //实现动画效果
        BezierAnimation.addCart(this, mRlAddShopCart, mIconShopCart, animImg, this);
    }

    private void setShCartCount(JSONObject data) {
        mGoodsThumbUrl = data.getString("thumb");
        if (mShopCount == 0) {
            mCircleTextView.setVisibility(View.GONE);
        }
    }

    public static GoodsDetailDelegate create( int goodsId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_GOODS_ID, goodsId);
        final GoodsDetailDelegate delegate = new GoodsDetailDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mGoodsId = args.getInt(ARG_GOODS_ID);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        //伸缩之后改变的颜色
        mCollapsingToolBarLayout.setContentScrimColor(Color.WHITE);
        //Appbar滚动的 事件监听
        mAppbar.addOnOffsetChangedListener(this);
        mCircleTextView.setCircleBackground(Color.RED);
        initData();
        initTabLayout();
    }

    private void initPager(JSONObject data) {
        final PagerAdapter adapter = new TabPagerAdapter(getChildFragmentManager(), data);
        mViewPager.setAdapter(adapter);
    }

    private void initTabLayout() {
        //tab 是平均分开的
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        //地下 线的颜色
        mTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(), R.color.app_main));
        //字的颜色
        mTabLayout.setTabTextColors(ColorStateList.valueOf(Color.BLACK));
        //背景色
        mTabLayout.setBackgroundColor(Color.WHITE);
        //设置ViewPager
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initData() {
        RestClient.builder()
                .url("goods_detail_data_0.json")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject date = JSON.parseObject(response).getJSONObject("data");
                        initBanner(date);
                        initPager(date);
                        initGoodsInfo(date);
                        setShCartCount(date);
                    }
                })
                .build()
                .get();
    }

    private void initGoodsInfo(JSONObject data) {
        final String goodsData = data.toJSONString();
        getSupportDelegate().loadRootFragment(R.id.frame_goods_info, GoodsInfoDelegate.create(goodsData));
    }

    private void initBanner(JSONObject date) {
        final JSONArray aray = date.getJSONArray("banners");
        final List<String> images = new ArrayList<>();
        final int size = aray.size();
        for (int i = 0; i < size; i++) {
            images.add(aray.getString(i));
        }
        //轮播图
        mBanner
                .setPages(new HolderCreate(), images)
                .setPageIndicator(new int[]{R.drawable.dot_focus, R.drawable.dot_normal})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .startTurning(3000)
                .setCanLoop(true);
    }

    /**
     * AppBar 滚动的事件监听
     */
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {

    }

    /**
     * 动画完成之后的回调
     */
    @Override
    public void onAnimationEnd() {
        YoYo.with(new ScaleUpAnimator())
                .duration(500)
                .playOn(mIconShopCart);
        mShopCount++;
        mCircleTextView.setVisibility(View.VISIBLE);
        mCircleTextView.setText(String.valueOf(mShopCount));
        RestClient.builder()
                .url("add_shop_cart_count")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                    }
                })
                .params("count", mShopCount)
                .build()
                .post();
    }
}
