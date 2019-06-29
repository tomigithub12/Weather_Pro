package com.example.jayjay.weather_pro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button getWeather= findViewById(R.id.startbtn);
        final EditText edit = (EditText)findViewById(R.id.FillCity);


        getWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newCityName =  edit.getText().toString();
                Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                //intent.putExtra("city", newCityName);
                if(newCityName.equals("")) {
                    intent.putExtra("city", "Vienna");
                    //Toast.makeText(MainActivity.this, "Please enter city name!", Toast.LENGTH_LONG).show();
                } else{
                    intent.putExtra("city", newCityName);
                }
                startActivity(intent);

            }
        });
    }
}
