package com.example.kenne.trivia;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PostHighscoreRequest extends StringRequest {

    int highscore_input;
    ArrayList array_input;

    // Constructor
    public PostHighscoreRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener, ArrayList array_input) {
        super(method, url, listener, errorListener);
        this.array_input = array_input;
    }

    // Method to supply parameters to the request
    @Override
    protected Map<String, String> getParams() {

        Log.d("highscore_test22","deel1"+array_input);
        Map<String, String> params = new HashMap<>();
        String name = String.valueOf(array_input.get(1));
        int highscore = (int) array_input.get(0);
        Log.d("highscore_test22","deel2");
        params.put("name", name);
        params.put("highscore", String.valueOf(highscore));
        Log.d("highscore_test22","deel3");
        return params;
    }



}
