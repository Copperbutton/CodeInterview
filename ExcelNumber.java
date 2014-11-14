package interviewquestions.facebook;

/**
 * Microsoft Excel numbers cells as 1...26 and after that AA, AB.... AAA,
 * AAB...ZZZ and so on. Given a number, convert it to that format and vice
 * versa.
 */
public class ExcelNumber {
    public int convertToInt(String excelNumber) {
        if (excelNumber == null)
            return 0;

        int num = 0;
        for (int i = 0; i < excelNumber.length(); i++) {
            num = num * 26 + (excelNumber.charAt(i) - 'A' + 1);
        }
        return num;
    }

    /**
     * This questions is tricky in that it transfered between different base: 1
     * base and 0 base. (N - 1) convert number to 0 base, then + 1 convert back
     * to normal 1 base.
     */
    public String convertToExcelNumber(int N) {
        StringBuilder builder = new StringBuilder();
        while (N > 0) {
            int d = (N - 1) % 26 + 1;
            builder.append((char) ('A' + d - 1));
            N = (N - d) / 26;
        }
        return builder.reverse().toString();
    }
}
