//
// Created by 罗斯 on 20/11/2022.
//
class Solution {
public:
    int climbStairs(int n) {
        int one = 1;
        int two = 1;
        int cur = 1;
        for (int i = 2; i <= n; ++i) {
            cur = one + two;
            two = one;
            one = cur;
        }
        return cur;
    }
};