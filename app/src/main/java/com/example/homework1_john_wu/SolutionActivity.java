package com.example.homework1_john_wu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class SolutionActivity extends AppCompatActivity {
    public static final String SOLUTION = "";
    public static final String USER_INPUT = "";
    private TextView tAnswer, tUserInput, tOutcome;
    private Button bPlayAgain, bCheckSolution;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution);

        Intent intent = getIntent();

        //get elapsed time
        long totalElapsedTime = (System.currentTimeMillis() - intent.getLongExtra("startTime", 0))/1000;
        TextView tTimeString = findViewById(R.id.txtTimeString);
        String timeString = "Total Time Spent: " + String.valueOf(totalElapsedTime) + " seconds";
        tTimeString.setText(timeString);

        String userInput = intent.getStringExtra("USER_INPUT");

        tUserInput = findViewById(R.id.txtUserInput);
        tUserInput.setText("Your Answer: " + userInput);

        String correctAnswer = intent.getStringExtra("SOLUTION");

        tAnswer = findViewById(R.id.txtAnswer);
        if(correctAnswer.equals(userInput)) {
            tAnswer.setText("Correct Answer: " + correctAnswer);

        } else {
            tAnswer.setText("Click back to retry");
        }

        //button to replay game
        bPlayAgain = findViewById(R.id.btPlayAgain);
        bPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SolutionActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        bCheckSolution = findViewById(R.id.btCheckAnswer);
//        bCheckSolution.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tAnswer.setText("Correct Answer: " + correctAnswer);
//            }
//        });

        tOutcome = findViewById(R.id.txtOutcome);
        String outputText = determineOutcome(correctAnswer, userInput);
        tOutcome.setText(outputText);

    }

    public String determineOutcome(String correctAnswer, String userInput) {
        String returnValue = "";
        if(correctAnswer.equals(userInput)) {
            returnValue = "Correct";
//            bCheckSolution.setVisibility(View.INVISIBLE);
        } else {
            returnValue = "Incorrect";
        }
        return returnValue;
    }

}