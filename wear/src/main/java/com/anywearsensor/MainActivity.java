package com.anywearsensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.view.View;
import android.widget.TextView;

import com.shared.WearConnectionHandler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends WearableActivity implements TextChangedListener {
    private TextView _titleView;
    private TextView _valuesView;
    private SensorManager _sensorManager;
    private WearConnectionHandler _wearConnectionHandler;
    private SensorEventHandler _sensorEventHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAmbientEnabled();

        _titleView = (TextView) findViewById(R.id.text_title);
        _valuesView = (TextView) findViewById(R.id.text_values);

        _wearConnectionHandler = new WearConnectionHandler(this);
        _sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        _sensorEventHandler = new SensorEventHandler(_sensorManager, this);

    }

    @Override
    public void onResume() {
        super.onResume();
        _sensorEventHandler.onStartListening();

    }

    @Override
    public void onPause() {
        super.onPause();
        _sensorEventHandler.onPauseListening();
    }


    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        updateDisplay();
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
        updateDisplay();
    }

    @Override
    public void onExitAmbient() {
        updateDisplay();
        super.onExitAmbient();
    }

    private void updateDisplay() {
        if (isAmbient()) {

        } else {

        }
    }

    @Override
    public void onTextChange(String text) {
        _valuesView.setText(text);
    }
}
