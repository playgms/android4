package com.example.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private double firstNumber = 0;
    private double secondNumber = 0;
    private String currentOperation = "";
    private boolean isOperationClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.editTextNumberDecimal);

        // Set number buttons
        setNumberButtonClickListener(R.id.bt0, "0");
        setNumberButtonClickListener(R.id.bt1, "1");
        setNumberButtonClickListener(R.id.bt2, "2");
        setNumberButtonClickListener(R.id.bt3, "3");
        setNumberButtonClickListener(R.id.bt4, "4");
        setNumberButtonClickListener(R.id.bt5, "5");
        setNumberButtonClickListener(R.id.bt6, "6");
        setNumberButtonClickListener(R.id.bt7, "7");
        setNumberButtonClickListener(R.id.bt8, "8");
        setNumberButtonClickListener(R.id.bt9, "9");

        // Set operation buttons
        findViewById(R.id.bt_add).setOnClickListener(v -> performOperation("+"));
        findViewById(R.id.bt_sub).setOnClickListener(v -> performOperation("-"));
        findViewById(R.id.bt_mul).setOnClickListener(v -> performOperation("x"));
        findViewById(R.id.bt_div).setOnClickListener(v -> performOperation("/"));

        // Clear button
        findViewById(R.id.bt_clear).setOnClickListener(v -> clear());

        // Result button
        findViewById(R.id.bt_result).setOnClickListener(v -> calculateResult());
    }

    private void setNumberButtonClickListener(int buttonId, String number) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> {
            if (isOperationClicked) {
                display.setText("");
                isOperationClicked = false;
            }
            display.append(number);
        });
    }

    private void performOperation(String operation) {
        if (!display.getText().toString().isEmpty()) {
            firstNumber = Double.parseDouble(display.getText().toString());
            currentOperation = operation;
            isOperationClicked = true;
        }
    }

    private void calculateResult() {
        if (!display.getText().toString().isEmpty() && !currentOperation.isEmpty()) {
            secondNumber = Double.parseDouble(display.getText().toString());
            double result = 0;
            switch (currentOperation) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "x":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }
            display.setText(String.valueOf(result));
            currentOperation = "";
        }
    }

    private void clear() {
        display.setText("");
        firstNumber = 0;
        secondNumber = 0;
        currentOperation = "";
    }
}
