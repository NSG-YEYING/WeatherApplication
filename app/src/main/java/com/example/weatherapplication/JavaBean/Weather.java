package com.example.weatherapplication.JavaBean;

public class Weather {

    private String city;
    private String date;
    private String high;
    private String low;
    private String now;
    private String weather;

    public String getCity() {
        return city;
    }

    void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    void setDate(String date) {
        this.date = date;
    }

    public String getHigh() {
        return high;
    }

    void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    void setLow(String low) {
        this.low = low;
    }

    public String getNow() {
        return now;
    }

    void setNow(String now) {
        this.now = now;
    }

    public String getWeather() {
        return weather;
    }

    void setWeather(String weather) {
        this.weather = weather;
    }
}
