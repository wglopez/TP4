package com.example.tp4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements SensorEventListener{
    private TextView iluminacion;
    //Se crea el administrador de sensores
    SensorManager mSensorManager;
    CameraManager cameraManager;
    private String getCameraID="";
    private float luminancia;
    private float umbral;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        iluminacion=findViewById(R.id.iluminacion);
        mSensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        cameraManager= (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        extras=getIntent().getExtras();
        umbral=extras.getFloat("umbral");


        //Iniciar sensores
        mSensorManager.registerListener((SensorEventListener) this, mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_NORMAL);
        try {
            getCameraID = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_second);
        iluminacion=findViewById(R.id.iluminacion);
        mSensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        cameraManager= (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        extras=getIntent().getExtras();
        umbral=extras.getFloat("umbral");


        //Iniciar sensores
        mSensorManager.registerListener((SensorEventListener) this, mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_NORMAL);
        try {
            getCameraID = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    protected void onStop() {
        super.onStop();
        //Apagar sensores
        mSensorManager.unregisterListener((SensorEventListener) this, mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT));

    }

    protected void onPause() {
        super.onPause();
        //Apagar sensores
        mSensorManager.unregisterListener((SensorEventListener) this, mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT));

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        luminancia=sensorEvent.values[0];
        String lum=Float.toString(luminancia);
        iluminacion.setText(lum);


        if (luminancia<umbral){
            try {
                cameraManager.setTorchMode(getCameraID, true);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                cameraManager.setTorchMode(getCameraID, false);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
