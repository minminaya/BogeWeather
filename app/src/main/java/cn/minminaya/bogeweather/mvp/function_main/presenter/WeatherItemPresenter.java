package cn.minminaya.bogeweather.mvp.function_main.presenter;

import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.minminaya.bogeweather.C;
import cn.minminaya.bogeweather.R;
import cn.minminaya.bogeweather.data.http.NetWorkForRestApi;
import cn.minminaya.bogeweather.data.http.model.DailyBean;
import cn.minminaya.bogeweather.data.http.model.ResultBean;
import cn.minminaya.bogeweather.data.http.model.WeatherModel;
import cn.minminaya.bogeweather.mvp.base.presenter.BasePresenter;
import cn.minminaya.bogeweather.mvp.function_main.fragment.WeatherItemFragment;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * WeatherItemFragment页的presenter
 * Created by Niwa on 2018/1/27.
 */

public class WeatherItemPresenter extends BasePresenter<WeatherItemFragment> {

    private static final String TAG = "WeatherPresenter_Log";
    /**
     * 当地的日落时间
     */
    private int sunset;

    private Observer<WeatherModel> mWeatherApiObserver = new Observer<WeatherModel>() {
        @Override
        public void onSubscribe(Disposable d) {
//            Log.e(TAG, "onSubscribe: ");
        }

        @Override
        public void onNext(WeatherModel value) {
//            Log.e(TAG, "onNext: " + value.getMsg());
//            Log.e(TAG, "onNext: " + value.getResult().getIndex().get(0).getDetail());
            WeatherItemFragment mWeatherItemFragment = getMvpView();
            ResultBean resultBean = value.getResult();
            mWeatherItemFragment.mTvTemporary.setText(resultBean.getTemp());
            mWeatherItemFragment.mTvMaxTemporary.setText(resultBean.getTemphigh() + "℃");
            mWeatherItemFragment.mTvMixTemporary.setText(resultBean.getTemplow() + "℃");
            mWeatherItemFragment.mTvWeather.setText(resultBean.getWeather());

            mWeatherItemFragment.mHourlyRecyclerViewAdapter.setHourlyBeans(resultBean.getHourly());
            mWeatherItemFragment.mHourlyRecyclerViewAdapter.notifyDataSetChanged();

//            MainActivity mainActivity = ((MainActivity) mWeatherItemFragment.getActivity());
            String currentCityName = value.getResult().getCity();
            mWeatherItemFragment.mToolbarTitle.setText(currentCityName);

            if (getMvpView().isCurrentLocation()) {
                C.CityNameConstant.currentLocationCity = currentCityName;
                C.CityNameConstant.citys.set(0, currentCityName);
            }

            //获取当前天气代号
            String img = value.getResult().getImg();

            sunset = Integer.parseInt(resultBean.getDaily().get(0).getSunset().substring(0, 2));
            Log.e(TAG, "日落时间为: " + sunset);

            setPic(img);


            mWeatherItemFragment.mTvDailyWeatherTomorrow1.setText(resultBean.getDaily().get(1).getDay().getWeather());
            mWeatherItemFragment.mTvDailyWeatherTomorrow2.setText(resultBean.getDaily().get(2).getDay().getWeather());
            mWeatherItemFragment.mTvDailyWeatherTomorrow3.setText(resultBean.getDaily().get(3).getDay().getWeather());

            mWeatherItemFragment.mTvDailyLowTempTomorrow1.setText(resultBean.getDaily().get(1).getNight().getTemplow() + "°");
            mWeatherItemFragment.mTvDailyLowTempTomorrow2.setText(resultBean.getDaily().get(2).getNight().getTemplow() + "°");
            mWeatherItemFragment.mTvDailyLowTempTomorrow3.setText(resultBean.getDaily().get(3).getNight().getTemplow() + "°");

            mWeatherItemFragment.mTvDailyHighTempTomorrow1.setText(resultBean.getDaily().get(1).getDay().getTemphigh() + "°");
            mWeatherItemFragment.mTvDailyHighTempTomorrow2.setText(resultBean.getDaily().get(2).getDay().getTemphigh() + "°");
            mWeatherItemFragment.mTvDailyHighTempTomorrow3.setText(resultBean.getDaily().get(3).getDay().getTemphigh() + "°");

            mWeatherItemFragment.mTvDailyTodayNameTomorrow1.setText(resultBean.getDaily().get(1).getWeek());
            mWeatherItemFragment.mTvDailyTodayNameTomorrow2.setText(resultBean.getDaily().get(2).getWeek());
            mWeatherItemFragment.mTvDailyTodayNameTomorrow3.setText(resultBean.getDaily().get(3).getWeek());

            mWeatherItemFragment.mIdTvAqiName.setText(resultBean.getAqi().getQuality());
            mWeatherItemFragment.mTvTodayWeek.setText(resultBean.getWeek());
            ;/////////////////

            //设置未来三天的天气图片
            setThreeDayImg(mWeatherItemFragment, resultBean.getDaily());


            getMvpView().mScrollViewLayout.setVisibility(View.VISIBLE);

        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();

            //网络连接失败使用默认背景
            getMvpView().mImagPic.setImageResource(R.drawable.pic_sleet);
            getMvpView().mPicView.setBackgroundResource(R.color.blue_sleet_5697D8_4);
            getMvpView().mPicView2.setBackgroundResource(R.color.blue_sleet_5697D8_4);
        }

        @Override
        public void onComplete() {

        }
    };

