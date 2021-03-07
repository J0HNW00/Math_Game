package com.example.homework1_john_wu;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Random random = new Random();
    public Integer num1 = random.nextInt(101);
    public Integer num2 = random.nextInt(101);
    public Integer arithmeticOperatorGenerator = random.nextInt(3);

    public String assignOperator(int arithmeticOperatorGenerator) {
        if(arithmeticOperatorGenerator == 0) {
            return "divide";
        } else if(arithmeticOperatorGenerator == 1) {
            return "multiply";
        } else if(arithmeticOperatorGenerator == 2) {
            return "add";
        } else {
            return "subtract";
        }
    }

    public String arithmeticOperator = assignOperator(arithmeticOperatorGenerator);

    public Double calculateOutput(String arithmeticOperator, Integer num1, Integer num2) {
        Double returnAnswer = (double)0;
        if(arithmeticOperator.equals("divide")) {
            returnAnswer = (double) num1/(double) num2;
        } else if(arithmeticOperator.equals("multiply")) {
            returnAnswer = (double) num1*(double) num2;
        } else if(arithmeticOperator.equals("add")) {
            returnAnswer = (double) num1+(double) num2;
        } else {
            returnAnswer = (double) num1-(double) num2;
        }
        return returnAnswer;
    }

    public Double answer = calculateOutput(arithmeticOperator, num1, num2);
    public Double roundedAnswer = roundTo2Decimal(answer);

    public Double roundTo2Decimal(Double number) {
        DecimalFormat twoDecimals = new DecimalFormat("#.##");
        return Double.valueOf(twoDecimals.format(number));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //record start time
        long startTime = System.currentTimeMillis();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView inputNumber1 = findViewById(R.id.txtNumber1);
        inputNumber1.setText(String.valueOf(num1));

        TextView inputNumber2 = findViewById(R.id.txtNumber2);
        inputNumber2.setText(String.valueOf(num2));

        TextView operator = findViewById(R.id.txtOperator);
        operator.setText(arithmeticOperator);

        EditText etUserInput = findViewById(R.id.etAnswerInput);

        //chronometer implementation
        Chronometer chronometer = findViewById(R.id.chronometer);
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();

        //after check answer is clicked
        final Button checkAnswerButton = findViewById(R.id.btCheckAnswer);
        checkAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SolutionActivity.class);
                intent.putExtra("SOLUTION", String.valueOf(roundedAnswer));

                if(etUserInput.getText().toString().matches("")) {
                    intent.putExtra("USER_INPUT", "No value inputted");
                } else {
                    Double userInput = Double.parseDouble(String.valueOf(etUserInput.getText()));
                  Double roundedUserInput = roundTo2Decimal(userInput);
                    intent.putExtra("USER_INPUT", String.valueOf(roundedUserInput));
                }

                intent.putExtra("Time", chronometer.getBase());

                intent.putExtra("startTime", startTime);

                startActivity(intent);

            }
        });

        //to debug the answer, delete later
        TextView outputAnswer = findViewById(R.id.txtAnswerTest);
        outputAnswer.setText(String.valueOf(roundedAnswer));
    }

}