package com.example.dailyroutineofflineapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {

            String user = username.getText().toString().trim();
            String pass = password.getText().toString();

            if (user.equals("Md Moyen Khan Shakib") && pass.equals("425264")) {
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Wrong Username or Password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
