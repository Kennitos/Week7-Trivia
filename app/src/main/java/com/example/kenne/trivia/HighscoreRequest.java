package com.example.kenne.trivia;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HighscoreRequest implements Response.Listener<JSONArray>, Response.ErrorListener{

    Context context;
    Callback activity;

    public interface Callback {
        void gotHighscores(ArrayList<HighscoreItem> HighscoreItems);
        void gotHighscoresError(String message);
    }

    public HighscoreRequest(Context input_context) {
        context = input_context;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d("highscores_test","Response error"+error);

    }

    @Override
    public void onResponse(JSONArray response) {
        Log.d("highscores_test","Runt onResponse!");
        ArrayList<HighscoreItem> HighscoreItems = new ArrayList();
        try {
//            JSONObject scoreObject = response.getJSONObject("highscore")
//            JSONArray scoresArray = response.getJSONArray("highscore");
            for (int i = 0; i < response.length(); i++) {
                JSONObject scoreObject = response.getJSONObject(i);
                String name = scoreObject.getString("name");
                int highscore = scoreObject.getInt("highscore");
                int id = scoreObject.getInt("id");
                Log.d("highscores_test",' '+highscore+name+id);

                HighscoreItem highscoreItem = new HighscoreItem(name,highscore,id);
                HighscoreItems.add(highscoreItem);
            }
            activity.gotHighscores(HighscoreItems);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void getHighscore(Callback activity){ //HighscoreRequest.Callback activity
        Log.d("highscores_test","Runt getHighscore!");
        this.activity = activity;
        String url = "https://ide50-kennitos.cs50.io:8080/list";
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,this,this);
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,null,this,this);
        queue.add(jsonArrayRequest);
    }
}
