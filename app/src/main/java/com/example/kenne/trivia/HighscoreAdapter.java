package com.example.kenne.trivia;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class HighscoreAdapter extends ArrayAdapter {

    private ArrayList<HighscoreItem> highscore_items;

    public HighscoreAdapter(@NonNull Context context, int resource, @NonNull ArrayList<HighscoreItem> objects) {
        super(context, resource, objects);
        highscore_items = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = highscore_items.get(position).getName();
        int score = highscore_items.get(position).getScore();
        int id = highscore_items.get(position).getId();


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.highscore_items, parent, false);
        }
        TextView name_view = convertView.findViewById(R.id.nameView);
        name_view.setText(name);

        TextView score_view = convertView.findViewById(R.id.scoreView);
        score_view.setText(String.valueOf(score));

        TextView id_view = convertView.findViewById(R.id.idView);
        id_view.setText(String.valueOf(id));


        return convertView;
    }
}
