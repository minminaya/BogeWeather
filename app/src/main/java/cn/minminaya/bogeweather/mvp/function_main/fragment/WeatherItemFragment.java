package cn.minminaya.bogeweather.mvp.function_main.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.minminaya.bogeweather.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeatherItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeatherItemFragment extends Fragment {

    public WeatherItemFragment() {
    }

    public static WeatherItemFragment newInstance() {
        WeatherItemFragment fragment = new WeatherItemFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather_item, container, false);
    }

}
