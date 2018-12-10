package com.example.kenne.trivia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class QuestionsActivity extends AppCompatActivity implements QuestionsRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

//        CategoriesRequest x = new CategoriesRequest(this);
//        x.getCategories(this);
//
//        Toast.makeText(this,"Started",Toast.LENGTH_SHORT).show();

        QuestionsRequest x = new QuestionsRequest(this);
        x.getQuestions(this);
        Toast.makeText(this,"Started",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void gotQuestions(ArrayList<Question> questions) {
        Toast.makeText(this, "hoi", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void gotQuestionsError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
