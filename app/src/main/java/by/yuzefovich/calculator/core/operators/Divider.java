package by.yuzefovich.calculator.core.operators;

public class Divider implements BinaryOperator {

    private final String OPERATOR = "/";

    @Override
    public float calculate(float firstValue, float secondValue) {
        return firstValue / secondValue;
    }

    @Override
    public String getSymbol() {
        return OPERATOR;
    }


}
