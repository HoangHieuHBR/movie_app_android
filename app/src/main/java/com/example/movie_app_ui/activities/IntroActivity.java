package com.example.movie_app_ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.movie_app_ui.R;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Button getStartedBtn = findViewById(R.id.getStartedBtn);
        getStartedBtn.setOnClickListener(v->{
            startActivity(new Intent(this, LoginActivity.class));
        });
    }
}