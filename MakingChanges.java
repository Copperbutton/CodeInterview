package interviewquestions.other;

/**
 * Calculate the minimal number of given changes of some amount of money. Pay
 * attention to potential problems, should use money + 1 number of array.
 */
public class MakingChanges {
    public int minChange(int[] changes, int money) {
        if (money == 1)
            return 1;
        int[] minChange = new int[money + 1];
        for (int i = 0; i <= money; i++)
            minChange[i] = i;

        for (int i = 1; i <= money; i++) {
            for (int change : changes) {
                if (change > i)
                    break;
                minChange[i] = Math.min(minChange[i - change] + 1, minChange[i]);
            }
        }

        return minChange[money];
    }
}
