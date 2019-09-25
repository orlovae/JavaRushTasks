package com.javarush.task.task34.task3404;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Рекурсия для мат. выражения
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recurse("sin(2*(-5+1.5*4)+28)", 0); //expected output 0.5 6
        String s;
//        s = "sin(2*(-5+1.5*4)+28)";
//        System.out.print(s + " expected output 0.5 6 actually ");
//        solution.recurse(s, 0);
//        s = "tan(45)";
//        System.out.print(s + " expected output 1 1 actually ");
//        solution.recurse(s, 0);
//        s = "tan(-45)";
//        System.out.print(s + " expected output -1 2 actually ");
//        solution.recurse(s, 0);
//        s = "0.305";
//        System.out.print(s + " expected output 0.3 0 actually ");
//        solution.recurse(s, 0);
//        s = "0.3051";
//        System.out.print(s + " expected output 0.31 0 actually ");
//        solution.recurse(s, 0);
//        s = "(0.3051)";
//        System.out.print(s + " expected output 0.31 0 actually ");
//        solution.recurse(s, 0);
//        s = "1+(1+(1+1)*(1+1))*(1+1)+1";
//        System.out.print(s + " expected output 12 8 actually ");
//        solution.recurse(s, 0);
//        s = "tan(44+sin(89-cos(180)^2))";
//        System.out.print(s + " expected output 1 6 actually ");
//        solution.recurse(s, 0);
//        s = "-2+(-2+(-2)-2*(2+2))";
//        System.out.print(s + " expected output -14 8 actually ");
//        solution.recurse(s, 0);
//        s = "sin(80+(2+(1+1))*(1+1)+2)";
//        System.out.print(s + " expected output 1 7 actually ");
//        solution.recurse(s, 0);
//        s = "1+4/2/2+2^2+2*2-2^(2-1+1)";
//        System.out.print(s + " expected output 6 11 actually ");
//        solution.recurse(s, 0);
//        s = "10-2^(2-1+1)";
//        System.out.print(s + " expected output 6 4 actually ");
//        solution.recurse(s, 0);
//        s = "2^10+2^(5+5)";
//        System.out.print(s + " expected output 2048 4 actually ");
//        solution.recurse(s, 0);
//        s = "1.01+(2.02-1+1/0.5*1.02)/0.1+0.25+41.1";
//        System.out.print(s + " expected output 72.96 8 actually ");
//        solution.recurse(s, 0);
//        s = "0.000025+0.000012";
//        System.out.print(s + " expected output 0 1 actually ");
//        solution.recurse(s, 0);
//        s = "-2-(-2-1-(-2)-(-2)-(-2-2-(-2)-2)-2-2)";
//        System.out.print(s + " expected output -3 16 actually ");
//        solution.recurse(s, 0);
//        s = "cos(3 + 19*3)";
//        System.out.print(s + " expected output 0.5 3 actually ");
//        solution.recurse(s, 0);
//        s = "2*(589+((2454*0.1548/0.01*(-2+9^2))+((25*123.12+45877*25)+25))-547)";
//        System.out.print(s + " expected output 8302231.36 14 actually ");
//        solution.recurse(s, 0);
//        s = "(-1 + (-2))";
//        System.out.print(s + " expected output -3 3 actually ");
//        solution.recurse(s, 0);
//        s = "-sin(2*(-5+1.5*4)+28)";
//        System.out.print(s + " expected output -0.5 7 actually ");
//        solution.recurse(s, 0);
//        s = "sin(100)-sin(100)";
//        System.out.print(s + " expected output 0 3 actually ");
//        solution.recurse(s, 0);
//        s = "-(-22+22*2)";
//        System.out.print(s + " expected output -22 4 actually ");
//        solution.recurse(s, 0);
//        s = "-2^(-2)";
//        System.out.print(s + " expected output -0.25 3 actually ");
//        solution.recurse(s, 0);
//        s = "-(-2^(-2))+2+(-(-2^(-2)))";
//        System.out.print(s + " expected output 2.5 10 actually ");
//        solution.recurse(s, 0);
//        s = "(-2)*(-2)";
//        System.out.print(s + " expected output 4 3 actually ");
//        solution.recurse(s, 0);
//        s = "(-2)/(-2)";
//        System.out.print(s + " expected output 1 3 actually ");
//        solution.recurse(s, 0);
//        s = "sin(-30)";
//        System.out.print(s + " expected output -0.5 2 actually ");
//        solution.recurse(s, 0);
//        s = "cos(-30)";
//        System.out.print(s + " expected output 0.87 2 actually ");
//        solution.recurse(s, 0);
//        s = "tan(-30)";
//        System.out.print(s + " expected output -0.58 2 actually ");
//        solution.recurse(s, 0);
//        s = "2+8*(9/4-1.5)^(1+1)";
//        System.out.print(s + " expected output 6.5 6 actually ");
//        solution.recurse(s, 0);
//        s = "0.005 ";
//        System.out.print(s + " expected output 0.01 0 actually ");
//        solution.recurse(s, 0);
//        s = "0.0049 ";
//        System.out.print(s + " expected output 0 0 actually ");
//        solution.recurse(s, 0);
//        s = "0+0.304";
//        System.out.print(s + " expected output 0.3 1 actually ");
//        solution.recurse(s, 0);
    }

    public void recurse(final String expression, int countOperation) {
        String expressionNoWhiteSpace = getExpressoinNoWhiteSpace(expression);
        countOperation = getCountOperation(countOperation, expressionNoWhiteSpace);
        Result result = new Result(expressionNoWhiteSpace, countOperation);

        if (isNumber(expressionNoWhiteSpace)) {
            DecimalFormat myFormat = new DecimalFormat("#.##");
            DecimalFormatSymbols dfs = new DecimalFormatSymbols();
            dfs.setDecimalSeparator('.');
            myFormat.setDecimalFormatSymbols(dfs);
            myFormat.setDecimalSeparatorAlwaysShown(false);
            System.out.println(String.format("%s %d", myFormat.format(Double.parseDouble(expressionNoWhiteSpace)),countOperation));
            return;
        } else {
            String lastParenthesis = getLastParenthesis(expressionNoWhiteSpace);
            if (lastParenthesis.equals("")) {
                lastParenthesis = expressionNoWhiteSpace;
            }

            result.setParenthesis(lastParenthesis);

            if (!isNumber(lastParenthesis)) {
                result = getMathOperation(lastParenthesis, result);
                result.getReplacementInParenthesis();
            } else {
                if (isTrigFunction(result)){
                    result = getTrigFunction(result);
                    result.getReplacementInParenthesis();
                } else {
                    String deleteParenthesis = getDeleteParenthesis(result.getExpression());
                    result.setExpression(deleteParenthesis);
                }
            }

            String newExpression = transformationsExpression(result).getExpression();
            recurse(newExpression, countOperation);
        }
    }

    private String getExpressoinNoWhiteSpace(String expression) {
        Pattern pattern = Pattern.compile(" ");
        Matcher matcher = pattern.matcher(expression);
        while (matcher.find()) {
            expression = matcher.replaceAll("");
        }
        return expression;
    }

    private boolean isTrigFunction(Result result){
        boolean isTrigFunction = false;
        String strTrigFunction = null;
        String expression = result.getExpression();
        String parenthesis = result.getParenthesis();

        if (!isNumber(parenthesis)) {
            return false;
        }

        Pattern patExpression = Pattern.compile(parenthesis);
        Matcher matExpression = patExpression.matcher(expression);

        try {
            if (matExpression.find()) {
                int start = matExpression.start();
                int end = matExpression.end();
                strTrigFunction = expression.substring(start - 4, end + 1);
            }

            Pattern patTrigFunction = Pattern.compile("(sin|cos|tan)");
            Matcher matTrigFunction = patTrigFunction.matcher(strTrigFunction);

            if (matTrigFunction.find()) {
                isTrigFunction = true;
                replaceTrigFunctionInExpression(strTrigFunction, result);
            }
        } catch (StringIndexOutOfBoundsException e) {
            isTrigFunction = false;
        }
        return isTrigFunction;
    }

    private void replaceTrigFunctionInExpression(String trigFunction, Result result) {
        result.setParenthesis(trigFunction);
    }

    private Result transformationsExpression(Result result) {
        String newExpression = result.getExpression();

        String replacementDoubleSing = getReplacementDoubleSing(newExpression);

        String deleteDoubleSing = getDeleteDoubleSing(replacementDoubleSing);

        result.setExpression(deleteDoubleSing);

        return result;
    }

    private int getCountOperation(int countOperation, String expression) {
        if (countOperation == 0) {
            Pattern pattern = Pattern.compile("(sin|cos|tan|\\^|\\/|\\*|\\-|\\+)");
            Matcher matcher = pattern.matcher(expression);
            while (matcher.find()) {
                countOperation++;
            }
        }
        return countOperation;
    }

    private String getDeleteDoubleSing(String expression) {
        Pattern pattern = Pattern.compile( "\\+-|--|-\\+");
        Matcher matcher = pattern.matcher(expression);

        if (matcher.find()) {
            String subLastParenthesis = matcher.group();

            switch (subLastParenthesis) {
                case "+-":
                    expression = matcher.replaceAll("-");
                    break;
                case "--":
                    expression = matcher.replaceAll("+");
                    break;
                case "-+":
                    expression = matcher.replaceAll("-");
                    break;
            }
        }
        return expression;
    }

    private String getDeleteParenthesis(String expression) {
        Pattern pattern = Pattern.compile( "(?<!\\d)\\(([-+]?\\d+\\.?\\d*\\))");
        Matcher matcher = pattern.matcher(expression);

        if (matcher.find()) {
            String subLastParenthesis = matcher.group();

            String inParenthesis = subLastParenthesis.substring(1, subLastParenthesis.length() - 1);

            expression = matcher.replaceFirst(inParenthesis);
        }
        return expression;
    }

    private String getReplacementDoubleSing(String expression) {
        Pattern pattern = Pattern.compile( "([-+])\\(((\\1)\\d+\\.?\\d*\\))");
        Matcher matcher = pattern.matcher(expression);

        if (matcher.find()) {
            String subLastParenthesis = matcher.group();

            String inParenthesis = "+" + subLastParenthesis.substring(3, subLastParenthesis.length() - 1);

            expression = matcher.replaceFirst(inParenthesis);
        }
        return expression;
    }

    private Result getTrigFunction (Result result) {
        String parenthesis = result.getParenthesis();
        Double resParenthesis = 0.0;

        String trigFunction = parenthesis.substring(0, 3);
        double inParenthesis = Double.parseDouble(parenthesis.substring(4,
                parenthesis.length() - 1));

        switch (trigFunction) {
            case "sin":
                resParenthesis = Math.sin(Math.toRadians(inParenthesis));
                break;
            case "cos":
                resParenthesis = Math.cos(Math.toRadians(inParenthesis));
                break;
            case "tan":
                resParenthesis = Math.tan(Math.toRadians(inParenthesis));
                break;
        }
        result.setResult(
                new BigDecimal(resParenthesis).setScale(4, RoundingMode.HALF_UP).doubleValue()
        );
        return result;
    }

    private Result getMathOperation(String parenthesis, Result result) {
        Double res = result.getResult();

        char[] signs = {'^', '/', '*', '-', '+'};
        for (char c : signs) {
            String stringPattern = String.format("-?\\d+\\.?\\d*\\%s-?\\d+\\.?\\d*", c);

            Pattern pattern = Pattern.compile(stringPattern);
            Matcher matcher = pattern.matcher(parenthesis);

            while (matcher.find()) {
                String subLastParenthesis = matcher.group();
                double a = Double.parseDouble(subLastParenthesis.substring(
                        0, subLastParenthesis.lastIndexOf(c)));
                double b = Double.parseDouble(subLastParenthesis.substring(
                        subLastParenthesis.lastIndexOf(c) + 1,
                        subLastParenthesis.length()));

                switch (c) {
                    case '^':
                        res = new BigDecimal(Math.pow(a, b)).setScale(4, RoundingMode.HALF_UP).doubleValue();
                        break;
                    case '/':
                        res = new BigDecimal(a / b).setScale(4, RoundingMode.HALF_UP).doubleValue();
                        break;
                    case '*':
                        res = new BigDecimal(a * b).setScale(4, RoundingMode.HALF_UP).doubleValue();
                        break;
                    case '-':
                        res = new BigDecimal(a - b).setScale(4, RoundingMode.HALF_UP).doubleValue();
                        break;
                    case '+':
                        res = new BigDecimal(a + b).setScale(4, RoundingMode.HALF_UP).doubleValue();
                        break;
                }
                parenthesis = matcher.replaceFirst(res + "");
                matcher = pattern.matcher(parenthesis);
                result.setResult(res);
            }
        }
        if (isNumber(parenthesis)) {
            return result;
        } else {
            return getMathOperation(parenthesis, result);
        }
    }

    private boolean isNumber (String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String getLastParenthesis (String expression) {
        int startParenthesis = 0;
        int endParenthesis = 0;

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '(') {
                startParenthesis = i + 1;
            }
            if (c == ')') {
                endParenthesis = i;
                break;
            }
        }
        return expression.substring(startParenthesis, endParenthesis);
    }

    public Solution() {
        //don't delete
    }
}
