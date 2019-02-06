package by.yuzefovich.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainScreen extends AppCompatActivity {

    private TextView topNum;
    private TextView bottomNum;
    private TextView operator;

    private float firstValue = 0;
    private float secondValue = 0;
    private String operatorValue = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        topNum = findViewById(R.id.topNum);
        bottomNum = findViewById(R.id.bottomNum);
        operator = findViewById(R.id.operator);

        topNum.setVisibility(View.INVISIBLE);
        topNum.setText("");
        operator.setVisibility(View.INVISIBLE);
        operator.setText("");
    }

    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.one:
                numericButton(1);
                break;
            case R.id.two:
                numericButton(2);
                break;
            case R.id.three:
                numericButton(3);
                break;
            case R.id.four:
                numericButton(4);
                break;
            case R.id.five:
                numericButton(5);
                break;
            case R.id.six:
                numericButton(6);
                break;
            case R.id.seven:
                numericButton(7);
                break;
            case R.id.eight:
                numericButton(8);
                break;
            case R.id.nine:
                numericButton(9);
                break;
            case R.id.zero:
                numericButton(0);
                break;


            case R.id.AC:
                clearAll();
                break;
            case R.id.CL:
                clear();
                break;
            case R.id.percent:
                functionalButton("%");
                break;
            case R.id.divide:
                functionalButton("/");
                break;
            case R.id.multiply:
                functionalButton("*");
                break;
            case R.id.minus:
                functionalButton("–");
                break;
            case R.id.plus:
                functionalButton("+");
                break;
            case R.id.plus_minus:
                plusMinus();
                break;
            case R.id.dot:
                dot();
                break;
            case R.id.equals:
                equal();
                break;
        }
    }

    public void dot() {
        String strNum = bottomNum.getText() + ".";
        bottomNum.setText(strNum);
        if (operator.getVisibility() == View.INVISIBLE) {
            firstValue = Float.valueOf(strNum);
        } else {
            secondValue = Float.valueOf(strNum);
        }
    }

    public void plusMinus() {
        String strNum = (String) bottomNum.getText();
        if (!strNum.equals("0") && !strNum.equals("")) {
            if (strNum.charAt(0) != '-')
                strNum = '-' + strNum;
            else
                strNum = strNum.replace("-", "");
            bottomNum.setText(strNum);
            if (operator.getVisibility() == View.INVISIBLE) {
                firstValue = Float.valueOf(strNum);
            } else {
                secondValue = Float.valueOf(strNum);
            }
        }
    }

    public void numericButton(int num) {
        String strNum;
        if (bottomNum.getText().equals("0")) {
            strNum = String.valueOf(num);
        } else {
            strNum = bottomNum.getText() + String.valueOf(num);
        }
        bottomNum.setText(strNum);
        if (operator.getVisibility() == View.INVISIBLE) {
            firstValue = Float.valueOf(strNum);
        } else {
            secondValue = Float.valueOf(strNum);
        }
    }

    public void clearAll() {
        firstValue = 0;
        secondValue = 0;
        topNum.setVisibility(View.INVISIBLE);
        operator.setVisibility(View.INVISIBLE);
        bottomNum.setText("0");
    }

    public void clear() {
        String strNum = (String) bottomNum.getText();
        if (!topNum.getText().equals("")) {
            if (!strNum.equals("")) {
                strNum = strNum.substring(0, strNum.length() - 1);
            } else {
                strNum = (String) topNum.getText();
                topNum.setText("");
                topNum.setVisibility(View.INVISIBLE);
                operator.setText("");
                operator.setVisibility(View.INVISIBLE);
            }
        } else {
            if (!strNum.equals("0")) {
                if (strNum.length() == 1) {
                    strNum = "0";
                } else {
                    strNum = strNum.substring(0, strNum.length() - 1);
                }
            }
        }
        bottomNum.setText(strNum);
    }

    public void functionalButton(String symbol) {
        if (!bottomNum.getText().equals("") && !topNum.getText().equals(""))
            equal();
        operator.setVisibility(View.VISIBLE);
        topNum.setVisibility(View.VISIBLE);
        topNum.setText(formatToDisplay(firstValue));
        bottomNum.setText("");
        switch (symbol) {
            case "%":
                operatorValue = "%";
                operator.setText("%");
                break;
            case "+":
                operatorValue = "+";
                operator.setText("+");
                break;
            case "–":
                operatorValue = "–";
                operator.setText("–");
                break;
            case "/":
                operatorValue = "/";
                operator.setText("/");
                break;
            case "*":
                operatorValue = "*";
                operator.setText("*");
                break;
        }
    }

    public void equal() {
        topNum.setVisibility(View.INVISIBLE);
        operator.setVisibility(View.INVISIBLE);
        float answer = 0;
        switch (operatorValue) {
            case "%":
                answer = Float.parseFloat(String.valueOf((int)(firstValue / secondValue)));
                break;
            case "+":
                answer = firstValue + secondValue;
                break;
            case "–":
                answer = firstValue - secondValue;
                break;
            case "/":
                if (secondValue != 0) {
                    answer = firstValue / secondValue;
                } else {
                    answer = firstValue;
                    Toast.makeText(this, "You cannot divide by zero", Toast.LENGTH_SHORT).show();
                }
                break;
            case "*":
                answer = firstValue * secondValue;
                break;
        }
        topNum.setText("");
        operator.setText("");
        bottomNum.setText(formatToDisplay(answer));
        firstValue = answer;
        secondValue = 0;
        operatorValue = "";
    }

    public String formatToDisplay(float number) {
       String formattedDisplayNum = String.valueOf(number);
       if (formattedDisplayNum.endsWith(".0"))
           formattedDisplayNum = formattedDisplayNum.replace(".0", "");
       return formattedDisplayNum;
    }

}
