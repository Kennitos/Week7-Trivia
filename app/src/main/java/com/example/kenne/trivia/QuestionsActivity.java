package com.example.kenne.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class QuestionsActivity extends AppCompatActivity implements QuestionsRequest.Callback {

    private int questionIndex;
    private ArrayList<Question> questions;
    private String correctAnswer;
    private int highscore;

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
        questionIndex = 0;
        highscore = 0;
        this.questions = questions;

        TextView question_view = findViewById(R.id.questionView);
        TextView answer_view = findViewById(R.id.answertestView);
        TextView highscore_view = findViewById(R.id.highscoreView);
        TextView progress_view = findViewById(R.id.progressView);

        question_view.setText(Html.fromHtml(questions.get(0).getQuestion()));
        answer_view.setText(questions.get(0).getCorrect_answer());
        highscore_view.setText("Highscore: "+highscore);
        progress_view.setText("Progress: "+(questionIndex+1)+"/10");

        Button button_a = findViewById(R.id.buttonA);
        Button button_b = findViewById(R.id.buttonB);
        Button button_c = findViewById(R.id.buttonC);
        Button button_d = findViewById(R.id.buttonD);

        try {
            correctAnswer = questions.get(questionIndex).getCorrect_answer();
            button_a.setText(questions.get(questionIndex).getIncorrect_answer().getString(0));
            button_b.setText(questions.get(questionIndex).getIncorrect_answer().getString(1));
            button_c.setText(questions.get(questionIndex).getIncorrect_answer().getString(2));
            button_d.setText(correctAnswer);
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        .getString(i)
    }

    public void nextQuestion(View view){
        Button answerButton = (Button) view;
        String chosen_answer = answerButton.getText().toString();
        if(chosen_answer==correctAnswer){
            highscore+=10;
            Toast.makeText(this, "Correct! You're score: "+highscore, Toast.LENGTH_SHORT).show();
            TextView highscore_view = findViewById(R.id.highscoreView);
            highscore_view.setText("Highscore: "+highscore);
        }
        if(questionIndex==9){
            Log.d("abc",' '+String.valueOf(highscore));
            Toast.makeText(this, "All questions done!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(QuestionsActivity.this, HighscoresActivity.class);
            intent.putExtra("highscore",highscore);

            startActivity(intent);
        }
        else {
            questionIndex += 1;

            TextView question_view = findViewById(R.id.questionView);
            TextView answer_view = findViewById(R.id.answertestView);
//            TextView highscore_view = findViewById(R.id.highscoreView);
            TextView progress_view = findViewById(R.id.progressView);

            question_view.setText(Html.fromHtml(questions.get(questionIndex).getQuestion()));
            answer_view.setText(questions.get(questionIndex).getCorrect_answer());
//            highscore_view.setText("Highscore: "+highscore);
            progress_view.setText("Progress: "+(questionIndex+1)+"/10");


            Button button_a = findViewById(R.id.buttonA);
            Button button_b = findViewById(R.id.buttonB);
            Button button_c = findViewById(R.id.buttonC);
            Button button_d = findViewById(R.id.buttonD);

            ArrayList answers_list = new ArrayList();
            try {
                correctAnswer = questions.get(questionIndex).getCorrect_answer();
                answers_list.add(questions.get(questionIndex).getIncorrect_answer().getString(0));
                answers_list.add(questions.get(questionIndex).getIncorrect_answer().getString(1));
                answers_list.add(questions.get(questionIndex).getIncorrect_answer().getString(2));
                answers_list.add(correctAnswer);

                Collections.shuffle(answers_list);

                button_a.setText((String) answers_list.get(0));
                button_b.setText((String) answers_list.get(1));
                button_c.setText((String) answers_list.get(2));
                button_d.setText((String) answers_list.get(3));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void gotQuestionsError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }


}
