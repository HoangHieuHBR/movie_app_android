package com.example.movie_app_ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.movie_app_ui.R;

public class LoginActivity extends AppCompatActivity {
    private EditText userEdt, passEdt;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        userEdt = findViewById(R.id.editTextUsername);
        passEdt = findViewById(R.id.editTextPassword);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(view -> {
            if (userEdt.getText().toString().isEmpty() || passEdt.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please fill your username and password", Toast.LENGTH_SHORT).show();
            } else if (userEdt.getText().toString().equals("admin") && passEdt.getText().toString().equals("admin")) {
                startActivity(new Intent(this, MainActivity.class));
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}