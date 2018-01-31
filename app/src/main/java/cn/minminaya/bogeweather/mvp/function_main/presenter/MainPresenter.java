package cn.minminaya.bogeweather.mvp.function_main.presenter;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.blankj.utilcode.util.NetworkUtils;

import cn.minminaya.bogeweather.mvp.base.presenter.BasePresenter;
import cn.minminaya.bogeweather.mvp.function_main.activity.MainActivity;
import cn.minminaya.bogeweather.utils.LocationUtils;

/**
 * Created by Niwa on 2018/1/16.
 */

public class MainPresenter extends BasePresenter<MainActivity> {

    public void test() {
        getBestLocation();
    }

    /**
     * 通过网络等获取定位信息
     */
    private void getNetworkLocation() {
        Location net = LocationUtils.getNetWorkLocation(getMvpView());
        if (net == null) {
            Toast.makeText(getMvpView(), "net location is null", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getMvpView(), "network location: lat==" + net.getLatitude() + "  lng==" + net.getLongitude(), Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 采用最好的方式获取定位信息
     */
    private void getBestLocation() {
        Criteria c = new Criteria();//Criteria类是设置定位的标准信息（系统会根据你的要求，匹配最适合你的定位供应商），一个定位的辅助信息的类
        c.setPowerRequirement(Criteria.POWER_LOW);//设置低耗电
        c.setAltitudeRequired(true);//设置需要海拔
        c.setBearingAccuracy(Criteria.ACCURACY_COARSE);//设置COARSE精度标准
        c.setAccuracy(Criteria.ACCURACY_LOW);//设置低精度
        //... Criteria 还有其他属性，就不一一介绍了
        Location best = LocationUtils.getBestLocation(getMvpView(), c);
        if (best == null) {
            Toast.makeText(getMvpView(), " best location is null", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getMvpView(), "best location: lat==" + best.getLatitude() + " lng==" + best.getLongitude(), Toast.LENGTH_SHORT).show();
        }
    }

}
