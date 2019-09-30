package com.scodeid.kamus;

import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ViewFlipper Flipper;
    private Animation fadeIn, fadeOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //animasi Backgroud Slider
        Flipper = findViewById(R.id.Flipper);
        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation( this, R.anim.fade_out);
        Flipper.setInAnimation(fadeIn);
        Flipper.setOutAnimation(fadeOut);
        Flipper.setAutoStart(true);
        Flipper.setFlipInterval(5000);
        Flipper.startFlipping();

        //Button 1
        ImageView tampilTerjemahan = findViewById(R.id.img_terjemahan);
        tampilTerjemahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, TerjemahanActivity.class);
                startActivity(intent);
            }
        });
    }
}
