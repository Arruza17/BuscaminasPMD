package com.example.pufferfishminesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.Locale;

public class HelpScreen extends AppCompatActivity {

    private Button btn_Back;
    private ImageButton buttonTutorial;


    private final String urlVideoCastellano = "https://www.youtube.com/watch?v=plNEIt9plzY&ab_channel=DaveT";
    private final String urlVideoIngles = "https://www.youtube.com/watch?v=7B85WbEiYf4&ab_channel=EricBuffington";
    private MediaController mediaController;
    private VideoView videoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_screen);
        //GET THE OBJECTS
        btn_Back = (Button) findViewById(R.id.buttonBack);
        buttonTutorial = findViewById(R.id.buttonTutorial);
        videoView = (VideoView) findViewById(R.id.videoView);
        //LISTENERS
        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        buttonTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentImplicitoYoutube();
            }
        });


        //Initialize media controller
        mediaController = new MediaController(this);
        //Add controllers to the video
        videoView.setMediaController(mediaController);
        //Put the widget in
        mediaController.setAnchorView(videoView);
        //Load the video
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.rickroll));

        //Reminding the user the video is loaded and prepared
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                // The control will be shown for 20 seconds
                videoView.start();
            }
        });

        TextView txtTut = findViewById(R.id.txtTutorial);
        buttonTutorial.setTranslationY(300);
        btn_Back.setTranslationY(300);
        txtTut.setTranslationY(300);

        btn_Back.setAlpha(0);

        btn_Back.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        buttonTutorial.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        txtTut.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();


    }

    private void intentImplicitoYoutube() {
        //GENERAMOS LA VENTANA HACIA UN APLICAION DE BUSQUEDA ONLINE
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        //DEPENDIENDO DE QUE IDIOMA TENGA IRA AL VIDEO EN INGLES O CASTELLANO
        String url = (Locale.getDefault().getLanguage().equals("es")) ? urlVideoCastellano : urlVideoIngles;
        intent.setData(Uri.parse(url));
        Intent chooser = intent.createChooser(intent, getString(R.string.txt_intent_map));
        if ((chooser.resolveActivity(getPackageManager())) != null) {
            startActivity(chooser);
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.txt_error_2), Toast.LENGTH_LONG).show();
        }
    }
}