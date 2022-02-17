package com.android.design.birthdaywish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    Button git, yt, insta;
    TextView btmText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        git = findViewById(R.id.btnGithub);
        yt = findViewById(R.id.btnYoutube);
        insta = findViewById(R.id.btnInsta);
        btmText = findViewById(R.id.tvBottom);

        btmText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri url = Uri.parse("https://youtube.com/ashishdangi");
                intent.setData(url);
                startActivity(intent);
            }
        });

        git.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri url = Uri.parse("https://github.com/dangiashish/birthday-wish-android-app");
                intent.setData(url);
                startActivity(intent);
            }
        });

        yt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri url = Uri.parse("https://www.youtube.com/watch?v=zBlhdEtYRpI");
                intent.setData(url);
                startActivity(intent);
            }
        });

        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri url = Uri.parse("https://instagram.com/coder.ashish");
                intent.setData(url);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}