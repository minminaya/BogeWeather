package cn.minminaya.bogeweather.data.http.model;

/**
 * 不同时段天气
 */
public class HourlyBean {
    /**
     * time : 19:00
     * weather : 多云
     * temp : 0
     * img : 1
     */

    private String time;
    private String weather;
    private String temp;
    private String img;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
