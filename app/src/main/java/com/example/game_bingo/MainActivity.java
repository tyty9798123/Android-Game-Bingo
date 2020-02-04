package com.example.game_bingo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private Button[][] balls;
    private boolean[][] positionClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setInitVariable();
        setRandomTextForBalls();
        setOnClickListenerForBalls();
    }

    public void setOnClickListenerForBalls(){
        for (int i = 0; i < balls.length; i++){
            for (int j = 0; j < balls[i].length; j++) {
                balls[i][j].setOnClickListener(ballClicked);
            }
        }
    }

    Button.OnClickListener ballClicked = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {


            for (int i = 0; i < balls.length; i++){
                for (int j = 0; j < balls[i].length; j++) {
                    if (v.getId() == balls[i][j].getId()){
                        // the button has been clicked
                        // do something
                        positionClicked[i][j] = true;


                        balls[i][j].setEnabled(false);
                        checkWeatherWin();
                    }
                }
            }


        }
    };

    public void setInitVariable(){
        positionClicked = new boolean[][]{
                {
                        false, false, false, false, false
                },
                {
                        false, false, false, false, false
                },
                {
                        false, false, false, false, false
                },
                {
                        false, false, false, false, false
                },
                {
                        false, false, false, false, false
                }
        };
        balls = new Button[][]{
                {
                        findViewById(R.id.button1), findViewById(R.id.button2), findViewById(R.id.button3), findViewById(R.id.button4), findViewById(R.id.button5),
                },
                {
                        findViewById(R.id.button6), findViewById(R.id.button7), findViewById(R.id.button8), findViewById(R.id.button9), findViewById(R.id.button10),
                },
                {
                        findViewById(R.id.button11), findViewById(R.id.button12), findViewById(R.id.button13), findViewById(R.id.button14), findViewById(R.id.button15),
                },
                {
                        findViewById(R.id.button16), findViewById(R.id.button17), findViewById(R.id.button18), findViewById(R.id.button19), findViewById(R.id.button20),
                },
                {
                        findViewById(R.id.button21), findViewById(R.id.button22), findViewById(R.id.button23), findViewById(R.id.button24), findViewById(R.id.button25),
                }
        };
        for (int i = 0; i < balls.length; i++){
            for (int j = 0; j < balls[i].length; j++) {
                balls[i][j].setEnabled(true);
            }
        }
    }

    void setRandomTextForBalls(){
        int[] array = new int[25];
        Set cache = new HashSet();
        for (int i = 0; i < 25 ;i++) {
            array[i] = getPoint();
            while(cache.contains(array[i])) {
                array[i] = getPoint();
            }
            cache.add(array[i]);
        }
        int arrayIndex = 0;
        for (int i = 0; i < balls.length; i++){
            for (int j = 0; j < balls[i].length; j++) {
                balls[i][j].setText( String.format("%d", array[arrayIndex++]) );
            }
        }
    }
    static int getPoint() {
        int value = 0;
        value = (int)(Math.floor(Math.random()*25)+1);
        return value;
    }

    void checkWeatherWin(){
        //列贏

        for (int i = 0; i < balls.length; i++){
            int row_count = 0;
            for (int j = 0; j < balls[i].length; j++) {
                if (positionClicked[i][j])
                    row_count ++;
            }
            if (checkWinCount(row_count)){
                showDialog("你贏了！");
                return;
            }
        }

        //欄贏
        int col = 0;
        while(col<5){
            int col_win = 0;
            for (int i = 0; i < 5; i++){
                if ( positionClicked[i][col] )
                    col_win++;
            }
            col++;
            if (checkWinCount(col_win)){
                showDialog("你贏了！");
                return;
            }
        }

        // 從左到右的斜對角
        // 00 11 22 33 44 贏
        // 04 13 22 31 40 贏
        int x, y;
        x = y = 0;
        int oblique_left_to_right = 0;
        while(x<5 && y < 5){
            if ( positionClicked[x++][y++] ){
                oblique_left_to_right++;
            }
        }
        if (checkWinCount(oblique_left_to_right)){
            showDialog("你贏了！");
            return;
        }

        x = 0;
        y = 4;
        int oblique_right_to_left = 0;

        while(x!=5 && y!=-1){
            if ( positionClicked[x++][y--] ){
                oblique_right_to_left++;
            }
        }
        if (checkWinCount(oblique_right_to_left)){
            showDialog("你贏了！");
            return;
        }
    }

    void showDialog(String str){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(str);
        builder.setNegativeButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {
                setInitVariable();
                setRandomTextForBalls();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    boolean checkWinCount(int count){
        if (count == 5){
            return true;
        }
        return false;
    }
}