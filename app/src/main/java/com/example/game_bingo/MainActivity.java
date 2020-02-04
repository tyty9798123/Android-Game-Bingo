package com.example.game_bingo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button[][] balls = {
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
    }
}
