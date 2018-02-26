package cn.minminaya.bogeweather.mvp.function_main.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.OnBoomListener;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.tmall.ultraviewpager.UltraViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


import butterknife.BindView;
import cn.minminaya.bogeweather.C;
import cn.minminaya.bogeweather.R;
import cn.minminaya.bogeweather.mvp.base.BaseActivity;
import cn.minminaya.bogeweather.mvp.base.view.MvpView;
import cn.minminaya.bogeweather.mvp.function_main.adapter.UltraViewpageAdapter;
import cn.minminaya.bogeweather.mvp.function_main.presenter.MainPresenter;

public class MainActivity extends BaseActivity implements MvpView {

    @BindView(R.id.ids_view_pager)
//    public UltraViewPager mViewPager;
    public ViewPager mViewPager;
    @BindView(R.id.ids_bmb)
    public BoomMenuButton mBoomMenuButton;
    private MainPresenter mainPresenter = new MainPresenter();

    UltraViewpageAdapter mUltraViewpageAdapter = null;
    public boolean flag;

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        //设置状态栏透明度
        BarUtils.setStatusBarAlpha(this, 30);
        //设置导航栏不显示
        BarUtils.setNavBarVisibility(this, false);

        mBoomMenuButton.setButtonEnum(ButtonEnum.TextOutsideCircle);
        mBoomMenuButton.setPiecePlaceEnum(PiecePlaceEnum.DOT_7_1);
        mBoomMenuButton.setButtonPlaceEnum(ButtonPlaceEnum.SC_7_3);

        for (int i = 0; i < 7; i++) {

            switch (i) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                    mBoomMenuButton.addBuilder(new TextOutsideCircleButton.Builder()
                            .normalText("")
                    );
                    break;
                case 5:
                    mBoomMenuButton.addBuilder(new TextOutsideCircleButton.Builder()
                            .normalText("点击收起"));
                    break;
                case 6:
                    mBoomMenuButton.addBuilder(new TextOutsideCircleButton.Builder()
                            .normalText("更多城市"));
                    break;
            }
        }

//        mViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        mViewPager.setOffscreenPageLimit(2);

        //配置指示器
//        mViewPager.initIndicator()
//                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
//                .setFocusColor(Color.WHITE)
//                .setNormalColor(Color.parseColor("#dddddd"))
//                .setRadius(ConvertUtils.dp2px(3))
//                .setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP)
//                .setMargin(0, ConvertUtils.dp2px(70), 0, 0)
//                .build();

    }

    @Override
    public void setListeners() {
        mBoomMenuButton.setOnBoomListener(new OnBoomListener() {
            @Override
            public void onClicked(int index, BoomButton boomButton) {

                if (index >= 0 && index <= 4) {
                    mViewPager.setCurrentItem(index);
                } else if (index == 6) {
                    ActivityUtils.startActivity(CityItemActivity.class);
                }
            }

            @Override
            public void onBackgroundClick() {

            }

            @Override
            public void onBoomWillHide() {

            }

            @Override
            public void onBoomDidHide() {

            }

            @Override
            public void onBoomWillShow() {

            }

            @Override
            public void onBoomDidShow() {
                mainPresenter.setBOBData();

            }
        });
    }


    @Override
    public void bind() {
        mainPresenter.attachView(this);

        EventBus.getDefault().register(this);

        String currentLocation = mainPresenter.getLocationInfo();

        mainPresenter.initCityLists();

        mUltraViewpageAdapter = new UltraViewpageAdapter(getSupportFragmentManager(), currentLocation);
        mUltraViewpageAdapter.initData();
        mViewPager.setAdapter(mUltraViewpageAdapter);

    }

    @Override
    public void unBind() {
        mainPresenter.detachView(this);
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onFailed(Throwable e) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        initPermission();
    }

    /**
     * 权限的结果回调函数
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            flag = grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED;
        }
    }

    private void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //检查权限
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //请求权限
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                flag = true;
            }
        } else {
            flag = true;
        }
    }

    /**
     * 接受来自CityItemActivity的信息
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void notifyFragmentAdapter(Integer eventNum) {

        if (eventNum == C.FORM_CITYITEMACTITVY_TO＿MAIN_ACTIVITY) {
            mUltraViewpageAdapter.initData();
            Log.e("notifyFragmentAdapter", "数据来了");
        }
        if (eventNum >= 0 && eventNum < 5) {
            mViewPager.setCurrentItem(eventNum);
        }
    }
}
