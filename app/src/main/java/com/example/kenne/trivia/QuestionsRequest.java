package com.example.kenne.trivia;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class QuestionsRequest implements Response.Listener<JSONObject>, Response.ErrorListener{

    Context context;
    Callback activity;

    public interface Callback {
        void gotQuestions(ArrayList<Question> questions);
        void gotQuestionsError(String message);
    }

    public QuestionsRequest(Context input_context) {
        context = input_context;
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }


    @Override
    public void onResponse(JSONObject response) {
        ArrayList<Question> questions = new ArrayList();

        try {
            JSONArray questionsArray = response.getJSONArray("results");

//            Log.d("testjson"," "+questionsArray);
            for (int i = 0; i < questionsArray.length(); i++) {
                JSONObject chooseObject = questionsArray.getJSONObject(i);
                String question = chooseObject.getString("question");
                String type = chooseObject.getString("type");
                String correct_answer = chooseObject.getString("correct_answer");

                JSONArray incorrect_answer = chooseObject.getJSONArray("incorrect_answers");

                Log.d("testjson41", ' '+type);
                Log.d("testjson42", ' '+question);
                Log.d("testjson43", ' '+correct_answer);

                Log.d("testjson45", String.valueOf(incorrect_answer));

                Question questionInput = new Question(question, correct_answer, type, incorrect_answer);
                questions.add(questionInput);

            }
            activity.gotQuestions(questions);
        } catch (JSONException e) {
            e.printStackTrace();

        }


    }

    public void getQuestions(Callback activity){
        this.activity = activity;
        String url = "https://opentdb.com/api.php?amount=10&category=15&type=multiple";
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,null,this,this);
        queue.add(jsonObjectRequest);
    }
}
