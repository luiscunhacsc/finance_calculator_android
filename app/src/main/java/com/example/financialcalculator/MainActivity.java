package com.example.financialcalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editTextPresentValue, editTextInterestRate, editTextPeriods, editTextPayment;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPresentValue = findViewById(R.id.editTextPresentValue);
        editTextInterestRate = findViewById(R.id.editTextInterestRate);
        editTextPeriods = findViewById(R.id.editTextPeriods);
        editTextPayment = findViewById(R.id.editTextPayment);
        textViewResult = findViewById(R.id.textViewResult);

        Button buttonCalculateFV = findViewById(R.id.buttonCalculateFV);
        buttonCalculateFV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateFutureValue();
            }
        });
    }

    private void calculateFutureValue() {
        try {
            double pv = Double.parseDouble(editTextPresentValue.getText().toString());
            double iY = Double.parseDouble(editTextInterestRate.getText().toString());
            int n = Integer.parseInt(editTextPeriods.getText().toString());
            double pmt = Double.parseDouble(editTextPayment.getText().toString());

            double fv = FinancialCalculations.calculateFutureValue(pv, pmt, iY, n);
            textViewResult.setText(String.format("Future Value: $%.2f", fv));
        } catch (NumberFormatException e) {
            textViewResult.setText("Please check your input values.");
        }
    }
}
