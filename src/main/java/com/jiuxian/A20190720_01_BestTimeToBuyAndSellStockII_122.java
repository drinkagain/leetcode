package com.jiuxian;

import java.util.Stack;

/**
 * @author: liuzejun
 * *
 * @email: 857591294@qq.com
 * *
 * @date: 2019-07-20 21:11:07
 * *
 * @comment: 122. 买卖股票的最佳时机 II
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * <p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A20190720_01_BestTimeToBuyAndSellStockII_122 {

    static class Solution {
        int maxProfit(int[] prices) {
            int len = prices.length;
            int sum = 0;
            boolean isBuy = true;
            int buyPrice = 0;
            for (int i = 0; i < len; i++) {
                int next = i + 1;
                if (isBuy) {
                    if (next == len) break;
                    if (prices[i] < prices[next]) {
                        buyPrice = prices[i];
                        isBuy = false;
                        continue;
                    }
                }
                if (!isBuy && prices[i] > buyPrice) {
                    if (next != len && prices[next] > prices[i]) continue;
                    sum += prices[i] - buyPrice;
                    isBuy = true;
                }
            }
            return sum;
        }

        int maxProfit2(int[] prices) {
            Stack<Integer> stack = new Stack<>();
            int sum = 0;
            for (int price : prices) {
                if (stack.isEmpty()) {
                    stack.push(price);
                } else {
                    if (stack.peek() < price) {
                        sum += price - stack.pop();
                        stack.push(price);
                    } else {
                        stack.pop();
                        stack.push(price);
                    }
                }

            }
            return sum;
        }

        int maxProfit3(int[] prices) {
            int sum = 0;
            for (int i = 0; i < prices.length - 1; i++) {
                if (prices[i + 1] > prices[i]) {
                    sum += prices[i + 1] - prices[i];
                }
            }
            return sum;
        }
    }


    public static void main(String[] args) {
        int[] prices = { 7, 1, 5, 3, 6, 4 };
        int i = new Solution().maxProfit3(prices);
        System.out.println("i: " + i + "  " + (i == 7));

        int[] prices2 = { 1, 2, 3, 4, 5 };
        i = new Solution().maxProfit3(prices2);
        System.out.println("i: " + i + "  " + (i == 4));

        int[] prices3 = { 7, 6, 4, 3, 1 };
        i = new Solution().maxProfit3(prices3);
        System.out.println("i: " + i + "  " + (i == 0));
    }
}
