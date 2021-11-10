package com.example.pufferfishminesweeper.game;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class PowerUpShower extends Thread {

    private int showTime;
    private int curTime;
    private TextView view;
    private ImageButton btn;
    private boolean used = false;

    public PowerUpShower(int showTime, TextView view, ImageButton btn) {
        this.showTime = showTime;
        this.view = view;
        this.btn = btn;
        this.run();
    }

    public void run() {
        while (!used) {
            curTime = Integer.parseInt(view.getText().toString());
            if (showTime == curTime) {
                btn.setVisibility(View.VISIBLE);
                used = true;
                this.interrupt();
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
