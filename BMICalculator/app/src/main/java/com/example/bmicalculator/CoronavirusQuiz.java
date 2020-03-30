package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CoronavirusQuiz extends AppCompatActivity {

    ArrayList<Integer> usedNumbers = new ArrayList<>();
    Button start;
    Button answer1;
    Button answer2;
    Button answer3;
    Button answer4;
    TextView question;
    TextView endGame;
    int points=0;
    int q =0;

    ArrayList<List<String>> answersList;
    List<String> questionList;
    List<String> question1Answers;
    List<String> question2Answers;
    List<String> question3Answers;
    List<String> question4Answers;
    List<String> question5Answers;
    List<String> question6Answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coronavirus_quiz);
        start= findViewById(R.id.StartButton);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });
    }

    private void startGame() {
        setContentView(R.layout.game_layout);
        setArrays();
        startQuiz();
    }

    private void startQuiz() {

        question = findViewById(R.id.questionView);
        answer1 = findViewById(R.id.button1);
        answer2 = findViewById(R.id.button2);
        answer3 = findViewById(R.id.button3);
        answer4 = findViewById(R.id.button4);

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRound(1);
            }
        });
        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRound(2);
            }
        });
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRound(3);
            }
        });
        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRound(4);
            }
        });

            startRound();

    }

    private void setArrays() {
        String[] questionsArray = getResources().getStringArray(R.array.Questions);
        questionList = Arrays.asList(questionsArray);

        String[] answers1 = getResources().getStringArray(R.array.Question1Answers);
        String[] answers2 = getResources().getStringArray(R.array.Question2Answers);
        String[] answers3 = getResources().getStringArray(R.array.Question3Answers);
        String[] answers4 = getResources().getStringArray(R.array.Question4Answers);
        String[] answers5 = getResources().getStringArray(R.array.Question5Answers);
        String[] answers6 = getResources().getStringArray(R.array.Question6Answers);

        question1Answers = Arrays.asList(answers1);
        question2Answers = Arrays.asList(answers2);
        question3Answers = Arrays.asList(answers3);
        question4Answers = Arrays.asList(answers4);
        question5Answers = Arrays.asList(answers5);
        question6Answers = Arrays.asList(answers6);

        answersList = new ArrayList<>();

        answersList.add(question1Answers);
        answersList.add(question2Answers);
        answersList.add(question3Answers);
        answersList.add(question4Answers);
        answersList.add(question5Answers);
        answersList.add(question6Answers);
    }

    void startRound(){
        q = new Random().nextInt(6);
        if(!usedNumbers.contains(q)){
            usedNumbers.add(q);
            question.setText(questionList.get(q));
            answer1.setText(answersList.get(q).get(0));
            answer2.setText(answersList.get(q).get(1));
            answer3.setText(answersList.get(q).get(2));
            answer4.setText(answersList.get(q).get(3));
        }else{
            startRound();
        }

    }
    private void checkRound(int number){
        if(usedNumbers.size()<6 && number==Integer.parseInt(answersList.get(q).get(4))){
                points+=1;
                startRound();
        }else if(usedNumbers.size()<6 && number!=Integer.parseInt(answersList.get(q).get(4))){
            startRound();
        }
        else if(usedNumbers.size()==6){
            endOfGame();
        }
    }
    @SuppressLint("SetTextI18n")
    private void endOfGame() {
            setContentView(R.layout.end_quiz);
            endGame = findViewById(R.id.textView);
            endGame.setText((getText(R.string.points).toString() + points + " / " + String.valueOf(questionList.size())));
    }
}
