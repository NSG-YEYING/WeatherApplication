package com.example.weatherapplication.JavaBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyJsonParser {

    public static Weather getWeatherInfo (String str) throws JSONException {

        JSONObject json = new JSONObject(str);
        JSONArray results = json.getJSONArray("results");
        JSONObject location = (JSONObject) results.get(0);
        String city = location.getJSONObject("location").getString("name");
        JSONArray daily = location.getJSONArray("daily");

        Weather ww = new Weather();
        ww.setCity(city);
        JSONObject day = (JSONObject) daily.get(0);
        String date = day.getString("date");
        ww.setDate(date);
        String high = day.getString("high");
        ww.setHigh(high);
        String low = day.getString("low");
        ww.setLow(low);

        return ww;
    }

//  泛型遍历
//    public static ArrayList<Weather> getWeather (String str) throws JSONException {
//
//        ArrayList<Weather> arrayList = new ArrayList<>();
//        JSONObject json = new JSONObject(str);
//        JSONArray results = json.getJSONArray("results");
//        JSONObject location = (JSONObject) results.get(0);
//        String city = location.getJSONObject("location").getString("name");
//        JSONArray daily = location.getJSONArray("daily");
//
//        Weather ww = new Weather();
//        ww.setCity(city);
//        JSONObject day = (JSONObject) daily.get(0);
//        String date = day.getString("date");
//        ww.setDate(date);
//        String high = day.getString("high");
//        ww.setHigh(high);
//        String low = day.getString("low");
//        ww.setLow(low);
//
//        arrayList.add(ww);
//        return arrayList;
//    }

//    遍历三天
//        for (int i = 0; i < daily.length(); i ++){
//        Weather ww = new Weather();
//            ww.setCity(city);
//            JSONObject day = (JSONObject) daily.get(i);
//            String date = day.getString("date");
//            ww.setDate(date);
//            String high = day.getString("high");
//            ww.setHigh(high);
//            String low = day.getString("low");
//            ww.setLow(low);
//
//            arrayList.add(ww);
//        }

}
