package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    Button buttonBMI;
    Button calories;
    Button quiz;
    Button game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        buttonBMI = findViewById(R.id.buttonBMI);
        calories = findViewById(R.id.buttonCalories);
        quiz = findViewById(R.id.buttonQuiz);
        game = findViewById(R.id.gameButton);

        buttonBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runBmiCalculator();
            }
        });

        calories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runCaloriesCalculator();
            }
        });

        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runCoronavirusQuiz();
            }
        });

//       // game.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                runGame();
//            }
//        });

    }


    void runCoronavirusQuiz(){
        Intent quiz = new Intent(this, CoronavirusQuiz.class);
        startActivity(quiz);
    }
    void runCaloriesCalculator() {
        Intent bmi = new Intent(this, MainActivity.class);
        startActivity(bmi);
    }

    void runBmiCalculator(){
        Intent calories = new Intent(this, CaloriesCalculator.class);
        startActivity(calories);
    }

//    void runGame(){
//        Intent game = new Intent(this, GameActivity.class);
//        startActivity(game);
//    }







}
