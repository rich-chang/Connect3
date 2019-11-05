package com.richc.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 0 for yellow, 1 for red
    int activePlayer = 0;

    // To memory each grid status. 2 for un-played. 0 or 1 means player.
    int[] gameStatus = {2,2,2,2,2,2,2,2,2};

    // All winning positions
    int[][] winningPosition = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    boolean gameIsActive = true;

    // Image resource
    int[] gridImageResource = {R.id.imageView,
            R.id.imageView2,
            R.id.imageView3,
            R.id.imageView4,
            R.id.imageView5,
            R.id.imageView6,
            R.id.imageView7,
            R.id.imageView8,
            R.id.imageView9};

    public void dropIn (View view) {
        ImageView counter = (ImageView) view;
        System.out.println("tag-"+counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        // If grid is un-played, then allow to toggle by player
        if ((gameStatus[tappedCounter] == 2) && gameIsActive) {
            gameStatus[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            // Check winning
            for (int[] winningPosition: winningPosition) {

                // if WIN
                if (gameStatus[winningPosition[0]] == gameStatus[winningPosition[1]] &&
                        gameStatus[winningPosition[1]] == gameStatus[winningPosition[2]] &&
                        gameStatus[winningPosition[0]] != 2) {

                    // Show msg
                    String winningPalyer = "yellow";
                    if (gameStatus[winningPosition[0]] == 1) {
                        winningPalyer = "Red";
                    }
                    TextView winningMsgText = findViewById(R.id.winningMsgTextView);
                    winningMsgText.setText(winningPalyer + " has won!!!");

                    // Show whole winning layout
                    LinearLayout layout = findViewById(R.id.winningMsgLayout);
                    layout.setVisibility(View.VISIBLE);

                    gameIsActive = false;
                }
                else {
                    // No win
                }
            }
        }
        else {
            // Show error msg
            if (!gameIsActive) {
                Toast.makeText(MainActivity.this, "Game Over. Press [Play Again] button to restart.", Toast.LENGTH_SHORT).show();
            }
            else {
                if (gameStatus[tappedCounter] != 2) {
                    Toast.makeText(MainActivity.this, "This grid already been played", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void playAgain (View view) {

        LinearLayout layout = findViewById(R.id.winningMsgLayout);
        layout.setVisibility(View.INVISIBLE);

        gameIsActive = true;
        activePlayer = 0;

        for (int i=0; i<gameStatus.length; i++) gameStatus[i] = 2;

        for (int i=0; i<gridImageResource.length; i++)
            ((ImageView) findViewById(gridImageResource[i])).setImageResource(0);
    }
}
