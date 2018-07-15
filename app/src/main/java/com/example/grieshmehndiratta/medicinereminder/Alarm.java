package com.example.grieshmehndiratta.medicinereminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Alarm extends AppCompatActivity  {

    AlarmManager alarm_Manager;
    PendingIntent pending_intent;
    Medicine_Reminder Medicine;
    TimePicker alarm_TimePicker;
    final int[] hour = new int[1];
    final int[] minute = new int[1];
    final int[] Phour=new int[1];
    final int[] Pminute=new int[1];
    final String[] hour_string = new String[1];
    final String[] minute_string = new String[1];
    final Calendar calender=Calendar.getInstance();
    boolean SET = false;
    private static String Time_display = "tett 12:30";
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        alarm_TimePicker=findViewById(R.id.Alarm);


        Medicine=new Medicine_Reminder();

        alarm_Manager=(AlarmManager) getSystemService(ALARM_SERVICE);

        Phour[0]=alarm_TimePicker.getHour();
        Pminute[0]=alarm_TimePicker.getMinute();



        final Intent my_intent=new Intent(Alarm.this,Alarm_Receiver.class);

        Button set=findViewById(R.id.Turn_on);
        set.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                SET = true;
                //set date
                //Calendar.set(Calendar.)

                hour[0] =alarm_TimePicker.getHour();
                 minute[0] =alarm_TimePicker.getMinute();
                 hour_string[0] =String.valueOf( hour[0]);
                 minute_string[0]=String.valueOf( minute[0] );

                 Time_display = hour_string[0]+":"+minute_string[0];

                Toast.makeText(Alarm.this, "Alarm set go back and Add Task", Toast.LENGTH_SHORT).show();

                pending_intent=PendingIntent.getBroadcast(Alarm.this,0,my_intent,PendingIntent.FLAG_UPDATE_CURRENT);
                alarm_Manager.set(AlarmManager.RTC_WAKEUP,calender.getTimeInMillis(),pending_intent);


            }
        });
        Button cancel=findViewById(R.id.Turn_off);
        cancel.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Alarm.this, "You Cancel the Alarm", Toast.LENGTH_SHORT).show();
            }
        } );



    }
    String Time_Show()
    {
        Log.e("calling",Time_display );
        return Time_display;

    }

    public int return_hour()
    {
        return hour[0];
    }
    public int return_min()
    {
        return minute[0];
    }

    public int schedule()
    {
        Phour[0]-=hour[0];
        Pminute[0]-=minute[0];
        Pminute[0]+=Phour[0]*60;
        return (Pminute[0]*60*1000);
    }


}
