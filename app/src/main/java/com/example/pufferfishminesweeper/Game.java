package com.example.pufferfishminesweeper;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.pufferfishminesweeper.game.Board;
import com.example.pufferfishminesweeper.game.NumberButton;
import com.example.pufferfishminesweeper.powerUps.BlindTimer;
import com.example.pufferfishminesweeper.powerUps.FreezeTimer;
import com.example.pufferfishminesweeper.powerUps.PowerUp;
import com.example.pufferfishminesweeper.powerUps.SlowTimer;

public class Game extends AppCompatActivity {


    private TextView timer;
    private Button btnMenu;
    private ImageButton btnPowerUp;
    private long totalTime;
    private final long downTime = 1000;
    private int btnSize = 2000;
    private int fontSize = 25;
    private CountDownTimer timerText;
    private GridLayout gridLayout;
    private int timeButton;
    private int difficulty;
    private String[][] boardString;
    private int bombs = 0;
    private String player;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        timer = findViewById(R.id.timer);
        timer.setText(String.valueOf(10));
        btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game.this, MainActivity.class);
                finish();
            }
        });
        btnPowerUp = findViewById(R.id.btnPowerUp);
        btnPowerUp.setVisibility(View.GONE);
        btnPowerUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                powerUpHandler(v);
                v.setEnabled(false);
            }
        });

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


    }

    private void powerUpHandler(View v) {
        PowerUp powerUp;
        if (v.getId() == R.id.btnPowerUp) {
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

    private void generateGame() {

        Board board = new Board(difficulty);
        boardString = board.getBombas();
        bombs = board.getBombCount();

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
        timerText = new FreezeTimer("Freeze", null, totalTime, timer).generarTemporizador();
        timeButton = (int) ((Math.random() * (int) (totalTime / 1000) + 1));
        // PowerUpShower pws = new PowerUpShower(timeButton, timer, btnPowerUp);

        gridLayout.setColumnCount(boardString.length);
        gridLayout.setRowCount(boardString[1].length);
        ViewGroup.LayoutParams params = gridLayout.getLayoutParams();
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.width = ViewGroup.LayoutParams.WRAP_CONTENT;


        for (int i = 0; i < boardString.length; i++) {

            GradientDrawable border = new GradientDrawable();
            border.setColor(Color.argb(100, 255, 255, 255)); //white background
            border.setStroke(1, 0xFF000000); //black border with full opacity

            for (int j = 0; j < boardString[i].length; j++) {
                NumberButton btn;
                ImageView imgBtn;
                if (boardString[i][j].equals("*")) {
                    imgBtn = new ImageView(this);
                    imgBtn.setImageDrawable(getDrawable(R.drawable.emptypng));
                    imgBtn.setBackgroundDrawable(border);
                    imgBtn.setAdjustViewBounds(true);
                    imgBtn.setLayoutParams(params);
                    imgBtn.setClickable(true);
                    imgBtn.setMaxHeight(btnSize);
                    imgBtn.setMaxWidth(btnSize);
                    imgBtn.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            Drawable myDrawable = getDrawable(R.drawable.flag);
                            if (((ImageView) v).getDrawable().getConstantState().equals(myDrawable.getConstantState())) {
                                imgBtn.setImageDrawable(getDrawable(R.drawable.emptypng));
                            } else {
                                imgBtn.setImageDrawable(getDrawable(R.drawable.flag));
                            }
                            return false;
                        }
                    });
                    imgBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            GradientDrawable clearBorder = new GradientDrawable();
                            clearBorder.setColor(Color.argb(100, 0, 0, 0));
                            clearBorder.setStroke(1, 0xFF000000);
                            imgBtn.setEnabled(false);
                            imgBtn.setClickable(false);
                            imgBtn.setBackgroundDrawable(clearBorder);
                            imgBtn.setImageDrawable(getDrawable(R.drawable.pufferfish));
                            loseScreen();
                        }
                    });


                    gridLayout.addView(imgBtn);
                } else {
                    btn = new NumberButton(this);
                    btn.setMaxHeight(btnSize);
                    btn.setMaxWidth(btnSize);
                    btn.setAdjustViewBounds(true);
                    btn.setLayoutParams(params);
                    btn.setImageDrawable(getDrawable(R.drawable.emptypng));
                    btn.setBackgroundDrawable(border);
                    btn.setRow(i);
                    btn.setColumn(j);
                    btn.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            Drawable myDrawable = getDrawable(R.drawable.flag);
                            if (((ImageView) v).getDrawable().getConstantState().equals(myDrawable.getConstantState())) {
                                btn.setImageDrawable(getDrawable(R.drawable.emptypng));
                            } else {
                                btn.setImageDrawable(getDrawable(R.drawable.flag));
                            }
                            return false;
                        }
                    });

                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            changeButtonProperties((NumberButton) v);
                            checkIfWin();
                            if (boardString[((NumberButton) v).getRow()][((NumberButton) v).getColumn()].equals("")) {
                                emptySurroundings((NumberButton) v);
                            }

                        }
                    });
                    gridLayout.addView(btn);
                }

            }
        }
    }

    private void establishButtonImage(NumberButton btn) {
        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .width(btnSize)  // width in px
                .height(btnSize) // height in px
                .endConfig()
                .buildRect(boardString[btn.getRow()][btn.getColumn()], Color.argb(100, 0, 0, 0));
        btn.setImageDrawable(drawable);
    }

    private void changeButtonProperties(NumberButton btn) {
        btn.setEnabled(false);
        btn.setClicked(true);
        GradientDrawable clearBorder = new GradientDrawable();
        clearBorder.setColor(Color.argb(100, 0, 0, 0));
        clearBorder.setStroke(1, 0xFF000000);
        establishButtonImage(btn);
        btn.setBackgroundDrawable(clearBorder);


    }

    private void emptySurroundings(NumberButton btn) {
        int x = btn.getRow();
        int y = btn.getColumn();
        NumberButton button;
        if (x == 0) {// FILA 0
            if (y == 0) { // COLUMNA 0
                button = searchForButton(x, y + 1); //  E
                if (button == null) {
                    button = searchForButton(x + 1, y); // S
                    if (button != null) {
                        emptySurroundings(button);
                    }
                } else {
                    emptySurroundings(button);
                }
            } else if (y == gridLayout.getColumnCount() - 1) { //  ULTIMA COLUMNA
                button = searchForButton(x, y - 1); // O
                if (button == null) {
                    button = searchForButton(x + 1, y); // S
                    if (button != null) {
                        emptySurroundings(button);
                    }
                } else {
                    emptySurroundings(button);
                }
            } else { // RESTO
                button = searchForButton(x, y + 1); // E
                if (button == null) {
                    button = searchForButton(x + 1, y); // S
                    if (button == null) {
                        button = searchForButton(x, y - 1); // O
                        if (button != null) {
                            emptySurroundings(button);
                        }
                    } else {
                        emptySurroundings(button);
                    }
                } else {
                    emptySurroundings(button);
                }
            }
        } else if (x == gridLayout.getRowCount() - 1) { // ULTIMA FILA
            if (y == 0) { // COLUMNA 0
                button = searchForButton(x, y + 1); // E
                if (button == null) {
                    button = searchForButton(x - 1, y); // N
                    if (button != null) {
                        emptySurroundings(button);
                    }
                } else {
                    emptySurroundings(button);
                }
            } else if (y == gridLayout.getColumnCount() - 1) { // ULTIMA COLUMNA
                button = searchForButton(x, y - 1); // O
                if (button == null) {
                    button = searchForButton(x - 1, y); // N
                    if (button != null) {
                        emptySurroundings(button);
                    }
                } else {
                    emptySurroundings(button);
                }
            } else {
                button = searchForButton(x, y - 1); // O
                if (button == null) {
                    button = searchForButton(x - 1, y); // N
                    if (button != null) {
                        emptySurroundings(button);
                    }
                    if (button == null) {
                        button = searchForButton(x, y + 1); // E
                        if (button != null) {
                            emptySurroundings(button);
                        }
                    } else {
                        emptySurroundings(button);
                    }
                } else {
                    emptySurroundings(button);
                }
            }
        } else {
            if (y == 0) { // COLUMNA 0
                button = searchForButton(x + 1, y); // S
                if (button == null) {
                    button = searchForButton(x, y + 1); // E
                    if (button == null) {
                        button = searchForButton(x - 1, y); // N
                        if (button != null) {
                            emptySurroundings(button);
                        }
                    } else {
                        emptySurroundings(button);
                    }
                } else {
                    emptySurroundings(button);
                }
            } else if (y == gridLayout.getColumnCount() - 1) { // ULTIMA COLUMNA
                button = searchForButton(x + 1, y); // S
                if (button == null) {
                    button = searchForButton(x, y - 1); // O
                    if (button == null) {
                        button = searchForButton(x - 1, y); // N


                        emptySurroundings(button);
                    } else {
                        button = searchForButton(x + 1, y); // N
                        if (button == null) {
                            button = searchForButton(x, y + 1); // E
                            if (button == null) {
                                button = searchForButton(x - 1, y); // S
                                if (button == null) {
                                    button = searchForButton(x, y - 1); // O
                                }
                            }
                        }
                        emptySurroundings(button);
                    }
                }

            }
        }
    }


    private NumberButton searchForButton(int x, int y) {
        NumberButton but = null;
        if (!boardString[x][y].equals("*")) {
            for (int j = 0; j < gridLayout.getChildCount(); j++) {
                if (gridLayout.getChildAt(j) instanceof NumberButton) {
                    NumberButton child = (NumberButton) gridLayout.getChildAt(j);
                    if (!child.isClicked() && (child.getRow() == x && child.getColumn() == y)) { //  boton sin clickar, posicion deseada
                        but = child;
                        changeButtonProperties(but);
                        if (!boardString[x][y].equals("")) {
                            but = null;
                        }
                    }
                }
            }
        }
        return but;
    }

    private void checkIfWin() {
        int num = 0;
        for (int j = 0; j < gridLayout.getChildCount(); j++) {
            if (gridLayout.getChildAt(j) instanceof NumberButton) {
                if (((NumberButton) gridLayout.getChildAt(j)).isClicked()) {
                    num++;
                }
            }
        }
        if (gridLayout.getChildCount() - bombs == num)
            win();
    }

    private void win() {
        timerText.cancel();
        int score = (int) (((totalTime / 100) - Integer.parseInt(timer.getText().toString())) * difficulty) * 1000;


    }

    private void loseScreen() {
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            if (!(gridLayout.getChildAt(i) instanceof NumberButton)) {
                ImageView view = (ImageView) gridLayout.getChildAt(i);
                GradientDrawable clearBorder = new GradientDrawable();
                clearBorder.setColor(Color.argb(100, 0, 0, 0));
                clearBorder.setStroke(1, 0xFF000000);
                view.setBackgroundDrawable(clearBorder);
                view.setImageDrawable(getDrawable(R.drawable.pufferfish));
                view.setEnabled(false);
            } else {
                gridLayout.getChildAt(i).setEnabled(false);
            }

        }
    }


}