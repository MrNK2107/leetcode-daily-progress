import java.util.Arrays;
import java.util.Comparator;

class Solution {
    /**
     * Finds the maximum total quantity of items that can be bought given a budget.
     * This is a greedy problem: prioritize buying items with the lowest price.
     * 
     * @param items 2D array where items[i] = [price, quantity]
     * @param budget The total budget available.
     * @return The maximum total quantity of items purchased.
     */
    public int maximumSaleItems(int[][] items, int budget) {
        if (items == null || items.length == 0 || budget <= 0) {
            return 0;
        }

        // 1. Sort items based on price (items[i][0]) in ascending order.
        // We use Comparator.comparingInt for clean sorting based on the price element.
        Arrays.sort(items, Comparator.comparingInt(a -> a[0]));

        int totalQuantity = 0;
        int remainingBudget = budget;

        // 2. Greedy selection: Iterate through items from cheapest to most expensive.
        for (int[] item : items) {
            int price = item[0];
            int quantity = item[1];

            if (remainingBudget <= 0) {
                break;
            }

            if (price == 0) {
                // If price is 0, we buy all of it as it costs nothing.
                totalQuantity += quantity;
                continue;
            }

            // Calculate how many units we can afford with the remaining budget
            int affordable = remainingBudget / price;

            // Determine the actual quantity to buy: limited by stock or affordability
            int buyQuantity = Math.min(quantity, affordable);

            // Update totals
            totalQuantity += buyQuantity;
            remainingBudget -= buyQuantity * price;
        }

        return totalQuantity;
    }
}