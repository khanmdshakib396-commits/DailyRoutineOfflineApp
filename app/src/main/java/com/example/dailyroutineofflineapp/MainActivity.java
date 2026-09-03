package com.example.dailyroutineofflineapp;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
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

    private int getTextSize() {
        return prefs.getInt("textSize", 18);
    }

    private boolean isDark() {
        return prefs.getBoolean("darkMode", false);
    }

    private void applyScreenStyle() {

        if (isDark()) {
            getWindow().getDecorView()
                    .setBackgroundColor(Color.rgb(25, 25, 25));
        } else {
            boolean gray = prefs.getBoolean("grayBackground", false);

            if (gray) {
                getWindow().getDecorView()
                        .setBackgroundColor(Color.rgb(245, 245, 245));
            } else {
                getWindow().getDecorView()
                        .setBackgroundColor(Color.WHITE);
            }
        }
    }

    private void styleText(TextView text) {
        text.setTextSize(getTextSize());

        if (isDark()) {
            text.setTextColor(Color.WHITE);
        } else {
            text.setTextColor(Color.BLACK);
        }
    }

    private void styleButton(Button button) {

        boolean large =
                prefs.getBoolean("largeButton", false);

        button.setTextSize(
                large ? getTextSize() + 2 : getTextSize()
        );

        if (large) {
            button.setPadding(20, 25, 20, 25);
        } else {
            button.setPadding(15, 15, 15, 15);
        }
    }

    private void showLogin() {

        setContentView(R.layout.activity_main);

        applyScreenStyle();

        EditText username =
                findViewById(R.id.username);

        EditText password =
                findViewById(R.id.password);

        Button loginButton =
                findViewById(R.id.loginButton);

        TextView title =
                findViewById(
                        getResources().getIdentifier(
                                "loginTitle",
                                "id",
                                getPackageName()
                        )
                );

        if (title != null) {
            styleText(title);
        }

        styleButton(loginButton);

        loginButton.setOnClickListener(v -> {

            String user =
                    username.getText().toString().trim();

            String pass =
                    password.getText().toString();

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

        TextView title =
                findViewById(R.id.dashboardTitle);

        TextView date =
                findViewById(R.id.dateText);

        if (title != null) {
            styleText(title);
        }

        if (date != null) {
            styleText(date);
        }

        Button addRoutineButton =
                findViewById(R.id.addRoutineButton);

        Button customizeButton =
                findViewById(R.id.customizeButton);

        styleButton(addRoutineButton);
        styleButton(customizeButton);

        addRoutineButton.setOnClickListener(v -> {

            EditText input = new EditText(this);

            input.setHint("Routine name");
            input.setPadding(30, 20, 30, 20);

            new AlertDialog.Builder(this)
                    .setTitle("Add Routine")
                    .setView(input)
                    .setPositiveButton(
                            "Add",
                            (dialog, which) -> {

                                String routine =
                                        input.getText()
                                                .toString()
                                                .trim();

                                if (!routine.isEmpty()) {

                                    Toast.makeText(
                                            this,
                                            "Routine added: "
                                                    + routine,
                                            Toast.LENGTH_SHORT
                                    ).show();
                                }
                            }
                    )
                    .setNegativeButton(
                            "Cancel",
                            null
                    )
                    .show();
        });

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

        RadioButton loginSimple =
                findViewById(R.id.loginSimple);

        RadioButton loginModern =
                findViewById(R.id.loginModern);

        RadioButton boardSimple =
                findViewById(R.id.boardSimple);

        RadioButton boardCard =
                findViewById(R.id.boardCard);

        RadioButton backgroundWhite =
                findViewById(R.id.backgroundWhite);

        RadioButton backgroundGray =
                findViewById(R.id.backgroundGray);

        Button save =
                findViewById(R.id.saveCustomize);

        Button reset =
                findViewById(R.id.resetCustomize);

        Button back =
                findViewById(R.id.backDashboard);

        boolean dark =
                prefs.getBoolean("darkMode", false);

        if (dark) {
            darkTheme.setChecked(true);
        } else {
            lightTheme.setChecked(true);
        }

        int savedSize =
                prefs.getInt("textSize", 18);

        textSizeBar.setProgress(
                savedSize - 10
        );

        preview.setTextSize(savedSize);

        boolean large =
                prefs.getBoolean("largeButton", false);

        if (large) {
            buttonLarge.setChecked(true);
        } else {
            buttonNormal.setChecked(true);
        }

        boolean modernLogin =
                prefs.getBoolean("modernLogin", false);

        if (modernLogin) {
            loginModern.setChecked(true);
        } else {
            loginSimple.setChecked(true);
        }

        boolean cardBoard =
                prefs.getBoolean("cardBoard", false);

        if (cardBoard) {
            boardCard.setChecked(true);
        } else {
            boardSimple.setChecked(true);
        }

        boolean gray =
                prefs.getBoolean("grayBackground", false);

        if (gray) {
            backgroundGray.setChecked(true);
        } else {
            backgroundWhite.setChecked(true);
        }

        textSizeBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onProgressChanged(
                            SeekBar seekBar,
                            int progress,
                            boolean fromUser) {

                        preview.setTextSize(
                                progress + 10
                        );
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

        save.setOnClickListener(v -> {

            prefs.edit()
                    .putBoolean(
                            "darkMode",
                            darkTheme.isChecked()
                    )
                    .putBoolean(
                            "largeButton",
                            buttonLarge.isChecked()
                    )
                    .putBoolean(
                            "modernLogin",
                            loginModern.isChecked()
                    )
                    .putBoolean(
                            "cardBoard",
                            boardCard.isChecked()
                    )
                    .putBoolean(
                            "grayBackground",
                            backgroundGray.isChecked()
                    )
                    .putInt(
                            "textSize",
                            textSizeBar.getProgress() + 10
                    )
                    .apply();

            Toast.makeText(
                    this,
                    "Customization Saved ✓",
                    Toast.LENGTH_SHORT
            ).show();

            showDashboard();
        });

        reset.setOnClickListener(v -> {

            prefs.edit().clear().apply();

            Toast.makeText(
                    this,
                    "Customization Reset ✓",
                    Toast.LENGTH_SHORT
            ).show();

            showDashboard();
        });

        back.setOnClickListener(v -> {
            showDashboard();
        });
    }
}
