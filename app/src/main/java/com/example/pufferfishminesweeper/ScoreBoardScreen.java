package com.example.pufferfishminesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.pufferfishminesweeper.classes.ScoreBoard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class ScoreBoardScreen extends AppCompatActivity {

    private TableLayout tableLayoutScores;

    private SQLiteDatabase databasePufferFish = null;

    private final String DATABASE_NAME = "PufferFishDataBase";
    private final String TABLE_NAME = "t_scoreboard";
    private final String COLUM_NOMBRE = "Nombre";
    private final String COLUM_PUNTUACION = "Puntuacion";
    private final String SELECT_ALL_DATA = "SELECT * FROM "+TABLE_NAME+" ORDER BY "+COLUM_PUNTUACION+" DESC LIMIT 10";
    private TableLayout tableLayoutScores1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        tableLayoutScores = findViewById(R.id.tableLayoutScores);
        databasePufferFish = openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
        databasePufferFish.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + COLUM_NOMBRE + " VARCHAR, " + COLUM_PUNTUACION + " INTEGER)");
        Random rand = new Random();
        int randPuntuacion = rand.nextInt((255 - 0) + 1) + 0;
        databasePufferFish.execSQL("INSERT INTO " + TABLE_NAME + "(" + COLUM_NOMBRE + "," + COLUM_PUNTUACION + ")" + "VALUES ('Yeray',"+ randPuntuacion+")");

        ArrayList<ScoreBoard> scoreBoards = getScores();
        int index = 0;
        for (ScoreBoard s : scoreBoards) {
            index++;
            //ADD A NEW ROW
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            //ADD THE NAME COLUMN
            TextView nombre = new TextView(this);
            nombre.setText(s.getNombre());
            nombre.setGravity(Gravity.CENTER);
            //ADD THE COLUMN TO THE ROW
            tableRow.addView(nombre);
            //ADD THE SCORE COLUMN
            TextView score = new TextView(this);
            score.setGravity(Gravity.CENTER);
            score.setText(String.valueOf(s.getPuntuacion()));
            if(index%2==0){
                nombre.setBackground(getDrawable(R.drawable.border));
                score.setBackground(getDrawable(R.drawable.border));
            }else{
                nombre.setBackground(getDrawable(R.drawable.border_odd));
                score.setBackground(getDrawable(R.drawable.border_odd));
            }
            //ADD THE COLUMN TO THE ROW
            tableRow.addView(score);

            tableLayoutScores.addView(tableRow);

        }
        databasePufferFish.close();
    }

    /**
     * Method that return all the scores
     *
     * @return an arraylist with all the scores
     */
    public ArrayList<ScoreBoard> getScores() {
        // on below line we are creating a cursor with query to read data from database.
        Cursor cursor = databasePufferFish.rawQuery(SELECT_ALL_DATA, null);
        // on below line we are creating a new array list.
        ArrayList<ScoreBoard> arrayList = new ArrayList<>();
        //moving our cursor to first position
        if (cursor.moveToFirst()) {
            // moving our cursor to next
            while (cursor.moveToNext()) {
                // on below line we are adding the data from cursor to our array list.
                arrayList.add(new ScoreBoard(cursor.getString(0), cursor.getInt(1)));
            }
        }
        // at last closing our cursor
        // and returning our array list.
        cursor.close();
        return arrayList;
    }
}