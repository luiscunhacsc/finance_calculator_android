package com.example.financialcalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editTextPresentValue, editTextInterestRate, editTextPeriods, editTextPayment, editTextFutureValue;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize all EditTexts
        editTextPresentValue = findViewById(R.id.editTextPresentValue);
        editTextInterestRate = findViewById(R.id.editTextInterestRate);
        editTextPeriods = findViewById(R.id.editTextPeriods);
        editTextPayment = findViewById(R.id.editTextPayment);
        editTextFutureValue = findViewById(R.id.editTextFutureValue);
        textViewResult = findViewById(R.id.textViewResult);

        // Set up the button to calculate Future Value
        Button buttonCalculateFV = findViewById(R.id.buttonCalculateFV);
        buttonCalculateFV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateFutureValue();
            }
        });

        // Set up the button to calculate PMT
        Button buttonCalculatePMT = findViewById(R.id.buttonCalculatePMT);
        buttonCalculatePMT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculatePMT();
            }
        });

        // Set up the button to calculate the Number of Periods (N)
        Button buttonCalculateN = findViewById(R.id.buttonCalculateN);
        buttonCalculateN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateN();
            }
        });

        // Set up the button to calculate the Interest Rate per Year (I/Y)
        Button buttonCalculateIY = findViewById(R.id.buttonCalculateIY);
        buttonCalculateIY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateIY();
            }
        });

        // Set up the button to calculate Present Value (PV)
        Button buttonCalculatePV = findViewById(R.id.buttonCalculatePV);
        buttonCalculatePV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculatePV();
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

    private void calculatePMT() {
        try {
            double pv = Double.parseDouble(editTextPresentValue.getText().toString());
            double fv = Double.parseDouble(editTextFutureValue.getText().toString());
            double iY = Double.parseDouble(editTextInterestRate.getText().toString());
            int n = Integer.parseInt(editTextPeriods.getText().toString());

            double pmt = FinancialCalculations.calculatePeriodicPayment(pv, fv, iY, n);
            textViewResult.setText(String.format("PMT: $%.2f", pmt));
        } catch (NumberFormatException e) {
            textViewResult.setText("Please check your input values.");
        }
    }

    private void calculateN() {
        try {
            double pv = Double.parseDouble(editTextPresentValue.getText().toString());
            double fv = Double.parseDouble(editTextFutureValue.getText().toString());
            double pmt = Double.parseDouble(editTextPayment.getText().toString());
            double iY = Double.parseDouble(editTextInterestRate.getText().toString());

            int n = FinancialCalculations.calculateNumberOfPeriods(pv, pmt, fv, iY);
            textViewResult.setText(String.format("Number of Periods (N): %d", n));
        } catch (NumberFormatException e) {
            textViewResult.setText("Please check your input values.");
        }
    }

    private void calculatePV() {
        try {
            double pmt = Double.parseDouble(editTextPayment.getText().toString());
            double fv = Double.parseDouble(editTextFutureValue.getText().toString());
            double iY = Double.parseDouble(editTextInterestRate.getText().toString());
            int n = Integer.parseInt(editTextPeriods.getText().toString());

            double pv = FinancialCalculations.calculatePresentValue(pmt, fv, iY, n);
            textViewResult.setText(String.format("Present Value (PV): $%.2f", pv));
        } catch (NumberFormatException e) {
            textViewResult.setText("Please check your input values.");
        }
    }

    private void calculateIY() {
        // Since the actual I/Y calculation requires a numerical method, we display a placeholder message.
        textViewResult.setText("Calculation for I/Y is not implemented.");
    }


}
