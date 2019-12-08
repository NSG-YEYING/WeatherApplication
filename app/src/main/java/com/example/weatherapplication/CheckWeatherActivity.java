package com.example.weatherapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapplication.JavaBean.MyJsonParser;
import com.example.weatherapplication.JavaBean.MyJsonParser2;
import com.example.weatherapplication.JavaBean.Weather;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CheckWeatherActivity extends AppCompatActivity {

    EditText editText_search;
    Button button_search;
    TextView nowTemp,
             nowCity,
             weather,
             low,
             high,
             nowDate;

//    ArrayList<Weather> arrayList;
    Weather nowInfo_1,
            nowInfo_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_weather);
        initView();
    }

    private void initView() {

        editText_search = findViewById(R.id.editText_search);
        button_search = findViewById(R.id.button_search);
        nowTemp = findViewById(R.id.textView_showNowTemp);
        nowCity = findViewById(R.id.textView_city);
        weather = findViewById(R.id.textView_weather);
        low = findViewById(R.id.textView_lowestTemp);
        high = findViewById(R.id.textView_highestTemp);
        nowDate = findViewById(R.id.textView_currentDate);

//        查询事件
        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                线程
                new Thread(){
                    @Override
                    public void run() {
//                        获取输入的城市
                        final String city = editText_search.getText().toString().trim();
//                        解析网址 1
                        parsingUrl(city);
//                        解析网址 2
                        parsingUrl_2(city);
//                        更改主界面的 UI
                        changeUI();
                    }
                }.start();
            }
        });
    }

//    解析网址 1
    private void parsingUrl(String city){
//                        解析网址
        String path_URL = "https://api.seniverse.com/v3/weather/daily.json?key=SsSyfgCRrPfsYJr34&location=" + city + "&language=zh-Hans&unit=c&start=0&days=5";
        try {
            URL url = new URL(path_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(5000);      //  连接时长
            conn.setRequestMethod("GET");   //  提交数据方式
            InputStream input = conn.getInputStream();
            StringBuilder str = new StringBuilder();
            int len;
            byte[] buffer = new byte[1024];
            while ((len = input.read(buffer)) > 0){
                str.append(new String(buffer, 0, len));
            }
//            arrayList = MyJsonParser.getWeather(str.toString());

            nowInfo_1 = MyJsonParser.getWeatherInfo(str.toString());

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

//    解析网址 2
    private void parsingUrl_2(String city){
        String url_path = "https://api.seniverse.com/v3/weather/now.json?key=SsSyfgCRrPfsYJr34&location=" + city + "&language=zh-Hans&unit=c";
        try {
            URL url = new URL(url_path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            InputStream input = conn.getInputStream();
            StringBuilder str = new StringBuilder();
            int len;
            byte [] buffer = new byte[1024];
            while ((len = input.read(buffer)) > 0){
                str.append(new String(buffer, 0, len));
            }

            nowInfo_2 = MyJsonParser2.getWeather(str.toString());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            showDialog("获取天气情况异常");
        }
    }

//    更改主界面的 UI
    private void changeUI(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                nowCity.setText(String.format("当前城市：%s", nowInfo_1.getCity()));
                high.setText(String.format("最高温度：%s", nowInfo_1.getHigh()));
                low.setText(String.format("最低温度：%s", nowInfo_1.getLow()));
                nowDate.setText(String.format("当前日期：%s", nowInfo_1.getDate()));
                weather.setText(String.format("天气情况：%s", nowInfo_2.getWeather()));
                nowTemp.setText(String.format("%s℃", nowInfo_2.getNow()));
            }
        });
    }

//    解析异常 dialog
    private void showDialog(final String info){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), info, Toast.LENGTH_SHORT).show();
            }
        });
    }

//                nowCity.setText(String.format("当前城市：%s", arrayList.get(0).getCity()));
//                high.setText(String.format("最高温度：%s", arrayList.get(0).getHigh()));
//                low.setText(String.format("最低温度：%s", arrayList.get(0).getLow()));
//                nowDate.setText(String.format("当前日期：%s", arrayList.get(0).getDate()));

}
