package com.example.utssyahrula1sisteminformasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CreateActivity extends AppCompatActivity {
    protected Cursor cursor;
    Database database;
    Button btnnew;
    RadioGroup radioGroup;
    RadioButton radio1;
    EditText namaprd, stok, qtymasuk, qtykeluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        database = new Database(this);
        namaprd = findViewById(R.id.namaprd);
        stok = findViewById(R.id.stok);
        qtymasuk = findViewById(R.id.qtymasuk);
        qtykeluar = findViewById(R.id.qtykeluar);
        btnnew = findViewById(R.id.btnnew);
        radioGroup = findViewById(R.id.radiogrup);


        btnnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                radio1 = findViewById(selectedId);

                SQLiteDatabase db = database.getWritableDatabase();
                db.execSQL("insert into inventory(namaprd, produsen, stok, qtymasuk, qtykeluar) values('" +
                        namaprd.getText().toString() + "','" +
                        radio1.getText().toString() + "','" +
                        stok.getText().toString() + "','" +
                        qtymasuk.getText().toString() + "', '" +
                        qtykeluar.getText().toString() +"')");
                Toast.makeText(CreateActivity.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });
    }
}