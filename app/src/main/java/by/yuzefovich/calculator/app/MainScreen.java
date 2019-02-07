package by.yuzefovich.calculator.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import by.yuzefovich.calculator.R;
import by.yuzefovich.calculator.core.Calculator;
import by.yuzefovich.calculator.core.operators.Divider;
import by.yuzefovich.calculator.core.operators.Minus;
import by.yuzefovich.calculator.core.operators.Multiplier;
import by.yuzefovich.calculator.core.operators.Percentage;
import by.yuzefovich.calculator.core.operators.Plus;

public class MainScreen extends AppCompatActivity {

    private Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        TextView displayValueTop = findViewById(R.id.topNum);
        TextView displayValueBottom = findViewById(R.id.bottomNum);
        TextView displayOperator = findViewById(R.id.operator);

        calculator = new Calculator(displayValueTop, displayValueBottom, displayOperator);
    }

    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.one:
                calculator.numericButton("1");
                break;
            case R.id.two:
                calculator.numericButton("2");
                break;
            case R.id.three:
                calculator.numericButton("3");
                break;
            case R.id.four:
                calculator.numericButton("4");
                break;
            case R.id.five:
                calculator.numericButton("5");
                break;
            case R.id.six:
                calculator.numericButton("6");
                break;
            case R.id.seven:
                calculator.numericButton("7");
                break;
            case R.id.eight:
                calculator.numericButton("8");
                break;
            case R.id.nine:
                calculator.numericButton("9");
                break;
            case R.id.zero:
                calculator.numericButton("0");
                break;
            case R.id.plus_minus:
                calculator.numericButton("+/-");
                break;
            case R.id.dot:
                calculator.numericButton(".");
                break;


            case R.id.percent:
                calculator.operatorButton(new Percentage());
                break;
            case R.id.divider:
                calculator.operatorButton(new Divider());
                break;
            case R.id.multiplier:
                calculator.operatorButton(new Multiplier());
                break;
            case R.id.minus:
                calculator.operatorButton(new Minus());
                break;
            case R.id.plus:
                calculator.operatorButton(new Plus());
                break;


            case R.id.AC:
                calculator.clearAll();
                break;
            case R.id.CE:
                calculator.clear();
                break;
            case R.id.equal:
                calculator.equal();
                break;
        }
    }

}