    private void setThreeDayImg(WeatherItemFragment mWeatherItemFragment, List<DailyBean> list) {
        for (int i = 1; i < 4; i++) {
            int imgNum = Integer.parseInt(list.get(i).getDay().getImg());
            int imgNumRes;
            switch (imgNum) {
                case 99:
                    imgNumRes = R.mipmap.icon_weather_99;
                    break;
                case 301:
                    imgNumRes = R.mipmap.icon_weather_301;
                    break;
                case 302:
                    imgNumRes = R.mipmap.icon_weather_302;
                    break;
                default:
                    imgNumRes = C.icon_pics[imgNum];
                    break;
            }

            switch (i) {
                case 1:
                    mWeatherItemFragment.mTvDailyWeatherImgTomorrow1.setImageResource(imgNumRes);
                    break;
                case 2:
                    mWeatherItemFragment.mTvDailyWeatherImgTomorrow2.setImageResource(imgNumRes);
                    break;
                case 3:
                    mWeatherItemFragment.mTvDailyWeatherImgTomorrow3.setImageResource(imgNumRes);
                    break;
            }
        }
    }

    /**
     * 连接网络加载天气的数据
     */
    public void loadData(String mCurrentCity, String currentLocation) {
        NetWorkForRestApi.getWeatherApi()
                .queryMainWeather(mCurrentCity, null, null, null, currentLocation)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mWeatherApiObserver);
    }


    /**
     * <p>根据不同的图片id设置不同的背景及插画</p>
     *
     * @param imgId 图片的id
     */
    private void setPic(String imgId) {


        switch (imgId) {
            case "0":
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
                Date date = new Date(System.currentTimeMillis());
                int currentTime = Integer.parseInt(simpleDateFormat.format(date).substring(0, 2));
                simpleDateFormat = null;
                date = null;
                if (sunset < currentTime) {
                    //如果日落了
                    getMvpView().mImagPic.setImageResource(R.drawable.pic_sunny_night);
                    getMvpView().mPicView.setBackgroundResource(R.color.blue_sunny_night_7C9EE6_2);
                    getMvpView().mPicView2.setBackgroundResource(R.color.blue_sunny_night_7C9EE6_2);
                } else {
                    getMvpView().mImagPic.setImageResource(R.drawable.pic_sunny_light);
                    getMvpView().mPicView.setBackgroundResource(R.color.green_sunny_light_4EC0F6_1);
                    getMvpView().mPicView2.setBackgroundResource(R.color.green_sunny_light_4EC0F6_1);
                }
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
                getMvpView().mImagPic.setImageResource(R.drawable.pic_snow);
                getMvpView().mPicView.setBackgroundResource(R.color.green_snow_62b1ff_3);
                getMvpView().mPicView2.setBackgroundResource(R.color.green_snow_62b1ff_3);
                break;
            case "6":
                getMvpView().mImagPic.setImageResource(R.drawable.pic_sleet);
                getMvpView().mPicView.setBackgroundResource(R.color.blue_sleet_5697D8_4);
                getMvpView().mPicView2.setBackgroundResource(R.color.blue_sleet_5697D8_4);
                break;
            case "20":
            case "31":
                getMvpView().mImagPic.setImageResource(R.drawable.pic_sandstorm);
                getMvpView().mPicView.setBackgroundResource(R.color.yellow_sandstorm_e99e3c_5);
                getMvpView().mPicView2.setBackgroundResource(R.color.yellow_sandstorm_e99e3c_5);
                break;
            case "4":
            case "3":
                getMvpView().mImagPic.setImageResource(R.drawable.pic_rain_thunder);
                getMvpView().mPicView.setBackgroundResource(R.color.blue_rain_thunder_7187DB_6);
                getMvpView().mPicView2.setBackgroundResource(R.color.blue_rain_thunder_7187DB_6);
                break;

            case "21":
            case "7":
            case "8":
            case "9":
            case "10":
            case "11":
            case "12":
            case "22":
            case "23":
            case "24":
            case "25":
            case "19":
            case "301":
                getMvpView().mImagPic.setImageResource(R.drawable.pic_rain);
                getMvpView().mPicView.setBackgroundResource(R.color.blue_rain_only_6188DA_7);
                getMvpView().mPicView2.setBackgroundResource(R.color.blue_rain_only_6188DA_7);
                break;
            case "2":
                getMvpView().mImagPic.setImageResource(R.drawable.pic_overcast);
                getMvpView().mPicView.setBackgroundResource(R.color.blue_overcast_6D8EB1_8);
                getMvpView().mPicView2.setBackgroundResource(R.color.blue_overcast_6D8EB1_8);
                break;
            case "53":
            case "54":
            case "55":
            case "56":
            case "30":
            case "29":
                getMvpView().mImagPic.setImageResource(R.drawable.pic_haze_polution);
                getMvpView().mPicView.setBackgroundResource(R.color.gray_haze_pollution_7f8195_9);
                getMvpView().mPicView2.setBackgroundResource(R.color.gray_haze_pollution_7f8195_9);
                break;
            case "5":
                getMvpView().mImagPic.setImageResource(R.drawable.pic_hail);
                getMvpView().mPicView.setBackgroundResource(R.color.green_hail_0cb399_10);
                getMvpView().mPicView2.setBackgroundResource(R.color.green_hail_0cb399_10);
                break;
            case "57":
            case "58":
                getMvpView().mImagPic.setImageResource(R.drawable.pic_fog);
                getMvpView().mPicView.setBackgroundResource(R.color.green_fog_8cadd3_11);
                getMvpView().mPicView2.setBackgroundResource(R.color.green_fog_8cadd3_11);
                break;
            case "1":
                getMvpView().mImagPic.setImageResource(R.drawable.pic_cloudy);
                getMvpView().mPicView.setBackgroundResource(R.color.green_cloudy_4ABFEB_12);
                getMvpView().mPicView2.setBackgroundResource(R.color.green_cloudy_4ABFEB_12);
                break;
            case "18":
                getMvpView().mImagPic.setImageResource(R.drawable.pic_fog);
                getMvpView().mPicView.setBackgroundResource(R.color.green_fog_8cadd3_11);
                getMvpView().mPicView2.setBackgroundResource(R.color.green_fog_8cadd3_11);
                break;
        }
    }

}
