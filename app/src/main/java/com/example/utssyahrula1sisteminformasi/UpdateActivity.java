package com.example.utssyahrula1sisteminformasi;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    protected Cursor cursor;
    private String produsen;
    Database database;
    Button btnnew;
    RadioGroup radioGroup;
    EditText namaprd, stok, qtymasuk, qtykeluar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        database = new Database(this);
        namaprd = findViewById(R.id.namaprd);
        stok = findViewById(R.id.stok);
        qtymasuk = findViewById(R.id.qtymasuk);
        qtykeluar = findViewById(R.id.qtykeluar);
        btnnew = findViewById(R.id.btnnew);
        radioGroup = findViewById(R.id.radiogrup);

        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM inventory WHERE namaprd = '" +
                getIntent().getStringExtra("namaprd") +"'", null);
        cursor.moveToFirst();
        if (cursor.getCount() >0){
            cursor.moveToPosition(0);
            namaprd.setText(cursor.getString(0).toString());
            produsen = cursor.getString(1).toString().replaceAll("\\s+", "");
            switch (produsen){
                case "Unilever":
                    radioGroup.check(R.id.radio1);
                    break;
                case "Wings":
                radioGroup.check(R.id.radio2);
                break;

                case "Indofood":
                radioGroup.check(R.id.radio3);
                break;

            }
            stok.setText(cursor.getString(2).toString());
            qtymasuk.setText(cursor.getString(3).toString());
            qtykeluar.setText(cursor.getString(4).toString());
        }

        btnnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String produsen2 = new String();

                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.radio1:
                        produsen2 = "Unilever";
                        break;
                    case R.id.radio2:
                        produsen2 = "Wings";
                        break;
                    case R.id.radio3:
                        produsen2 = "Indofood";
                        break;
                }

                SQLiteDatabase db = database.getWritableDatabase();
                db.execSQL("update inventory set namaprd='" +
                        namaprd.getText().toString() +"', produsen='" +
                                produsen2 +"', stok='" +
                        stok.getText().toString() +"', qtymasuk='" +
                        qtymasuk.getText().toString() +"', qtykeluar='" +
                        qtykeluar.getText().toString() +"' where namaprd = '" +
                        getIntent().getStringExtra("namaprd") +"'");
                Toast.makeText(UpdateActivity.this, "Data berhasil diupdate", Toast.LENGTH_SHORT).show();
                MainActivity.ma.RefreshList();
                finish();

            }
        });
    }
}