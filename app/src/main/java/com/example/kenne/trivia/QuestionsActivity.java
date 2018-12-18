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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;


public class QuestionsActivity extends AppCompatActivity implements QuestionsRequest.Callback, Response.Listener, Response.ErrorListener {

    private int questionIndex;
    private ArrayList<Question> questions;
    private String correctAnswer;
    private int highscore;
    private String player_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        Intent intent = getIntent();
        player_name = intent.getStringExtra("player_name");

//        CategoriesRequest x = new CategoriesRequest(this);
//        x.getCategories(this);
//
//        Toast.makeText(this,"Started",Toast.LENGTH_SHORT).show();

        QuestionsRequest x = new QuestionsRequest(this);
        x.getQuestions(this);
        Toast.makeText(this,"Started",Toast.LENGTH_SHORT).show();

    }


    public void loadQuestions(Integer questionIndex){
        TextView question_view = findViewById(R.id.questionView);
        TextView answer_view = findViewById(R.id.answertestView);
        TextView highscore_view = findViewById(R.id.highscoreView);
        TextView progress_view = findViewById(R.id.progressView);

        question_view.setText(Html.fromHtml(questions.get(questionIndex).getQuestion()));
        answer_view.setText(questions.get(questionIndex).getCorrect_answer());
        highscore_view.setText("Highscore: "+highscore);
        progress_view.setText("Progress: "+(questionIndex+1)+"/10");


        Button button_a = findViewById(R.id.buttonA);
        Button button_b = findViewById(R.id.buttonB);
        Button button_c = findViewById(R.id.buttonC);
        Button button_d = findViewById(R.id.buttonD);


        // Hustle the answers for the new question and set them to the buttons
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

    @Override
    public void gotQuestions(ArrayList<Question> questions) {
        questionIndex = 0;
        highscore = 0;
        this.questions = questions;
        loadQuestions(questionIndex);
    }

    @Override
    public void gotQuestionsError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }



    public void nextQuestion(View view){
        Button answerButton = (Button) view;
        String chosen_answer = answerButton.getText().toString();
        // Check if the chosen answer is the correct one
        if(chosen_answer==correctAnswer){
            highscore+=10;
            Toast.makeText(this, "Correct! You're score: "+highscore, Toast.LENGTH_SHORT).show();
            TextView highscore_view = findViewById(R.id.highscoreView);
            highscore_view.setText("Highscore: "+highscore);
        }
        // Repeat the the whole process until all questions are done, start intent to go new activity
        if(questionIndex==9){
            Log.d("abc",' '+String.valueOf(highscore));
            Toast.makeText(this, "All questions done!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, HighscoresActivity.class);
            intent.putExtra("highscore",highscore);
            intent.putExtra("player_name",player_name);

            ArrayList combine_array = new ArrayList<>();
            combine_array.add(highscore);
            combine_array.add(player_name);

            postHighscore(combine_array);
            // Use finish() to make it not possible to go back to this page from the highscore activity
            startActivity(intent);
            finish();
        }
        else {
            questionIndex += 1;
            loadQuestions(questionIndex);

        }
    }

    public void postHighscore(ArrayList input_array){
        String url = "http://ide50-kennitos.cs50.io:8080/list";
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        PostHighscoreRequest request = new PostHighscoreRequest(Request.Method.POST, url, this, this, input_array);
        queue.add(request);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        error.printStackTrace();
    }

    @Override
    public void onResponse(Object response) {
        Log.d(" response", "onResponse: " + response.toString());
    }
}
