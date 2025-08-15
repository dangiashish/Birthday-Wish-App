package com.codebyashish.birthdaywish.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.codebyashish.birthdaywish.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

public class LogActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        databaseReference.child("Birthday-Wish-App").child("Login")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        code = snapshot.child("pass").getValue(String.class);
                        if (code != null) {
                            if (code.isEmpty()) {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        startActivity(new Intent(getApplicationContext(), SplashScreen.class));
                                        finish();
//                                    Toast.makeText(LogActivity.this, "OKAAAYYy", Toast.LENGTH_SHORT).show();
                                    }
                                }, 1500);

                            } else {
                                new Handler().postDelayed(() -> showBottomDialog(), 500);
                            }

                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(LogActivity.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 1500);

                    }
                });

    }

    public void showBottomDialog() {
        final DialogPlus dialogPlus = DialogPlus.newDialog(this)
                .setContentHolder(new ViewHolder(R.layout.aaaaaaaa))
                .setCancelable(true)
                .setGravity(Gravity.BOTTOM)
                .setBackgroundColorResId(Color.TRANSPARENT)
                .create();
        View view = dialogPlus.getHolderView();

        AppCompatButton btnNext = view.findViewById(R.id.btnAdd);
        AppCompatEditText etCode = view.findViewById(R.id.etCategory);
        btnNext.setOnClickListener(click -> {
            String value = etCode.getEditableText().toString().trim();
            if (value.equals(code)) {
                dialogPlus.dismiss();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    startActivity(new Intent(getApplicationContext() , SplashScreen.class));
                    finish();
//                        Toast.makeText(LogActivity.this, "OKAAAYYy", Toast.LENGTH_SHORT).show();
                    }
                }, 1000);
            } else {
                Toast.makeText(this, "Code not matched 😥", Toast.LENGTH_SHORT).show();
            }


        });

        dialogPlus.show();
    }
}