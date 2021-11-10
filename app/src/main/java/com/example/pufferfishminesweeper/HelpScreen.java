package com.example.pufferfishminesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HelpScreen extends AppCompatActivity  {

    private Button btn_Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_screen);
        btn_Back=(Button) findViewById(R.id.btnBack);
        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateMainWindow();
            }
        });

    }

    private void generateMainWindow(){

        if(btn_Back.isPressed()){
            Intent i;
            i = new Intent(HelpScreen.this,MainActivity.class);
            Bundle b = ActivityOptions.makeSceneTransitionAnimation(HelpScreen.this).toBundle();
            startActivity(i,b);
        }

    }

}