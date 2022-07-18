package jgeun.study.maskinfo.model;

import com.squareup.moshi.Json;

public class Store implements  Comparable<Store>{
    @Json(name = "addr")
    String addr;

    @Json(name = "code")
    String code;

    @Json(name = "created_at")
    String created_at;

    @Json(name = "lat")
    double lat;

    @Json(name = "lng")
    double lng;

    @Json(name = "name")
    String name;

    @Json(name = "remain_stat")
    String remain_stat;

    @Json(name = "stock_at")
    String stock_at;

    @Json(name = "type")
    String type;

    private double distance;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemain_stat() {
        return remain_stat;
    }

    public void setRemain_stat(String remain_stat) {
        this.remain_stat = remain_stat;
    }

    public String getStock_at() {
        return stock_at;
    }

    public void setStock_at(String stock_at) {
        this.stock_at = stock_at;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int compareTo(Store s) {
        return Double.compare(this.getDistance(), s.getDistance());
    }
}