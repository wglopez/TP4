package com.example.tp4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView iluminacion;
    private EditText umbral;
    private Button btnUmbral;
    private Float umb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iluminacion=findViewById(R.id.descripcion);
        umbral=findViewById(R.id.umbral);
        btnUmbral=findViewById(R.id.btnUmbral);
        btnUmbral.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnUmbral){
            Intent intent=new Intent(this, SecondActivity.class);
            umb=Float.parseFloat(umbral.getText().toString());
            if (umbral.getText().toString() == ""){
                umbral.setText("0");
                umb= Float.valueOf(0);
            }
            intent.putExtra("umbral", umb);
            startActivity(intent);
        }
    }
}


