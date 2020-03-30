package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CaloriesCalculator extends AppCompatActivity {

    Button calculate;
    EditText age;
    EditText height;
    EditText weight;
    Spinner genderSpinner;
    TextView res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories_calculator);

        calculate = findViewById(R.id.calculateCalories);
        age = findViewById(R.id.ageTextEdit);
        weight = findViewById(R.id.weightTextEdit);
        height = findViewById(R.id.heightTextEdit);
        genderSpinner = findViewById(R.id.gender_spinner);
        res = findViewById(R.id.resultCalories);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this ,R.array.gender,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (age.getText().length() > 0
                        && weight.getText().length() > 0
                        && height.getText().length() > 0) {
                        calculateCaloriesWariants();
                }else{
                    Toast.makeText(CaloriesCalculator.this, R.string.data_warning, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    void calculateCaloriesWariants(){
        double result = 0;
        switch (genderSpinner.getSelectedItem().toString()){
            case "Mężczyzna":
                result = calculateFormulaMale();
                break;
            case "Kobieta":
                result = calculateFormulaMale();
                break;
            default:
                break;
        }
        res.setText(String.valueOf(result));
    }

    double calculateFormulaMale(){
        return 655.1 + (9.563 * Double.parseDouble(weight.getText().toString()))
                + (1.85 * Double.parseDouble(height.getText().toString()))
                - (4.676 * Double.parseDouble(age.getText().toString()));
    }
    double calculateFormulaFemale(){
        return 66.5 + (13.75 * Double.parseDouble(weight.getText().toString()))
                + (5.003 * Double.parseDouble(height.getText().toString()))
                - (6.775 * Double.parseDouble(age.getText().toString()));
    }
}



