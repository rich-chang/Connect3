package com.richc.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn (View view) {
        ImageView counter = (ImageView) view;

        System.out.println(counter.getTag().toString());

        counter.setTranslationY(-1000f);
        counter.setImageResource(R.drawable.yellow);
        counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
    }
}
