package com.example.grieshmehndiratta.medicinereminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class date_act extends AppCompatActivity {


    AlarmManager alarm_Manager;
    Medicine_Reminder Medicine;
    DatePicker Date_Picker;
    final Calendar calender=Calendar.getInstance();
    boolean flag=false;
    final int[] Year = new int[1];
    final int[] Month = new int[1];
    final int[] Day = new int[1];
    final String[] Year_string = new String[1];
    final String[] Month_string = new String[1];
    final String[] Day_string = new String[1];
    boolean SET = false;
    private static String Date_display = "12/12/12";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        flag=true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_act);
        Date_Picker=findViewById( R.id.Date_Picker );
        Medicine=new Medicine_Reminder();

        alarm_Manager=(AlarmManager) getSystemService(ALARM_SERVICE);
        Button set_Button;
        set_Button=findViewById(R.id.set_button);
        set_Button.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)

            @Override
            public void onClick(View v) {
                SET = true;
                Year[0] =Date_Picker.getYear();
                Month[0] =Date_Picker.getMonth()+1;
                Day[0] =Date_Picker.getDayOfMonth();
                Year_string[0] =String.valueOf(Year[0]);
                Month_string[0]=String.valueOf(Month[0] );
                Day_string[0]=String.valueOf( Day[0] );

                Date_display =Day_string[0]+"/"+ Month_string[0]+"/"+Year_string[0];
                Toast.makeText(date_act.this, "Date set go back and add Task", Toast.LENGTH_SHORT).show();
            }
        });
        Button cancel=findViewById(R.id.cancel_button);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=true;
                SET=false;

                Toast.makeText(date_act.this, "You Cancel the Date", Toast.LENGTH_SHORT).show();
            }
        });

    }
    String Show()
    {
        Log.e("calling",Date_display );
        return (Date_display);
    }

    public  boolean for_intent()
    {
        return flag;
    }

    int return_Year()
    {
        return Year[0];
    }
    int return_Month()
    {
        return Month[0];
    }
    int return_Day()
    {
        return Day[0];
    }
}
