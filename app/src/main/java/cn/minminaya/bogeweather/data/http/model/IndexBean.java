package cn.minminaya.bogeweather.data.http.model;

/**
 * 各种指数
 */
public class IndexBean {
    /**
     * iname : 空调指数
     * ivalue : 开启制暖空调
     * detail : 您将感到有些冷，可以适当开启制暖空调调节室内温度，以免着凉感冒。
     */

    private String iname;
    private String ivalue;
    private String detail;

    public String getIname() {
        return iname;
    }

    public void setIname(String iname) {
        this.iname = iname;
    }

    public String getIvalue() {
        return ivalue;
    }

    public void setIvalue(String ivalue) {
        this.ivalue = ivalue;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
