package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean gameActive = true;
    /*
        1-> X
        2-> O
     */

    int curPlayer = 1;
    int [] gridState = {0,0,0,0,0,0,0,0,0};
    int [][] winPos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int counter = 0;

    // If any player tap
    public void playerTap (View view){
        ImageView img = (ImageView) view;
        int curBox = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            Reset(view);
        }
        if(gridState[curBox] == 0){
            if(counter == 9){
                gameActive = false;
            }
            counter++;
            gridState[curBox] = curPlayer;
            img.setTranslationY(-1000);
            if(curPlayer == 1){
                img.setImageResource(R.drawable.x);
                curPlayer = 2;
                ((TextView)findViewById(R.id.Result)).setText("Player 2 Turn");
            }
            else{
                img.setImageResource(R.drawable.o);
                curPlayer = 1;
                ((TextView)findViewById(R.id.Result)).setText("Player 1 Turn");
            }
            img.animate().translationYBy(1000f).setDuration(300);
            int flag = 0;
            // Check If anyone won
            for(int[] win: winPos){
                if(gridState[win[0]] == gridState[win[1]] && gridState[win[0]] == gridState[win[2]] && gridState[win[0]] != 0){
                    flag = 1;
                    gameActive = false;
                    if(gridState[win[0]] == 1){
                        ((TextView)findViewById(R.id.Result)).setText("Player 1 Won!");
                    }
                    else{
                        ((TextView)findViewById(R.id.Result)).setText("Player 2 Won!");
                    }
                }
            }
            if(counter == 9 && flag == 0){
                ((TextView)findViewById(R.id.Result)).setText("Match Drawn!");
            }
        }
    }

    public void Reset(View view){
        gameActive = true;
        curPlayer = 1;
        counter = 0;
        for(int i = 0;i<gridState.length; i++){
            gridState[i] = 0;
        }
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);

        ((TextView)findViewById(R.id.Result)).setText("Player 1 Turn!");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TextView)findViewById(R.id.Result)).setText("Player 1 Turn!");
    }
}