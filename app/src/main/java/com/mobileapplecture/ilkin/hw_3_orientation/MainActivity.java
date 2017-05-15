package com.mobileapplecture.ilkin.hw_3_orientation;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView txt_x;
    TextView txt_y;
    TextView txt_z;
    TextView txt;

    ImageView orientation;

    // nanosecond to second
    private static final float NS2S = 1.0f / 1000000000.0f;
    private SensorManager mSensorManager;
    private Sensor mAcc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        Log.i("Sensor", "oncreate");


        txt_x = (TextView) findViewById(R.id.txt_X);
        txt_y = (TextView) findViewById(R.id.txt_Y);
        txt_z = (TextView) findViewById(R.id.txt_Z);

        txt = (TextView) findViewById(R.id.text_o);

        orientation = (ImageView) findViewById(R.id.img_orientation);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAcc = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            setContentView(R.layout.main_land);
        }
        else {
            setContentView(R.layout.activity_main);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.i("Sensor", "onsensorchanged");


        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        txt_x.setText(String.valueOf(x));
        txt_y.setText(String.valueOf(y));
        txt_z.setText(String.valueOf(z));


        if ((x > 7 && x < 10)) {
            orientation.setBackgroundResource(R.drawable.lan);
            txt.setText("Landscape Left");
            Log.e("Sensor", "Landscape");
        } else if ((x > -10 && x < -7)) {
            orientation.setBackgroundResource(R.drawable.lan);
            txt.setText("Landscape Right");
            Log.e("Sensor", "Landscape");
        } else if (y > 7 && y < 10) {
            orientation.setBackgroundResource(R.drawable.po);
            txt.setText("Portrait Up");
            Log.e("Sensor", "Portrait");
        } else if (y > -10 && y < -7) {
            orientation.setBackgroundResource(R.drawable.po);
            txt.setText("Portrait Down");
            Log.e("Sensor", "Portrait");
        } else if (z > 7 && z < 10) {
            orientation.setBackgroundResource(R.drawable.flat);
            txt.setText("Flat Up");
            Log.e("Sensor", "Flat");
        } else if (z > -10 && z < -7) {
            orientation.setBackgroundResource(R.drawable.flat);
            txt.setText("Flat Down");
            Log.e("Sensor", "Flat");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        Log.i("Sensor", "onresume");
        super.onResume();

        // sensor delay = 1000000 microsec = 1 sec = 1 Hz
        mSensorManager.registerListener(this, mAcc, 1000000);
    }

    @Override
    protected void onPause() {
        Log.i("Sensor", "onpause");
        super.onPause();
        mSensorManager.unregisterListener(this, mAcc);
    }
}
