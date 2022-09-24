//
// Created by 罗斯 on 24/9/2022.
//
#include <queue>
#include <unordered_set>
#include <string>

using namespace std;

class Solution {
public:
    int racecar(int target) {
        queue<pair<int, int>> q;
        q.push({0,1});
        unordered_set<string> v;
        v.insert("0_1");
        v.insert("0_-1");
        int steps = 0;
        while (!q.empty()){
            int size = q.size();
            while (size--){
                auto p = q.front();
                q.pop();
                int pos = p.first;
                int speed = p.second;
                {
                    int pos1 = pos + speed;
                    int speed1 = speed * 2;
                    pair<int, int> p1{pos1, speed1};
                    if (pos1 == target) return steps + 1;
                    if (p1.first > 0 && p1.first < 2 * target)
                        q.push(p1);
                }
                {
                    int speed2 = speed > 0 ? -1 : 1;
                    pair<int, int> p2{pos, speed2};
                    string key2 = to_string(pos) + "_" + to_string(speed2);
                    if (!v.count(key2)) {
                        q.push(p2);
                        v.insert(key2);
                    }
                }
            }
            ++steps;
        }
        return -1;


//        m_ = vector<int>(target + 1, 0);
//        return dp(target);
    }
private:
    vector<int> m_;
    int dp(int t) {
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