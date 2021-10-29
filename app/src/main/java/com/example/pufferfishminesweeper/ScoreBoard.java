package com.example.pufferfishminesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreBoard extends AppCompatActivity implements View.OnClickListener {

    private TextView txtList;
    private Button btnBack;
    public static final int MAIN_ACTIVITY=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);

        txtList=(TextView) findViewById(R.id.txtScoreList);
        btnBack=(Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(ScoreBoard.this, MainActivity.class);
        startActivityForResult(intent,MAIN_ACTIVITY);
        finish();


    }
}