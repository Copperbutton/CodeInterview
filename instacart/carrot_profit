import java.util.*;

public class Solution {
    // case 1: one transaction 
    public int maxProfit(int[] prices) {
        if (prices.length <= 1)
            return 0;
        
        // This is to track the min price before the current price
        int minPrice = prices[0];
        
        // This is to track the historical max profit
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            // calculate the current profit: current price - min price
            // if current profit > historical max profit
            maxProfit = Math.max(prices[i] - minPrice, maxProfit);
            // update the min price if we see one
            minPrice = Math.min(minPrice, prices[i]);
        }
        return maxProfit;
    }
    
    // Part II:  allows multiple transactions to happen throughout the sequence.
    public int maxProfitMultiTranscation(int[] prices) {
        if (prices.length <= 1)
            return 0;

        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            int currProfit = prices[i] - prices[i - 1];
            // every time there is a positive profile we harvest it
            if (currProfit > 0)
                maxProfit += currProfit;
        }
        return maxProfit;
    }
    
    // Part III: Multiple products and an overall limit
    public int topProfits(int[][] prices) {
        // Use a priority queue, by default a min heap, to find the top profits
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>();
        
        for (int[] price: prices) {
            int currentMaxProfile = maxProfitMultiTranscation(price);
            priorityQueue.add(currentMaxProfile);
            // if more than 3 + 1 elements, poll the lastest one
            if (priorityQueue.size() == 4) {
                priorityQueue.poll();
            }
        }
        
        int maxTopProfits = 0;
        while (!priorityQueue.isEmpty()) {
            int val = priorityQueue.poll();
            System.out.println(val);
            maxTopProfits += val;
        }
        return maxTopProfits;
    }
    
        
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Part I        
        int[] prices1 = new int[] {
            2, 1, 4, 3, 8, 5
        };
        
        System.out.println(solution.maxProfit(prices1));
        
        
        // Part II
        int[] prices2 = new int[] {
            2, 1, 4, 3, 8, 5
        };
        
        System.out.println(solution.maxProfitMultiTranscation(prices2));
        
        
        // Part III
        int[][] prices3 = new int[][] {
            new int[] {2, 1, 4, 3, 8, 5},
            new int[] {2, 1, 4, 3, 8, 9},
            new int[] {2, 1, 4, 3, 8, 10},
            new int[] {2, 1, 4, 3, 1, 5}
        };
        System.out.println(solution.topProfits(prices3));
    }
}
