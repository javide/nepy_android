/*
 *  ---------------------------------------------------------------------------
 *  Copyright (C) 2016, Davide Baj - All Rights Reserved
 *
 *  Project name: Nepy
 *  Filename: ChallengeFragment.java
 *  Author: Davide Baj
 *  -------------------------------------------------------------------------
 */

package com.davidebaj.nepy.fragments;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.davidebaj.nepy.MainActivity;
import com.davidebaj.nepy.R;
import com.davidebaj.nepy.Settings;
import com.davidebaj.nepy.dao.Challenge;
import com.davidebaj.nepy.dao.Plant;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by davide on 08/03/16.
 */
public class ChallengeFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "ChallengeFragment";
    private static final String showOptions = MainActivity.resources.getProperty("quiz.SHOW_OPTIONS");
    private static final String hideOptions = MainActivity.resources.getProperty("quiz.HIDE_OPTIONS");
    private static final String nextChallenge = MainActivity.resources.getProperty("quiz.NEXT_CHALLENGE");
    private static final String gameOver = MainActivity.resources.getProperty("quiz.GAME_OVER");
    private Settings settings;
    public static final int GREEN = Color.rgb(96, 178, 67);
    public static final int RED = Color.rgb(226, 30, 53);
    private String title;
    private List<Challenge> challenges;
    private int counter = -1;
    private TextView lastScoresIndicator;
    Button buttonGame;
    Button buttonOption1;
    Button buttonOption2;
    Button buttonOption3;
    MediaPlayer mpGood;
    MediaPlayer mpBad;
    int gameScores;
    int attempts;

    /**
     * Creates a new instance of this fragment
     * @param challengesList - the full list of challenges for this quiz
     * @return the fragment
     */
    public static ChallengeFragment newInstance(List<Challenge> challengesList) {
        ChallengeFragment f = new ChallengeFragment();
        f.title = MainActivity.resources.getProperty("quiz.QUESTION");
        f.challenges = challengesList;
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(title);

        View view = inflater.inflate(R.layout.challenge, container, false);
        view.setClickable(true);

        settings = Settings.buildSettings(getContext());

        lastScoresIndicator = (TextView) view.findViewById(R.id.lastscores_indicator);

        gameScores = 0;
        attempts = 0;

        buttonOption1 = (Button) view.findViewById(R.id.buttonOption1);
        buttonOption2 = (Button) view.findViewById(R.id.buttonOption2);
        buttonOption3 = (Button) view.findViewById(R.id.buttonOption3);
        buttonGame = (Button) view.findViewById(R.id.buttonGame);

        buttonOption1.setOnClickListener(this);
        buttonOption2.setOnClickListener(this);
        buttonOption3.setOnClickListener(this);
        buttonGame.setOnClickListener(this);

        mpGood = MediaPlayer.create(getContext(), R.raw.correct);
        mpBad = MediaPlayer.create(getContext(), R.raw.wrong);

        updateScores();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        showNewChallenge();
    }


    /**
     * Displays the new species to guess
     */
    private void showNewChallenge() {

        counter++;
        Challenge challenge = challenges.get(counter);
        ImageView imageView = (ImageView) getActivity().findViewById(R.id.imageGame);
        Plant plant = challenge.getPlant();
        String plantFileName = "img/plants/" + plant.getPlantFileName(challenge.getPhotoNum());

        AssetManager assetManager = getContext().getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open(plantFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (inputStream == null) {
            return;
        }

        imageView.setImageDrawable(Drawable.createFromStream(inputStream, ""));

        List<String> answers = challenge.getAnswers();
        buttonOption1.setText(answers.get(0));
        buttonOption2.setText(answers.get(1));
        buttonOption3.setText(answers.get(2));

        buttonOption1.setBackgroundColor(Color.BLACK);
        buttonOption2.setBackgroundColor(Color.BLACK);
        buttonOption3.setBackgroundColor(Color.BLACK);
        buttonOption1.setTextColor(Color.LTGRAY);
        buttonOption2.setTextColor(Color.LTGRAY);
        buttonOption3.setTextColor(Color.LTGRAY);
        buttonOption1.setEnabled(true);
        buttonOption2.setEnabled(true);
        buttonOption3.setEnabled(true);

        hideOptions();
    }

    /**
     * Hides the option buttons
     */
    private void hideOptions() {
        buttonOption1.setVisibility(View.INVISIBLE);
        buttonOption2.setVisibility(View.INVISIBLE);
        buttonOption3.setVisibility(View.INVISIBLE);
        buttonGame.setText(showOptions);
    }

    /**
     * Shows the option buttons
     */
    private void showOptions() {
        buttonOption1.setVisibility(View.VISIBLE);
        buttonOption2.setVisibility(View.VISIBLE);
        buttonOption3.setVisibility(View.VISIBLE);
        buttonGame.setText(hideOptions);
    }

    @Override
    public void onClick(View v) {

        if (buttonGame.getId() == v.getId()) {

            if (buttonGame.getText().equals(showOptions)) {

                // if label "Show options" then display the three button options
                showOptions();

            } else if (buttonGame.getText().equals(hideOptions)) {

                // if label "Hide options" then hide the three button options
                hideOptions();

            } else if (buttonGame.getText().equals(nextChallenge)) {

                showNewChallenge();

            } else if (buttonGame.getText().equals(gameOver)) {
                // if label "Game over" then do nothing
            }

        } else {

            // user has clicked an option button, effectively answering the challenge

            // disable all option buttons
            buttonOption1.setEnabled(false);
            buttonOption2.setEnabled(false);
            buttonOption3.setEnabled(false);

            buttonOption1.setTextColor(Color.BLACK);
            buttonOption2.setTextColor(Color.BLACK);
            buttonOption3.setTextColor(Color.BLACK);

            String correctAnswer = challenges.get(counter).getPlant().getSpecies();

            if (buttonOption1.getId() == v.getId()) {
                //Log.d(TAG, "Clicked button option 1");

                if (buttonOption1.getText().equals(correctAnswer)) {
                    // correct answer is in button 1
                    buttonOption1.setBackgroundColor(GREEN);
                    buttonOption2.setVisibility(View.INVISIBLE);
                    buttonOption3.setVisibility(View.INVISIBLE);
                    goodFeedback();
                } else {
                    buttonOption1.setBackgroundColor(RED);
                    if (buttonOption2.getText().equals(correctAnswer)) {
                        // correct answer is in button 2
                        buttonOption2.setBackgroundColor(GREEN);
                        buttonOption3.setVisibility(View.INVISIBLE);
                    } else {
                        // correct answer is in button 3
                        buttonOption3.setBackgroundColor(GREEN);
                        buttonOption2.setVisibility(View.INVISIBLE);
                    }

                    badFeedback();
                }

            } else if (buttonOption2.getId() == v.getId()) {
                //Log.d(TAG, "Clicked button option 2");

                if (buttonOption2.getText().equals(correctAnswer)) {
                    // correct answer is in button 2
                    buttonOption2.setBackgroundColor(GREEN);
                    buttonOption1.setVisibility(View.INVISIBLE);
                    buttonOption3.setVisibility(View.INVISIBLE);
                    goodFeedback();
                } else {
                    buttonOption2.setBackgroundColor(RED);
                    if (buttonOption1.getText().equals(correctAnswer)) {
                        // correct answer is in button 1
                        buttonOption1.setBackgroundColor(GREEN);
                        buttonOption3.setVisibility(View.INVISIBLE);
                    } else {
                        // correct answer is in button 3
                        buttonOption3.setBackgroundColor(GREEN);
                        buttonOption1.setVisibility(View.INVISIBLE);
                    }

                    badFeedback();
                }
            } else if (buttonOption3.getId() == v.getId()) {
                //Log.d(TAG, "Clicked button option 3");

                if (buttonOption3.getText().equals(correctAnswer)) {
                    // correct answer is in button 3
                    buttonOption3.setBackgroundColor(GREEN);
                    buttonOption1.setVisibility(View.INVISIBLE);
                    buttonOption2.setVisibility(View.INVISIBLE);
                    goodFeedback();
                } else {
                    buttonOption3.setBackgroundColor(RED);
                    if (buttonOption1.getText().equals(correctAnswer)) {
                        // correct answer is in button 1
                        buttonOption1.setBackgroundColor(GREEN);
                        buttonOption2.setVisibility(View.INVISIBLE);
                    } else {
                        // correct answer is in button 2
                        buttonOption2.setBackgroundColor(GREEN);
                        buttonOption1.setVisibility(View.INVISIBLE);
                    }

                    badFeedback();
                }
            }

            updateScores();

            if (!isGameOver()) {
                buttonGame.setText(nextChallenge);
            } else {
                buttonGame.setText(gameOver);
            }
        }
    }

    /**
     * Play successful sound and update the scores accordingly
     */
    private void goodFeedback() {

        if (settings.isSoundOn()) {
            // play good sound
            mpGood = MediaPlayer.create(getContext(), R.raw.correct);
            mpGood.start();
        }

        attempts = attempts + 1;
        gameScores = gameScores + 1;
    }

    /**
     * Play unsuccessful sound and update the scores accordingly
     */
    private void badFeedback() {

        if (settings.isSoundOn()) {
            // play bad sound
            mpBad = MediaPlayer.create(getContext(), R.raw.wrong);
            mpBad.start();
        }

        attempts = attempts + 1;
    }

    /**
     * Updates the score display and record scores
     */
    private void updateScores() {

        String gameScoreIndicator = MainActivity.resources.getProperty("quiz.ACTUAL_SCORES") +
                " " + gameScores + MainActivity.resources.getProperty("quiz.SCORE_SEPARATOR") + attempts;

        lastScoresIndicator.setText(gameScoreIndicator);

        settings.setLastScore(gameScores);

        if (gameScores > settings.getHighestScore()) {
            settings.setHighestScore(gameScores);
        }
    }

    /**
     * Has the user completed all the challenges?
     * @return - boolean
     */
    private boolean isGameOver() {
        return (counter >= challenges.size() - 1);
    }
}
