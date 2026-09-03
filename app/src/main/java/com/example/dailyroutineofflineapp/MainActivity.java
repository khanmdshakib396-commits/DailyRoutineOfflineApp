package com.example.dailyroutineofflineapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textView = new TextView(this);
        textView.setText("Daily Routine");
        textView.setTextSize(28);
        textView.setPadding(40, 80, 40, 40);

        setContentView(textView);
    }
}
