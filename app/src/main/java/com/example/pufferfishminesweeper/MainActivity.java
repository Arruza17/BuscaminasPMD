package com.example.pufferfishminesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    private EditText editText_Nombre;
    private RadioGroup radioGroupDifficulties;
    private Button buttonPlay;
    private Button buttonHelp;
    private Button buttonScoreBoard;
    private String choosedDifficulty = null;
    private ImageButton logoChange;
    private int easterEgg = 10;
    private boolean cambioImagen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //OBTENCIÓN DE OBJETOS
        editText_Nombre = findViewById(R.id.editText_Nombre);
        radioGroupDifficulties = findViewById(R.id.radioGroupDifficulties);
        buttonPlay = findViewById(R.id.buttonPlay);
        buttonHelp = findViewById(R.id.buttonHelp);
        buttonScoreBoard = findViewById(R.id.buttonScoreBoard);
        logoChange = findViewById(R.id.buttonChange);
        //AÑADIDO DE LISTENERS
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkEveryField()) {
                    generatePlayWindow();
                }
            }
        });
        logoChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (easterEgg > 0) {
                    if (cambioImagen) {
                        logoChange.setBackgroundResource(R.drawable.pezglobo);
                        cambioImagen = false;
                    } else {
                        logoChange.setBackgroundResource(R.drawable.pezglobovuelta);
                        cambioImagen = true;
                    }
                    logoChange.setMaxWidth(75);
                    logoChange.setMaxHeight(75);
                    logoChange.setAdjustViewBounds(true);
                    easterEgg--;
                }
            }
        });
    }


    private boolean checkEveryField() {
        boolean ok = true;
        if (editText_Nombre.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), getString(R.string.escribeAqui), Toast.LENGTH_LONG).show();
        }
        return ok;
    }

    private void generateHelpWindow() {
    }

    private void generateScoreBoardWindow() {
    }

    private void generatePlayWindow() {
        //intent = new Intent(MainActivity.this, Game.class);
        //startActivityForResult(intent, SECODNARY_ACTIVITY_1);
    }


}