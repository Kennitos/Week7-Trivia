package com.example.kenne.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

public class HighscoresActivity extends AppCompatActivity implements HighscoreRequest.Callback, Response.Listener, Response.ErrorListener {

    int highscore_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);
        Intent intent = getIntent();
        int highscore = intent.getIntExtra("highscore",0);
        highscore_input = highscore;
        Log.d("abc","intent "+highscore);


        TextView testintent = findViewById(R.id.testhsView);
        testintent.setText(String.valueOf(highscore));

        HighscoreRequest x = new HighscoreRequest(this);
        x.getHighscore(this);
        Toast.makeText(this,"Started",Toast.LENGTH_SHORT).show();

//        PostHighscoreRequest post = new PostHighscoreRequest();
//        x.getQuestions(this);
//        Toast.makeText(this,"Started",Toast.LENGTH_SHORT).show();
        postHighscore(highscore);

    }

    @Override
    public void gotHighscores(ArrayList<String> highscores) {
        Log.d("highscores_test","Runt deel2"+highscores);
        Toast.makeText(this, highscores.get(0), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, highscores.get(1), Toast.LENGTH_SHORT).show();

        try {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, highscores);
            ListView listView = findViewById(R.id.listview);
            listView.setAdapter(adapter);
        }
        catch (Error e){
            Log.d("highscores_test","Empyt arraylist highscores");
        }

    }


    @Override
    public void gotHighscoresError(String message) {
        Log.d("highscores_test","Runt response error");
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    public void postHighscore(int score_input){
        String url = "http://ide50-kennitos.cs50.io:8080/list";
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        PostHighscoreRequest request = new PostHighscoreRequest(Request.Method.POST, url, this, this, score_input);
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
