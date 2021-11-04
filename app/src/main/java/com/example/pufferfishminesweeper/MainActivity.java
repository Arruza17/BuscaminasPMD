package com.example.pufferfishminesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    boolean animation_Notdone = true;
    private TableLayout tableLayout;
    private EditText editText_Nombre;
    private RadioGroup radioGroupDifficulties;
    private TextView appName;
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
        //tableLayout = findViewById(R.id.tableLayout);
        editText_Nombre = findViewById(R.id.editText_Nombre);
        radioGroupDifficulties = findViewById(R.id.radioGroupDifficulties);
        buttonPlay = findViewById(R.id.buttonPlay);
        buttonHelp = findViewById(R.id.buttonHelp);
        appName = findViewById(R.id.appName);
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
                    easterEgg--;
                } else {
                    easterEgg();
                }
            }
        });
    }

    private void easterEgg() {
        if (editText_Nombre.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), getString(R.string.introduceTextoPrimero), Toast.LENGTH_LONG).show();
        } else {
            if (animation_Notdone) {
                animation_Notdone = false;
                Toast.makeText(getApplicationContext(), getString(R.string.easterEggFound), Toast.LENGTH_LONG).show();
                ObjectAnimator backgroundColorAnimator = ObjectAnimator.ofObject(appName,
                        "textColor",
                        new ArgbEvaluator(),
                        Color.rgb(74, 67, 67),
                        Color.rgb(239, 184, 16));
                //2 second animation done
                backgroundColorAnimator.setDuration(2000);
                backgroundColorAnimator.start();
                RadioButton extreme = new RadioButton(null);
                radioGroupDifficulties.addView(extreme);
            }
        }
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
