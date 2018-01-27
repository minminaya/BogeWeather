package cn.minminaya.bogeweather.mvp.function_main.presenter;

import android.util.Log;

import cn.minminaya.bogeweather.R;
import cn.minminaya.bogeweather.data.http.NetWorkForRestApi;
import cn.minminaya.bogeweather.data.http.model.ResultBean;
import cn.minminaya.bogeweather.data.http.model.WeatherModel;
import cn.minminaya.bogeweather.mvp.base.BaseFragment;
import cn.minminaya.bogeweather.mvp.base.presenter.BasePresenter;
import cn.minminaya.bogeweather.mvp.function_main.activity.MainActivity;
import cn.minminaya.bogeweather.mvp.function_main.fragment.WeatherItemFragment;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Niwa on 2018/1/27.
 */

public class WeatherItemPresenter extends BasePresenter<WeatherItemFragment> {

    private static final String TAG = "WeatherPresenter_Log";


    private Observer<WeatherModel> mWeatherApiObserver = new Observer<WeatherModel>() {
        @Override
        public void onSubscribe(Disposable d) {
            Log.e(TAG, "onSubscribe: ");
        }

        @Override
        public void onNext(WeatherModel value) {
            Log.e(TAG, "onNext: " + value.getMsg());
            Log.e(TAG, "onNext: " + value.getResult().getIndex().get(0).getDetail());
            WeatherItemFragment mWeatherItemFragment = getMvpView();
            ResultBean resultBean = value.getResult();
            mWeatherItemFragment.mTvTemporary.setText(resultBean.getTemp());
            mWeatherItemFragment.mTvMaxTemporary.setText(resultBean.getTemphigh() + "℃");
            mWeatherItemFragment.mTvMixTemporary.setText(resultBean.getTemplow() + "℃");
            mWeatherItemFragment.mTvWeather.setText(resultBean.getWeather());

            mWeatherItemFragment.mHourlyRecyclerViewAdapter.setHourlyBeans(resultBean.getHourly());
            mWeatherItemFragment.mHourlyRecyclerViewAdapter.notifyDataSetChanged();

            MainActivity mainActivity = ((MainActivity) mWeatherItemFragment.getActivity());

            mWeatherItemFragment.mToolbarTitle.setText(value.getResult().getCity());

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
     * 连接网络加载天气的数据
     */
    public void loadData(String mCurrentCity) {
        NetWorkForRestApi.getWeatherApi()
                .queryMainWeather(mCurrentCity, null, null, null, null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mWeatherApiObserver);
    }


    /**
     * <p>根据不同的图片id设置不同的背景及插画</p>
     *
     * @param imgId 图片的id
     */
    private void setPic(String imgId, MainActivity mainActivity) {


        switch (imgId) {
            case "0":
                getMvpView().mImagPic.setImageResource(R.drawable.pic_sunny_light);
                getMvpView().mPicView.setBackgroundResource(R.color.green_sunny_light_4EC0F6_1);
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
                break;
            case "6":
                getMvpView().mImagPic.setImageResource(R.drawable.pic_sleet);
                getMvpView().mPicView.setBackgroundResource(R.color.blue_sleet_5697D8_4);
                break;
            case "20":
            case "31":
                getMvpView().mImagPic.setImageResource(R.drawable.pic_sandstorm);
                getMvpView().mPicView.setBackgroundResource(R.color.yellow_sandstorm_e99e3c_5);
                break;
            case "4":
            case "3":
                getMvpView().mImagPic.setImageResource(R.drawable.pic_rain_thunder);
                getMvpView().mPicView.setBackgroundResource(R.color.blue_rain_thunder_7187DB_6);
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
                getMvpView().mImagPic.setImageResource(R.drawable.pic_rain);
                getMvpView().mPicView.setBackgroundResource(R.color.blue_rain_only_6188DA_7);
                break;
            case "2":
                getMvpView().mImagPic.setImageResource(R.drawable.pic_overcast);
                getMvpView().mPicView.setBackgroundResource(R.color.blue_overcast_6D8EB1_8);
                break;
            case "53":
            case "54":
            case "55":
            case "56":
            case "30":
            case "29":
                getMvpView().mImagPic.setImageResource(R.drawable.pic_haze_polution);
                getMvpView().mPicView.setBackgroundResource(R.color.gray_haze_pollution_7f8195_9);
                break;
            case "5":
                getMvpView().mImagPic.setImageResource(R.drawable.pic_hail);
                getMvpView().mPicView.setBackgroundResource(R.color.green_hail_0cb399_10);
                break;
            case "57":
            case "58":
                getMvpView().mImagPic.setImageResource(R.drawable.pic_fog);
                getMvpView().mPicView.setBackgroundResource(R.color.green_fog_8cadd3_11);
                break;
            case "12":
                getMvpView().mImagPic.setImageResource(R.drawable.pic_cloudy);
                getMvpView().mPicView.setBackgroundResource(R.color.green_cloudy_4ABFEB_12);
                break;
        }
    }

}
