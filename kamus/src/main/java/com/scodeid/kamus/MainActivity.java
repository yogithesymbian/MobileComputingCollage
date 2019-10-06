package com.scodeid.kamus;

import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ViewFlipper;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_1);

        //animasi Backgroud Slider
        ViewFlipper flipper = findViewById(R.id.flipper_translate);
        flipperImage(flipper);

        //Button 1
        //ImageView tampilTerjemahan = findViewById(R.id.img_terjemahan);
        Button btnTerjemahan = findViewById(R.id.btn_translate);
        btnTerjemahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, TerjemahanActivity.class);
                startActivity(intent);
            }
        });

        Button btnLogOut = findViewById(R.id.btn_log_out);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
//                finish();
            }
        });
    }

    private void flipperImage(ViewFlipper flipper) {
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        flipper.setInAnimation(fadeIn);
        flipper.setOutAnimation(fadeOut);
        flipper.setAutoStart(true);
        flipper.setFlipInterval(5000);
        flipper.startFlipping();
    }
}
