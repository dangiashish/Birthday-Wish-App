package com.android.design.birthdaywish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class MainActivity extends AppCompatActivity {

    KonfettiView konfettiView;
    Button btnSetting;
    TextView bdayMsg;
    RelativeLayout layout0, layoutW;
    ViewSwitcher logo0, logo1, logo2, logo3, logow, logox, logoy, logoz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo0 = findViewById(R.id.logo0);
        logo1 = findViewById(R.id.logo1);
        logo2 = findViewById(R.id.logo2);
        logo3 = findViewById(R.id.logo3);
        logow = findViewById(R.id.logow);
        logox = findViewById(R.id.logox);
        logoy = findViewById(R.id.logoy);
        logoz = findViewById(R.id.logoz);

        bdayMsg = findViewById(R.id.bdaywishText);
        btnSetting = findViewById(R.id.btnSettings);
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });


        layout0 = findViewById(R.id.layout0);
        layoutW = findViewById(R.id.layoutW);

        Animation rotation = AnimationUtils.loadAnimation(this, R.anim.anim_pic);
        logo0.setAnimation(rotation);
        logow.setAnimation(rotation);

        layoutOneView();


        konfettiView = findViewById(R.id.viewKonfetti);
        konfettiView.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                .addSizes(new Size(12, 5f))
                .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                .streamFor(300, 5000000L);

        // to increase the particals time, increase the emitting time

    }

    private void layoutOneView() {

        // we didnt initialized the layout0 and layoutW



        layout0.setVisibility(View.VISIBLE);
        layoutW.setVisibility(View.GONE);

    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            if (logo0.getDisplayedChild() == 0) {
                logo0.showNext();
                bdayMsg.setText(getResources().getText(R.string.bday2));
            } else
                 logo0.showPrevious();
        }
    }, 10000) ;  // 10000 = 10 seconds

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (logo1.getDisplayedChild() == 0) {
                    logo1.showNext();
                    bdayMsg.setText(getResources().getText(R.string.bday3));
                } else
                    logo1.showPrevious();
            }
        }, 20000) ;  // we will increase seconds in every viewswitcher... you will understand it in the output


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (logo2.getDisplayedChild() == 0) {
                    logo2.showNext();
                    bdayMsg.setText(getResources().getText(R.string.bday4));
                } else
                    logo2.showPrevious();
            }
        }, 30000) ;  // 10000 = 10 seconds


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (logo3.getDisplayedChild() == 0) {
                    logo3.showNext();
                    bdayMsg.setText(getResources().getText(R.string.bday5));
                } else
                    logo3.showPrevious();
            }
        }, 40000) ;  // 10000 = 10 seconds

        // one layout is not visible in first 40 seconds... now its term to visible to layoutW

        layoutTwoView();
    }

    private void layoutTwoView() {

        layout0.setVisibility(View.GONE);
        layoutW.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (logow.getDisplayedChild() == 0 ){
                    logow.showNext();
                    bdayMsg.setText(getResources().getText(R.string.bday6));
                 } else
                     logow.showPrevious();
            }
        }, 10000);
        // we will write 10000, not 50000 becuase the another layoutW is in begin state

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (logox.getDisplayedChild() == 0 ){
                    logox.showNext();
                    bdayMsg.setText(getResources().getText(R.string.bday7));
                } else
                    logox.showPrevious();
            }
        }, 20000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (logoy.getDisplayedChild() == 0 ){
                    logoy.showNext();
                    bdayMsg.setText(getResources().getText(R.string.bday8));
                } else
                    logoy.showPrevious();
            }
        }, 30000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (logoz.getDisplayedChild() == 0 ){
                    logoz.showNext();
                    bdayMsg.setText(getResources().getText(R.string.bday9));
                } else
                    logoz.showPrevious();
            }
        }, 40000);

    }

    @Override
    protected void onStart() {
        startService(new Intent(this,music_service.class));
        super.onStart();
    }

    // if you want to stop the song when app is in minimize or back pressed then we have to stop the service


    @Override
    public void onBackPressed() {
        stopService(new Intent(this, music_service.class));
        finishAffinity();
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        stopService(new Intent(this, music_service.class));
        super.onPause();
    }
}