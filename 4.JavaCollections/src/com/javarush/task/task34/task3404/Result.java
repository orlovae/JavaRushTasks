package com.javarush.task.task34.task3404;

public class Result {
    private String expression;
    private String parenthesis;
    private final int countOperation;
    private Double result;

    public Result(String expression, int countOperation) {
        this.expression = expression;
        this.countOperation = countOperation;
    }

    public String getExpression() {
        return expression;
    }

    public String getParenthesis() {
        return parenthesis;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Double getResult() {
        return result;
    }

    public void setParenthesis(String parenthesis) {
        this.parenthesis = parenthesis;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public String getReplacementInParenthesis() {
        expression = expression.replace(parenthesis, result.toString());
        return expression;
    }
}
