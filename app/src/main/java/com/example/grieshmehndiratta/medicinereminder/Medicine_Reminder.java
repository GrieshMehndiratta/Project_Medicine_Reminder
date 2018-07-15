package com.example.grieshmehndiratta.medicinereminder;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class Medicine_Reminder extends AppCompatActivity {
    DatabaseHelper db;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Button b_time,b_date;
    Alarm alarm = new Alarm();
    date_act Date_Act;
    String medicine_name="Crocine";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine__reminder);

        final FloatingActionButton FButton=findViewById(R.id.Action);
        listView = findViewById(R.id.listView);
        list = new ArrayList<>();
        db = new DatabaseHelper(Medicine_Reminder.this);

        displayAll();
        Date_Act = new date_act();
        if(Date_Act.for_intent())
        {
            open_dialog_box();
        }

        FButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                open_dialog_box();
            }
        });
    }
    void open_dialog_box() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Medicine_Reminder.this);
        LayoutInflater inflater = Medicine_Reminder.this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_box, null);
        b_time =  dialogView.findViewById(R.id.time_button);
        b_date =  dialogView.findViewById(R.id.Date);

/*
        editText=dialogView.findViewById( R.id.edit1 );
*/


        b_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Medicine_Reminder.this,Alarm.class);
                startActivity(intent);
            }
        });

        b_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Medicine_Reminder.this,date_act.class);
                startActivity(intent);
            }
        });
        dialogBuilder.setView(dialogView);

         final EditText edt = (EditText) dialogView.findViewById(R.id.edit1);

        dialogBuilder.setTitle("Custom dialog");
        dialogBuilder.setMessage("Enter text below");





        dialogBuilder.setPositiveButton("Add Task", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onClick(DialogInterface dialog, int whichButton)
            {

                medicine_name=edt.getText().toString();

                db.insert(medicine_name, alarm.Time_Show(),Date_Act.Show());
                    Log.e("Time",alarm.Time_Show());
                    displayAll();
                    Toast.makeText(Medicine_Reminder.this, "Item Created", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent( getApplicationContext(),Alarm_Receiver.class );

                Calendar calendar;
                calendar=Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis() );
                calendar.set( Calendar.HOUR_OF_DAY,alarm.return_hour());
                calendar.set(Calendar.MINUTE,alarm.return_min());

                calendar.set(Calendar.YEAR,Date_Act.return_Year());
                calendar.set(Calendar.MONTH,Date_Act.return_Month());
                calendar.set(Calendar.DAY_OF_MONTH,Date_Act.return_Day());

                PendingIntent pendingIntent=PendingIntent.getActivity( getApplicationContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT );
                AlarmManager alarmManager=(AlarmManager)getApplicationContext().getSystemService( getBaseContext().ALARM_SERVICE );

                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),6000,pendingIntent);


            }
        });


        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }



    public void displayAll(){
        Cursor cursor = db.fetchAll();

        if(cursor != null && cursor.moveToNext()){

           do {

                if(cursor.isLast())
                list.add("Medicine  :"+cursor.getString(1)+"   Date :"+cursor.getString(2)+" Time :"+cursor.getString(3));


            }while (cursor.moveToNext());

            adapter = new ArrayAdapter<>(Medicine_Reminder.this,
                    android.R.layout.simple_list_item_1,list);
            listView.setAdapter(adapter);
        }else{
            Toast.makeText(Medicine_Reminder.this, "Empty List", Toast.LENGTH_SHORT).show();
        }
    }


    String Med_Name()
    {
        return medicine_name;
    }
}
