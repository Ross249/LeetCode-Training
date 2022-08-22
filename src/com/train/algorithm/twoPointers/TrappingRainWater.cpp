//
// Created by 罗斯 on 22/8/2022.
//

#include <vector>

using namespace std;

class Solution {
public:
    int trap(vector<int>& height) {
//        const int n = height.size();
//        vector<int> l(n);
//        vector<int> r(n);
//        int ans = 0;
//        for (int i = 0; i < n; ++i)
//            l[i] = i == 0 ? height[i] : max(l[i - 1], height[i]);
//        for (int i = n - 1; i >= 0; --i)
//            r[i] = i == n - 1 ? height[i] : max(r[i + 1], height[i]);
//        for (int i = 0; i < n; ++i)
//            ans += min(l[i], r[i]) - height[i];
//        return ans;

//        const int n = height.size();
//        int ans = 0;
//        auto sit = cbegin(height);
//        auto eit = cend(height);
//        for (int i = 0; i < n; ++i) {
//            int l = *max_element(sit, sit + i + 1);
//            int r = *max_element(sit + i, eit);
//            ans += min(l, r) - height[i];
//        }
//        return ans;

        const int n = height.size();
        if(n == 0) return 0;
        int l = 0;
        int r = n - 1;
        int max_left = height[l];
        int max_right = height[r];
        int res = 0;
        while(l < r){
            if(max_left < max_right){
                res += max_left - height[l];
                max_left = max(max_left,height[++l]);
            }else{
                res += max_right - height[r];
                max_right = max(max_right,height[--r]);
            }
        }
        return res;
    }
};