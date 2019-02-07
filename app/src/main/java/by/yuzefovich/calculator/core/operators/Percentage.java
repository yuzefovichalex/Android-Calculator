package by.yuzefovich.calculator.core.operators;

public class Percentage implements BinaryOperator {

    private final String OPERATOR = "%";

    @Override
    public float calculate(float firstValue, float secondValue) {
        return 0;
    }

    @Override
    public String getSymbol() {
        return OPERATOR;
    }
}
