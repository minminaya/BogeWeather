package cn.minminaya.bogeweather;

import java.util.ArrayList;
import java.util.List;

/**
 * 常量类
 * Created by Niwa on 2018/1/26.
 */

public class C {

    public static int[] icon_pics = new int[]{
            R.mipmap.icon_weather_0,
            R.mipmap.icon_weather_1,
            R.mipmap.icon_weather_2,
            R.mipmap.icon_weather_3,
            R.mipmap.icon_weather_4,
            R.mipmap.icon_weather_5,
            R.mipmap.icon_weather_6,
            R.mipmap.icon_weather_7,
            R.mipmap.icon_weather_8,
            R.mipmap.icon_weather_9,
            R.mipmap.icon_weather_10,
            R.mipmap.icon_weather_11,
            R.mipmap.icon_weather_12,
            R.mipmap.icon_weather_13,
            R.mipmap.icon_weather_14,
            R.mipmap.icon_weather_15,
            R.mipmap.icon_weather_16,
            R.mipmap.icon_weather_17,
            R.mipmap.icon_weather_18,
            R.mipmap.icon_weather_19,
            R.mipmap.icon_weather_20,
            R.mipmap.icon_weather_21,
            R.mipmap.icon_weather_22,
            R.mipmap.icon_weather_23,
            R.mipmap.icon_weather_24,
            R.mipmap.icon_weather_25,
            R.mipmap.icon_weather_26,
            R.mipmap.icon_weather_27,
            R.mipmap.icon_weather_28,
            R.mipmap.icon_weather_29,
            R.mipmap.icon_weather_30,
            R.mipmap.icon_weather_31,
            R.mipmap.icon_weather_32,
            0,
            0,
            0,
            0,
            0,
            0,
            R.mipmap.icon_weather_39,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            R.mipmap.icon_weather_49,
            0,
            0,
            0,
            R.mipmap.icon_weather_53,
            R.mipmap.icon_weather_54,
            R.mipmap.icon_weather_55,
            R.mipmap.icon_weather_56,
            R.mipmap.icon_weather_57,
            R.mipmap.icon_weather_58,
    };


    /**
     * 模拟的数据
     */
    public static class CityNameConstant {
        public static String[] cityConstant = new String[]{"定位中.......", "广州", "湛江", "邯郸", "拉萨", "点击收起", "更多城市"};

        public static List<String> citys = new ArrayList<>();

        public static String currentLocationCity = null;
    }

    public final static int FORM_CITYITEMACTITVY_TO＿MAIN_ACTIVITY = 0x9343434;
    public  static String cityTemp = null;
}
