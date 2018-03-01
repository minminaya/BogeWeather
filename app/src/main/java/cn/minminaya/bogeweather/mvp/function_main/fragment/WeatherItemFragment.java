package cn.minminaya.bogeweather.mvp.function_main.fragment;


import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.minminaya.bogeweather.R;
import cn.minminaya.bogeweather.mvp.base.BaseFragment;
import cn.minminaya.bogeweather.mvp.base.view.MvpView;
import cn.minminaya.bogeweather.mvp.function_main.adapter.HourlyRecyclerViewAdapter;
import cn.minminaya.bogeweather.mvp.function_main.presenter.WeatherItemPresenter;

public class WeatherItemFragment extends BaseFragment implements MvpView {

    private static final String TAG = "WeatherItemFragment_Log";


    @BindView(R.id.ids_smart_refresh_layout)
    public SmartRefreshLayout mSmartRefreshLayout;
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

    //以下是未来3天天气控件
    @BindView(R.id.ids_pic_view_2)
    public LinearLayout mPicView2;
    @BindView(R.id.ids_tv_daily_weather_img_tomorrow_1)
    public ImageView mTvDailyWeatherImgTomorrow1;
    @BindView(R.id.ids_tv_daily_weather_tomorrow_1)
    public TextView mTvDailyWeatherTomorrow1;
    @BindView(R.id.ids_tv_daily_low_temp_tomorrow_1)
    public TextView mTvDailyLowTempTomorrow1;
    @BindView(R.id.ids_tv_daily_high_temp_tomorrow_1)
    public TextView mTvDailyHighTempTomorrow1;
    @BindView(R.id.ids_tv_daily_weather_img_tomorrow_2)
    public ImageView mTvDailyWeatherImgTomorrow2;
    @BindView(R.id.ids_tv_daily_weather_tomorrow_2)
    public TextView mTvDailyWeatherTomorrow2;
    @BindView(R.id.ids_tv_daily_low_temp_tomorrow_2)
    public TextView mTvDailyLowTempTomorrow2;
    @BindView(R.id.ids_tv_daily_high_temp_tomorrow_2)
    public TextView mTvDailyHighTempTomorrow2;
    @BindView(R.id.ids_tv_daily_weather_img_tomorrow_3)
    public ImageView mTvDailyWeatherImgTomorrow3;
    @BindView(R.id.ids_tv_daily_weather_tomorrow_3)
    public TextView mTvDailyWeatherTomorrow3;
    @BindView(R.id.ids_tv_daily_low_temp_tomorrow_3)
    public TextView mTvDailyLowTempTomorrow3;
    @BindView(R.id.ids_tv_daily_high_temp_tomorrow_3)
    public TextView mTvDailyHighTempTomorrow3;
    @BindView(R.id.ids_tv_daily_today_name_tomorrow_1)
    public TextView mTvDailyTodayNameTomorrow1;
    @BindView(R.id.ids_tv_daily_today_name_tomorrow_2)
    public TextView mTvDailyTodayNameTomorrow2;
    @BindView(R.id.ids_tv_daily_today_name_tomorrow_3)
    public TextView mTvDailyTodayNameTomorrow3;
    @BindView(R.id.ids_id_tv_aqi_name)
    public TextView mIdTvAqiName;
    @BindView(R.id.ids_tv_today_week)
    public TextView mTvTodayWeek;
    @BindView(R.id.ids_scroll_view_layout)
    public NestedScrollView mScrollViewLayout;


    private String mCurrentCity;
    private String mCurrentLocation;

    /**
     * 是否是定位的城市
     */
    private boolean isCurrentLocation = false;

    public boolean isCurrentLocation() {
        return isCurrentLocation;
    }

    public void setCurrentLocation(boolean currentLocation) {
        isCurrentLocation = currentLocation;
    }

    public HourlyRecyclerViewAdapter mHourlyRecyclerViewAdapter;

    private WeatherItemPresenter mWeatherItemPresenter = new WeatherItemPresenter();

    private static final String CURRENT_CITY = "currentCity";
    private static final String CURRENT_LOCATION = "currentLocation";

    public WeatherItemFragment() {
    }


    public static WeatherItemFragment newInstance(String currentCity, String currentLocation) {
        WeatherItemFragment fragment = new WeatherItemFragment();
        Bundle args = new Bundle();
        args.putString(CURRENT_CITY, currentCity);
        args.putString(CURRENT_LOCATION, currentLocation);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCurrentCity = getArguments().getString(CURRENT_CITY);
            mCurrentLocation = getArguments().getString(CURRENT_LOCATION);
        }
    }

    @Override
    public void iniView(View view) {

        mSmartRefreshLayout.setEnableRefresh(false);

        mHourlyRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mHourlyRecyclerViewAdapter = new HourlyRecyclerViewAdapter();
        mHourlyRecyclerView.setAdapter(mHourlyRecyclerViewAdapter);
    }

    @Override
    public void bind() {
        mWeatherItemPresenter.attachView(this);

        //连接网络加载天气的数据
        mWeatherItemPresenter.loadData(mCurrentCity, mCurrentLocation);
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
