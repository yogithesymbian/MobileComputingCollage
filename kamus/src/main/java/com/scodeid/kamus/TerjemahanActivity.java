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

    SQLiteDatabase sqLiteDatabase = null;
    Cursor kamusCursor = null;
    private EditText textSpanyol;
    private EditText textIndonesia;
    private EditText textBali;
    DatabaseHelper databaseHelper = null;

    public static final String SPANYOL = "spanyol";
    public static final String INDONESIA = "indonesia";
    public static final String BALI = "bali";
    public String translateFrom = INDONESIA;
    private String TAG_LOG = TerjemahanActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terjemahan);

        databaseHelper = new DatabaseHelper(this);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        setContentView(R.layout.activity_terjemahan);

        textSpanyol = findViewById(R.id.txt_spanyol);
        textIndonesia = findViewById(R.id.txt_indonesia);
        textBali = findViewById(R.id.txt_bali);

        Button btnIndoState = findViewById(R.id.btn_frm_indonesia);
        Button btnSpanyolState = findViewById(R.id.btn_frm_spanyol);
        Button btnBaliState = findViewById(R.id.btn_frm_bali);
        final Button btnTerjemah = findViewById(R.id.btn_terjemahkan);

        btnIndoState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translateFrom = INDONESIA;
                btnTerjemah.setText(R.string.terjemahan_activity_indonesia);
                Log.d(TAG_LOG,"1. Translate From" + translateFrom);
                //visible | change position | hint text field from -> to result , later's
            }
        });
        btnSpanyolState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translateFrom = SPANYOL;
                btnTerjemah.setText(R.string.terjemahan_activity_spanyol);
                Log.d(TAG_LOG,"2. Translate From" + translateFrom);
                //visible | change position | hint text field from -> to result , later's
            }
        });
        btnBaliState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translateFrom = BALI;
                btnTerjemah.setText(R.string.terjemahan_activity_balinese);
                Log.d(TAG_LOG,"3. Translate From" + translateFrom);
                //visible | change position | hint text field from -> to result , later's
            }
        });


        btnTerjemah.setOnClickListener(new View.OnClickListener(
        ) {
            @Override
            public void onClick(View v) {
                switch (translateFrom) {
                    case INDONESIA: {

                        String bhsSpanyol = "";
                        String bhsBali = "";
                        String bhsIndonesia = textIndonesia.getText().toString();
                        letsTranslate(INDONESIA, bhsIndonesia, bhsBali, bhsSpanyol, 1);

                        break;
                    }
                    case SPANYOL: {

                        String bhsSpanyol = textSpanyol.getText().toString();
                        String bhsBali = "";
                        String bhsIndonesia = "";
                        letsTranslate(SPANYOL, bhsSpanyol, bhsBali, bhsIndonesia, 2);

                        break;
                    }
                    case BALI: {

                        String bhsSpanyol = "";
                        String bhsBali = textBali.getText().toString();
                        String bhsIndonesia = "";
                        letsTranslate(BALI, bhsBali, bhsSpanyol, bhsIndonesia, 3);

                        break;
                    }
                }
            }

            private void letsTranslate(String fieldFrom, String valueTranslate, String index3, String index2, int stateFrom) {
                kamusCursor = sqLiteDatabase.rawQuery("SELECT _ID, INDONESIA, SPANYOL, BALI " + " FROM kamus WHERE "+ fieldFrom +"= '"+ valueTranslate +"' ORDER BY INDONESIA",null);

                if (stateFrom == 1){
                    if (kamusCursor.moveToFirst()){
                        for(; !kamusCursor.isAfterLast(); kamusCursor.moveToNext()){
                            index2 = kamusCursor.getString(2); // SPANYOL
                            index3 = kamusCursor.getString(3); // BALI
                        }
                    }else{
                        Toast.makeText(getBaseContext(), "Terjemahan tidak ditemukan",
                                Toast.LENGTH_SHORT).show();
                    }
                    textSpanyol.setText(index2);
                    textBali.setText(index3);
                }
                else if(stateFrom == 2){
                    if (kamusCursor.moveToFirst()){
                        for(; !kamusCursor.isAfterLast(); kamusCursor.moveToNext()){
                            index2 = kamusCursor.getString(1); // INDONESIA
                            index3 = kamusCursor.getString(3); // BALI
                        }
                    }else{
                        Toast.makeText(getBaseContext(), "Terjemahan tidak ditemukan",
                                Toast.LENGTH_SHORT).show();
                    }
                    textIndonesia.setText(index2);
                    textBali.setText(index3);
                } else if(stateFrom == 3){
                    if (kamusCursor.moveToFirst()){
                        for(; !kamusCursor.isAfterLast(); kamusCursor.moveToNext()){
                            index2 = kamusCursor.getString(1); // INDONESIA
                            index3 = kamusCursor.getString(2); // SPANYOL
                        }
                    }else{
                        Toast.makeText(getBaseContext(), "Terjemahan tidak ditemukan",
                                Toast.LENGTH_SHORT).show();
                    }
                    textIndonesia.setText(index2);
                    textSpanyol.setText(index3);
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        try{
            kamusCursor.close();
            sqLiteDatabase.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
