package com.example.pufferfishminesweeper.powerUps;


import static java.lang.Thread.sleep;

import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.Random;

public class FreezeTimer extends PowerUp {
    private long totalTime;
    private long downTime;
    private TextView timer;


    public FreezeTimer() {
        super();
    }

    public FreezeTimer(String name, Drawable img, long totalTime, TextView timer) {
        super(name, img);
        this.totalTime = totalTime;
        this.downTime = 1000;
        this.timer = timer;

    }

    public CountDownTimer generarTemporizador(){
        CountDownTimer myTimer = new CountDownTimer(totalTime, downTime) {
            public void onTick(long millisUntilFinished) {
                int time = (int) (millisUntilFinished / 1000);
                String timeString = String.valueOf(time);
                if (timeString.length() == 2) {
                    timeString = "0" + timeString;
                } else if (timeString.length() == 1) {
                    timeString = "00" + timeString;
                }
                ;
                timer.setText(timeString);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                endGame();
            }

        }.start();
        return myTimer;
    }

    private void endGame() {
        //metodos para generar la ventana de scoreboard
    }

    public CountDownTimer usePower() {
        CountDownTimer myTimer;
        String timeString = timer.getText().toString();
        timeString = timeString.replace("0", "");
        int power = new Random().nextInt();
        totalTime = Long.valueOf(timeString) * 1000;
        try {
            sleep(5000);
        } catch (InterruptedException e) {

        }
        myTimer= generarTemporizador();
        return myTimer;

    }
}
