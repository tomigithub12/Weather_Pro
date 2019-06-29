package com.example.jayjay.weather_pro;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Weather {

    private String city;
    private String status;
    private String wind;
    private String rain;
    private String temperature;
    private int weatherCondition;


    public Weather(String city, String status, String wind, String rain, String temperature, int weatherCondition) {
        this.city = city;
        this.temperature = temperature;
        this.status = status;
        this.wind = wind;
        this.rain = rain;
        this.weatherCondition= weatherCondition;
    }


    public static Weather getJson(JSONObject jsonObject) {

        String actualWind, actualRain, actualTemperature;
        try {

            String city = jsonObject.getString("name");

            String status = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");

            int weatherCondition = jsonObject.getJSONArray("weather").getJSONObject(0).getInt("id");

            Double mWind = jsonObject.getJSONObject("wind").getDouble("speed");
            actualWind = Double.toString(mWind);

            Double mRain = jsonObject.getJSONObject("clouds").getDouble("all");
            actualRain = Double.toString(mRain);

            Double mTemperature = jsonObject.getJSONObject("main").getDouble("temp");
            mTemperature=round(mTemperature-273.15,1);
            actualTemperature = Double.toString(mTemperature);


            Weather weather = new Weather(city, status, actualWind, actualRain, actualTemperature, weatherCondition);
            return weather;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static double round(double number, int places){
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        number = number * factor;
        long tmp = Math.round(number);
        return (double) tmp / factor;
    }

    public static String getCondition(int value) {

        if (value >= 200 && value <= 232) {
            return "stormy";
        } else if (value >= 300 && value <= 321) {
            return "partly_rainy";
        } else if (value >= 500 && value <= 531) {
            return "rainy";
        } else if (value >= 600 && value <= 622) {
            return "snow";
        } else if (value >= 701 && value <= 781) {
            return "partly_cloudy";
        } else if (value == 800) {
            return "clear";
        } else if (value >= 801 && value <= 804) {
            return "cloudy";
        }

        return null;

    }


    public int getWeathercondition() {
        return weatherCondition;
    }

    public void setWeathercondition(int weathercondition) {
        this.weatherCondition = weathercondition;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperatur) {
        this.temperature = temperature;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }
}
