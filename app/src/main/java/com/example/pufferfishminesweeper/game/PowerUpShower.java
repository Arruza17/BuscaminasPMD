package com.example.pufferfishminesweeper.game;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PowerUpShower extends Thread{

    private int showTime;
    private int curTime;
    private TextView view;
    private ImageButton btn;
    private boolean notUsed=true;

    public PowerUpShower(int showTime, TextView view, ImageButton btn){
        this.showTime = showTime;
        this.view = view;
        this.btn = btn;
        this.run();
    }

    public void run(){
        while(notUsed)
        curTime=Integer.parseInt(view.getText().toString());
        if(showTime==curTime){
            btn.setVisibility(View.VISIBLE);
            notUsed=true;
            this.interrupt();
        }
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
