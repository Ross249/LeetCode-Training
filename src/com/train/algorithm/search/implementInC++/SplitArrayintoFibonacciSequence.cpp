//
// Created by 罗斯 on 8/10/2022.
//
#include <vector>
#include <string>

using namespace std;

class Solution {
public:
    vector<int> splitIntoFibonacci(string s) {
        const int n = s.length();
        vector<int> nums;
        function<bool(int)> dfs = [&](int pos){
            if (pos == n)
                return nums.size() >= 3;
            int max_len = s[pos] == '0' ? 1 : 10;
            long num = 0;
            for (int i = pos; i < min(pos + max_len, n); ++i) {
                num = num * 10 + (s[i] - '0');
                if (num > INT_MAX)
                    break;
                if (nums.size() >= 2){
                    long sum = nums.rbegin()[0];
                    sum += nums.rbegin()[1];
                    if (num > sum)
                        break;
                    else if (num < sum)
                        continue;
                }
                nums.push_back(num);
                if (dfs(i + 1))
                    return true;
                nums.pop_back();
            }
            return false;
        };
        dfs(0);
        return nums;
    }
};