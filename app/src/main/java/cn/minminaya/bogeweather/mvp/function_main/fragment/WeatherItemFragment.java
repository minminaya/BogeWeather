package cn.minminaya.bogeweather.mvp.function_main.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import cn.minminaya.bogeweather.R;
import cn.minminaya.bogeweather.data.http.NetWorkForRestApi;
import cn.minminaya.bogeweather.data.http.api.WeatherApi;
import cn.minminaya.bogeweather.data.http.model.ResultBean;
import cn.minminaya.bogeweather.data.http.model.WeatherModel;
import cn.minminaya.bogeweather.mvp.base.BaseFragment;
import cn.minminaya.bogeweather.mvp.function_main.activity.MainActivity;
import cn.minminaya.bogeweather.mvp.function_main.adapter.HourlyRecyclerViewAdapter;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WeatherItemFragment extends BaseFragment {

    private static final String TAG = "WeatherItemFragment_Log";


    @BindView(R.id.ids_tv_temporary)
    TextView mTvTemporary;
    @BindView(R.id.ids_tv_weather)
    TextView mTvWeather;
    @BindView(R.id.ids_tv_max_temporary)
    TextView mTvMaxTemporary;
    @BindView(R.id.ids_tv_mix_temporary)
    TextView mTvMixTemporary;

    @BindView(R.id.ids_img_pic)
    ImageView mImagPic;

    @BindView(R.id.ids_pic_view)
    RelativeLayout mPicView;
    @BindView(R.id.ids_toolbar_title)
    TextView mToolbarTitle;

    @BindView(R.id.ids_recycler_view_hourly)
    RecyclerView mHourlyRecyclerView;

    private String mCurrentCity;

    HourlyRecyclerViewAdapter mHourlyRecyclerViewAdapter;

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
        NetWorkForRestApi.getWeatherApi()
                .queryMainWeather(mCurrentCity, null, null, null, null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mWeatherApiObserver);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void lazyLoad() {

    }

    @Override
    protected void unBind() {

    }

    @Override
    public int setContView() {
        return R.layout.fragment_weather_item;
    }

    Observer<WeatherModel> mWeatherApiObserver = new Observer<WeatherModel>() {
        @Override
        public void onSubscribe(Disposable d) {
            Log.e(TAG, "onSubscribe: ");
        }

        @Override
        public void onNext(WeatherModel value) {
            Log.e(TAG, "onNext: " + value.getMsg());
            Log.e(TAG, "onNext: " + value.getResult().getIndex().get(0).getDetail());

            ResultBean resultBean = value.getResult();
            mTvTemporary.setText(resultBean.getTemp());
            mTvMaxTemporary.setText(resultBean.getTemphigh() + "℃");
            mTvMixTemporary.setText(resultBean.getTemplow() + "℃");
            mTvWeather.setText(resultBean.getWeather());

            mHourlyRecyclerViewAdapter.setHourlyBeans(resultBean.getHourly());
            mHourlyRecyclerViewAdapter.notifyDataSetChanged();

            MainActivity mainActivity = ((MainActivity) getActivity());

            mToolbarTitle.setText(value.getResult().getCity());

            //获取当前天气代号
            String img = value.getResult().getImg();

            setPic(img, mainActivity);

        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
        }

        @Override
        public void onComplete() {

        }
    };

    /**
     * <p>根据不同的图片id设置不同的背景及插画</p>
     *
     * @param imgId 图片的id
     */
    private void setPic(String imgId, MainActivity mainActivity) {
        switch (imgId) {
            case "0":
                mImagPic.setImageResource(R.drawable.pic_sunny_light);
                mPicView.setBackgroundResource(R.color.green_sunny_light_4EC0F6_1);
                break;
            case "13":
            case "14":
            case "15":
            case "16":
            case "17":
            case "26":
            case "27":
            case "28":
            case "302":
                mImagPic.setImageResource(R.drawable.pic_snow);
                mPicView.setBackgroundResource(R.color.green_snow_62b1ff_3);
                break;
            case "6":
                mImagPic.setImageResource(R.drawable.pic_sleet);
                mPicView.setBackgroundResource(R.color.blue_sleet_5697D8_4);
                break;
            case "20":
            case "31":
                mImagPic.setImageResource(R.drawable.pic_sandstorm);
                mPicView.setBackgroundResource(R.color.yellow_sandstorm_e99e3c_5);
                break;
            case "4":
            case "3":
                mImagPic.setImageResource(R.drawable.pic_rain_thunder);
                mPicView.setBackgroundResource(R.color.blue_rain_thunder_7187DB_6);
                break;

            case "21":
            case "7":
            case "8":
            case "9":
            case "10":
            case "22":
            case "23":
            case "24":
            case "25":
            case "19":
            case "301":
                mImagPic.setImageResource(R.drawable.pic_rain);
                mPicView.setBackgroundResource(R.color.blue_rain_only_6188DA_7);
                break;
            case "2":
                mImagPic.setImageResource(R.drawable.pic_overcast);
                mPicView.setBackgroundResource(R.color.blue_overcast_6D8EB1_8);
                break;
            case "53":
            case "54":
            case "55":
            case "56":
            case "30":
            case "29":
                mImagPic.setImageResource(R.drawable.pic_haze_polution);
                mPicView.setBackgroundResource(R.color.gray_haze_pollution_7f8195_9);
                break;
            case "5":
                mImagPic.setImageResource(R.drawable.pic_hail);
                mPicView.setBackgroundResource(R.color.green_hail_0cb399_10);
                break;
            case "57":
            case "58":
                mImagPic.setImageResource(R.drawable.pic_fog);
                mPicView.setBackgroundResource(R.color.green_fog_8cadd3_11);
                break;
            case "12":
                mImagPic.setImageResource(R.drawable.pic_cloudy);
                mPicView.setBackgroundResource(R.color.green_cloudy_4ABFEB_12);
                break;
        }
    }
}
