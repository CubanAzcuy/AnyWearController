package com.anywearsensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

/**
 * Created by robertgross on 4/3/16.
 */
public class SensorEventHandler implements SensorEventListener {

    private final SensorManager _sensorManager;
    private float gyroX;
    private float gyroY;
    private float gyroZ;
    private float acclerationX;
    private float acclerationY;
    private float acclerationZ;
    private float gravitynX;
    private float gravityY;
    private float gravityZ;
    private boolean _stopResult;
    private TextChangedListener _listener;

    public SensorEventHandler(SensorManager sensorManager, TextChangedListener listener) {
        _sensorManager = sensorManager;
        _listener = listener;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // If sensor is unreliable, then just return
        if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE || _stopResult)
        {
            return;
        }
        float[] mGeomagnetic;
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            mGeomagnetic = event.values.clone();
        }


        String x =  "x = " + getX() + "\n";
        String y =  "y = " + getY() + "\n";
        String z =  "z = " + getZ() + "\n";

        int testvalue = 3;
        int negativeValue = -1 * testvalue;

        printDebugLog(x, y, z, testvalue, negativeValue);


        setText(x + y + z);


        if(event.sensor.getType() == Sensor.TYPE_GRAVITY) {
            gravitynX = event.values[0];
            gravityY = event.values[1];
            gravityZ = event.values[2];

        }
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            acclerationX = event.values[0];
            acclerationY = event.values[1];
            acclerationZ = event.values[2];
        }
        else if(event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            gyroX = event.values[0];
            gyroY = event.values[1];
            gyroZ = event.values[2];
        }
    }

    private void printDebugLog(String x, String y, String z, int testvalue, int negativeValue) {
        if(getZ() > testvalue && getY() > testvalue && getX() > testvalue) {
            // Log.d("Log:", "Z: " + z + " X: " + x + " Y: " + y);
            Log.d("Log:", "Up Left Out");

        } else if(getZ() > testvalue && getY() > testvalue && getX() <= negativeValue) {
            Log.d("Log:", "Z: " + z + " X: " + x + " Y: " + y);
        } else if(getZ() > testvalue && getY() <= negativeValue && getX() > testvalue) {
            Log.d("Log:", "Z: " + z + " X: " + x + " Y: " + y);
        } else if(getZ() > testvalue && getY() <= negativeValue && getX() <= negativeValue) {
            Log.d("Log:", "Z: " + z + " X: " + x + " Y: " + y);
        } else if(getZ() <= negativeValue && getY() > testvalue && getX() > testvalue) {
            Log.d("Log:", "Z: " + z + " X: " + x + " Y: " + y);
        } else if(getZ() <= negativeValue && getY() > testvalue && getX() <= negativeValue) {
            Log.d("Log:", "Z: " + z + " X: " + x + " Y: " + y);
        } else if(getZ() <= negativeValue && getY() <= negativeValue && getX() > testvalue) {
            Log.d("Log:", "Z: " + z + " X: " + x + " Y: " + y);
        } else if(getZ() <= negativeValue && getY() <= negativeValue && getX() <= negativeValue) {
            Log.d("Log:", "Z: " + z + " X: " + x + " Y: " + y);
        } else if(getZ() > testvalue && getX() > testvalue) {
            Log.d("Log:", "Z: " + z + " X: " + x);
        } else if(getZ() > testvalue && getX() <= negativeValue) {
            Log.d("Log:", "Z: " + z + " X: " + x);
        } else if(getZ() <= negativeValue && getX() > testvalue) {
            Log.d("Log:", "Z: " + z + " X: " + x);
        } else if(getZ() <= negativeValue && getX() <= negativeValue) {
            Log.d("Log:", "Z: " + z + " X: " + x);
        } else if(getZ() > testvalue && getY() > testvalue) {
            Log.d("Log:", "Z: " + z + " Y: " + y);
        } else if(getZ() > testvalue && getY() <= negativeValue) {
            Log.d("Log:", "Z: " + z + " Y: " + y);
        } else if(getZ() <= negativeValue && getY() > testvalue) {
            Log.d("Log:", "Z: " + z + " Y: " + y);
        } else if(getZ() <= negativeValue && getY() <= negativeValue) {
            Log.d("Log:", "Z: " + z + " Y: " + y);
        } else if(getX() > testvalue && getY() > testvalue) {
            Log.d("Log:", "X: " + x + " Y: " + y);
        } else if(getX() > testvalue && getY() <= negativeValue) {
            Log.d("Log:", "X: " + x + " Y: " + y);
        } else if(getX() <= negativeValue && getY() > testvalue) {
            Log.d("Log:", "X: " + x + " Y: " + y);
        } else if(getX() <= negativeValue && getY() <= negativeValue) {
            Log.d("Log:", "X: " + x + " Y: " + y);
        } else if(getX() <= negativeValue) {
            Log.d("Log X", x);
        } else if(getX() > testvalue) {
            Log.d("Log X", x);
        } else if(getY() <= negativeValue) {
            Log.d("Log Y", y);
        } else if(getY() > testvalue) {
            Log.d("Log Y", y);
        } else if(getZ() <= negativeValue) {
            Log.d("Log Z", z);
        } else if(getZ() > testvalue) {
            Log.d("Log Z", z);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public float getX() {
        boolean test = isValuePostive(gyroX);
        float value = Math.abs(acclerationX - gravitynX);
        value = test ? value : value * -1;
        return value;
    }

    public float getY() {
        boolean test = isValuePostive(gyroY);
        float value = Math.abs(acclerationY - gravityY);
        value = test ? value : value * -1;
        return value;
    }

    public float getZ() {
        boolean test = isValuePostive(gyroZ);
        float value = Math.abs(acclerationZ- gravityZ);
        value = test ? value : value * -1;
        return value;
    }

    private boolean isValuePostive(float gyro) {
        return gyro >= 0;
    }

    public void setText(String text) {
        _listener.onTextChange(text);
    }

    public void onPauseListening() {
        _sensorManager.unregisterListener(this);
    }

    public void onStartListening() {
        _sensorManager.registerListener(this, _sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
        _sensorManager.registerListener(this, _sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY), SensorManager.SENSOR_DELAY_GAME);
        _sensorManager.registerListener(this, _sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE), SensorManager.SENSOR_DELAY_GAME);
    }
}
