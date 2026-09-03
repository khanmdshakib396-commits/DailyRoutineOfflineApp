package com.example.dailyroutineofflineapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLogin();
    }

    private void showLogin() {
        setContentView(R.layout.activity_main);

        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            String user = username.getText().toString().trim();
            String pass = password.getText().toString();

            if (user.equals("Md Moyen Khan Shakib") && pass.equals("425264")) {
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();
                showDashboard();
            } else {
                Toast.makeText(this, "Wrong Username or Password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDashboard() {
        setContentView(R.layout.dashboard);

        Button addRoutineButton = findViewById(R.id.addRoutineButton);

        addRoutineButton.setOnClickListener(v -> {
            EditText input = new EditText(this);
            input.setHint("Routine name");
            input.setPadding(30, 20, 30, 20);

            new AlertDialog.Builder(this)
                    .setTitle("Add Routine")
                    .setView(input)
                    .setPositiveButton("Add", (dialog, which) -> {
                        String routine = input.getText().toString().trim();

                        if (!routine.isEmpty()) {
                            Toast.makeText(
                                    this,
                                    "Routine added: " + routine,
                                    Toast.LENGTH_SHORT
                            ).show();
                        } else {
                            Toast.makeText(
                                    this,
                                    "Routine name লিখো",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
    }
}
