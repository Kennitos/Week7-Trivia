package com.example.kenne.trivia;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToQuestions(View view){
        Intent intent = new Intent(MainActivity.this, QuestionsActivity.class);
//        intent.putExtra("menu",menu);
        startActivity(intent);

    }
}
