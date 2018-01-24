package cn.minminaya.bogeweather.data.http.model;

/**
 * 夜间天气
 */

public class NightBean {
    /**
     * weather : 雨夹雪
     * templow : 0
     * img : 6
     * winddirect : 东北风
     * windpower : 3-5级
     */

    private String weather;
    private String templow;
    private String img;
    private String winddirect;
    private String windpower;

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemplow() {
        return templow;
    }

    public void setTemplow(String templow) {
        this.templow = templow;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getWinddirect() {
        return winddirect;
    }

    public void setWinddirect(String winddirect) {
        this.winddirect = winddirect;
    }

    public String getWindpower() {
        return windpower;
    }

    public void setWindpower(String windpower) {
        this.windpower = windpower;
    }
}
