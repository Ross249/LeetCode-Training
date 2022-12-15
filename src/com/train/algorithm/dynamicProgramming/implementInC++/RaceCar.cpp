//
// Created by 罗斯 on 16/12/2022.
//
#include <vector>
#include <math.h>
#include <algorithm>

using namespace std;

constexpr int kMaxT = 10000;
//          m[t][d] : min steps to reach t and facing d (0 = right, 1 = left)
int m[kMaxT + 1][2]={0};

class Solution {
public:
    int racecar(int target) {
//        m_ = vector<int>(target + 1, 0);
//        return dp(target);

        if (m[1][0] == 0) {
            for (int t = 1; t <= kMaxT; ++t) {
                const int n = ceil(log2(t + 1));
                if (1 << n == t + 1) {
                    m[t][0] = n;
                    m[t][1] = n + 1;
                    continue;
                }
                const int l = (1 << n) - 1 - t;
                m[t][0] = n + 1 + min(m[l][1], m[l][0] + 1);
                m[t][1] = n + 1 + min(m[l][0], m[l][1] + 1);
                for (int i = 1; i < t; ++i) {
                    const int mi = min(m[i][0] + 2, m[i][1] + 1);
                    m[t][0] = min(m[t][0], m[t - i][0] + mi);
                    m[t][1] = min(m[t][1], m[t - i][1] + mi);
                }
            }
        }
        return min(m[target][0], m[target][1]);
    }
private:
    vector<int> m_;
    int dp(int t){
        if (m_[t] > 0) return m_[t];
        int n = ceil(log2(t + 1));
        // AA...A (nA) best case
        if (1 << n == t + 1) return m_[t] = n;
        // AA...AR (nA + 1R) + dp(left)
        m_[t] = n + 1 + dp((1 << n) - 1 - t);
        for (int m = 0; m < n - 1; ++m) {
            int cur = (1 << (n - 1)) - (1 << m);
            //AA...ARA...AR (n-1A + 1R + mA + 1R) + dp(left)
            m_[t] = min(m_[t], n + m + 1 + dp(t - cur));
        }
        return m_[t];
    }
};