package cn.minminaya.bogeweather.mvp.function_main.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import cn.minminaya.bogeweather.R;
import cn.minminaya.bogeweather.mvp.base.BaseFragment;
import cn.minminaya.bogeweather.mvp.base.view.MvpView;
import cn.minminaya.bogeweather.mvp.function_main.adapter.HourlyRecyclerViewAdapter;
import cn.minminaya.bogeweather.mvp.function_main.presenter.WeatherItemPresenter;

public class WeatherItemFragment extends BaseFragment implements MvpView {

    private static final String TAG = "WeatherItemFragment_Log";


    @BindView(R.id.ids_tv_temporary)
    public TextView mTvTemporary;
    @BindView(R.id.ids_tv_weather)
    public TextView mTvWeather;
    @BindView(R.id.ids_tv_max_temporary)
    public TextView mTvMaxTemporary;
    @BindView(R.id.ids_tv_mix_temporary)
    public TextView mTvMixTemporary;

    @BindView(R.id.ids_img_pic)
    public ImageView mImagPic;

    @BindView(R.id.ids_pic_view)
    public RelativeLayout mPicView;
    @BindView(R.id.ids_toolbar_title)
    public TextView mToolbarTitle;

    @BindView(R.id.ids_recycler_view_hourly)
    RecyclerView mHourlyRecyclerView;

    private String mCurrentCity;

    public HourlyRecyclerViewAdapter mHourlyRecyclerViewAdapter;

    private WeatherItemPresenter mWeatherItemPresenter = new WeatherItemPresenter();

    private static final String CURRENT_CITY = "currentCity";

    public WeatherItemFragment() {
    }


    public static WeatherItemFragment newInstance(String currentCity) {
        WeatherItemFragment fragment = new WeatherItemFragment();
        Bundle args = new Bundle();
        args.putString(CURRENT_CITY, currentCity);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCurrentCity = getArguments().getString(CURRENT_CITY);
        }
    }

    @Override
    public void iniView(View view) {
        mHourlyRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mHourlyRecyclerViewAdapter = new HourlyRecyclerViewAdapter();
        mHourlyRecyclerView.setAdapter(mHourlyRecyclerViewAdapter);
    }

    @Override
    public void bind() {
        mWeatherItemPresenter.attachView(this);

        //连接网络加载天气的数据
        mWeatherItemPresenter.loadData(mCurrentCity);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void lazyLoad() {

    }

    @Override
    protected void unBind() {
        mWeatherItemPresenter.detachView(this);
    }

    @Override
    public int setContView() {
        return R.layout.fragment_weather_item;
    }




    @Override
    public void onFailed(Throwable e) {

    }
}
