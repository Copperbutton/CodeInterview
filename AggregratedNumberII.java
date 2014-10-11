package interviewquestions.google;

/**
 * we will name a number "aggregated number" if this number has the following
 * attribute: just like the Fibonacci numbers 1,1,2,3,5,8,13.....
 * 
 * the digits in the number can divided into several parts, and the later part
 * is the sum of the former parts.
 * 
 * like 112358, because 1+1=2, 1+2=3, 2+3=5, 3+5=8 122436, because 12+24=36
 * 1299111210, because 12+99=111, 99+111=210 112112224, because 112+112=224 so
 * can you provide a function to check whether this number is aggregated number
 * or not?
 */
public class AggregrateNumberII {
    public boolean isAggregrateNumber(String num) {
        for (int i = 1; i < num.length() - 1; i++) {
            for (int j = i + 1; j < num.length(); j++) {
                if (isMatch(num, i, j))
                    return true;
            }
        }
        return false;
    }

    public boolean isMatch(String num, int first, int second) {
        try {
            int num1 = Integer.parseInt(num.substring(0, first));
            int num2 = Integer.parseInt(num.substring(first, second));
            StringBuilder builder = new StringBuilder();
            builder.append(num1);
            builder.append(num2);
            while (builder.length() < num.length()) {
                int num3 = num1 + num2;
                builder.append(num3);
                num1 = num2;
                num2 = num3;
            }
            return num.equals(builder.toString());
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
