package com.example.android.scorekeeper;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.System.exit;

public class MainActivity extends AppCompatActivity {

    private int teamOneScore = 0;
    private int teamTwoScore = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void incrementScore(View view) {
        Context context = getApplicationContext();
        Resources r = context.getResources();

        //retrieving team number from xml Id
        String teamStr = r.getResourceEntryName(view.getId());

        //retrieving score from button text
        //Heiko Rupp - retrieving text of a button
        //http://stackoverflow.com/questions/5620772/get-text-from-pressed-button
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        int score = Integer.parseInt(buttonText);
        //regex to eliminate everything except the team number
        int team = Integer.parseInt(teamStr.replaceAll("[^0-9]",""));

        switch (team) {
            case 1:
                teamOneScore += score;
                displayScore(team);
                break;
            case 2:
                teamTwoScore += score;
                displayScore(team);
                break;
            default:
                exit(1);
                break;

        }

    }

    private void displayScore(int team) {
        switch (team) {
            case 1:
                //adapted from JustJava code
                TextView scoreTextView1 = (TextView) findViewById(R.id.teamOneScore);
                scoreTextView1.setText("" + teamOneScore);
                break;
            case 2:
                //adapted from JustJava code
                TextView scoreTextView2 = (TextView) findViewById(R.id.teamTwoScore);
                scoreTextView2.setText("" + teamTwoScore);
                break;
            default:
                exit(1);
                break;

        }
    }

    public void reset(View view){
        EditText teamName1 = (EditText) findViewById(R.id.teamOneName);
        EditText teamName2 = (EditText) findViewById(R.id.teamTwoName);

        //reset scores to 0
        teamOneScore = 0;
        teamTwoScore = 0;

        //reset displayed scores
        displayScore(1);
        displayScore(2);

        //set team names to default
        teamName1.setText(R.string.teamOne);
        teamName2.setText(R.string.teamTwo);
    }

    public void changeDirections(View view) {
        int dummyScore;
        String dummyTeamName;

        EditText teamName1 = (EditText) findViewById(R.id.teamOneName);
        EditText teamName2 = (EditText) findViewById(R.id.teamTwoName);

        //switch team scores
        dummyScore = teamOneScore;
        teamOneScore = teamTwoScore;
        teamTwoScore = dummyScore;

        //display new team scores
        displayScore(1);
        displayScore(2);

        //switch team names
        dummyTeamName = teamName1.getText().toString();
        teamName1.setText(teamName2.getText().toString());
        teamName2.setText(dummyTeamName);


    }

}
