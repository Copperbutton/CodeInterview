package interviewquestions.other;

import java.util.*;

/**
 * Implement a two stack arithmetic calculator. This algorithm is weaker than
 * the reverse polish tree since it required the parenthese to indicate the
 * order ot computation.
 */
public class TwoStackArithmeticCaculation {
    public int calculateArithmetic(String expression) {
        Stack<String> values = new Stack<String>();
        Stack<Character> operators = new Stack<Character>();
        if (!isValidExpression(expression))
            throw new IllegalArgumentException("Error: expression invalid.");
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (ch == '(' || ch == ' ')
                continue;
            if (ch == ')') {
                String res = calculation(values.pop(), values.pop(),
                        operators.pop());
                values.push(res);
            } else if (isOperator(ch)) {
                operators.push(ch);
            } else {
                StringBuilder builder = new StringBuilder();
                while (i < expression.length()
                        && isNumber(ch = expression.charAt(i))) {
                    builder.append(ch);
                    i++;
                }
                values.push(builder.toString());
                i--;
            }
        }
        while (!operators.isEmpty()) {
            String res = calculation(values.pop(), values.pop(),
                    operators.pop());
            values.push(res);
        }

        return Integer.parseInt(values.pop());
    }

    private String calculation(String val1, String val2, char op) {
        int val1Int = Integer.parseInt(val1);
        int val2Int = Integer.parseInt(val2);
        int result = 0;
        switch (op) {
        case '+':
            result = val2Int + val1Int;
            break;
        case '-':
            result = val2Int - val1Int;
        case '*':
            result = val2Int * val1Int;
            break;
        case '/':
            if (val1Int == 0)
                throw new IllegalArgumentException(
                        "Error: cannot use 0 case divden");
            result = val2Int / val1Int;
            break;
        default:
            break;
        }
        return Integer.toString(result);
    }

    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    private boolean isNumber(char ch) {
        return ch >= '0' && ch <= '9';
    }

    public boolean isValidExpression(String expression) {
        int leftParen = 0;
        for (char ch : expression.toCharArray()) {
            if (ch == '(')
                leftParen++;
            else if (ch == ')') {
                if (leftParen > 0)
                    leftParen--;
                else
                    return false;
            }
        }
        return leftParen == 0;
    }

}
