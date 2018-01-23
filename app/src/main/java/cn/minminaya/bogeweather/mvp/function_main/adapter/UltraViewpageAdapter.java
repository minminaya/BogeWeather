package cn.minminaya.bogeweather.mvp.function_main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.minminaya.bogeweather.mvp.function_main.fragment.WeatherItemFragment;

/** MainActivity页的ViewPager的adapter
 * Created by Niwa on 2018/1/23.
 */

public class UltraViewpageAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList = new ArrayList<>();

    public UltraViewpageAdapter(FragmentManager fm) {
        super(fm);
        Fragment fragment1 = WeatherItemFragment.newInstance();
//        Fragment fragment2 = WeatherItemFragment.newInstance();
//        Fragment fragment3 = WeatherItemFragment.newInstance();
//        Fragment fragment4 = WeatherItemFragment.newInstance();
        fragmentList.add(fragment1);
//        fragmentList.add(fragment2);
//        fragmentList.add(fragment3);
//        fragmentList.add(fragment4);
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
