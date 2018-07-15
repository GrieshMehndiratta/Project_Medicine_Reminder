package com.example.grieshmehndiratta.medicinereminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String db_name = "medicine_data";
    private static String tablename  = "med_table";
    private static String col1 = "ID";
    private static String col2 = "MED_NAME";
    private static String col3 = "TIME";
    private static String col4 = "DATE";

    Context context;

    public DatabaseHelper(Context context){
        super(context, db_name,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL("CREATE TABLE IF NOT EXISTS "+tablename+" (id INTEGER PRIMARY KEY AUTOINCREMENT, med_name VARCHAR, time VARHCAR, date VARCHAR )");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         db.execSQL("DROP TABLE IF EXISTS "+tablename);
        onCreate(db);
    }


    public boolean insert(String med_name, String time, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2, med_name);
        contentValues.put(col3, time);
        contentValues.put(col4, date);
        db.insert(tablename,null,contentValues);
        return true;
    }

    public Cursor fetchAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+tablename,null);

        return c;
    }

    public void delete(String med_name){

    }

}
