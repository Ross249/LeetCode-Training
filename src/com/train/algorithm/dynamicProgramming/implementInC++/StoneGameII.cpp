//
// Created by 罗斯 on 24/1/2023.
//

#include <vector>
#include <algorithm>
#include <unordered_map>
#include <numeric>

using namespace std;

class Solution {
public:
    int stoneGameII(vector<int>& piles) {
        const int n = piles.size();
        unordered_map<int, int> cache;
        function<int(int, int)> solve = [&](int s, int M){
            if (s >= n) return 0;
            const int key = (s << 8) | M;
            if (cache.count(key)) return cache[key];
            int best = INT_MIN;
            int cur = 0;
            for (int x = 1; x <= 2 * M ; ++x) {
                if (s + x > n) break;
                cur += piles[s + x - 1];
                best = max(best, cur - solve(s + x, max(x, M)));
            }
            return cache[key] = best;
        };

        int total = accumulate(begin(piles), end(piles), 0);
        return (total + solve(0, 1)) / 2;
    }

};
