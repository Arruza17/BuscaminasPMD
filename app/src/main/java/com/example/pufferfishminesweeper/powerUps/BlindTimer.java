package com.example.pufferfishminesweeper.powerUps;

import static java.lang.Thread.sleep;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

public class BlindTimer extends PowerUp {
    private TextView timer;


    public BlindTimer() {
        super();
    }

    public BlindTimer(String name, Drawable img, TextView timer) {
        super(name, img);
        this.timer = timer;

    }


    public void usePower() {
        timer.setVisibility(View.GONE);
        try {
            sleep(5000);
        } catch (InterruptedException e) {

        }
        timer.setVisibility(View.VISIBLE);
    }
}
