package com.example.pufferfishminesweeper.game;

import android.content.Context;
import android.widget.ImageView;

import com.example.pufferfishminesweeper.R;

public class NumberButton extends androidx.appcompat.widget.AppCompatImageView {

    private int row;
    private int column;
    private boolean clicked;

    public NumberButton(Context context) {
        super(context, null, R.style.Widget_AppCompat_Button_Small);
        clicked = false;
        super.setClickable(true);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
}
