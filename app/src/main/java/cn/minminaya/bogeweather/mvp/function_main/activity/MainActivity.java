package cn.minminaya.bogeweather.mvp.function_main.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.view.Gravity;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.tmall.ultraviewpager.UltraViewPager;

import butterknife.BindView;
import cn.minminaya.bogeweather.R;
import cn.minminaya.bogeweather.mvp.base.BaseActivity;
import cn.minminaya.bogeweather.mvp.base.view.MvpView;
import cn.minminaya.bogeweather.mvp.function_main.adapter.UltraViewpageAdapter;
import cn.minminaya.bogeweather.mvp.function_main.presenter.MainPresenter;

public class MainActivity extends BaseActivity implements MvpView {

    @BindView(R.id.ids_view_pager)
    public UltraViewPager mViewPager;
    private MainPresenter mainPresenter = new MainPresenter();


    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        BarUtils.setStatusBarAlpha(this,30);

        BarUtils.setNavBarVisibility(this, false);


        mViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        UltraViewpageAdapter adapter = new UltraViewpageAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);

        //配置指示器
        mViewPager.initIndicator()
                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                .setFocusColor(Color.WHITE)
                .setNormalColor(Color.parseColor("#dddddd"))
                .setRadius(ConvertUtils.dp2px(3))
                .setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP)
                .setMargin(0, ConvertUtils.dp2px(70), 0, 0)
                .build();

    }

    @Override
    public void setListeners() {

    }

    @Override
    public void bind() {
        mainPresenter.attachView(this);
        mainPresenter.test();
    }

    @Override
    public void unBind() {
        mainPresenter.detachView(this);
    }

    @Override
    public void onFailed(Throwable e) {

    }



}
