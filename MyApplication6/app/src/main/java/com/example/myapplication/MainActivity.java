package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView textViewAcc,textViewLight;
    SensorManager sensorManager,sensorManagerAcce;
    Sensor sensorLight,sensorAcc;
    Integer lightFlag = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewAcc = (TextView) findViewById(R.id.textViewAcc);
        textViewLight = (TextView) findViewById(R.id.textViewLight);

        sensorManager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);

        sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorAcc = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        sensorManager.registerListener(this,sensorLight,SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(this,sensorAcc,SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_LIGHT){
            if(event.values[0]>=750){
                textViewLight.setText("Masada");
            }else{
                textViewLight.setText("Cepte");
            }
        }else if(event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION){
            if(event.values[0]>0.1 || event.values[0] < -0.1 ||
               event.values[1]>0.1 || event.values[1] < -0.1 ||
               event.values[2]>0.1 || event.values[2] < -0.1){
                textViewAcc.setText("Hareketli");
            }else{
                textViewAcc.setText("Duruyor");
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}