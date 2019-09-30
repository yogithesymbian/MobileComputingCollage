package com.scodeid.kamus;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.scodeid.kamus.helper.DatabaseHelper;

public class TerjemahanActivity extends AppCompatActivity {

    SQLiteDatabase db = null;
    Cursor kamusCursor = null;
    EditText textInggris;
    EditText textIndonesia;
    EditText textBanjar;
    DatabaseHelper datakamus = null;
    Button btnTerjemah;

    public static final String INGGRIS = "inggris";
    public static final String INDONESIA = "indonesia";
    public static final String BANJAR = "banjar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terjemahan);

        datakamus = new DatabaseHelper(this);
        db = datakamus.getWritableDatabase();
        setContentView(R.layout.activity_terjemahan);
        textInggris = findViewById(R.id.txtInggris);
        textIndonesia = findViewById(R.id.txtIndonesia);
        textBanjar = findViewById(R.id.txtBanjar);

        btnTerjemah = findViewById(R.id.btn_terjemah);
        btnTerjemah.setOnClickListener(new View.OnClickListener(
        ) {
            @Override
            public void onClick(View v) {
                String bhsinggris = "";
                String bhsbanjar = "";
                String bhsindonesia = textIndonesia.getText().toString();
                kamusCursor = db.rawQuery("SELECT _ID, INDONESIA, INGGRIS, BANJAR " + " FROM kamus WHERE INDONESIA= '"+ bhsindonesia +"' ORDER BY INDONESIA",null);
                if (kamusCursor.moveToFirst()){
                    for(; !kamusCursor.isAfterLast(); kamusCursor.moveToNext()){
                        bhsinggris = kamusCursor.getString(2);
                        bhsbanjar = kamusCursor.getString(3);
                    }
                }else{
                    Toast.makeText(getBaseContext(), "Terjemahan tidak ditemukan",
                            Toast.LENGTH_SHORT).show();
                }
                textInggris.setText(bhsinggris);
                textBanjar.setText(bhsbanjar);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try{
            kamusCursor.close();
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
