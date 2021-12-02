package com.example.pufferfishminesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class RecordedVideoPlayer extends AppCompatActivity {

    private static final int GRABAR_VIDEO = 0;

    private Button btnRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorded_video_player);
        btnRecord=findViewById(R.id.btnRecord);
        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comenzarGrabacion(view);
            }
        });
    }

    // Código asociado al botón COMENZAR_GRABACIÓN
    public void comenzarGrabacion(View view){
        // Creación del intent
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        // El vídeo se grabará en calidad baja (0)
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
        // Limitamos la duración de la grabación a 5 segundos
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 5);
        // Nos aseguramos de que haya una aplicación que pueda manejar el intent
        if (intent.resolveActivity(getPackageManager()) != null) {
            // Lanzamos el intent
            startActivityForResult(intent, GRABAR_VIDEO);

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GRABAR_VIDEO && resultCode == RESULT_OK) {
            VideoView videoView = (VideoView) findViewById(R.id.videoView);
            videoView.setVideoURI(data.getData());
            videoView.start();
        }
    }
}