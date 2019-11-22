package test.twentyfour;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author created by zzz at 2019/11/15 17:13
 */

public class StringConvertor {

    private static final int PLUS = -1;

    private static final int MINUS = -2;

    private static final int MULTIPLE = -11;

    private static final int DIVIDE = -12;

    private static int arithmetic(int[] input) {
        Deque<Double> stack = new ArrayDeque<>();
        int i = 0;
        while (i < input.length) {
            int a = input[i];
            if (a >= 0) {
                stack.push((double) a);
            } else if (a < -10) {
                i++;
                if (a == MULTIPLE) {
                    stack.push(stack.pop() * input[i]);
                }
                if (a == DIVIDE) {
                    stack.push(stack.pop() / input[i]);
                }
            } else {
                stack.push((double) a);
            }
            i++;
        }
        while (!stack.isEmpty() && stack.size() > 2) {
            double second = stack.pop();
            int operation = stack.pop().intValue();
            double first = stack.pop();
            if (operation == PLUS) {
                stack.push(first + second);
            } else if (operation == MINUS) {
                stack.push(first - second);
            }
        }
        return stack.pop().intValue();
    }

    public int[] converter(String input) {
        Matcher numberMatcher = Pattern.compile("\\d+").matcher(input);
        Matcher operatorMatcher = Pattern.compile("[+\\-*/]").matcher(input);
        List<Integer> numbers = new LinkedList<>();
        List<Character> operators = new LinkedList<>();
        while (numberMatcher.find()) {
            String number = numberMatcher.group();
            numbers.add(Integer.parseInt(number));
            if (operatorMatcher.find()) {
                String operator = operatorMatcher.group();
                operators.add(operator.charAt(0));
            } else {
                break;
            }
        }
        int[] ret = new int[numbers.size() + operators.size()];
        int index = 0;
        for (int i = 0; i < numbers.size(); i++) {
            int number = numbers.get(i);
            ret[index] = number;
            if (i < operators.size()) {
                int operator = convertOperator(operators.get(i));
                index++;
                ret[index] = operator;
            }
            index++;
        }
        return ret;
    }

    private int convertOperator(char c) {
        if (c == '+') {
            return PLUS;
        } else if (c == '-') {
            return MINUS;
        } else if (c == '*') {
            return MULTIPLE;
        } else if (c == '/') {
            return DIVIDE;
        }
        return 0;
    }
}
