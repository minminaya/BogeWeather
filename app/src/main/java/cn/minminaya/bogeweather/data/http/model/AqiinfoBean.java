package cn.minminaya.bogeweather.data.http.model;

public class AqiinfoBean {
    /**
     * level : 一级
     * color : #00e400
     * affect : 空气质量令人满意，基本无空气污染
     * measure : 各类人群可正常活动
     */

    private String level;
    private String color;
    private String affect;
    private String measure;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAffect() {
        return affect;
    }

    public void setAffect(String affect) {
        this.affect = affect;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }
}
