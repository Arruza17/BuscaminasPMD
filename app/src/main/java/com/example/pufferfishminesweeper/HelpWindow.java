package com.example.pufferfishminesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HelpWindow extends AppCompatActivity implements View.OnClickListener {

    private Button btnBack;
    public static final int MAIN_ACTIVITY=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_window);
        btnBack=(Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {


        Intent intent = new Intent(HelpWindow.this, MainActivity.class);
        startActivityForResult(intent,MAIN_ACTIVITY);
        finish();

    }
}