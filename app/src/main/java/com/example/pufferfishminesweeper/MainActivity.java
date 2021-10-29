package com.example.pufferfishminesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private Intent intent;
    private EditText editText_Nombre;
    private RadioGroup radioGroupDifficulties;
    private Button buttonPlay;
    private Button buttonHelp;
    private Button buttonScoreBoard;
    private String choosedDifficulty = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //OBTENCIÓN DE OBJECTOS
        editText_Nombre = findViewById(R.id.editText_Nombre);
        radioGroupDifficulties = findViewById(R.id.radioGroupDifficulties);
        buttonPlay = findViewById(R.id.buttonPlay);
        buttonHelp = findViewById(R.id.buttonHelp);
        buttonScoreBoard = findViewById(R.id.buttonScoreBoard);
        //AÑADIDO DE LISTENERS
        buttonPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonPlay:
                generatePlayWindow();
                break;
            case R.id.buttonScoreBoard:
                generateScoreBoardWindow();
                break;
            case R.id.buttonHelp:
                generateHelpWindow();
                break;

        }
    }

    private void generateHelpWindow() {
    }

    private void generateScoreBoardWindow() {
    }

    private void generatePlayWindow() {
        //intent = new Intent(MainActivity.this, Game.class);
        //startActivityForResult(intent, SECODNARY_ACTIVITY_1);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        RadioButton radioButtonCheck = findViewById(radioGroupDifficulties.getCheckedRadioButtonId());
        choosedDifficulty = radioButtonCheck.getText().toString();
    }
}