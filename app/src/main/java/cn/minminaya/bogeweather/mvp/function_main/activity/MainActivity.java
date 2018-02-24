package cn.minminaya.bogeweather.mvp.function_main.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ReflectUtils;
import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.OnBoomListener;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.nightonke.boommenu.Util;
import com.tmall.ultraviewpager.UltraViewPager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import butterknife.BindView;
import cn.minminaya.bogeweather.App;
import cn.minminaya.bogeweather.C;
import cn.minminaya.bogeweather.R;
import cn.minminaya.bogeweather.mvp.base.BaseActivity;
import cn.minminaya.bogeweather.mvp.base.view.MvpView;
import cn.minminaya.bogeweather.mvp.function_main.adapter.UltraViewpageAdapter;
import cn.minminaya.bogeweather.mvp.function_main.presenter.MainPresenter;

public class MainActivity extends BaseActivity implements MvpView {

    @BindView(R.id.ids_view_pager)
    public UltraViewPager mViewPager;
    @BindView(R.id.ids_bmb)
    public BoomMenuButton mBoomMenuButton;
    private MainPresenter mainPresenter = new MainPresenter();


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
            mBoomMenuButton.addBuilder(new TextOutsideCircleButton.Builder()
                    .normalText(C.CityNameConstant.cityConstant[i])
            );
        }

        mViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        mViewPager.setOffscreenPageLimit(3);


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


        mBoomMenuButton.setOnBoomListener(new OnBoomListener() {
            @Override
            public void onClicked(int index, BoomButton boomButton) {

                if (index >= 0 && index <= 4) {
                    mViewPager.setCurrentItem(index);
                } else if (index == 6) {
                    // TODO: 25/02/2018 跳转到选择城市的Activity
                }

                //当前城市
//                TextView textView = ReflectUtils.reflect(boomButton).field("text").get();
//
//                Log.e("boomButton-onClicked", "textView:" + textView.getText());
//
//                textView.setText("修改名字啦啊哈哈哈哈");

                Log.e("boomButton-onClicked", "index:" + index);

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
                //如果定位成功了
                if (App.getINSTANCE().isLocation()) {
                    mBoomMenuButton.getBoomButton(0).getTextView().setText(C.CityNameConstant.currentLocationCity);
                }
            }
        });
    }

    @Override
    public void bind() {
        mainPresenter.attachView(this);

        String currentLocation = mainPresenter.getLocationInfo();

        UltraViewpageAdapter mUltraViewpageAdapter = new UltraViewpageAdapter(getSupportFragmentManager(), currentLocation);
        mViewPager.setAdapter(mUltraViewpageAdapter);

    }

    @Override
    public void unBind() {
        mainPresenter.detachView(this);
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
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
}
