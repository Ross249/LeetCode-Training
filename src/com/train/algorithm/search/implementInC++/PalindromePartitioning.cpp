//
// Created by 罗斯 on 5/10/2022.
//
#include <vector>
#include <string>

using namespace std;


class Solution {
public:
    vector<vector<string>> partition(string s) {
//        int n = s.length();
//        vector<vector<vector<string>>> dp(n + 1);
//        for (int len = 1; len <= n; ++len) {
//            for (int i = 0; i < len; ++i) {
//                string right = s.substr(i, len - i);
//                if (!isPalindrome(right)) continue;
//                if (i == 0) dp[len].push_back({right});
//                for (const auto& p : dp[i]) {
//                    dp[len].push_back(p);
//                    dp[len].back().push_back(right);
//                }
//            }
//        }
//        return dp[n];

        int n = s.length();
        vector<vector<string>> res;
        vector<string> cur;
        function<void(int)> dfs = [&](int start){
            if (start == n){
                res.push_back(cur);
                return;
            }

            for (int i = start; i < n; ++i) {
                if (!isPalindrome(s, start, i)) continue;
                cur.push_back(s.substr(start, i - start + 1));
                dfs(i + 1);
                cur.pop_back();
            }
        };
        dfs(0);
        return res;
    }

private:
//    bool isPalindrome(const string& s) {
//        const int n = s.length();
//        for (int i = 0; i < n / 2; ++i)
//            if (s[i] != s[n - 1 - i]) return false;
//        return true;
//    }
    bool isPalindrome(const string& s, int l, int r){
        while (l < r)
            if (s[l++] != s[r--]) return false;
        return true;
    }
};