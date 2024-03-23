package com.example.financialcalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editTextPresentValue, editTextInterestRate, editTextPeriods, editTextPayment, editTextFutureValue;
    private TextView textViewResult;
    private Button buttonNewCalculation, buttonExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize all EditTexts and TextViews
        editTextPresentValue = findViewById(R.id.editTextPresentValue);
        editTextInterestRate = findViewById(R.id.editTextInterestRate);
        editTextPeriods = findViewById(R.id.editTextPeriods);
        editTextPayment = findViewById(R.id.editTextPayment);
        editTextFutureValue = findViewById(R.id.editTextFutureValue);
        textViewResult = findViewById(R.id.textViewResult);

        // Initialize and hide the "New Calculation" and "Exit" buttons
        buttonNewCalculation = findViewById(R.id.buttonNewCalculation);
        buttonExit = findViewById(R.id.buttonExit);
        buttonNewCalculation.setVisibility(View.GONE);
        buttonExit.setVisibility(View.GONE);

        // Set up the button to calculate Future Value
        Button buttonCalculateFV = findViewById(R.id.buttonCalculateFV);
        buttonCalculateFV.setOnClickListener(view -> calculateFutureValue());

        // Set up the button to calculate PMT
        Button buttonCalculatePMT = findViewById(R.id.buttonCalculatePMT);
        buttonCalculatePMT.setOnClickListener(view -> calculatePMT());

        // Set up the button to calculate the Number of Periods (N)
        Button buttonCalculateN = findViewById(R.id.buttonCalculateN);
        buttonCalculateN.setOnClickListener(view -> calculateN());

        // Set up the button to calculate Present Value (PV)
        Button buttonCalculatePV = findViewById(R.id.buttonCalculatePV);
        buttonCalculatePV.setOnClickListener(view -> calculatePV());

        // Set up the button to calculate Present Value (IY)
        // Button buttonCalculateIY = findViewById(R.id.buttonCalculateIY);
        // buttonCalculateIY.setOnClickListener(view -> calculateInterestPerYear());

        // Listener for "New Calculation" button
        buttonNewCalculation.setOnClickListener(view -> resetCalculationFields());

        // Listener for "Exit" button
        buttonExit.setOnClickListener(view -> finish());
    }

    private void resetCalculationFields() {
        editTextPresentValue.getText().clear();
        editTextInterestRate.getText().clear();
        editTextPeriods.getText().clear();
        editTextPayment.getText().clear();
        editTextFutureValue.getText().clear();
        textViewResult.setText("");
        buttonNewCalculation.setVisibility(View.GONE);
        buttonExit.setVisibility(View.GONE);
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    // Your existing calculation methods like calculateFutureValue, calculatePMT, calculateN, calculatePV will go here.
    // You need to replace the direct calls to textViewResult.setText() with displayResultAndManageButtons() as shown below.

    private void displayResultAndManageButtons(String result) {
        textViewResult.setText(result);
        hideKeyboard();
        buttonNewCalculation.setVisibility(View.VISIBLE);
        buttonExit.setVisibility(View.VISIBLE);
    }

    private void calculateFutureValue() {
        try {
            double pv = Double.parseDouble(editTextPresentValue.getText().toString());
            double pmt = Double.parseDouble(editTextPayment.getText().toString());
            double iY = Double.parseDouble(editTextInterestRate.getText().toString());
            int n = Integer.parseInt(editTextPeriods.getText().toString());

            double fv = FinancialCalculations.calculateFutureValue(pv, pmt, iY, n);
            displayResultAndManageButtons(String.format("Future Value: $%.2f", fv));
        } catch (NumberFormatException e) {
            displayResultAndManageButtons("Please check your input values.");
        }
    }

    private void calculatePMT() {
        try {
            double pv = Double.parseDouble(editTextPresentValue.getText().toString());
            double fv = Double.parseDouble(editTextFutureValue.getText().toString());
            double iY = Double.parseDouble(editTextInterestRate.getText().toString());
            int n = Integer.parseInt(editTextPeriods.getText().toString());

            double pmt = FinancialCalculations.calculatePeriodicPayment(pv, fv, iY, n);
            displayResultAndManageButtons(String.format("PMT: $%.2f", pmt));
        } catch (NumberFormatException e) {
            displayResultAndManageButtons("Please check your input values.");
        }
    }
    private void calculateN() {
        try {
            double pv = Double.parseDouble(editTextPresentValue.getText().toString());
            double pmt = Double.parseDouble(editTextPayment.getText().toString());
            double fv = Double.parseDouble(editTextFutureValue.getText().toString());
            double iY = Double.parseDouble(editTextInterestRate.getText().toString());

            double n = FinancialCalculations.calculateNumberOfPeriods(pv, pmt, fv, iY);
            displayResultAndManageButtons(String.format("Number of Periods (N): %.1f", n));
        } catch (NumberFormatException e) {
            displayResultAndManageButtons("Please check your input values.");
        }
    }

    private void calculatePV() {
        try {
            double pmt = Double.parseDouble(editTextPayment.getText().toString());
            double fv = Double.parseDouble(editTextFutureValue.getText().toString());
            double iY = Double.parseDouble(editTextInterestRate.getText().toString());
            int n = Integer.parseInt(editTextPeriods.getText().toString());

            double pv = FinancialCalculations.calculatePresentValue(pmt, fv, iY, n);
            displayResultAndManageButtons(String.format("Present Value (PV): $%.2f", pv));
        } catch (NumberFormatException e) {
            displayResultAndManageButtons("Please check your input values.");
        }
    }

    private void calculateInterestPerYear() {
        try {
            double pv = Double.parseDouble(editTextPresentValue.getText().toString());
            double pmt = Double.parseDouble(editTextPayment.getText().toString());
            double fv = Double.parseDouble(editTextFutureValue.getText().toString());
            int n = Integer.parseInt(editTextPeriods.getText().toString());

            double iY = FinancialCalculations.calculateInterestPerYear(pv, pmt, fv, n);
            displayResultAndManageButtons(String.format("Interest Rate per Year (I/Y): %.2f", iY));

        } catch (NumberFormatException e) {
            displayResultAndManageButtons("Please check your input values.");
        }
    }


}
