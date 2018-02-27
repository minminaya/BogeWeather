package cn.minminaya.bogeweather.mvp.function_main.presenter;

import android.location.Criteria;
import android.location.Location;
import android.widget.Toast;

import com.nightonke.boommenu.BoomMenuButton;

import cn.minminaya.bogeweather.App;
import cn.minminaya.bogeweather.C;
import cn.minminaya.bogeweather.R;
import cn.minminaya.bogeweather.mvp.base.presenter.BasePresenter;
import cn.minminaya.bogeweather.mvp.function_main.activity.MainActivity;
import cn.minminaya.bogeweather.utils.LocationUtils;

/**
 * Created by Niwa on 2018/1/16.
 */

public class MainPresenter extends BasePresenter<MainActivity> {


    private BoomMenuButton mBoomMenuButton;

    /**
     * 获取当前坐标
     */
    public String getLocationInfo() {
        return getBestLocation();
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
    private String getBestLocation() {
        Criteria c = new Criteria();//Criteria类是设置定位的标准信息（系统会根据你的要求，匹配最适合你的定位供应商），一个定位的辅助信息的类
        c.setPowerRequirement(Criteria.POWER_LOW);//设置低耗电
        c.setAltitudeRequired(true);//设置需要海拔
        c.setBearingAccuracy(Criteria.ACCURACY_COARSE);//设置COARSE精度标准
        c.setAccuracy(Criteria.ACCURACY_LOW);//设置低精度
        //... Criteria 还有其他属性，就不一一介绍了
        Location best = LocationUtils.getBestLocation(getMvpView(), c);
        if (best == null) {
//            Toast.makeText(getMvpView(), " best location is null", Toast.LENGTH_SHORT).show();
        } else {
            App.getINSTANCE().setLocation(true);//标记已定位成功
//            Toast.makeText(getMvpView(), "best location: lat==" + best.getLatitude() + " lng==" + best.getLongitude(), Toast.LENGTH_SHORT).show();
            return best.getLatitude() + "," + best.getLongitude();
        }
        return null;
    }

    /**
     * 设置BOB财当的最新数据
     */
    public void setBOBData() {
        mBoomMenuButton = getMvpView().mBoomMenuButton;
        //如果定位成功了
        if (App.getINSTANCE().isLocation()) {
            mBoomMenuButton.getBoomButton(0).getTextView().setText(C.CityNameConstant.currentLocationCity);
            mBoomMenuButton.getBoomButton(0).getImageView().setImageResource(R.drawable.icon_location_1);
            setBOBImgScale(0);
        } else {
            mBoomMenuButton.getBoomButton(0).getTextView().setText("北京");
            mBoomMenuButton.getBoomButton(0).getImageView().setImageResource(R.drawable.icon_location_1);
            setBOBImgScale(0);
        }
        for (int i = 1; i < 5; i++) {
            mBoomMenuButton.getBoomButton(i).getTextView().setText(C.CityNameConstant.citys.get(i));
            mBoomMenuButton.getBoomButton(i).getImageView().setImageResource(R.drawable.icon_city);
            setBOBImgScale(i);
        }
        mBoomMenuButton.getBoomButton(5).getImageView().setImageResource(R.drawable.icon_close);
        mBoomMenuButton.getBoomButton(6).getImageView().setImageResource(R.drawable.icon_more);
        setBOBImgScale(5);
        setBOBImgScale(6);
    }

    private void setBOBImgScale(int i) {
        mBoomMenuButton.getBoomButton(i).getImageView().setScaleX(0.5f);
        mBoomMenuButton.getBoomButton(i).getImageView().setScaleY(0.5f);
    }

    /**
     * 初始化cityItem
     */
    public void initCityLists() {
        C.CityNameConstant.citys.add("定位中......");
        C.CityNameConstant.citys.add("广州");
        C.CityNameConstant.citys.add("北京");
        C.CityNameConstant.citys.add("杭州");
        C.CityNameConstant.citys.add("成都");

    }
}
