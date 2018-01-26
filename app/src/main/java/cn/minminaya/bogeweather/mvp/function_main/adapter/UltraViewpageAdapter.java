package cn.minminaya.bogeweather.mvp.function_main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.minminaya.bogeweather.mvp.function_main.fragment.WeatherItemFragment;

/**
 * MainActivity页的ViewPager的adapter
 * Created by Niwa on 2018/1/23.
 */

public class UltraViewpageAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList = new ArrayList<>();

    public UltraViewpageAdapter(FragmentManager fm) {
        super(fm);
        Fragment fragment1 = WeatherItemFragment.newInstance("上海");
        Fragment fragment2 = WeatherItemFragment.newInstance("广州");
        Fragment fragment3 = WeatherItemFragment.newInstance("北京");
        Fragment fragment4 = WeatherItemFragment.newInstance("湛江");
        Fragment fragment5 = WeatherItemFragment.newInstance("邯郸");
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        fragmentList.add(fragment4);
        fragmentList.add(fragment5);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
