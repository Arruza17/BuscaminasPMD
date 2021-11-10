package com.example.pufferfishminesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
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

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    boolean animation_NotDone = true;
    private TableLayout tableLayout;
    private EditText editText_Nombre;
    private RadioGroup radioGroupDifficulties;
    private RadioButton radioButtonEasy;
    private RadioButton radioButtonMedium;
    private RadioButton radioButtonHard;
    private TextView appName;
    private Button buttonPlay;
    private Button buttonHelp;
    private Button buttonScoreBoard;
    private String choosedDifficulty = null;
    private ImageButton logoChange;
    private int easterEgg = 10;
    private int radioButtonExtremeId;
    private boolean cambioImagen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Setting up the object to work with them
        editText_Nombre = findViewById(R.id.editText_Nombre);
        radioGroupDifficulties = findViewById(R.id.radioGroupDifficulties);
        buttonPlay = findViewById(R.id.buttonPlay);
        buttonHelp = findViewById(R.id.buttonHelp);
        buttonScoreBoard = findViewById(R.id.buttonScoreBoard);
        appName = findViewById(R.id.appName);
        radioButtonEasy = findViewById(R.id.radioButtonFacil);
        radioButtonMedium = findViewById(R.id.radioButtonMedia);
        radioButtonHard = findViewById(R.id.radioButtonDificil);
        logoChange = findViewById(R.id.buttonChange);
        //Adding all the listeners
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkEveryField()) {
                    generatePlayWindow();
                }
            }
        });
        buttonHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateHelpWindow();
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
        buttonScoreBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateScoreBoardWindow();
            }
        });
    }


    private void easterEgg() {
        if (animation_NotDone) {
            animation_NotDone = false;
            //Making sure that the user had found the Easter Egg
            Toast.makeText(getApplicationContext(), getString(R.string.easterEggFound), Toast.LENGTH_LONG).show();
            //A simple animation between two colors
            ObjectAnimator backgroundColorAnimator = ObjectAnimator.ofObject(appName,
                    "textColor",
                    new ArgbEvaluator(),
                    Color.rgb(74, 67, 67),
                    Color.rgb(239, 184, 16));
            //2 second animation set
            backgroundColorAnimator.setDuration(2000);
            backgroundColorAnimator.start();
            //Adding a new RadioButton to the RadioGroup
            RadioButton rb = new RadioButton(this);
            radioButtonExtremeId = RadioButton.generateViewId();
            rb.setId(radioButtonExtremeId);
            rb.setTextSize(16);
            rb.setTextColor(Color.WHITE);
            //rb.setPadding(0,5,0,0);
            //Adding the same paramethers that the other children have
            rb.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
            rb.setText(getString(R.string.extrema));
            //If its possible the color of the radio will change depending on your SKD level
            if (Build.VERSION.SDK_INT >= 21) {
                ColorStateList colorStateList = new ColorStateList(
                        new int[][]
                                {
                                        new int[]{-android.R.attr.state_enabled}, // Disabled
                                        new int[]{android.R.attr.state_enabled}   // Enabled
                                },
                        new int[]
                                {
                                        Color.rgb(128, 116, 116), // disabled
                                        Color.rgb(128, 116, 116)  // enabled
                                }
                );
                rb.setButtonTintList(colorStateList); // set the color tint list
            }
            //Add the new RadioButton to the RadioGroup
            radioGroupDifficulties.addView(rb);
            //Simple Color animation in the RadioButton Extreme
            ObjectAnimator backgroundColorAnimatorExtreme = ObjectAnimator.ofObject(rb,
                    "textColor",
                    new ArgbEvaluator(),
                    Color.rgb(generateRandomColor(), generateRandomColor(), generateRandomColor()),
                    Color.rgb(generateRandomColor(), generateRandomColor(), generateRandomColor()));
            //2 second animation set
            backgroundColorAnimatorExtreme.setDuration(2500);
            backgroundColorAnimatorExtreme.start();
        }
    }

    private int generateRandomColor() {
        Random rand = new Random();
        int randcolor = rand.nextInt((255 - 0) + 1) + 0;
        return randcolor;
    }

    private boolean checkEveryField() {
        boolean ok = true;
        if (editText_Nombre.getText().toString().isEmpty()) {
            ok = false;
            Toast.makeText(getApplicationContext(), getString(R.string.introduceTextoPrimero), Toast.LENGTH_SHORT).show();
        }
        if (radioGroupDifficulties.getCheckedRadioButtonId() == -1) {
            ok = false;
            Toast.makeText(getApplicationContext(), getString(R.string.introduceDificultad), Toast.LENGTH_SHORT).show();
        }
        return ok;
    }

    private void generateHelpWindow() {

        Intent i;
        i = new Intent(MainActivity.this,HelpScreen.class);
        Bundle b = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle();
        startActivity(i,b);

    }

    private void generateScoreBoardWindow() {
        Intent i;
        i = new Intent(MainActivity.this,ScoreBoardScreen.class);
        Bundle b = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle();
        startActivity(i,b);
    }

    private void generatePlayWindow() {
        // Setting up the the ids
        int radioButtonEasyId = radioButtonEasy.getId();
        int radioButtonMediumId = radioButtonMedium.getId();
        int radioButtonHardId = radioButtonHard.getId();
        // get selected radio button from radioGroup
        int selectedId = radioGroupDifficulties.getCheckedRadioButtonId();
        /*
        //Create intent to the other activity
        intent = new Intent(getApplicationContext(),Game.class);
        //We give the name and the difficulty to the other activity
        intent.putExtra("message","Animation Beauty");
        //Start the intents
        startActivities(intent);
         */
        //ANIMACION SI O NO?
        // find the radiobutton by returned id
        if (selectedId == radioButtonEasyId) {
        } else if (selectedId == radioButtonMediumId) {

        } else if (selectedId == radioButtonHardId) {

        } else {

        }
        //Toast.makeText(MainActivity.this,String.valueOf(radioButton.getId()), Toast.LENGTH_SHORT).show();
        //intent = new Intent(MainActivity.this, Game.class);
        //startActivityForResult(intent, SECODNARY_ACTIVITY_1);
    }


}
