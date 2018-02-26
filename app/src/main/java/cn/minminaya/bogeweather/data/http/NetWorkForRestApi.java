package cn.minminaya.bogeweather.data.http;

import android.webkit.WebSettings;

import java.io.IOException;

import cn.minminaya.bogeweather.App;
import cn.minminaya.bogeweather.data.http.api.Urls;
import cn.minminaya.bogeweather.data.http.api.WeatherApi;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Niwa on 2018/1/24.
 */

public class NetWorkForRestApi {
//    private static OkHttpClient mOkHttpClient = new OkHttpClient();
    private static WeatherApi weatherApi;
    private static Converter.Factory mGsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory mRxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create();

    public static WeatherApi getWeatherApi(){
        if (weatherApi == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Urls.BASE_URL_FOR_WEATHER)
                    .client(getOkHttpClient())
                    .addConverterFactory(mGsonConverterFactory)
                    .addCallAdapterFactory(mRxJava2CallAdapterFactory)
                    .build();
            weatherApi = retrofit.create(WeatherApi.class);
        }
        return weatherApi;
    }

    /**
     * <p>获取OkHttpCient对象，通过拦截器修改http头部，添加appcode信息</p>
     */
    private static OkHttpClient getOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .removeHeader("User-Agent")
                                .addHeader("User-Agent", WebSettings.getDefaultUserAgent(App.getINSTANCE()))//添加真正的头部
                                .addHeader("Authorization", "APPCODE f81154acc3d54c61852acbcd0b65c3f3")
                                .build();
                        return chain.proceed(request);
                    }
                }).build();
        return okHttpClient;
    }
}
