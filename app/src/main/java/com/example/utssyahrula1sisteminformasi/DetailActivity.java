package com.example.utssyahrula1sisteminformasi;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    protected Cursor cursor;
    Database database;
    Button btnnew;
    TextView namaprd, produsen, stok, qtymasuk, qtykeluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        database = new Database(this);
        namaprd = findViewById(R.id.namaprd);
        produsen = findViewById(R.id.produsen);
        stok = findViewById(R.id.stok);
        qtymasuk = findViewById(R.id.qtymasuk);
        qtykeluar = findViewById(R.id.qtykeluar);
        btnnew = findViewById(R.id.btnnew);

        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM inventory WHERE namaprd = '" +
                getIntent().getStringExtra("namaprd") +"'", null);
        cursor.moveToFirst();
        if (cursor.getCount() >0) {
            cursor.moveToPosition(0);
            namaprd.setText(cursor.getString(0).toString());
            produsen.setText(cursor.getString(1).toString());
            stok.setText(cursor.getString(2).toString());
            qtymasuk.setText(cursor.getString(3).toString());
            qtykeluar.setText(cursor.getString(4).toString());
        }
    }
}