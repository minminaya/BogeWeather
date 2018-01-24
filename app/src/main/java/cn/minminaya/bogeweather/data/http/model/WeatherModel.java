package cn.minminaya.bogeweather.data.http.model;


/** Weather接口主Model
 * Created by Niwa on 2018/1/24.
 */

public class WeatherModel {

    /**
     * status : 0
     * msg : ok
     * result : {"city":"上海","cityid":"24","citycode":"101020100","date":"2018-01-24","week":"星期三","weather":"阴","temp":"3","temphigh":"4","templow":"0","img":"2","humidity":"57","pressure":"1031","windspeed":"4.5","winddirect":"东北风","windpower":"3级","updatetime":"2018-01-24 19:23:00","index":[{"iname":"空调指数","ivalue":"开启制暖空调","detail":"您将感到有些冷，可以适当开启制暖空调调节室内温度，以免着凉感冒。"},{"iname":"运动指数","ivalue":"较不宜","detail":"阴天，且天气寒冷，推荐您在室内进行低强度运动；若坚持户外运动，请选择合适的运动并注意保暖。"},{"iname":"紫外线指数","ivalue":"最弱","detail":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"},{"iname":"感冒指数","ivalue":"极易发","detail":"将有一次强降温过程，天气寒冷，且风力较强，极易发生感冒，请特别注意增加衣服保暖防寒。"},{"iname":"洗车指数","ivalue":"不宜","detail":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"},{"iname":"空气污染扩散指数","ivalue":"良","detail":"气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。"},{"iname":"穿衣指数","ivalue":"冷","detail":"天气冷，建议着棉服、羽绒服、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣、冬大衣或厚羽绒服。"}],"aqi":{"so2":"9","so224":"9","no2":"30","no224":"25","co":"0.560","co24":"0.610","o3":"69","o38":"77","o324":"82","pm10":"28","pm1024":"34","pm2_5":"14","pm2_524":"23","iso2":"4","ino2":"15","ico":"6","io3":"22","io38":"39","ipm10":"28","ipm2_5":"21","aqi":"39","primarypollutant":"O3","quality":"优","timepoint":"2018-01-24 19:00:00","aqiinfo":{"level":"一级","color":"#00e400","affect":"空气质量令人满意，基本无空气污染","measure":"各类人群可正常活动"}},"daily":[{"date":"2018-01-24","week":"星期三","sunrise":"06:52","sunset":"17:21","night":{"weather":"雨夹雪","templow":"0","img":"6","winddirect":"东北风","windpower":"3-5级"},"day":{"weather":"阴","temphigh":"4","img":"2","winddirect":"东北风","windpower":"4-5级"}},{"date":"2018-01-25","week":"星期四","sunrise":"06:51","sunset":"17:22","night":{"weather":"中雪","templow":"0","img":"15","winddirect":"东北风","windpower":"4-5级"},"day":{"weather":"雨夹雪","temphigh":"3","img":"6","winddirect":"东北风","windpower":"4-5级"}},{"date":"2018-01-26","week":"星期五","sunrise":"06:51","sunset":"17:23","night":{"weather":"阴","templow":"0","img":"2","winddirect":"北风","windpower":"3-5级"},"day":{"weather":"阴","temphigh":"3","img":"2","winddirect":"东北风","windpower":"4-5级"}},{"date":"2018-01-27","week":"星期六","sunrise":"06:50","sunset":"17:24","night":{"weather":"中雨","templow":"1","img":"8","winddirect":"东北风","windpower":"4-5级"},"day":{"weather":"小雨","temphigh":"4","img":"7","winddirect":"北风","windpower":"3-5级"}},{"date":"2018-01-28","week":"星期日","sunrise":"06:50","sunset":"17:25","night":{"weather":"晴","templow":"1","img":"0","winddirect":"北风","windpower":"3-5级"},"day":{"weather":"中雨","temphigh":"5","img":"8","winddirect":"北风","windpower":"3-5级"}},{"date":"2018-01-29","week":"星期一","sunrise":"06:49","sunset":"17:26","night":{"weather":"晴","templow":"-2","img":"0","winddirect":"北风","windpower":"微风"},"day":{"weather":"晴","temphigh":"3","img":"0","winddirect":"东北风","windpower":"微风"}},{"date":"2018-01-30","week":"星期二","sunrise":"06:49","sunset":"17:27","night":{"weather":"小雨","templow":"-2","img":"7","winddirect":"西北风","windpower":"微风"},"day":{"weather":"晴","temphigh":"3","img":"0","winddirect":"东北风","windpower":"微风"}}],"hourly":[{"time":"19:00","weather":"多云","temp":"0","img":"1"},{"time":"20:00","weather":"多云","temp":"0","img":"1"},{"time":"21:00","weather":"多云","temp":"1","img":"1"},{"time":"22:00","weather":"小雨","temp":"1","img":"7"},{"time":"23:00","weather":"雨夹雪","temp":"1","img":"6"},{"time":"0:00","weather":"多云","temp":"1","img":"1"},{"time":"1:00","weather":"多云","temp":"1","img":"1"},{"time":"2:00","weather":"雨夹雪","temp":"1","img":"6"},{"time":"3:00","weather":"小雨","temp":"1","img":"7"},{"time":"4:00","weather":"多云","temp":"1","img":"1"},{"time":"5:00","weather":"多云","temp":"1","img":"1"},{"time":"6:00","weather":"多云","temp":"1","img":"1"},{"time":"7:00","weather":"雨夹雪","temp":"2","img":"6"},{"time":"8:00","weather":"雨夹雪","temp":"2","img":"6"},{"time":"9:00","weather":"多云","temp":"2","img":"1"},{"time":"10:00","weather":"多云","temp":"2","img":"1"},{"time":"11:00","weather":"多云","temp":"3","img":"1"},{"time":"12:00","weather":"雨夹雪","temp":"3","img":"6"},{"time":"13:00","weather":"多云","temp":"2","img":"1"},{"time":"14:00","weather":"雨夹雪","temp":"2","img":"6"},{"time":"15:00","weather":"雨夹雪","temp":"2","img":"6"},{"time":"16:00","weather":"雨夹雪","temp":"2","img":"6"},{"time":"17:00","weather":"多云","temp":"1","img":"1"},{"time":"18:00","weather":"多云","temp":"1","img":"1"}]}
     */

    private String status;
    private String msg;
    private ResultBean result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

}
