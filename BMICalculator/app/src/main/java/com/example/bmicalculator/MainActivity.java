package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Math.pow;

public class MainActivity extends AppCompatActivity {

    Button calculate;
    EditText weight;
    EditText height;
    double calculatedBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculate = findViewById(R.id.calculate);
        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkBMIrange(calculate());
            }
        });
    }

    private double calculate(){
        double weightFromForm;
        double heightFromForm;

        weightFromForm = Double.parseDouble(weight.getText().toString());
        heightFromForm = Double.parseDouble(height.getText().toString());

        return weightFromForm/(pow(heightFromForm/100,2));
    }

    private void checkBMIrange(double bmi){

        if(bmi < 16){
            Toast.makeText(getApplicationContext(),getText(R.string.under_16),Toast.LENGTH_SHORT).show();
        }
        else if( bmi > 16.00 && bmi < 16.99){
            Toast.makeText(getApplicationContext(),getText(R.string.bmi_16_to_17),Toast.LENGTH_SHORT).show();
        }
        else if( bmi > 17 && bmi < 18.49){
            Toast.makeText(getApplicationContext(),getText(R.string.bmi_17_to_18_and_half),Toast.LENGTH_SHORT).show();
        }
        else if( bmi > 18.50 && bmi < 25){
            Toast.makeText(getApplicationContext(),getText(R.string.bmi_18_and_half_to_25),Toast.LENGTH_SHORT).show();
        }
        else if( bmi > 25 && bmi < 30){
            Toast.makeText(getApplicationContext(),getText(R.string.bmi_25_to_30),Toast.LENGTH_SHORT).show();
        }
        else if( bmi > 30 && bmi < 35){
            Toast.makeText(getApplicationContext(),getText(R.string.bmi_30_to_35),Toast.LENGTH_SHORT).show();
        }
        else if(bmi > 40){
            Toast.makeText(getApplicationContext(),getText(R.string.more_than_40),Toast.LENGTH_SHORT).show();
        }
    }

}
