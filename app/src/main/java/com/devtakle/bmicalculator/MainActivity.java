package com.devtakle.bmicalculator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    double bmi, weight;
    int feet, inches;
    int age;
    EditText editAge;
    EditText editWeight;
    EditText editFeet;
    EditText editInches;
    TextView textResult;
    TextView textAdvice;
    TextView textResultMsg;
    TextView textCategory;
    Button calcButn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editAge = (EditText) findViewById(R.id.editTextAge);
        editWeight = (EditText) findViewById(R.id.editTextWeight);
        editFeet = (EditText) findViewById(R.id.editTextFt);
        editInches = (EditText) findViewById(R.id.editTextInches);
        textResultMsg = (TextView)findViewById(R.id.textResult);
        textCategory = (TextView)findViewById(R.id.textCategory);
        textResult = (TextView) findViewById(R.id.textBMIAns);
        textAdvice = (TextView) findViewById(R.id.textAdvice);
        calcButn = (Button) findViewById(R.id.buttonCalc);

        calcButn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    age = Integer.parseInt(editAge.getText().toString());
                    if (age < 18) {
                        Toast.makeText(getApplicationContext(), "Age must be above 18", Toast.LENGTH_LONG).show();
                    } else {
                        try {
                            weight = Double.parseDouble(editWeight.getText().toString());
                            try {
                                feet = Integer.parseInt(editFeet.getText().toString());

                                try {
                                    inches = feet * 12 + Integer.parseInt(editInches.getText().toString());

                                    bmi = (703 * weight) / Math.pow(inches, 2);
                                    double pounds;
                                    textResultMsg.setText("Result");
                                    textResult.setText("BMI = " + Double.toString(Math.round(bmi)));


                                    if (bmi < 18.5) {
                                        textCategory.setTextColor(Color.rgb(0, 0, 255));
                                        textCategory.setText("Underweight");
                                        pounds = Math.round((25 * Math.pow(inches, 2)) / 703 - weight);

                                        textAdvice.setText("You will need to gain " + pounds + " lbs to reach a BMI of 18.5");
                                    } else if (bmi > 25 & bmi < 30) {
                                        textCategory.setTextColor(Color.rgb(255, 0, 0));
                                        textCategory.setText("Overweight");
                                        pounds = Math.round(weight - (18.5 * Math.pow(inches, 2)) / 703);
                                        textAdvice.setText("You will need to lose " + pounds + " lbs to reach a BMI of 25");
                                    } else if (bmi >= 30) {
                                        textCategory.setTextColor(Color.rgb(255, 100, 0));
                                        textCategory.setText("Obese");
                                        pounds = Math.round(weight - (18.5 * Math.pow(inches, 2)) / 703);
                                        textAdvice.setText("You will need to lose " + pounds + " lbs to reach a BMI of 25");
                                    } else {
                                        textCategory.setTextColor(Color.rgb(0, 255, 0));
                                        textCategory.setText("Normal");
                                        textAdvice.setText("Keep up the good work!");
                                    }

                                } catch (NumberFormatException ex) {
                                    textResult.setText("Inches field is required");

                                }
                            } catch (NumberFormatException ex) {
                                textResult.setText("Feet field is required");

                            }

                        } catch (NumberFormatException ex) {
                            textResult.setText("Weight is required");
                            Toast.makeText(getApplicationContext(), "Age is required", Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (NumberFormatException ex) {
                    textResult.setText("Age is required");
                }


            }
        });
    }
}