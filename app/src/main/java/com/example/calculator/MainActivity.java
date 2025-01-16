package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextInput;
    TextView textViewResult;
    StringBuilder inputBuilder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link views with XML layout
        editTextInput = findViewById(R.id.editTextInput);
        textViewResult = findViewById(R.id.textViewResult);

        // Number buttons
        int[] numberButtons = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9
        };

        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                inputBuilder.append(button.getText().toString());
                editTextInput.setText(inputBuilder.toString());
            }
        };

        for (int id : numberButtons) {
            findViewById(id).setOnClickListener(numberClickListener);
        }

        // Operation buttons
        findViewById(R.id.buttonAdd).setOnClickListener(v -> appendOperator("+"));
        findViewById(R.id.buttonSubtract).setOnClickListener(v -> appendOperator("-"));
        findViewById(R.id.buttonMultiply).setOnClickListener(v -> appendOperator("*"));
        findViewById(R.id.buttonDivide).setOnClickListener(v -> appendOperator("/"));

        // Clear button
        findViewById(R.id.buttonClear).setOnClickListener(v -> clearInput());

        // Equal button
        findViewById(R.id.buttonEqual).setOnClickListener(v -> calculateResult());
    }

    // Append operator to input
    private void appendOperator(String operator) {
        if (inputBuilder.length() > 0 && !isOperator(inputBuilder.charAt(inputBuilder.length() - 1))) {
            inputBuilder.append(" ").append(operator).append(" ");
            editTextInput.setText(inputBuilder.toString());
        }
    }

    // Clear input
    private void clearInput() {
        inputBuilder.setLength(0);
        editTextInput.setText("");
        textViewResult.setText("Result: ");
    }

    // Check if character is an operator
    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    // Calculate and display result
    private void calculateResult() {
        String inputText = inputBuilder.toString();

        if (inputText.isEmpty()) {
            Toast.makeText(this, "Please enter a calculation", Toast.LENGTH_SHORT).show();
            return;
        }

        String[] tokens = inputText.split(" ");

        if (tokens.length != 3) {
            Toast.makeText(this, "Invalid input format", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double num1 = Double.parseDouble(tokens[0]);
            double num2 = Double.parseDouble(tokens[2]);
            String operator = tokens[1];
            double result = 0;

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    result = num1 / num2;
                    break;
            }

            textViewResult.setText("Result: " + result);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid number format", Toast.LENGTH_SHORT).show();
        }
    }
}
