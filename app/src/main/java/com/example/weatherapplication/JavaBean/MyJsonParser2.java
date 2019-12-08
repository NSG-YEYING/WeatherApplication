package com.example.weatherapplication.JavaBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyJsonParser2 {

    public static Weather getWeather(String str) throws JSONException {

        JSONObject json = new JSONObject(str);
        JSONArray results = json.getJSONArray("results");
        JSONObject location = (JSONObject) results.get(0);
        JSONObject now = location.getJSONObject("now");
        String weather = now.getString("text");
        String nowTemperature = now.getString("temperature");
        Weather ww = new Weather();
        ww.setWeather(weather);
        ww.setNow(nowTemperature);

        return ww;
    }


}
