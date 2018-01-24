package cn.minminaya.bogeweather.data.http.model;

/**
 * 近七天的天气
 */
public class DailyBean {
    /**
     * date : 2018-01-24
     * week : 星期三
     * sunrise : 06:52
     * sunset : 17:21
     * night : {"weather":"雨夹雪","templow":"0","img":"6","winddirect":"东北风","windpower":"3-5级"}
     * day : {"weather":"阴","temphigh":"4","img":"2","winddirect":"东北风","windpower":"4-5级"}
     */

    private String date;
    private String week;
    private String sunrise;
    private String sunset;
    private NightBean night;
    private DayBean day;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public NightBean getNight() {
        return night;
    }

    public void setNight(NightBean night) {
        this.night = night;
    }

    public DayBean getDay() {
        return day;
    }

    public void setDay(DayBean day) {
        this.day = day;
    }

}
