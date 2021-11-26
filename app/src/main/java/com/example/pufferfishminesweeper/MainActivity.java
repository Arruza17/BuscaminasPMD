package com.example.pufferfishminesweeper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText editText_Nombre;
    private RadioGroup radioGroupDifficulties;
    private TextView appName;
    private Button buttonPlay;
    private Button buttonHelp;
    private Button buttonScoreBoard;
    private ImageButton logoChange;
    private int easterEgg = 10;
    private int radioButtonExtremeId;
    private boolean cambioImagen = false;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageButton buttonPhoto;
    private ImageView imagePhoto;
    private static final int CAMERA_PERM_CODE = 100;
    private byte[] bArray;
    private Bitmap imageBitmap;


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
        logoChange = findViewById(R.id.buttonChange);
        buttonPhoto = findViewById(R.id.buttonPhoto);
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
        buttonPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto();
            }
        });
    }

    private void generatePlayWindow() {
        //Getting the id and position of the selected radiobutton
        int radioButtonID = radioGroupDifficulties.getCheckedRadioButtonId();
        View radioButton = radioGroupDifficulties.findViewById(radioButtonID);
        int idx = radioGroupDifficulties.indexOfChild(radioButton) + 1;
        // Make sound when the play button is pressed
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.salto_al_agua);
        mediaPlayer.start();
        // Create Intent to the GAME CLASS
        Intent intent = new Intent(MainActivity.this, Game.class);
        // We give the name and the difficulty to the other activity
        intent.putExtra("difficulty", String.valueOf(idx));
        intent.putExtra("player", editText_Nombre.getText().toString());
        byte[] image = getBytesFromBitmap(imageBitmap);
        intent.putExtra("photo",image);
        Bundle b = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle();
        startActivity(intent, b);
        //Toast.makeText(MainActivity.this,String.valueOf(radioButton.getId()), Toast.LENGTH_SHORT).show();

    }

    private void generateHelpWindow() {

        Intent i;
        i = new Intent(MainActivity.this, HelpScreen.class);
        Bundle b = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle();
        startActivity(i, b);

    }

    private void generateScoreBoardWindow() {
        Intent i;
        i = new Intent(MainActivity.this, ScoreBoardScreen.class);
        Bundle b = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle();
        startActivity(i, b);
    }

    private void easterEgg() {
        //Makes a sound when the easterEgg is found
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.thunder);
        mediaPlayer.start();
        //Get the mobile device status
        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        //If the device status is not the SILENT does the VIBRATION
        if (am.getRingerMode() != AudioManager.RINGER_MODE_SILENT) {
            //Make the phone vibrate
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            // Vibrate for 500 milliseconds
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                //deprecated in API 26
                v.vibrate(500);
            }
        }
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
        //Adding the same paramethers that the other children have
        rb.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
        rb.setText(getString(R.string.extrema));
        //Change the color of the radiobutton
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
        logoChange.setClickable(false);

    }

    private int generateRandomColor() {
        Random rand = new Random();
        return rand.nextInt((255) + 1);
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

    private void takePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            buttonPhoto.setImageBitmap(imageBitmap);
        }
    }
    private byte[] getBytesFromBitmap(Bitmap bitmap){
        if(bitmap != null){
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
            bArray = bos.toByteArray();
            return bArray;
        }
        return null;
    }

}
