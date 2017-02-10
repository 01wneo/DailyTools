package com.wneo.neo.dailytools;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity {
    private String et_Start_Time;
    private String et_End_Time;

    private Button Btn_Calculation;
    private EditText Et_Start_Time;
    private EditText Et_End_Time;
    private EditText Et_Difference_Time;

    StringBuilder difTime = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidget();
        setDate(); // get user input time
        setListener(); // click button start calculation
    }

    private void initWidget() {
        Btn_Calculation = (Button) findViewById(R.id.btn_calculation);
        Et_Start_Time = (EditText) findViewById(R.id.et_start_time);
        Et_End_Time = (EditText) findViewById(R.id.et_end_time);
        Et_Difference_Time = (EditText) findViewById(R.id.etT_difference_time);
    }

    private void setDate() {
        et_Start_Time = Et_Start_Time.getText().toString();
        et_End_Time = Et_End_Time.getText().toString();

        Et_Start_Time.setText(et_Start_Time);
        Et_End_Time.setText(et_End_Time);
    }

    private void setListener() {
        Btn_Calculation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formatTime();
            }
        });
    }

    private void formatTime() {
        String[] startTime = Et_Start_Time.getText().toString().split(":");
        String[] endTime = Et_End_Time.getText().toString().split(":");
        // not empty
        Log.i("Count", "et_Start_Time == " + Et_Start_Time.getText().toString() + "/n et_End_Time == " + Et_End_Time.getText().toString());
        if (Et_Start_Time.getText().toString().equals("") || Et_End_Time.getText().toString().equals("") || Et_Start_Time.getText().toString() == null || Et_End_Time.getText().toString() == null) {
            Toast.makeText(this, getString(R.string.ta_empty), Toast.LENGTH_SHORT).show();
        } else {
            Log.i("count ", "startTime[0] == " + startTime[0] + "; startTime[1] == " + startTime[1]);
            long longStartTime = Long.parseLong(startTime[0]) * 60 * 60 + Long.parseLong(startTime[1]) * 60 + Long.parseLong(startTime[2]);
            long longEndTime = Long.parseLong(endTime[0]) * 60 * 60 + Long.parseLong(endTime[1]) * 60 + Long.parseLong(endTime[2]);
            Log.i("count ", "longStartTime == " + longStartTime + ";longEndTime == " + longEndTime);
            if(longEndTime >=  longStartTime) {
                getTimeString(longStartTime, longEndTime);
            } else {
                getTimeString(longEndTime, longStartTime);
            }
        }
    }

    private void getTimeString(long lStartTime, long lEndTime) {
        Log.i("count ", "calculation time == " + (lEndTime - lStartTime));
        String hour;
        String minute;
        String second;
        // hour (lEndTime - lStartTime) / 3600 < 10 plus 0
        // minute ((lEndTime - lStartTime) % 3600) / 60 < 10 plus 0
        // second ((((lEndTime - lStartTime) % 3600) % 60) / 60) < 10 plus 0
        Log.e("hour = ", "" + (lEndTime - lStartTime) / 3600);
        Log.e("minute = ", "" + ((lEndTime - lStartTime) % 3600));
        Log.e("minute = ", "" + ((lEndTime - lStartTime) % 3600) / 60);
        Log.e("second = ", "" + (((lEndTime - lStartTime) % 3600) % 60));
        Log.e("second = ", "" + ((((lEndTime - lStartTime) % 3600) % 60) / 60));

        if (((lEndTime - lStartTime) / 3600) < 10) { //
            hour = "0" + ((lEndTime - lStartTime) / 3600);
        } else {
            hour = "" + ((lEndTime - lStartTime) / 3600);
        }
        Log.e("hour = ", hour);

        if ((((lEndTime - lStartTime) % 3600) / 60) < 10) { //
            minute = "0" + (((lEndTime - lStartTime) % 3600) / 60);
        } else {
            minute = "" + (((lEndTime - lStartTime) % 3600) / 60);
        }
        Log.e("minute = ", minute);

        if (((((lEndTime - lStartTime) % 3600) % 60)) < 10) {
            second = "0" + ((((lEndTime - lStartTime) % 3600) % 60));
        } else {
            second = "" + ((((lEndTime - lStartTime) % 3600) % 60));
        }
        Log.e("second = ", second);
        Log.e("difTime length = ", difTime.length() + "");
        if (difTime.length() > 0) {
            difTime.delete(0, difTime.length());
        } else {
            difTime.append(hour);
            difTime.append(":" + minute);
            difTime.append(":" + second);

            Log.e("difTime = ", "" + difTime);
            Log.e("difTime length = ", difTime.length() + "");

            Et_Difference_Time.setText(difTime);
        }
    }
}
