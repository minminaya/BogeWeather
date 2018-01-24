package cn.minminaya.bogeweather.data.http.model;

import java.util.List;

/** City Model
 * Created by Niwa on 2018/1/24.
 */

public class CityModel {

    /**
     * status : 0
     * msg : ok
     * result : [{"cityid":"1","parentid":"0","citycode":"101010100","city":"北京"},{"cityid":"24","parentid":"0","citycode":"101020100","city":"上海"},{"cityid":"26","parentid":"0","citycode":"101030100","city":"天津"},{"cityid":"31","parentid":"0","citycode":"101040100","city":"重庆"},{"cityid":"32","parentid":"0","citycode":"101320101","city":"香港"}]
     */

    private String status;
    private String msg;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * cityid : 1
         * parentid : 0
         * citycode : 101010100
         * city : 北京
         */

        private String cityid;
        private String parentid;
        private String citycode;
        private String city;

        public String getCityid() {
            return cityid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public String getParentid() {
            return parentid;
        }

        public void setParentid(String parentid) {
            this.parentid = parentid;
        }

        public String getCitycode() {
            return citycode;
        }

        public void setCitycode(String citycode) {
            this.citycode = citycode;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }
}
