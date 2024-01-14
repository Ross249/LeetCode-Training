class Solution(object):
    def maxProfit(self, prices):
        buy, sell = 0, 1
        profit = 0
        while sell < len(prices):
            cur_profit = prices[sell] - prices[buy]
            if prices[buy] < prices[sell]:
                profit = max(cur_profit, profit)
            else:
                buy = sell
            sell += 1
        return profit