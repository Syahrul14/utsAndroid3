package com.example.utssyahrula1sisteminformasi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {
    private static final String DATABSE_NAME ="inventory.db";
    private static final int DATABASE_VERSION = 1;

    public Database(Context context){
        super(context, DATABSE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table inventory(namaprd text null, produsen text null, stok text null, qtymasuk text null, qtykeluar text null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db0, int db1, int db2) {

    }
}
