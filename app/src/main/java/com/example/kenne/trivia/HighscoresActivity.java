package com.example.kenne.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HighscoresActivity extends AppCompatActivity implements HighscoreRequest.Callback {

    int highscore_input;
    ArrayList input_array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);
        Intent intent = getIntent();
        int highscore = intent.getIntExtra("highscore",0);
        highscore_input = highscore;

        String player_name = intent.getStringExtra("player_name");
        Log.d("abc","intent "+highscore+player_name);
        ArrayList combine_array = new ArrayList<>();
        combine_array.add(highscore);
        combine_array.add(player_name);


        input_array = combine_array;

        TextView testintent = findViewById(R.id.testhsView);
        testintent.setText(String.valueOf(highscore));

        HighscoreRequest x = new HighscoreRequest(this);
        x.getHighscore(this);
        Toast.makeText(this,"Started",Toast.LENGTH_SHORT).show();

//        PostHighscoreRequest post = new PostHighscoreRequest();
//        x.getQuestions(this);
//        Toast.makeText(this,"Started",Toast.LENGTH_SHORT).show();
//        postHighscore(input_array);

    }

//    public void postHighscore(ArrayList input_array){
//        String url = "http://ide50-kennitos.cs50.io:8080/list";
//        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
//        PostHighscoreRequest request = new PostHighscoreRequest(Request.Method.POST, url, this, this, input_array);
//        queue.add(request);
//    }

    @Override
    public void gotHighscores(ArrayList<HighscoreItem> highscoreItems) {
        Log.d("highscores_test","Runt deel2");

        try {
            HighscoreAdapter adapter = new HighscoreAdapter(this, R.layout.highscore_items, highscoreItems);
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




//    @Override
//    public void onErrorResponse(VolleyError error) {
//        error.printStackTrace();
//    }
//
//    @Override
//    public void onResponse(Object response) {
//        Log.d(" response", "onResponse: " + response.toString());
//    }
}
