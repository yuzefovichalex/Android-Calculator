package by.yuzefovich.calculator.core.operators;

public interface BinaryOperator {

    float calculate(float firstValue, float secondValue);
    String getSymbol();
}
