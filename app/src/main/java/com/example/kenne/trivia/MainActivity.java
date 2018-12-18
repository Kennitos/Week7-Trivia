package com.example.kenne.trivia;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToQuestions(View view){
        EditText editName = findViewById(R.id.nameEdit);
        String name = editName.getText().toString();
        if(name==""){
            name = "Anonymous";
        }
        Intent intent = new Intent(this, QuestionsActivity.class);
        intent.putExtra("player_name",name);
        startActivity(intent);

    }
}
