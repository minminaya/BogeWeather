package cn.minminaya.bogeweather.mvp.function_main.fragment;


import android.util.Log;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import cn.minminaya.bogeweather.R;
import cn.minminaya.bogeweather.data.http.NetWorkForRestApi;
import cn.minminaya.bogeweather.data.http.api.WeatherApi;
import cn.minminaya.bogeweather.data.http.model.WeatherModel;
import cn.minminaya.bogeweather.mvp.base.BaseFragment;
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

    public WeatherItemFragment() {
    }

    public static WeatherItemFragment newInstance() {
        WeatherItemFragment fragment = new WeatherItemFragment();
        return fragment;
    }


    @Override
    public void iniView(View view) {

    }

    @Override
    public void bind() {
        NetWorkForRestApi.getWeatherApi()
                .queryMainWeather("上海", null, null, null, null)
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
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
        }

        @Override
        public void onComplete() {

        }
    };

}
