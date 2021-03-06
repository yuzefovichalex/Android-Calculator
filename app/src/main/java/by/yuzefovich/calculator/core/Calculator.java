package by.yuzefovich.calculator.core;

import android.view.View;
import android.widget.TextView;

import by.yuzefovich.calculator.core.operators.BinaryOperator;

public class Calculator {

    private TextView displayValueTop;
    private TextView displayValueBottom;
    private TextView displayOperator;

    private float firstValue = 0;
    private float secondValue = 0;
    private BinaryOperator operator = null;

    public Calculator(TextView displayValueTop, TextView displayValueBottom, TextView displayOperator) {
        this.displayValueTop = displayValueTop;
        this.displayValueBottom = displayValueBottom;
        this.displayOperator = displayOperator;
        initialState(0);
    }



    public void numericButton(String value) {
        String editableNumber = (String)displayValueBottom.getText();
        if (editableNumber.equals("Infinity")
                || editableNumber.equals("-Infinity")
                || editableNumber.equals("NaN")) {
            editableNumber = "0";
        }
        switch (value) {
            case "+/-": {
                if (!editableNumber.equals("0") && !editableNumber.equals("")) {
                    if (editableNumber.contains("-")) {
                        editableNumber = editableNumber.replace("-", "");
                    } else {
                        editableNumber = "-" + editableNumber;
                    }
                } else {
                    return;
                }
            }
                break;
            case ".": {
                if (!editableNumber.equals("")) {
                    if (!editableNumber.contains(".")) {
                        editableNumber += ".";
                    }
                } else {
                    editableNumber += "0.";
                }

            }
                break;
            default: {
                if (editableNumber.equals("0")) {
                    editableNumber = value;
                } else {
                    editableNumber += value;
                }
            }
        }
        displayValueBottom.setText(editableNumber);
        updateValues(editableNumber);
    }

    public void operatorButton(BinaryOperator operator) {
        if (this.operator == null) {
            displayValueTop.setVisibility(View.VISIBLE);
            displayValueTop.setText(formatForDisplay(firstValue));
            displayOperator.setVisibility(View.VISIBLE);
        } else {
            if (!displayValueBottom.getText().equals("")) {
                float result = this.operator.calculate(firstValue, secondValue);
                displayValueTop.setText(formatForDisplay(result));
                firstValue = result;
                secondValue = 0;
            }
        }
        displayValueBottom.setText("");
        this.operator = operator;
        displayOperator.setText(operator.getSymbol());
    }

    public void clear() {
        if (!displayValueBottom.getText().equals("0")) {
            String editableNumber = (String)displayValueBottom.getText();
            if (editableNumber.length() != 0) {
                if (!editableNumber.equals("Infinity")
                        && !editableNumber.equals("-Infinity")
                        && !editableNumber.equals("NaN")
                        && !(editableNumber.startsWith("-") && editableNumber.length() == 2)) {
                    editableNumber = editableNumber.substring(0, editableNumber.length() - 1);
                } else {
                    editableNumber = "";
                }
                displayValueBottom.setText(editableNumber);
                if (editableNumber.length() != 0) {
                    updateValues(editableNumber);
                } else {
                    secondValue = 0;
                    if (displayValueTop.getText().equals("")) {
                        initialState(0);
                    }
                }
            } else {
                initialState(firstValue);
            }
        }
    }

    public void clearAll() {
        initialState(0);
    }

    public void equal() {
        if (operator != null && !displayValueBottom.getText().equals("")) {
            float result = operator.calculate(firstValue, secondValue);
            initialState(result);
        } else {
            initialState(firstValue);
        }
    }



    private void initialState(float displayValue) {
        displayValueTop.setText("");
        displayValueTop.setVisibility(View.INVISIBLE);
        displayValueBottom.setText(formatForDisplay(displayValue));
        displayOperator.setText("");
        displayOperator.setVisibility(View.INVISIBLE);

        firstValue = displayValue;
        secondValue = 0;
        operator = null;
    }

    private void updateValues(String value) {
        if (operator == null) {
            firstValue = Float.parseFloat(value);
        } else {
            secondValue = Float.parseFloat(value);
        }
    }

    private String formatForDisplay(float value) {
        String formattedValue = String.valueOf(value);
        if (formattedValue.endsWith(".0"))
            formattedValue = formattedValue.replace(".0", "");
        return formattedValue;
    }

}
