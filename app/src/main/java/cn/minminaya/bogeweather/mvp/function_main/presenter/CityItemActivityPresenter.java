package cn.minminaya.bogeweather.mvp.function_main.presenter;

import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cn.minminaya.bogeweather.C;
import cn.minminaya.bogeweather.data.http.NetWorkForRestApi;
import cn.minminaya.bogeweather.mvp.base.presenter.BasePresenter;
import cn.minminaya.bogeweather.mvp.function_main.activity.CityItemActivity;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Niwa on 2018/2/25.
 */

public class CityItemActivityPresenter extends BasePresenter<CityItemActivity> {

    /**
     * 处理EditText的文本
     */
    public void handleCityEditText(final String text) {


        NetWorkForRestApi.getWeatherApi()
                .queryMainWeatherForJsonObject(text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody value) {
                        String jsonStr = null;
                        try {
                            jsonStr = new String(value.bytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        JSONObject mJsonObject = null;
                        try {
                            mJsonObject = new JSONObject(jsonStr);
                            String temp = mJsonObject.getString("status");
                            Log.e("temp", "temp:" + temp);

                            if (temp.equals("202")) {
                                Log.e("next", "遇到错误");
                            }

                            if (temp.equals("0")) {
                                temp = null;
                                //只有不为空才执行逻辑操作
                                C.CityNameConstant.citys.set(4, text);

                                getMvpView().mCityItemRecyclerViewAdapter.notifyDataSetChanged();

                                //发到ManActivity
                                EventBus.getDefault().post(C.FORM_CITYITEMACTITVY_TO＿MAIN_ACTIVITY);
                                getMvpView().onBackPressed();
                                EventBus.getDefault().post(4);//设置当前显示的fragment
                                ToastUtils.showShort("设置成功");

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
