/*
 *  ---------------------------------------------------------------------------
 *  Copyright (C) 2016, Davide Baj - All Rights Reserved
 *
 *  Project name: Nepy
 *  Filename: QuizFragment.java
 *  Author: Davide Baj
 *  -------------------------------------------------------------------------
 */

package com.davidebaj.nepy.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.davidebaj.nepy.MainActivity;
import com.davidebaj.nepy.R;
import com.davidebaj.nepy.Settings;
import com.davidebaj.nepy.dao.Challenge;
import com.davidebaj.nepy.dao.Quiz;

import java.util.List;

/**
 * Created by davide on 28/02/16.
 */
public class QuizFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "QuizFragment";
    private String title;
    Button startButton;

    public static QuizFragment newInstance(String title) {
        QuizFragment f = new QuizFragment();
        f.title = title;
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(title);

        View view = inflater.inflate(R.layout.quiz_home, container, false);
        view.setClickable(true);

        Settings settings = Settings.buildSettings(getContext());

        TextView textView = (TextView) view.findViewById(R.id.score_summary);
        String scoreSummary = "\n" + MainActivity.resources.getProperty("quiz.HIGHEST_SCORES") + " " + settings.getHighestScore() + "\n\n"
                + MainActivity.resources.getProperty("quiz.LAST_SCORES") + " " + settings.getLastScore();
        textView.setText(scoreSummary);

        startButton = (Button) view.findViewById(R.id.StartQuiz);
        startButton.setText(MainActivity.resources.getProperty("label.START_QUIZ_BUTTON"));
        startButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    }

    @Override
    public void onClick(View v) {

        if (startButton.getId() != v.getId()) {
            return;
        }

        Quiz quiz = Quiz.getInstance();
        List<Challenge> challenges = quiz.getQuizData();

        //quiz.logQuizData(); // to debug the quiz

        Fragment fragment = ChallengeFragment.newInstance(challenges);

        getFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.content_main, fragment)
                .commit();
    }

}
