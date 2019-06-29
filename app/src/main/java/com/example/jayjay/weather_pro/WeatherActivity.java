package com.example.jayjay.weather_pro;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.*;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class WeatherActivity extends AppCompatActivity {

    final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather";
    final String WEATHER_ICON = "http://openweathermap.org/img/w/";
    final String API_KEY = "5ab525f8cc7ee73090df0a69307771ba";
    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        final TextView txtTemperature = findViewById(R.id.Temperature);
        final TextView txtCity = findViewById(R.id.txtCity);
        final TextView txtStatus = findViewById(R.id.Status);
        final TextView txtWind = findViewById(R.id.Wind);
        final TextView txtRain = findViewById(R.id.Rain);
        //final ImageView weatherIcon= findViewById(R.id.imageView2);


        Intent intent = getIntent();
        final String cityName = intent.getStringExtra("city");

        RequestParams requestParams = new RequestParams();

        requestParams.put("q", cityName);
        requestParams.put("appid", API_KEY);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(WEATHER_URL, requestParams, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Weather weather = Weather.getJson(response);

                txtTemperature.setText(weather.getTemperature());
                txtCity.setText(weather.getCity());
                txtStatus.setText(weather.getStatus());
                txtWind.setText(weather.getWind());
                txtRain.setText(weather.getRain());

                String state = Weather.getCondition(weather.getWeathercondition());
                setIcon(state);
                setTips();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

                Toast.makeText(WeatherActivity.this, "Error occurred while making request!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(WeatherActivity.this, MainActivity.class);
                startActivity(intent);
            }


        });


    }

    public void setTips(){
        final TextView txtTips = findViewById(R.id.Tips);
        if(flag==1){
            txtTips.setText("Enjoy your day outside!");
        }else if(flag==2){
            txtTips.setText("Enjoyable day today!");
        }
        else if(flag==3){
            txtTips.setText("Drive carefully!");
        }
        else if(flag==4){
            txtTips.setText("Umbrella recommended!");
        }
        else if(flag==5){
            txtTips.setText("Umbrella necessary!");
        }
        else if(flag==6){
            txtTips.setText("Wrap up warm!");
        }
        else if(flag==7){
            txtTips.setText("Stay at home!");
        }
    }

    public void setIcon(String value) {

        final ImageView weatherIcon = findViewById(R.id.imageView2);

        if (value.equals("clear")) {
            weatherIcon.setImageDrawable(getResources().getDrawable(R.drawable.clear));
            flag=1;
        } else if (value.equals("cloudy")) {
            weatherIcon.setImageDrawable(getResources().getDrawable(R.drawable.cloudy));
            flag=2;
        } else if (value.equals("partly_cloudy")) {
            weatherIcon.setImageDrawable(getResources().getDrawable(R.drawable.partly_cloudy));
            flag=3;
        } else if (value.equals("partly_rainy")) {
            weatherIcon.setImageDrawable(getResources().getDrawable(R.drawable.partly_rainy));
            flag=4;
        } else if (value.equals("rainy")) {
            weatherIcon.setImageDrawable(getResources().getDrawable(R.drawable.rainy));
            flag=5;
        } else if (value.equals("snow")) {
            weatherIcon.setImageDrawable(getResources().getDrawable(R.drawable.snow));
            flag=6;
        } else if (value.equals("stormy")) {
            weatherIcon.setImageDrawable(getResources().getDrawable(R.drawable.stormy));
            flag=7;
        }
    }
}

