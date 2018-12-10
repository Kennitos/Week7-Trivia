package com.example.kenne.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class HighscoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);
        Intent intent = getIntent();
        int highscore = intent.getIntExtra("highscore",0);
        Log.d("abc","intent "+highscore);


        TextView testintent = findViewById(R.id.testhsView);
        testintent.setText(String.valueOf(highscore));

    }
}
