package com.example.dailyroutineofflineapp;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = getSharedPreferences(
                "DailyRoutineSettings",
                MODE_PRIVATE
        );

        showLogin();
    }

    private void showLogin() {
        setContentView(R.layout.activity_main);

        applyScreenStyle();

        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setTextSize(getTextSize());
        applyButtonStyle(loginButton);

        loginButton.setOnClickListener(v -> {

            String user = username.getText().toString().trim();
            String pass = password.getText().toString();

            if (user.equals("Md Moyen Khan Shakib")
                    && pass.equals("425264")) {

                Toast.makeText(
                        this,
                        "Login Successful!",
                        Toast.LENGTH_SHORT
                ).show();

                showDashboard();

            } else {

                Toast.makeText(
                        this,
                        "Wrong Username or Password",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    private void showDashboard() {
        setContentView(R.layout.dashboard);

        applyScreenStyle();

        Button addRoutineButton =
                findViewById(R.id.addRoutineButton);

        applyButtonStyle(addRoutineButton);

        addRoutineButton.setOnClickListener(v -> {

            EditText input = new EditText(this);
            input.setHint("Routine name");
            input.setPadding(30, 20, 30, 20);

            new AlertDialog.Builder(this)
                    .setTitle("Add Routine")
                    .setView(input)
                    .setPositiveButton("Add", (dialog, which) -> {

                        String routine =
                                input.getText().toString().trim();

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

        // Customize Button
        Button customizeButton =
                findViewById(R.id.customizeButton);

        applyButtonStyle(customizeButton);

        customizeButton.setOnClickListener(v -> {
            showCustomize();
        });
    }

    private void showCustomize() {

        setContentView(R.layout.customize);

        applyScreenStyle();

        RadioButton lightTheme =
                findViewById(R.id.lightTheme);

        RadioButton darkTheme =
                findViewById(R.id.darkTheme);

        SeekBar textSizeBar =
                findViewById(R.id.textSizeBar);

        TextView preview =
                findViewById(R.id.textSizePreview);

        RadioButton buttonNormal =
                findViewById(R.id.buttonNormal);

        RadioButton buttonLarge =
                findViewById(R.id.buttonLarge);

        Button save =
                findViewById(R.id.saveCustomize);

        Button reset =
                findViewById(R.id.resetCustomize);

        // Current Theme
        boolean dark =
                prefs.getBoolean("darkMode", false);

        if (dark) {
            darkTheme.setChecked(true);
        } else {
            lightTheme.setChecked(true);
        }

        // Current Text Size
        int savedSize =
                prefs.getInt("textSize", 18);

        textSizeBar.setProgress(savedSize - 10);
        preview.setTextSize(savedSize);

        // Current Button Style
        boolean largeButton =
                prefs.getBoolean("largeButton", false);

        if (largeButton) {
            buttonLarge.setChecked(true);
        } else {
            buttonNormal.setChecked(true);
        }

        // Text Size Preview
        textSizeBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onProgressChanged(
                            SeekBar seekBar,
                            int progress,
                            boolean fromUser) {

                        int size = progress + 10;
                        preview.setTextSize(size);
                    }

                    @Override
                    public void onStartTrackingTouch(
                            SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(
                            SeekBar seekBar) {
                    }
                }
        );

        // SAVE
        save.setOnClickListener(v -> {

            boolean darkMode =
                    darkTheme.isChecked();

            boolean large =
                    buttonLarge.isChecked();

            int textSize =
                    textSizeBar.getProgress() + 10;

            prefs.edit()
                    .putBoolean("darkMode", darkMode)
                    .putBoolean("largeButton", large)
                    .putInt("textSize", textSize)
                    .apply();

            Toast.makeText(
                    this,
                    "Customization Saved ✓",
                    Toast.LENGTH_SHORT
            ).show();

            showDashboard();
        });

        // RESET
        reset.setOnClickListener(v -> {

            prefs.edit().clear().apply();

            Toast.makeText(
                    this,
                    "Customization Reset",
                    Toast.LENGTH_SHORT
            ).show();

            showDashboard();
        });
    }

    private void applyScreenStyle() {

        boolean dark =
                prefs.getBoolean("darkMode", false);

        if (dark) {

            getWindow()
                    .getDecorView()
                    .setBackgroundColor(
                            Color.rgb(25, 25, 25)
                    );

        } else {

            getWindow()
                    .getDecorView()
                    .setBackgroundColor(
                            Color.WHITE
                    );
        }
    }

    private int getTextSize() {

        return prefs.getInt(
                "textSize",
                18
        );
    }

    private void applyButtonStyle(Button button) {

        boolean large =
                prefs.getBoolean(
                        "largeButton",
                        false
                );

        if (large) {

            button.setTextSize(
                    getTextSize() + 2
            );

            button.setPadding(
                    20,
                    25,
                    20,
                    25
            );

        } else {

            button.setTextSize(
                    getTextSize()
            );

            button.setPadding(
                    15,
                    15,
                    15,
                    15
            );
        }
    }
}
