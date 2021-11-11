package com.example.pufferfishminesweeper.powerUps;

import android.graphics.drawable.Drawable;

public class PowerUp {
    private String name;
    private Drawable img;

    public PowerUp() {

    }

    public PowerUp(String name, Drawable img) {
        this.name = name;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }
}
