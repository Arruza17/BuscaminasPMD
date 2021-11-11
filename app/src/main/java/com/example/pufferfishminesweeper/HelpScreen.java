package com.example.pufferfishminesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class HelpScreen extends AppCompatActivity {

    private Button btn_Back;
    private Button buttonTutorial;

    private final String urlVideoCastellano = "https://www.youtube.com/watch?v=plNEIt9plzY&ab_channel=DaveT";
    private final String urlVideoIngles = "https://www.youtube.com/watch?v=7B85WbEiYf4&ab_channel=EricBuffington";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_screen);
        //GET THE OBJECTS
        btn_Back = (Button) findViewById(R.id.buttonBack);
        buttonTutorial = findViewById(R.id.buttonTutorial);
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

    }

    private void intentImplicitoYoutube() {
        //GENERAMOS LA VENTANA HACIA UN APLICAION DE BUSQUEDA ONLINE
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        //DEPENDIENDO DE QUE IDIOMA TENGA IRA AL VIDEO EN INGLES O CASTELLANO
        String url = (Locale.getDefault().getLanguage().equals("es"))?urlVideoCastellano:urlVideoIngles;
        intent.setData(Uri.parse(url));
        Intent chooser = intent.createChooser(intent, getString(R.string.txt_intent_map));
        if ((chooser.resolveActivity(getPackageManager())) != null) {
            startActivity(chooser);
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.txt_error_2), Toast.LENGTH_LONG).show();
        }
    }
}