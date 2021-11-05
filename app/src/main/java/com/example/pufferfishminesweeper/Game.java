package com.example.pufferfishminesweeper;


import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pufferfishminesweeper.game.Board;
import com.example.pufferfishminesweeper.powerUps.BlindTimer;
import com.example.pufferfishminesweeper.powerUps.FreezeTimer;
import com.example.pufferfishminesweeper.powerUps.PowerUp;
import com.example.pufferfishminesweeper.powerUps.SlowTimer;

public class Game extends AppCompatActivity implements View.OnClickListener {


    private TextView timer;
    private Button btnMenu;
    private ImageButton btnPowerUp;
    private long totalTime;
    private long downTime = 1000;
    private int btnSize = 2000;
    private int fontSize = 25;
    private CountDownTimer timerText;
    private PowerUp powerUp;
    private GridLayout gridLayout;


    private int difficulty;
    private String[][] boardString;
    private TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        timer = findViewById(R.id.timer);
        timer.setText(String.valueOf(10));
        btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(this);
        btnPowerUp = findViewById(R.id.btnPowerUp);
        btnPowerUp.setOnClickListener(this);

        gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        gridLayout.setBackground(getDrawable(R.drawable.background));

        difficulty = 1;

        if (difficulty > 2) {
            btnSize = btnSize / (difficulty * 6);
            fontSize -= difficulty;

        } else {
            btnSize = btnSize / (difficulty * 9);

        }

        generateGame();

        timerText = new FreezeTimer("Freeze", null, totalTime, timer).generarTemporizador();


    }

    private void generateGame() {
        boardString = new Board(difficulty).getBombas();
        switch (difficulty) {
            case 1:
                totalTime = 100000;
                break;
            case 2:
                totalTime = 120000;
                break;
            case 3:
                totalTime = 240000;
                break;
            case 4:
                totalTime = 300000;
                break;
        }

        gridLayout.setColumnCount(boardString.length);
        gridLayout.setRowCount(boardString[1].length);
        ViewGroup.LayoutParams params = gridLayout.getLayoutParams();
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.width = ViewGroup.LayoutParams.WRAP_CONTENT;


        for (int i = 0; i < boardString.length; i++) {
            for (int j = 0; j < boardString[i].length; j++) {
                Button btn;
                ImageButton imgBtn;
                if (boardString[i][j].equals("*")) {
                    imgBtn = new ImageButton(this);
                    imgBtn.setImageDrawable(getDrawable(R.drawable.pufferfishday));
                    imgBtn.setAdjustViewBounds(true);
                    imgBtn.setLayoutParams(params);
                    imgBtn.setOnClickListener(this);
                    imgBtn.setMaxHeight(btnSize);
                    imgBtn.setMaxWidth(imgBtn.getMaxHeight());

                    createBorder(null, imgBtn);

                    gridLayout.addView(imgBtn);

                } else {
                    btn = new Button(this, null, R.style.Widget_AppCompat_Button_Small);
                    btn.setHeight(btnSize);
                    btn.setWidth(btnSize);
                    btn.setLayoutParams(params);
                    btn.setText(boardString[i][j]);
                    btn.setGravity(Gravity.CENTER);
                    btn.setTextSize(fontSize);
                    btn.setTextColor(Color.WHITE);
                    btn.setBackgroundColor(Color.argb(255, 255, 0, 0));
                    createBorder(btn, null);
                    gridLayout.addView(btn);

                }

            }
        }
    }

    private void createBorder(Button btn, ImageButton imgBtn) {
        ShapeDrawable shapedrawable = new ShapeDrawable();
        shapedrawable.setShape(new RectShape());
        shapedrawable.getPaint().setColor(Color.WHITE);
        shapedrawable.getPaint().setStrokeWidth(2f);
        shapedrawable.getPaint().setStyle(Paint.Style.STROKE);
        if (imgBtn != null)
            imgBtn.setBackground(shapedrawable);
        if (btn != null)
            btn.setBackground(shapedrawable);

    }


    @Override
    public void onClick(View view) {
        PowerUp powerUp;
        if (view.getId() == R.id.btnPowerUp) {
            int power = (int) ((Math.random() * 3) + 1);
            switch (power) {
                case 1:
                    btnPowerUp.setImageDrawable(getDrawable(R.drawable.timeslow));
                    btnPowerUp.setEnabled(false);
                    timerText.cancel();
                    powerUp = new FreezeTimer("Freeze", getDrawable(R.drawable.timeslow), totalTime, timer);

                    timerText = ((FreezeTimer) powerUp).usePower();
                    btnPowerUp.setEnabled(true);
                    break;
                case 2:
                    btnPowerUp.setImageDrawable(getDrawable(R.drawable.timefast));
                    timerText.cancel();
                    powerUp = new SlowTimer("SlowTimer", getDrawable(R.drawable.timefast), totalTime, timer);
                    timerText = ((SlowTimer) powerUp).usePower();
                    break;
                case 3:
                    btnPowerUp.setImageDrawable(getDrawable(R.drawable.jellyfish));
                    btnPowerUp.setEnabled(false);
                    powerUp = new BlindTimer("BlindTimer", getDrawable(R.drawable.jellyfish), timer);
                    ((BlindTimer) powerUp).usePower();
                    btnPowerUp.setEnabled(true);
                    break;
            }
        }

    }
}