package cn.minminaya.bogeweather.data.http.api;

import org.json.JSONObject;

import cn.minminaya.bogeweather.data.http.model.CityModel;
import cn.minminaya.bogeweather.data.http.model.WeatherModel;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 天气API
 * Created by Niwa on 2018/1/24.
 */

public interface WeatherApi {

    /**
     * 天气预报查询接口
     *
     * @param city     城市
     * @param citycode 城市天气代号
     * @param cityid   城市ID
     * @param ip       IP
     * @param location 经纬度 纬度在前，分割 如：39.983424,116.322987
     */
    @GET("query")
    Observable<WeatherModel> queryMainWeather(
            @Query("city") String city,
            @Query("citycode") String citycode,
            @Query("cityid") String cityid,
            @Query("ip") String ip,
            @Query("location") String location
    );

    /**
     * 天气预报查询接口
     *
     * @param city     城市
     */
    @GET("query")
    Observable<ResponseBody> queryMainWeatherForJsonObject(
            @Query("city") String city
    );

    /**
     * 查询所有城市
     */
    @GET("city")
    Observable<CityModel> queryCity(
    );
}
