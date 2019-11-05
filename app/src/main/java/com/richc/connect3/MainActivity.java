package com.richc.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 0 for yellow, 1 for red
    int activePlayer = 0;

    // To memory each grid status. 2 for unplayed. 0 or 1 means player.
    int[] gameStatus = {2,2,2,2,2,2,2,2,2};

    public void dropIn (View view) {
        ImageView counter = (ImageView) view;
        System.out.println("tag-"+counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        // If grid is unplayed, then allow to toggle by player
        if (gameStatus[tappedCounter] == 2) {
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
        }
        else {
            Toast.makeText(MainActivity.this, "This grid already been played", Toast.LENGTH_SHORT).show();
        }
    }
}
