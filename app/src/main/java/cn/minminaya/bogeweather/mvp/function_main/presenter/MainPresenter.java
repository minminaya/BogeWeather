package cn.minminaya.bogeweather.mvp.function_main.presenter;

import android.widget.Toast;

import cn.minminaya.bogeweather.mvp.base.presenter.BasePresenter;
import cn.minminaya.bogeweather.mvp.function_main.activity.MainActivity;

/**
 * Created by Niwa on 2018/1/16.
 */

public class MainPresenter extends BasePresenter<MainActivity> {

    public void test(){
        Toast.makeText(getMvpView(), "测试成功", Toast.LENGTH_SHORT).show();
    }
}
