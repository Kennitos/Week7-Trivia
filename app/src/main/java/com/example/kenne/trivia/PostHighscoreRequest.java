package com.example.kenne.trivia;

import android.content.Context;

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

    // Constructor
    public PostHighscoreRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener, int score_input) {
        super(method, url, listener, errorListener);
        this.highscore_input = score_input;
    }

    // Method to supply parameters to the request
    @Override
    protected Map<String, String> getParams() {

        Map<String, String> params = new HashMap<>();
        params.put("highscore", String.valueOf(highscore_input));
        return params;
    }



}
