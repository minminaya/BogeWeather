package cn.minminaya.bogeweather.mvp.function_main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.minminaya.bogeweather.App;
import cn.minminaya.bogeweather.C;
import cn.minminaya.bogeweather.mvp.function_main.fragment.WeatherItemFragment;

/**
 * MainActivity页的ViewPager的adapter
 * Created by Niwa on 2018/1/23.
 */

public class UltraViewpageAdapter extends FragmentStatePagerAdapter {

    private String mCurrentLocation;

    private List<Fragment> fragmentList = new ArrayList<>();
    private Fragment fragment = null;
    private int fragmentSize = 5;

    public UltraViewpageAdapter(FragmentManager fm, String currentLocation) {
        super(fm);
        this.mCurrentLocation = currentLocation;
        initData();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size() > 0 ? fragmentList.size() : 0;
    }

    public void initData() {
        for (int i = 0; i < fragmentSize; i++) {

            if (i == 0) {
                //如果是第一个，判断有没成功定位
                if (App.getINSTANCE().isLocation()) {
                    fragment = WeatherItemFragment.newInstance("", mCurrentLocation);
                    ((WeatherItemFragment) fragment).setCurrentLocation(true);//设置当前fragment是定位城市

                } else {
                    //未定位成功则显示北京的天气
                    fragment = WeatherItemFragment.newInstance("北京", null);
                }
                fragmentList.add(fragment);
                fragment = null;
            } else {
                //第2到第5个城市
                fragment = WeatherItemFragment.newInstance(C.CityNameConstant.cityConstant[i], null);
                fragmentList.add(fragment);
                fragment = null;
            }
        }
    }
}
